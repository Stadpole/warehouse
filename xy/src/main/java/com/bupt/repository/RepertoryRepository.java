package com.bupt.repository;


import com.bupt.entity.Repertory;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Stadpole on 2017/9/21.
 */
public interface RepertoryRepository extends JpaRepository<Repertory, String> {
   Repertory findByCkIdAndSpId(String ckId,String spId);
}
