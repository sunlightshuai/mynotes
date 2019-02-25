package com.encrypt.des;


/**
 * 密码字符处理
 * @author sunli
 *
 */
public class EncryptUtil {

	/**
	 * 将string转成64位的二进制数组
	 * @param str string字符串
	 * @return
	 */
	public static int[] strToBt(String str){
		int leng = str.toCharArray().length;
		int[] bt = new int[64];
		if(leng < 4){
			int i=0,j=0,p=0,q=0;
			for(i = 0;i<leng;i++){
				int k = (byte)str.charAt(i);
				for(j=0;j<16;j++){			
					int pow=1,m=0;
					for(m=15;m>j;m--){
						pow *= 2;
					}				
					bt[16*i+j]=(k/pow)%2;
				}
			}
			for(p = leng;p<4;p++){
				int k = 0;
				for(q=0;q<16;q++){			
					int pow=1,m=0;
					for(m=15;m>q;m--){
						pow *= 2;
					}				
					bt[16*p+q]=(k/pow)%2;
				}
			}	
		}else{
			int i=0,j=0;
			for(i = 0;i<4;i++){
				int k = (byte)str.charAt(i);
				for(j=0;j<16;j++){			
					int pow=1,m=0;
					for(m=15;m>j;m--){
						pow *= 2;
					}				
					bt[16*i+j]=(k/pow)%2;
				}
			}	
		}
		return bt;
	}
	
	/**
	 * 将二进制转成十六进制
	 * @param str 二进制数据
	 * @return
	 */
	public static String bt4ToHex(String str) {
		StringBuffer strBuffer = new StringBuffer();
		int leng = str.length()/4;
		for(int i = 0; i<leng;i++){
			String tempStr = str.substring(i*4+0,i*4+4);
			if("0000".equals(tempStr)){
				strBuffer.append("0");
			}else if("0001".equals(tempStr)){
				strBuffer.append("1");
			}else if("0010".equals(tempStr)){
				strBuffer.append("2");
			}else if("0011".equals(tempStr)){
				strBuffer.append("3");
			}else if("0100".equals(tempStr)){
				strBuffer.append("4");
			}else if("0101".equals(tempStr)){
				strBuffer.append("5");
			}else if("0110".equals(tempStr)){
				strBuffer.append("6");
			}else if("0111".equals(tempStr)){
				strBuffer.append("7");
			}else if("1000".equals(tempStr)){
				strBuffer.append("8");
			}else if("1001".equals(tempStr)){
				strBuffer.append("9");
			}else if("1010".equals(tempStr)){
				strBuffer.append("A");
			}else if("1011".equals(tempStr)){
				strBuffer.append("B");
			}else if("1100".equals(tempStr)){
				strBuffer.append("C");
			}else if("1101".equals(tempStr)){
				strBuffer.append("D");
			}else if("1110".equals(tempStr)){
				strBuffer.append("E");
			}else if("1111".equals(tempStr)){
				strBuffer.append("F");
			}
		}
		return strBuffer.toString();
	}
	
	/**
	 * 将16进制转成2进制
	 * @param str 16进制字符串
	 * @return
	 */
	public static String hexToBt4(String str) {
		StringBuffer strBuffer = new StringBuffer();
		for(int i = 0; i<str.length();i++){
			char hex = str.charAt(i);
			switch (hex) {
				case '0' : strBuffer.append("0000"); break;
				case '1' : strBuffer.append("0001"); break;
				case '2' : strBuffer.append("0010"); break;
				case '3' : strBuffer.append("0011"); break;
				case '4' : strBuffer.append("0100"); break;
				case '5' : strBuffer.append("0101"); break;
				case '6' : strBuffer.append("0110"); break;
				case '7' : strBuffer.append("0111"); break;
				case '8' : strBuffer.append("1000"); break;
				case '9' : strBuffer.append("1001"); break;
				case 'A' : strBuffer.append("1010"); break;
				case 'B' : strBuffer.append("1011"); break;
				case 'C' : strBuffer.append("1100"); break;
				case 'D' : strBuffer.append("1101"); break;
				case 'E' : strBuffer.append("1110"); break;
				case 'F' : strBuffer.append("1111"); break;
			}
		}
		return strBuffer.toString();
	}
	
	
	
	/**
	 * 将0~15的数字转成二进制
	 * @param i 0~15的数字
	 * @return
	 */
	public static String getBoxBinary(int i) {
		String binary = "";
		switch (i) {
			case  0 :binary = "0000";break;
			case  1 :binary = "0001";break;
			case  2 :binary = "0010";break;
			case  3 :binary = "0011";break;
			case  4 :binary = "0100";break;
			case  5 :binary = "0101";break;
			case  6 :binary = "0110";break;
			case  7 :binary = "0111";break;
			case  8 :binary = "1000";break;
			case  9 :binary = "1001";break;
			case 10 :binary = "1010";break;
			case 11 :binary = "1011";break;
			case 12 :binary = "1100";break;
			case 13 :binary = "1101";break;
			case 14 :binary = "1110";break;
			case 15 :binary = "1111";break;
		}
		return binary;
	}
	
	
	public static String bt64ToHex(int[] byteData){
		String hex = "";
		for(int i = 0;i<16;i++){
			String bt = "";
			for(int j=0;j<4;j++){		
				bt += byteData[i*4+j];
			}		
			hex+=EncryptUtil.bt4ToHex(bt);
		}
		return hex;
	}
}
