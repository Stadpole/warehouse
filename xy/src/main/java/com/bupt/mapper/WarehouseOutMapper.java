package com.bupt.mapper;

import com.bupt.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Stadpole on 2018/5/22.
 */
@Mapper
@Component(value ="warehouseOutMapper")
public interface WarehouseOutMapper {
    @Select("select sum(out_count) as quantity ,concat(date_format(create_time, '%Y'),FLOOR((date_format(create_time, '%m')+2)/3)) as date from t_warehouse_out group by concat(date_format(create_time, '%Y'),FLOOR((date_format(create_time, '%m')+2)/3)); \n")
    List<Account> findAccountQuarter();
   /* @Select("select sum(out_count) from t_warehouse_out group by concat(date_format(create_time, '%Y'),FLOOR((date_format(create_time, '%m')+2)/3)); \n")
    double[] findAccountQuarter();*/
    /*@Select("select sum(out_count)  from t_warehouse_out group by date_format(create_time, '%Y-%m');")
    double[]  findAccountMonth();*/
   @Select("select sum(out_count) as quantity,date_format(create_time, '%Y-%m')as date from t_warehouse_out group by date_format(create_time, '%Y-%m');")
   List<Account> findAccountMonth();
    /*@Select("select sum(out_count)  from t_warehouse_out group by date_format(create_time, '%Y-%m-%d');")
    double[]  findAccountDay();*/

    @Select("select IFNULL(sum(out_count),'0') as quantity,date_format(datelist, '%Y-%m-%d') as date from t_warehouse_out RIGHT JOIN calendar on DATE_FORMAT(create_time,'%y-%m-%d')=datelist WHERE datelist BETWEEN #{arg0} AND #{arg1} group by date_format(datelist, '%Y-%m-%d');")
    List<Account> findAccountDay(Date start, Date end);
    @Select("select sum(out_count) as quantity from  t_warehouse_out  WHERE  ck_id=#{arg0} And sp_id=#{arg1} AND create_time BETWEEN  #{arg2}AND #{arg3}  ;")
    Account findSpRkSum(String ckId,String spId,Date start,Date end);
}