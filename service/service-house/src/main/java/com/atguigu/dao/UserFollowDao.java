package com.atguigu.dao;


import com.atguigu.base.dao.BaseDao;
import com.atguigu.entity.UserFollow;
import org.apache.ibatis.annotations.Param;

public interface UserFollowDao extends BaseDao<UserFollow> {
    Integer countByUserIdAndHouseId(@Param("userId")Long userId,
                                    @Param("houseId")Long houseId);
}
