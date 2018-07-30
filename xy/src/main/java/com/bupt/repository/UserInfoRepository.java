package com.bupt.repository;

import com.bupt.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    @Query
    UserInfo findByUsername(String username);
    @Query
    UserInfo findByUid(Integer uid);
    @Query
    void deleteByUid(Integer uid);

}
