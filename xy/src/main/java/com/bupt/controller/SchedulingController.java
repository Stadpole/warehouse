package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.entity.ScheduleResult;
import com.bupt.service.SchedulingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//import com.bupt.rabbitmq.send.GetInfos;
//import com.bupt.repository.BoxSizeRepository;
//import com.bupt.service.BoxSizeService;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/scheduling")
public class SchedulingController extends BaseCommonController {
    @Autowired
    private SchedulingService schedulingService;

    @RequestMapping(value = "/In", method = RequestMethod.GET)
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "入库调度",operationDetail= "调度单")
    public List<ScheduleResult> recentIn(double applyCount,String spId) {
        try{
            List<String> result=schedulingService.solutionIn(applyCount);
            List<ScheduleResult> scheduleResults=new ArrayList<>();
            if(result!=null){
                String[] strs= new String[result.size()];
                for(int i=0;i<result.size();i++){
                    strs[i]=result.get(i);
                }
                for(int j=0;j<strs.length;j++){
                    ScheduleResult scheduleResult=new ScheduleResult();
                    String [] s=strs[j].split("#");
                    scheduleResult.setCkId(s[0]);
                    scheduleResult.setApplyCount(Double.parseDouble(s[1]));
                    scheduleResults.add(scheduleResult);
                }
                return scheduleResults;

            }
           else{
                ArrayList<ScheduleResult> list=new ArrayList<>();
                ScheduleResult scheduleResult=new ScheduleResult();
                scheduleResult.setCkId("no result");
                scheduleResult.setApplyCount(0.0);
                list.add(scheduleResult);
                return list;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "/Out", method = RequestMethod.GET)
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "出库调度",operationDetail= "调度单")
    public List<ScheduleResult> recentOut(double applyCount,String spId) {
        try{
            List<String> result=schedulingService.solutionOut(applyCount,spId);
            List<ScheduleResult> scheduleResults=new ArrayList<>();
            if(result!=null){
                String[] strs= new String[result.size()];
                for(int i=0;i<result.size();i++){
                    strs[i]=result.get(i);
                }
                for(int j=0;j<strs.length;j++){
                    ScheduleResult scheduleResult=new ScheduleResult();
                    String [] s=strs[j].split("#");
                    scheduleResult.setCkId(s[0]);
                    scheduleResult.setApplyCount(Double.parseDouble(s[1]));
                    scheduleResults.add(scheduleResult);
                }
                return scheduleResults;

            }
            else{
                ArrayList<ScheduleResult> list=new ArrayList<>();
                ScheduleResult scheduleResult=new ScheduleResult();
                scheduleResult.setCkId("no result");
                scheduleResult.setApplyCount(0.0);
                list.add(scheduleResult);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}

