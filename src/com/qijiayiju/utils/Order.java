package com.qijiayiju.utils;


public class Order {
     public static String control_W="021000820001020000";
     public static String setWOrder(String four){
    	 String ob1=CRC16(control_W.replace("0000", four));
    	 
    	 
		return ob1;
    	 
     }
     public static String setROrder(String eight){
    	 String s="";
 		return s;
     	 
      }
     public static String CRC16(String s){
    	 String ob2=CRC16M.getBufHexStr(CRC16M.getSendBuf(s));
		return ob2;
    	 
     }
     
}
