package com.bupt.repository;

import com.bupt.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface LoginLogRepository extends JpaRepository<LoginLog, String> {

}
