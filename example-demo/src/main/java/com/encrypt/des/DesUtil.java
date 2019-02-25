package com.encrypt.des;

/**
 * DES加密算法
 * @author sunli
 *
 */
public class DesUtil {

	/**
	 * 根据秘钥的长度，将秘钥分为n组64位的二维二进制数组
	 * @param key 秘钥
	 * @return
	 */
	public static int[][] getKeyBytes(String key){
		int leng = key.length();
		int iterator = leng/4;
		int remainder = leng%4;
		int i = 0;
		int[][] results = new int[remainder > 0?iterator+1:iterator][];
		for(i = 0;i < iterator; i ++){
			int[] result = EncryptUtil.strToBt(key.substring(i*4+0,i*4+4));
			results[i] = result;
			
		}
		if(remainder > 0){
			int[] result = EncryptUtil.strToBt(key.substring(i*4+0,leng));
			results[i] = result;
		}		
		return results;
	}

	
	/**
	 * 对相同长度的数组进行异或
	 * @param byteOne	数组1
	 * @param byteTwo	数组2
	 * @return
	 */
	private static int[] xor(int[] byteOne,int[] byteTwo){	
		int[] xorByte = new int[byteOne.length];
		int i = 0;
		for(i = 0;i < byteOne.length; i ++){			
			xorByte[i] = byteOne[i] ^ byteTwo[i];
		}	
		return xorByte;
	}
	
	
	
