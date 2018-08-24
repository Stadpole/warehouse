package com.bupt.csv;

import com.bupt.common.utils.PredictUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2018/8/4.
 */
public class PredictTest {
    private static String filePath="E://out_put/raw_dataset.csv";
//    public static List<String> importCsv(){
//       return  PredictUtils.importCsv();
//    }
    public static void main(String args[]) throws Exception {
        List<String> dataset= PredictUtils.importCsv(new File(filePath));
        System.out.println(dataset.get(0));
        ArrayList<String> oneHotDataSet=PredictUtils.oneHot(dataset,6);
      boolean b= PredictUtils.exportCsv(new File("E://out_put/dataset.csv"),oneHotDataSet);
       if(!b){
         throw new Exception("exportCsv fail");
       }
       List<Double> coef=PredictUtils.Getcoef("E://out_put/dataset.csv");
      String test="152237592916973,白炽灯M02,三雄.极光,light,152222760072925,152222760072925,5,1.522378119,1.516525448";

        List<String> list=new ArrayList<>();
      list.add(test);
     String oneHotT=PredictUtils.getONEHOT(test);
     System.out.println(oneHotT);
     String[] str=oneHotT.split(",");
     Double[] d=new Double[str.length];
     double result=0;
        for(int i=0;i<str.length;i++){
         d[i]=Double.parseDouble(str[i]);
         result=result+d[i]*coef.get(i+1);
     }
     result=result+coef.get(0);
        System.out.println("result"+result);
        //String log=PredictUtils.getONEHOT()
    }
}
