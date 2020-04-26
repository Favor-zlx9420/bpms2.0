package com.rong.admin.service.impl;

import com.rong.admin.consts.AdmConstValue;
import com.rong.admin.mapper.AdmColumnMapper;
import com.rong.admin.module.entity.TbAdmColumn;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.query.TsAdmColumn;
import com.rong.admin.module.request.TqAdmColumn;
import com.rong.admin.module.view.TvAdmColumn;
import com.rong.admin.service.AdmColumnService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.TreeNode;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RefreshScope
public class AdmColumnServiceImpl extends BasicServiceImpl<TbAdmColumn, TqAdmColumn, TvAdmColumn, AdmColumnMapper> implements AdmColumnService {
    private static List<TvAdmColumn> cacheColumns;
    private static Map<String, Set<Long>> authenticationPaths = new HashMap<>();
    @Value("${noAuthenticationRequiredPath:''}")
    Set<String> noAuthenticationRequiredPath;
    @Autowired
    public AdmColumnServiceImpl(AdmColumnMapper mapper){
        super.mapper = mapper;
        resetAuthInfo();
    }
    private void resetAuthInfo(){
        authenticationPaths = new HashMap<>();
        //填入鉴权的
        List<TvAdmColumn> cs=getCacheColumns();
        for(TvAdmColumn e:cs){
            if(StringUtil.isEmpty(e.getRightUrls())){
                continue;
            }
            String[] rightUrls = e.getRightUrls().split(",");
            for(String s:rightUrls){
                if(StringUtil.isNotEmpty(s)){
                    putAuth(s,e.getId());
                }
            }
        }
    }
    @Override
    public int updateSelectiveByPrimaryKey(TbAdmColumn entity){
        int effectRows = super.updateSelectiveByPrimaryKey(entity);
        if(effectRows == 1){
            cacheColumns = null;
            resetAuthInfo();
        }
        return effectRows;
    }
    @Override
    public int updateSelectItem(UpdateWrapper wrapper){
        int effectRows = super.updateSelectItem(wrapper);
        if(effectRows == 1){
            cacheColumns = null;
            resetAuthInfo();
        }
        return effectRows;
    }
    @Override
    public Long hashNonAuth(String actionPath){
        return noAuthenticationRequiredPath.contains(actionPath) ? 0L : null;
    }

    @Override
    public Set<Long> hashAuth(String actionPath){
        return authenticationPaths.get(actionPath);
    }
    @Override
    public Long hashAuthSingle(String actionPath){
        Set<Long> sets = authenticationPaths.get(actionPath);
        if(CommonUtil.isNull(sets) || sets.isEmpty()){
            return null;
        }
        return sets.iterator().next();
    }
    @Override
    public boolean hasColumnPermission(UserStorage user, Long columnId) {
        return CommonUtil.isNotNull(columnId) && (isRoot(user) || user.getPermissions().contains(columnId));
    }
    @Override
    public List<TvAdmColumn> listPermissionColumnsByPid(UserStorage user, List<TvAdmColumn> tv, long pid){
        List<TvAdmColumn> columns=new ArrayList<>();
        for(TvAdmColumn column:tv){
            if(CommonUtil.isEqual(pid, column.getParentId()) && hasColumnPermission(user,column.getId())){
                columns.add(new TvAdmColumn(column));
            }
        }
        return columns;
    }
    @Override
    public List<TvAdmColumn> listPermissionColumns(UserStorage user){
//        if(isRoot(user)){
//            return getCacheColumns();
//        }
        List<TvAdmColumn> columns=new ArrayList<>();//返回的列表请不要更改项
        for(TvAdmColumn column:getCacheColumns()){
            if(hasColumnPermission(user,column.getId())){
                columns.add(new TvAdmColumn(column));//
            }
        }
        return columns;
    }
    @Override
    public List<TreeNode> listPermissionRecursiveColumnsByPid(UserStorage user, List<TvAdmColumn> tvSearchColumns, String rightStrs, long pid){
        tvSearchColumns = pid < 1 ? getCacheColumns() : listPermissionTreeColumnsByPid(user, tvSearchColumns, pid);
        //当前 pid 下的直属子节点
        List<TvAdmColumn> tvColumns=listPermissionColumnsByPid(user,tvSearchColumns,pid);
        List<TreeNode> treeNodes=new ArrayList<>();
        for(TvAdmColumn column:tvColumns){
            //logger.info("tvs:"+tvSearchColumns.size()+";number:"+column.getNumber());
            TreeNode node = new TreeNode();
            node.setChecked(CommonUtil.inStr(rightStrs, String.valueOf(column.getId()),','));
            node.setEvt("javascript:void(0);");
            node.setNavigateUrl("");
            node.setShowCheckBox(true);
            node.setText(column.getName());
            node.setValue(String.valueOf(column.getId()));
            node.setToolTip(column.getName());
            //传的是tvSearchColumns指向地址
            List<TreeNode> sunTvAdmins=listPermissionRecursiveColumnsByPid(user,tvSearchColumns,rightStrs,column.getId());

            node.setExpand(CommonUtil.inStr(rightStrs, String.valueOf(column.getId()),','));
            node.setChildren(sunTvAdmins);
            treeNodes.add(node);
        }
        return treeNodes;
    }

    /********************************************************private********************************************************/

    private List<TvAdmColumn> getCacheColumns(){
        if(CommonUtil.isNull(cacheColumns) || cacheColumns.isEmpty() ){
            cacheColumns = mapper.selectViewList(
                    new MultiTableQueryWrapper()
                            .eq(CompareAlias.valueOf(TsAdmColumn.Fields.deltag), CommonEnumContainer.Deltag.NORMAL.getValue())
                            .orderBy(OrderBy.valueOf(Order.DESC, TsAdmColumn.Fields.sort)));
        }
        return cacheColumns;
    }
    private void putAuth(String actionPath,Long rightId){
        Set<Long> sets = authenticationPaths.get(actionPath);
        if(CommonUtil.isNull(sets)){
            sets = new HashSet<>();
            authenticationPaths.put(actionPath,sets);
        }
        sets.add(rightId);
    }
    private boolean isRoot(UserStorage user) {
        return CommonUtil.isEqual(user.getId(), AdmConstValue.SUP_ADMIN_ID);
    }
    private List<TvAdmColumn> listPermissionTreeColumnsByPid(UserStorage user, List<TvAdmColumn> tbColumns, long pid){
        List<TvAdmColumn> columns=new ArrayList<>();
        TvAdmColumn currentColumn=geTvColumnById(pid);
        if(CommonUtil.isNull(currentColumn)){
            return columns;
        }
        for(TvAdmColumn column:tbColumns){
            if(hasColumnPermission(user,column.getId()) && !CommonUtil.isEqual(column.getId(),currentColumn.getId())){
                columns.add(new TvAdmColumn(column));//
            }
        }
        return columns;
    }
    private TvAdmColumn geTvColumnById(long id){
        for(TvAdmColumn column:getCacheColumns()){
            if(CommonUtil.isEqual(id,column.getId())){
                return column;
            }
        }
        return null;
    }
}