package com.example.exam;

import java.util.Scanner;

public class Main {
	/*
	 *一种压缩算法的思路是将连续3个及的相同的字符串的个数变为数字，例如aaa压缩为3a,现在要求写出一种解压缩算法
	 *输入压缩后的字符串，判断输入的字符串是否合法，若不合法输出"!error" ,若合法，则输出原字符串，例如：
	 * 输入：3abbc4d
	 * 输出：aaabbcdddd
	 * 输入：2a3d
	 * 输出：!error
	 * (备注):原题还有时间和空间要求，没记住
	 */
	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		while(in.hasNextLine()){
			String str=in.next();
			unzip(str);
		}
		in.close();
	}
	private static void unzip(String str){
		long begin=System.currentTimeMillis();
		String[] rt=new String[255];
		for(int i=0;i<255;i++){
			rt[i]="0";
		}
		int k=0;
		int len=str.length();
		int m=0;
		while(k<len){
			char ch=str.charAt(k);
			char pre=0;
			if(k>0) pre=str.charAt(k-1);
			if(str.charAt(0)=='0'){
				System.out.println("!error");
				return;
			}
			if(ch>=48&&ch<=57){//数字
				m=m*10+Integer.valueOf(String.valueOf(ch));
				k++;
			}else if(ch>=97&&ch<=122){
				//小写字母
				if(pre>=48&&pre<=57){//前一个是数字
					if(m<3) {
						System.out.println("!error");
						return;
					}
					rt[ch]=String.valueOf(m);
					m=0;
				}else {//前一个是字母，出现一次叠加一次
					if(Integer.valueOf(rt[ch])>=2){
						System.out.println("!error");
						return;
					}
					rt[ch]=(Integer.valueOf(rt[ch])+1)+"";
				}
				k++;
			}else{
				System.out.println("!error");
				return;
			}
		}
		//输出字符串
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<rt.length;i++){
			if(Integer.valueOf(rt[i])>0){
				int num =Integer.valueOf(rt[i]);
				String s=String.valueOf((char)i);
				/*
				 * num:字符个数
				 * str:字符
				 */
				sb.append(generateStr(num,s));
			}
		}
		System.out.println(sb.toString());
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-begin));
	}
	private static String generateStr(int num,String str){
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<num;i++){
			sb.append(str);
		}
		return sb.toString();
	}
}
