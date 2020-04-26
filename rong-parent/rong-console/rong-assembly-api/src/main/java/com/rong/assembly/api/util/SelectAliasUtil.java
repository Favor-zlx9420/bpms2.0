package com.rong.assembly.api.util;

import com.vitily.mybatis.util.SelectAlias;

public abstract class SelectAliasUtil {
    public static SelectAlias getFavOfPro(String securityIdStr,Long userId){
        return SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_pro_fav where security_id="+securityIdStr+" and user_id="+userId+" and deltag=false) fav",true);
    }
    public static SelectAlias getFavOfPro(Long userId) {
        return getFavOfPro("e.security_id",userId);
    }
    public static SelectAlias getFavOfPeople(String peopleIdStr,Long userId){
        return SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_people_fav where person_id="+peopleIdStr+" and user_id="+userId+" and deltag=false) fav",true);
    }
    public static SelectAlias getFavOfPeople(Long userId) {
        return getFavOfPeople("e.person_id",userId);
    }
    public static SelectAlias getFavOfOrg(String partyIdStr,Long userId){
        return SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_org_fav where party_id="+partyIdStr+" and user_id="+userId+" and deltag=false) fav",true);
    }
    public static SelectAlias getFavOfOrg(Long userId) {
        return getFavOfOrg("e.party_id",userId);
    }

    public static SelectAlias getFavOfRoadShow(String roadshowIdStr,Long userId){
        return SelectAlias.valueOf("(select case when count(id) > 0 then true else false end from tb_user_road_show_fav where road_show_id="+roadshowIdStr+" and user_id="+userId+" and deltag=false) fav",true);
    }
    public static SelectAlias getFavOfRoadShow(Long userId) {
        return getFavOfRoadShow("e.id",userId);
    }
}
