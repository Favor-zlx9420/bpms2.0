package com.rong.assembly.api.wrapper;

import com.rong.common.util.WrapperFactory;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;

public abstract class UserCardWrapper {
    public static MultiTableQueryWrapper selectCardInfoList(){
        return WrapperFactory.multiQueryWrapper()
                .select("e.id,e.userId,e.firstName,e.lastName,e.fullName,e.headPortrait,e.company,e.position")
                .select0(
                        SelectAlias.valueOf("(select count(id) from tb_user_card_browse_history where card_info_id=e.id) browseUserCount",true)
                        ,
                        SelectAlias.valueOf("(select count(id) from tb_user_card_like where card_info_id=e.id and deltag = false) likeUserCount",true)
                )
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                ;
    }
    public static MultiTableQueryWrapper selectCardInfoIndex(){
        return WrapperFactory.multiQueryWrapper()
                .selectAllFiels(true)
                .select0(
                        SelectAlias.valueOf("(select count(id) from tb_user_card_browse_history where card_info_id=e.id) browseUserCount",true)
                        ,
                        SelectAlias.valueOf("(select count(id) from tb_user_card_like where card_info_id=e.id and deltag = false) likeUserCount",true)
                )
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                ;
    }
}
