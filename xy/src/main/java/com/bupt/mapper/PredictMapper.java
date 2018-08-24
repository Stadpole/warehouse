package com.bupt.mapper;

import com.bupt.entity.Predict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stadpole on 2018/5/22.
 */
@Mapper
@Component(value ="PredictMapper")
public interface PredictMapper {
    @Select("select commodity.sp_id as spId ," +
            "commodity.sp_name as spName," +
            "commodity.sp_brand as spBrand," +
            "commodity.sp_type as spType," +
            "IFNULL(warehouse_in.ck_id,'0') as rkCk ," +
            "IFNULL(warehouse_in.rk_count,'0') as rkCount," +
            "warehouse_in.create_time as rkTime," +
            "IFNULL(t_warehouse_out.ck_id ,'0')as outCk," +
            " t_warehouse_out.create_time  as outTime," +
            "IFNULL(t_warehouse_out.out_count,'0')as outCount " +
            "from commodity LEFT JOIN warehouse_in" +
            " on commodity.sp_id=warehouse_in.sp_id " +
            "LEFT JOIN t_warehouse_out on commodity.sp_id=t_warehouse_out.sp_id;")
    List<Predict> selectDetail();

}