	/**
	 * S盒置换
	 * 由8个S盒，每一个S盒都有6位输入，4位输出，8个S盒不同。
	 * 48位输入被分成8个6位的分组，每个分组对应一个S盒代替操作：分组1由盒1操作，分组2由盒2操作。
	 * 最终代替的结果是8个4位的分组，他们重新合在一起形成一个32位的分组
	 * @param expandByte 密钥从56位选出48位 与扩展分组异或后的48位结果
	 * @return
	 */
	private static int[]  sBoxPermute(int[]  expandByte){
		  int[] sBoxByte = new int[32];
		  String binary = "";
		  		/* s1 */
		  int[][] s1 = {
				  {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
				  {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
				  {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
				  {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
				   };

				  /* s2 */
		  int[][] s2 = {
				  {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
				  {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
				  {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
				  {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
				  };

				  /* s3 */
		  int[][] s3= {
				  {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
				  {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
				  {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
				  {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
				  };

				  /* s4 */
		  int[][] s4 = {
				  {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
				  {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
				  {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
				  {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
		  			};

				  /* s5 */
		  int[][] s5 = {
				  {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
				  {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
				  {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
				  {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }};

				  /* s6 */
		  int[][] s6 = {
				  {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
				  {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
				  {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
				  {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }};

				  /* s7 */
		  int[][] s7 = {
				  {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
				  {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
				  {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
				  {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

				  /* s8 */
		  int[][] s8 = {
				  {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
				  {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
				  {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
				  {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
		  int m = 0;
		  for(m=0;m<8;m++){
			int i=0,j=0;
			i = expandByte[m*6+0]*2+expandByte[m*6+5];
			j = expandByte[m * 6 + 1] * 2 * 2 * 2 
				+ expandByte[m * 6 + 2] * 2* 2 
				+ expandByte[m * 6 + 3] * 2 
				+ expandByte[m * 6 + 4];
			switch (m) {
				case 0 :
					binary = EncryptUtil.getBoxBinary(s1[i][j]);
					break;
				case 1 :
					binary = EncryptUtil.getBoxBinary(s2[i][j]);
					break;
				case 2 :
					binary = EncryptUtil.getBoxBinary(s3[i][j]);
					break;
				case 3 :
					binary = EncryptUtil.getBoxBinary(s4[i][j]);
					break;
				case 4 :
					binary = EncryptUtil.getBoxBinary(s5[i][j]);
					break;
				case 5 :
					binary = EncryptUtil.getBoxBinary(s6[i][j]);
					break;
				case 6 :
					binary = EncryptUtil.getBoxBinary(s7[i][j]);
					break;
				case 7 :
					binary = EncryptUtil.getBoxBinary(s8[i][j]);
					break;
			}			
			sBoxByte[m*4+0] = Integer.parseInt(binary.substring(0, 1));
			sBoxByte[m*4+1] = Integer.parseInt(binary.substring(1,2));
			sBoxByte[m*4+2] = Integer.parseInt(binary.substring(2,3));
			sBoxByte[m*4+3] = Integer.parseInt(binary.substring(3,4));
		}
		return sBoxByte;
	}
	
	/**
	 * P盒置换
	 * 把输入为映射成输出位，任一位不能映射两次。
	 * 例如，第21位移至第4位，将第4位移到第31位。
	 * @param sBoxByte s盒置换后的结果
	 * @return
	 */
	private static int[] pPermute(int[] sBoxByte){
		int[] pBoxPermute = new int[32];
		pBoxPermute[ 0] = sBoxByte[15]; 
		pBoxPermute[ 1] = sBoxByte[ 6]; 
		pBoxPermute[ 2] = sBoxByte[19]; 
		pBoxPermute[ 3] = sBoxByte[20]; 
		pBoxPermute[ 4] = sBoxByte[28]; 
		pBoxPermute[ 5] = sBoxByte[11]; 
		pBoxPermute[ 6] = sBoxByte[27]; 
		pBoxPermute[ 7] = sBoxByte[16]; 
		pBoxPermute[ 8] = sBoxByte[ 0]; 
		pBoxPermute[ 9] = sBoxByte[14]; 
		pBoxPermute[10] = sBoxByte[22]; 
		pBoxPermute[11] = sBoxByte[25]; 
		pBoxPermute[12] = sBoxByte[ 4]; 
		pBoxPermute[13] = sBoxByte[17]; 
		pBoxPermute[14] = sBoxByte[30]; 
		pBoxPermute[15] = sBoxByte[ 9]; 
		pBoxPermute[16] = sBoxByte[ 1]; 
		pBoxPermute[17] = sBoxByte[ 7]; 
		pBoxPermute[18] = sBoxByte[23]; 
		pBoxPermute[19] = sBoxByte[13]; 
		pBoxPermute[20] = sBoxByte[31]; 
		pBoxPermute[21] = sBoxByte[26]; 
		pBoxPermute[22] = sBoxByte[ 2]; 
		pBoxPermute[23] = sBoxByte[ 8]; 
		pBoxPermute[24] = sBoxByte[18]; 
		pBoxPermute[25] = sBoxByte[12]; 
		pBoxPermute[26] = sBoxByte[29]; 
		pBoxPermute[27] = sBoxByte[ 5]; 
		pBoxPermute[28] = sBoxByte[21]; 
		pBoxPermute[29] = sBoxByte[10]; 
		pBoxPermute[30] = sBoxByte[ 3]; 
		pBoxPermute[31] = sBoxByte[24];		
		return pBoxPermute;
	}
	
	/**
	 * 密钥置换
	 * 不考虑每个字节的第8位，DES密钥又64位减至56位。每个字节的第8位作为奇偶校验位。
	 * 在每一轮DES中，56位密钥产生出48位的子密钥。
	 * @param keyByte
	 * @return
	 */
	private static int[][] generateKeys(int[] keyByte){
		int[] key   = new int[56];
		int[][] keys = new int[16][];
		
		keys[ 0] = new int[48];
		keys[ 1] = new int[48];
		keys[ 2] = new int[48];
		keys[ 3] = new int[48];
		keys[ 4] = new int[48];
		keys[ 5] = new int[48];
		keys[ 6] = new int[48];
		keys[ 7] = new int[48];
		keys[ 8] = new int[48];
		keys[ 9] = new int[48];
		keys[10] = new int[48];
		keys[11] = new int[48];
		keys[12] = new int[48];
		keys[13] = new int[48];
		keys[14] = new int[48];
		keys[15] = new int[48];	
		int[] loop = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
		int i = 0,j=0,k=0;
		
		// 将64位的密钥去掉每个字节的第8位，减至56位
		for(i=0;i<7;i++){
			for(j=0,k=7;j<8;j++,k--){
				key[i*8+j]=keyByte[8*k+i];
			}
		}		
		
		// 56位密钥被分为两部分，每部分28位。
		// 根据轮数，这两部分分别循环左移一位。
		// 从56位中选出48位。
		for(i = 0;i < 16;i ++){
			int tempLeft=0;
			int tempRight=0;
			for(j = 0; j < loop[i];j ++){					
				tempLeft = key[0];
				tempRight = key[28];
				for(k = 0;k < 27 ;k ++){
					key[k] = key[k+1];
					key[28+k] = key[29+k];
				}	
				key[27]=tempLeft;
				key[55]=tempRight;
			}
			int[] tempKey = new int[48];
			tempKey[ 0] = key[13];
			tempKey[ 1] = key[16];
			tempKey[ 2] = key[10];
			tempKey[ 3] = key[23];
			tempKey[ 4] = key[ 0];
			tempKey[ 5] = key[ 4];
			tempKey[ 6] = key[ 2];
			tempKey[ 7] = key[27];
			tempKey[ 8] = key[14];
			tempKey[ 9] = key[ 5];
			tempKey[10] = key[20];
			tempKey[11] = key[ 9];
			tempKey[12] = key[22];
			tempKey[13] = key[18];
			tempKey[14] = key[11];
			tempKey[15] = key[ 3];
			tempKey[16] = key[25];
			tempKey[17] = key[ 7];
			tempKey[18] = key[15];
			tempKey[19] = key[ 6];
			tempKey[20] = key[26];
			tempKey[21] = key[19];
			tempKey[22] = key[12];
			tempKey[23] = key[ 1];
			tempKey[24] = key[40];
			tempKey[25] = key[51];
			tempKey[26] = key[30];
			tempKey[27] = key[36];
			tempKey[28] = key[46];
			tempKey[29] = key[54];
			tempKey[30] = key[29];
			tempKey[31] = key[39];
			tempKey[32] = key[50];
			tempKey[33] = key[44];
			tempKey[34] = key[32];
			tempKey[35] = key[47];
			tempKey[36] = key[43];
			tempKey[37] = key[48];
			tempKey[38] = key[38];
			tempKey[39] = key[55];
			tempKey[40] = key[33];
			tempKey[41] = key[52];
			tempKey[42] = key[45];
			tempKey[43] = key[41];
			tempKey[44] = key[49];
			tempKey[45] = key[35];
			tempKey[46] = key[28];
			tempKey[47] = key[31];
			int m = 0;
			switch(i){
				case  0 : for(m=0;m < 48 ;m++){ keys[ 0][m] = tempKey[m]; } break;
				case  1 : for(m=0;m < 48 ;m++){ keys[ 1][m] = tempKey[m]; } break;
				case  2 : for(m=0;m < 48 ;m++){ keys[ 2][m] = tempKey[m]; } break;
				case  3 : for(m=0;m < 48 ;m++){ keys[ 3][m] = tempKey[m]; } break;
				case  4 : for(m=0;m < 48 ;m++){ keys[ 4][m] = tempKey[m]; } break;
				case  5 : for(m=0;m < 48 ;m++){ keys[ 5][m] = tempKey[m]; } break;
				case  6 : for(m=0;m < 48 ;m++){ keys[ 6][m] = tempKey[m]; } break;
				case  7 : for(m=0;m < 48 ;m++){ keys[ 7][m] = tempKey[m]; } break;
				case  8 : for(m=0;m < 48 ;m++){ keys[ 8][m] = tempKey[m]; } break;
				case  9 : for(m=0;m < 48 ;m++){ keys[ 9][m] = tempKey[m]; } break;
				case 10 : for(m=0;m < 48 ;m++){ keys[10][m] = tempKey[m]; } break;
				case 11 : for(m=0;m < 48 ;m++){ keys[11][m] = tempKey[m]; } break;
				case 12 : for(m=0;m < 48 ;m++){ keys[12][m] = tempKey[m]; } break;
				case 13 : for(m=0;m < 48 ;m++){ keys[13][m] = tempKey[m]; } break;
				case 14 : for(m=0;m < 48 ;m++){ keys[14][m] = tempKey[m]; } break;
				case 15 : for(m=0;m < 48 ;m++){ keys[15][m] = tempKey[m]; } break;
			}
		}
		return keys;	
	}
	
	/**
	 * 加密入口方法
	 * @param data 需要加密的数字
	 * @param firstKey 秘钥1
	 * @param secondKey秘钥2
	 * @param thirdKey 秘钥3
	 * @return
	 */
	public static String strEnc(String data,String firstKey,String secondKey,String thirdKey){
		// 需要加密的明文的长度
		int leng = data.length();
		String encData = "";
		int[][] firstKeyBt={},secondKeyBt={},thirdKeyBt={};
		int firstLength =0,secondLength=0,thirdLength=0;
		// 根据秘钥的长度分成n组二维二进制的数组
		if(firstKey != null && firstKey != ""){		
			firstKeyBt = getKeyBytes(firstKey);
			firstLength = firstKeyBt.length;
		}
		if(secondKey != null && secondKey != ""){
			secondKeyBt = getKeyBytes(secondKey);
			secondLength = secondKeyBt.length;
		}
		if(thirdKey != null && thirdKey != ""){
			thirdKeyBt = getKeyBytes(thirdKey);
			thirdLength = thirdKeyBt.length;
		}	
		
		if(leng > 0){
			if(leng < 4){
				// 将明文转成二进制数组
				encData = EncryptUtil.bt64ToHex(calcCipher(firstKey,secondKey,thirdKey,
						firstLength,secondLength,thirdLength,data,
						firstKeyBt,secondKeyBt,thirdKeyBt));
			}else{
				int iterator = (leng/4);
				int remainder = leng%4;
				int i=0;			
				for(i = 0;i < iterator;i++){
					// 按4个长度分组
					String tempData = data.substring(i*4+0,i*4+4);
					encData += EncryptUtil.bt64ToHex(calcCipher(firstKey,secondKey,thirdKey,
							firstLength,secondLength,thirdLength,tempData,
							firstKeyBt,secondKeyBt,thirdKeyBt));
				}
				// 需要加密的明文长度按4分组后的余数
				if(remainder > 0){
					String remainderData = data.substring(iterator*4+0,leng);
					encData += EncryptUtil.bt64ToHex(calcCipher(firstKey,secondKey,thirdKey,
							firstLength,secondLength,thirdLength,remainderData,
							firstKeyBt,secondKeyBt,thirdKeyBt));
				}								
			}
		}
		return encData;
	}
	
	/**
	 * 计算密文
	 * @param firstKey 密钥一
	 * @param secondKey密钥二
	 * @param thirdKey 密钥三
	 * @param firstLength 分组后二维二进制秘钥长度，即，分成了几组（密钥一）
	 * @param secondLength分组后二维二进制秘钥长度，即，分成了几组（密钥二）
	 * @param thirdLength分组后二维二进制秘钥长度，即，分成了几组（密钥三）
	 * @param tempData	需加密的密文
	 * @param firstKeyBt 分组后二维二进制密钥（密钥一）
	 * @param secondKeyBt分组后二维二进制密钥（密钥二）
	 * @param thirdKeyBt 分组后二维二进制密钥（密钥三）
	 * @return
	 */
	private static int[] calcCipher(String firstKey,String secondKey,String thirdKey,
			int firstLength,int secondLength,int thirdLength,String tempData,
			int[][] firstKeyBt,int[][] secondKeyBt,int[][] thirdKeyBt){
		int[] tempByte = EncryptUtil.strToBt(tempData);
		int[] encByte = null;
		// 三个key
		if(firstKey != null && firstKey !="" && secondKey != null && secondKey != "" && thirdKey != null && thirdKey != ""){
			int[] tempBt;
			int x,y,z;
			tempBt = tempByte;
			for(x = 0;x < firstLength ;x ++){
				tempBt = enc(tempBt,firstKeyBt[x]);
			}
			for(y = 0;y < secondLength ;y ++){
				tempBt = enc(tempBt,secondKeyBt[y]);
			}
			for(z = 0;z < thirdLength ;z ++){
				tempBt = enc(tempBt,thirdKeyBt[z]);
			}
			encByte = tempBt;
		}else{
			// 两个key
			if(firstKey != null && firstKey !="" && secondKey != null && secondKey != ""){
				int[] tempBt;
				int x,y;
				tempBt = tempByte;
				for(x = 0;x < firstLength ;x ++){
					tempBt = enc(tempBt,firstKeyBt[x]);
				}
				for(y = 0;y < secondLength ;y ++){
					tempBt = enc(tempBt,secondKeyBt[y]);
				}
				encByte = tempBt;
			}else{// 一个key
				if(firstKey != null && firstKey !=""){											
					int[] tempBt;
					int x;
					tempBt = tempByte;
					for(x = 0;x < firstLength ;x ++){								
						tempBt = enc(tempBt,firstKeyBt[x]);
					}
					encByte = tempBt;							
				}
			}
		}
		return encByte;
	}
	
	/**
	 * 根据二进制明文和密钥，进行DES计算
	 * @param dataByte 二进制明文
	 * @param keyByte 秘钥
	 * @return
	 */
	private static int[] enc(int[] dataByte,int[] keyByte){	
		int[][] keys = generateKeys(keyByte);		
		int[] ipByte   = initPermute(dataByte);	
		int[] ipLeft   = new int[32];
		int[] ipRight  = new int[32];
		int[] tempLeft = new int[32];
		int i = 0,j = 0,k = 0,m = 0, n = 0;
		// 将二进制明文转换成32位左右两部分
		for(k = 0;k < 32;k ++){
			ipLeft[k] = ipByte[k];
			ipRight[k] = ipByte[32+k];
		}	
		for(i = 0;i < 16;i ++){
			for(j = 0;j < 32;j ++){
				tempLeft[j] = ipLeft[j];
				ipLeft[j] = ipRight[j];			
			}	
			int[] key = new int[48];
			for(m = 0;m < 48;m ++){
				key[m] = keys[i][m];
			}
			int[] tempRight = xor(pPermute(sBoxPermute(xor(expandPermute(ipRight),key))), tempLeft);			
			for(n = 0;n < 32;n ++){
				ipRight[n] = tempRight[n];
			}	
			
		}	
		
		int[] finalData = new int[64];
		// 将左右两部分形成一个分组
		for(i = 0;i < 32;i ++){
			finalData[i] = ipRight[i];
			finalData[32+i] = ipLeft[i];
		}
		return finallyPermute(finalData);	
	}
	
	/**
	 * 扩展置换
	 * 将右部分的数据从32位扩展到48位
	 * @param rightData 右半部分数据
	 * @return
	 */
	private static int[] expandPermute(int[] rightData){	
		int[] epByte = new int[48];
		for (int i = 0; i < 8; i++) {
			if (i == 0) {
				epByte[i * 6 + 0] = rightData[31];
			} else {
				epByte[i * 6 + 0] = rightData[i * 4 - 1];
			}
			epByte[i * 6 + 1] = rightData[i * 4 + 0];
			epByte[i * 6 + 2] = rightData[i * 4 + 1];
			epByte[i * 6 + 3] = rightData[i * 4 + 2];
			epByte[i * 6 + 4] = rightData[i * 4 + 3];
			if (i == 7) {
				epByte[i * 6 + 5] = rightData[0];
			} else {
				epByte[i * 6 + 5] = rightData[i * 4 + 4];
			}
		}			
		return epByte;
	}
	
	/**
	 * 初始置换
	 * 明文的第58位换到第1位，50位换到第2位等以此类推
	 * @param originalData
	 * @return
	 */
	private static int[] initPermute(int[] originalData){
		int[] ipByte = new int[64];
		int i,m,n;
		for (i = 0, m = 1, n = 0; i < 4; i++, m += 2, n += 2) {
			int j,k;
			for (j = 7, k = 0; j >= 0; j--, k++) {
				ipByte[i * 8 + k] = originalData[j * 8 + m];
				ipByte[i * 8 + k + 32] = originalData[j * 8 + n];
			}
		}		
		return ipByte;
	}
	
	/**
	 * 末置换
	 * 初始置换的逆过程
	 * @param endByte P盒处理后的结果
	 * @return
	 */
	private static int[] finallyPermute(int[] endByte){		
		int[] fpByte = new int[64];	
		fpByte[ 0] = endByte[39]; 
		fpByte[ 1] = endByte[ 7]; 
		fpByte[ 2] = endByte[47]; 
		fpByte[ 3] = endByte[15]; 
		fpByte[ 4] = endByte[55]; 
		fpByte[ 5] = endByte[23]; 
		fpByte[ 6] = endByte[63]; 
		fpByte[ 7] = endByte[31]; 
		fpByte[ 8] = endByte[38]; 
		fpByte[ 9] = endByte[ 6]; 
		fpByte[10] = endByte[46]; 
		fpByte[11] = endByte[14]; 
		fpByte[12] = endByte[54]; 
		fpByte[13] = endByte[22]; 
		fpByte[14] = endByte[62]; 
		fpByte[15] = endByte[30]; 
		fpByte[16] = endByte[37]; 
		fpByte[17] = endByte[ 5]; 
		fpByte[18] = endByte[45]; 
		fpByte[19] = endByte[13]; 
		fpByte[20] = endByte[53]; 
		fpByte[21] = endByte[21]; 
		fpByte[22] = endByte[61]; 
		fpByte[23] = endByte[29]; 
		fpByte[24] = endByte[36]; 
		fpByte[25] = endByte[ 4]; 
		fpByte[26] = endByte[44]; 
		fpByte[27] = endByte[12]; 
		fpByte[28] = endByte[52]; 
		fpByte[29] = endByte[20]; 
		fpByte[30] = endByte[60]; 
		fpByte[31] = endByte[28]; 
		fpByte[32] = endByte[35]; 
		fpByte[33] = endByte[ 3]; 
		fpByte[34] = endByte[43]; 
		fpByte[35] = endByte[11]; 
		fpByte[36] = endByte[51]; 
		fpByte[37] = endByte[19]; 
		fpByte[38] = endByte[59]; 
		fpByte[39] = endByte[27]; 
		fpByte[40] = endByte[34]; 
		fpByte[41] = endByte[ 2]; 
		fpByte[42] = endByte[42]; 
		fpByte[43] = endByte[10]; 
		fpByte[44] = endByte[50]; 
		fpByte[45] = endByte[18]; 
		fpByte[46] = endByte[58]; 
		fpByte[47] = endByte[26]; 
		fpByte[48] = endByte[33]; 
		fpByte[49] = endByte[ 1]; 
		fpByte[50] = endByte[41]; 
		fpByte[51] = endByte[ 9]; 
		fpByte[52] = endByte[49]; 
		fpByte[53] = endByte[17]; 
		fpByte[54] = endByte[57]; 
		fpByte[55] = endByte[25]; 
		fpByte[56] = endByte[32]; 
		fpByte[57] = endByte[ 0]; 
		fpByte[58] = endByte[40]; 
		fpByte[59] = endByte[ 8]; 
		fpByte[60] = endByte[48]; 
		fpByte[61] = endByte[16]; 
		fpByte[62] = endByte[56]; 
		fpByte[63] = endByte[24];
		return fpByte;
	}
	
}
