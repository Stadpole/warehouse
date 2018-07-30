package com.bupt.repository;

import com.bupt.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface OperationLogRepository extends JpaRepository<OperationLog, String> {

}
