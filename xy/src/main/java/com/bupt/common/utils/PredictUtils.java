package com.bupt.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;




public class PredictUtils {
	private List<Double> coef=new ArrayList<Double>();
//  ���ȱ��룬��ĳһ�н��б���
    private static String filePath="E://121.json";
    private static double learnRate=0.0001;
    public static ArrayList<String> oneHot(List<String> ls, int indexs) throws Exception {
    	List<String> features=new ArrayList<String>();
    	for(int i=indexs;i<ls.get(0).split(",").length-1;i++){
    		features.add(i+"");
    	}
        List<String> label=new ArrayList<>();
        int labelindex=ls.get(0).split(",").length-1;
        for(int index=0;index<indexs;index++){
            label.clear();
//      ������ֵ
        HashSet<String> set = new HashSet<String>();
        for (String l : ls) {
            set.add(l.split(",")[index]);
        }
        for(String i:set){
        	features.add(index+":"+i);
        }

//        Ϊ��ֵӳ�������±�
        HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
        int ind = 0;
        for (String a : set) {
            toIndex.put(a, ind);
            ind++;
        }
//      ��ʼ����

        for (int i=0; i<ls.size(); i++) {
            int a[] = new int[set.size()];
            a[ toIndex.get( ls.get(i).split(",")[index] ) ] = 1;
            String[] temp=ls.get(i).split(",");
            label.add(temp[labelindex]);
            ls.set(i, ls.get(i) + ","+array2string(a));
        }
        }
        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists())
            file.delete();
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            for(int i=0;i<features.size();i++){
//            ps.println(i);// ���ļ���д���ַ���
            ps.append(features.get(i));// �����еĻ���������ַ���
        	if(i!=features.size()-1){
                ps.append(",");// �����еĻ���������ַ���        		
        	}
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0;i<ls.size();i++){
        	String[] temp=ls.get(i).split(",");
        	String tt="";
        	for(int j=indexs;j<temp.length;j++){
        	    if(j!=labelindex)
        		tt+=(temp[j]+",");
        	}
        	tt+=label.get(i);
        	ls.set(i, tt);
        }
        return (ArrayList<String>) ls;
    }
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }
    public static List<String> importCsv(File file){
        List<String> dataList=new ArrayList<String>();
        
        BufferedReader br=null;
        try { 
            br = new BufferedReader(new FileReader(file));
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                dataList.add(line);
            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return dataList;
    }
	private static String array2string(int[] a) {
		String result="";
	for(int i=0;i<a.length;i++)
	{
		if(a[i]!=1){
			result+="0";
		}else {
			result+="1";
		}
		if(i!=a.length-1){
			result+=",";
		}
	}
	return result;
}
	static String getONEHOT(String log) throws IOException{
		File file = new File(filePath);
        InputStreamReader reader = new InputStreamReader(  
                new FileInputStream(filePath)); // ����һ������������reader
        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
        String line = "";  
        line = br.readLine();
        String[] standard=line.split(",");
        String[] records=log.split(",");
        int index=Integer.parseInt(standard[0]);
        String result="";
        for(int i=index;i<records.length;i++){
        	result+=(records[i]+",");
        }
        for(int i=0;i<index;i++){
        	for (int j = 0; j < standard.length; j++) {
				if(standard[j].split(":").length==2&&Integer.parseInt(standard[j].split(":")[0])==i){
					if((standard[j].split(":")[1]).equals(records[i])){
			        	result+=(1+",");
					}else {
			        	result+=(0+",");
					}
				}
			}
        }
        result=result.substring(0,result.length()-1);
        return result;
	}
	List<Double> Getcoef(String path) throws Exception{
        DataSource source=new DataSource(path);//ѵ������  
        Instances data=source.getDataSet();
        if(data.classIndex()==-1)    
            data.setClassIndex(data.numAttributes()-1);//ʹ�����һ��������Ϊ�������  

        LinearRegression linear = new LinearRegression();
        linear.buildClassifier(data);//����ѵ�����ݹ��������
        for(double i:linear.coefficients())
            coef.add(i);
	return coef; 
	}
	double Caculate(String log){
        double result=0;
        List<String> nums=new ArrayList<>();
        try {
            for(String ll:getONEHOT(log).split(",")){
                nums.add(ll);
}
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<coef.size();i++){
            result+=coef.get(i)*Integer.parseInt(nums.get(i));
        }
        return result;
	}
	void Updatecoef(float real,float predict,String log){
	    for(int i=0;i<coef.size();i++){
            try {
                coef.set(i,coef.get(i)+learnRate*(real-predict)*Double.parseDouble(getONEHOT(log).split(",")[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	public static void main(String[] args) throws Exception {
		List<String> ls=new ArrayList<String>();
		ls.add("as,we,er,1,2,3");
		ls.add("ae,wt,er,1,2,3");
		ls.add("ae,we,er,1,2,3");
		
		List<String> reList=oneHot(ls,3);
//		System.out.println(getONEHOT("as,we,er,1,2,3"));
		for(String i:reList)

			System.out.println(i);
	}
}
