package com.rong.admin.service;

import com.rong.admin.module.entity.TbAdmColumn;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.module.request.TqAdmColumn;
import com.rong.admin.module.view.TvAdmColumn;
import com.rong.common.module.TreeNode;
import com.rong.common.service.BasicService;

import java.util.List;
import java.util.Set;

public interface AdmColumnService extends BasicService<TbAdmColumn, TqAdmColumn, TvAdmColumn> {
    Long hashNonAuth(String actionPath);
    Set<Long> hashAuth(String actionPath);
    Long hashAuthSingle(String actionPath);

    /**
     * 某用户是否有栏目id 权限
     * @param user 1
     * @param columnId 2
     * @return 3
     */
    boolean hasColumnPermission(UserStorage user, Long columnId);
    /**
     * 获取某一树节点下的所有直属子节点：过滤权限
     * @param user 1
     * @param tbColumns 某一节点集合
     * @param pid 2
     * @return 3
     */
    List<TvAdmColumn> listPermissionColumnsByPid(UserStorage user, List<TvAdmColumn> tbColumns, long pid);
    /**
     * 获得当前所有权限列表
     * @param user 1
     * @return 2
     */
    List<TvAdmColumn> listPermissionColumns(UserStorage user);/**
     * 获取某一个节点下的所有子节点（递归）：过滤权限
     * @param user 1
     * @param tvSearchColumns 2
     * @param rightStrs 3
     * @param pid 4
     * @return 5
     */
    List<TreeNode> listPermissionRecursiveColumnsByPid(UserStorage user, List<TvAdmColumn> tvSearchColumns, String rightStrs, long pid);
}