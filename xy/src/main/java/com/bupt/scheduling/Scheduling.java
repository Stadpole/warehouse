package com.bupt.scheduling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stadpole on 2018/3/29.
 */
public class Scheduling {

    //	need表示需要货物的总数
 //	recent为拥有需要货物的货柜号，格式为 仓库号#货柜号
//	locations为仓库所在的位置坐标，格式为 仓库号#经度#纬度
//	p_loc为当前用户的位置信息，格式为 经度#纬度
    public static List<String> Deal(double need,List<String> recent,List<String> locations,String p_loc) {
        if(need>recent.size()) return null;
        List<String> oreder=getOrder(locations);//按序得到仓库号
        List<Integer> p=getDistance(locations,p_loc);//表示第各个仓库到当前用户位置的距离，即代价
        List<Integer> w=getnum(oreder,recent);//仓库商品数目
        for (int i = 0; i < w.size(); i++) {
            System.out.print(oreder.get(i)+"\t"+"\t");
        }
        System.out.println();
        for (int i = 0; i < w.size(); i++) {
            System.out.print(w.get(i)+"\t"+"\t");
        }
        System.out.println();
        for (int i = 0; i < w.size(); i++) {
            System.out.print(p.get(i)+"\t"+"\t");
        }
        System.out.println();
        int[][] c=BackPack_Solution((int)need+1,w,p);

        //回溯法选择仓库
        List<String> result=new ArrayList<String>();
        List<Integer> choice=new ArrayList<Integer>();
        int min=MAX;
        int index=0;
        int j=c[0].length-1;
        for(int i=0;i<oreder.size();i++){
            if(c[i][j]<min){
                min=c[i][j];
                index=i;
            }
        }
        int i=index;
        while(i>0&&j>=0){
            if(c[i][j]==c[i-1][j]){
                i--;
            }
            else{
                choice.add(i);
                j=j-w.get(i);
                i--;
            }
        }
        int sum=0;
        for(int h=0;h<choice.size();h++){
            sum+=w.get(choice.get(h));
        }
        if(sum<need){
            choice.add(0);
        }
        int needd=(int) need;
        System.out.println("选择仓库号有：");
        for(int k=0;k<choice.size();k++){
            if(k==choice.size()-1){
                result.add(oreder.get(choice.get(k))+"#"+needd);
            }else{
                result.add(oreder.get(choice.get(k))+"#"+w.get(k));
            }
            needd-=w.get(k);
            System.out.print(oreder.get(choice.get(k))+"\t");
        }
        return result;
    }
    //按照距离给定的顺序得到仓库编号
    private static List<String> getOrder(List<String> locations) {
        List<String> order=new ArrayList<String>();
        for(int i=0;i<locations.size();i++)
        {
            String[] info=locations.get(i).split("#");
            order.add(info[0]);
        }
        return order;
    }
    //得到每个仓库拥有的货柜数目
    private static List<Integer> getnum(List<String> oreder, List<String> recent) {
        List<Integer> num=new ArrayList<Integer>();
        for(int i=0;i<oreder.size();i++){
            num.add(0);
        }
        for(int i=0;i<recent.size();i++)
        {
            String[] info=recent.get(i).split("#");
            num.set(oreder.indexOf(info[0]), num.get(oreder.indexOf(info[0]))+1);
        }
        return num;
    }
    // 按仓库求代价,即距    离
    private static List<Integer> getDistance(List<String> locations, String p_loc) {
        String[] recent=p_loc.split("#");
        double lat1=Double.parseDouble(recent[0]);
        double lng1=Double.parseDouble(recent[1]);
        List<Integer> result=new ArrayList<Integer>();
        for (int i = 0; i < locations.size(); i++) {
            String[] loc=locations.get(i).split("#");
            double lat2=Double.parseDouble(loc[1]);
            double lng2=Double.parseDouble(loc[2]);
            result.add((int)Distance(lat1,lng1,lat2,lng2));
        }
        return result;
    }
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
    public static double Distance(double lat1, double lng1, double lat2,
                                  double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }
    private final static int MAX = Integer.MAX_VALUE/2;
//	w表示仓库拥有的商品数目
//	p代表从该仓库取得商品的代价
    /**
     * @param m 表示需求货物的最大数量
     *
     * @param w 表示仓库可存放商品的数量
     * @param p 表示商品运输成本（距离）+管理成本
     */
    public static int[][] BackPack_Solution(int m, List<Integer> w, List<Integer> p) {
        //c[i][v]表示前i个仓库调入一个需求为m的仓库并使之满足需求所需要的最小代价
        int n=w.size();
        int c[][] = new int[n][m];
        for (int j = 0; j < m ; j++)
            if (w.get(0) <= j)
                c[0][j] = MAX;
            else{
                c[0][j]=p.get(0);
            }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //当仓库i是否取得，c[i][j]为下列两种情况之一：
                //(1)仓库i不取，所以c[i][j]为c[i-1][j]的值
                //(2)选取仓库i，则还需要的数量为j-w[i],所以c[i][j]为c[i-1][j-w[i-1]]的值加上仓库i的开销
                if (c[i - 1][j] > ((j-w.get(i)>=0?c[i - 1][j - w.get(i)]:0) + p.get(i)))
                {
                    c[i][j] = ((j-w.get(i)>=0?c[i - 1][j - w.get(i)]:0) + p.get(i));
                }
                else
                    c[i][j] = c[i - 1][j];
            }
        }
        return c;
    }
}
