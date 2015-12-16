package com.qijiayiju.utils;

import android.util.Log;

public class MyStrinUtils {
      public static int  fiveToint(String s){
    	  String string1;
    	  int sum=0;
    	  int num=0;
    	  string1=MyStrinUtils.Totwostring(s.charAt(0))+MyStrinUtils.Totwostring(s.charAt(1))+MyStrinUtils.Totwostring(s.charAt(3))+MyStrinUtils.Totwostring(s.charAt(4));
    	 Log.i("ad----------->", string1);
    	  num=string1.length();
    	  for(int i=0;i<num;i++){
    		  if(string1.charAt(i)=='1'){
    			  sum=sum+MyStrinUtils.chengFangOfTwo(num-1-i);
    		  }
    	  }
    	  
		return sum;
    	  
      }
      
      public static String Totwostring(char d){
    	  String a = null;
    	  switch (d) {
    	  case '0':
  			a="0000";
  			break;
		case '1':
			a="0001";
			break;
case '2':
			a="0010";
			break;
case '3':
	a="0011";
	break;
case '4':
	a="0100";
	break;
case '5':
	a="0101";
	break;
case '6':
	a="0110";
	break;
case '7':
	a="0111";
	break;
case '8':
	a="1000";
	break;
case '9':
	a="1001";
	break;
case 'a'|'A':
	a="1010";
	break;
case 'b'|'B':
	a="1011";
	break;
case 'c'|'C':
	a="1100";
	break;
case 'd'|'D':
	a="1101";
	break;
case 'e'|'E':
	a="1110";
	break;
case 'f'|'F':
	a="1111";
	break;
		}
    	  
		return a;
    	  
      }
      public static int chengFangOfTwo(int a){
    	  int Ji=1;
    	
    	  for(int i=a;i>0;i-- ){
    		  Ji=Ji*2;
    	  }
    	  
		return Ji;
    	  
      }
}
