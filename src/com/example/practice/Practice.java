package com.example.practice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Practice {
	
	/*
	 *  #################################################################################
	            题目描述 
		输入任意个字符串，将其中的小写字母变为大写，大写字母变为小写，其他字符不用处理； 
		输入描述: 
		任意字符串：abcd12#%XYZ 
		输出描述: 
		输出字符串：ABCD12#%xyz
		
		示例1： 
		输入： abcd12#%XYZ 
		输出： ABCD12#%xyz
		#################################################################################
	 */
	
	
	public static String changeStr(String str){
		
		char[] ch=str.toCharArray();
		int a='a'-'A';
		for(int i= 0;i<ch.length;i++){
			if('a'<=ch[i]&&ch[i]<='z'){
				ch[i]=(char) (ch[i]-a);//小寫變大寫
			}else if('A'<=ch[i]&&ch[i]<='Z'){
				ch[i]=(char) (ch[i]+a);//大寫變小寫
			}
		}
		
		
		return new String(ch);
	}
	
	/**
	 * @param m 背包的重量
	 * @param n 物品的個數
	 * @param w 物品的重量
	 * @param v	物品的價值
	 * @return
	 */
	
	/*
	 * #################################################################################
	            小偷来到了一个神秘的王宫，突然眼前一亮，发现5个宝贝，每个宝贝的价值都不一样，且重量也不一样，但是小偷的背包携带重量 
		有限，所以他不得不在宝贝中做出选择，才能使偷到的财富最大，请你帮助小偷计算一下。
		
		输入描述: 
		宝贝价值：6,3,5,4,6 
		宝贝重量：2,2,6,5,4 
		小偷背包容量：10 
		输出描述: 
		偷到宝贝的总价值：15
		
		示例1 
		输入 
		6,3,5,4,6 
		2,2,6,5,4 
		10 
		输出 
		15
		#################################################################################
	 */
	public static int[][] backpack_solution(int m,int n,int[] w,int[] v){
		//定義一個二維數組f[i][v]表示前i件物品恰放入一個重量為m的背包可以獲得的最大價值
		int f[][]=new int[n+1][m+1];
		for(int i=0;i<n+1;i++)
			f[i][0]=0;
		for(int j=0;j<m+1;j++)
			f[0][j]=0;
		for(int i=1;i<n+1;i++){
			for(int j=1;j<m+1;j++){
				/*
				 * 當第i件物品的重量w[i-1]小於背包容量j時，f[i][j]為下列兩種情況之一：
				 * (1)物品i不放入背包中，所以f[i][j]為f[i-1][j]的值
				 * (2)物品i放入背包中，則背包剩餘重量為j-w[i-1],所以f[i][j]為f[i-1][j-w[i-1]]+v[i-1]
				 */
				if(w[i-1]<=j){
					//放的下，則比較放和不放哪個價值大,取大的
					if(f[i-1][j]<f[i-1][j-w[i-1]]+v[i-1])
						f[i][j]=f[i-1][j-w[i-1]]+v[i-1];
					else
						f[i][j]=f[i-1][j];
						
				}else{
					f[i][j]=f[i-1][j];
				}
			}
		}
		return f;
	}
	
	/*
	 * 按要求分解字符串，输入两个数M，N；
	 * M代表输入的M串字符串，N代表输出的每串字符串的位数，不够补0。
	 * 例如：输入2,8， “abc” ，“123456789”，
	 * 则输出为 
	 * 	"abc00000”,“12345678“,”90000000"；
	 */
	
	public static String [] trans(String str,int b){
		int len=str.length();
		int part=0;//part表示最終輸出幾串字符串
		int yu=len%b;
		if(yu==0)
			part=len/b;
		else
			part=len/b+1;
		String [] rt=new String[part];//最終輸出的字符串
		//開始處理
		for(int i=0;i<part;i++){
			int begin=i*b;
			int end=0;
			if(i<part-1) {
				end=i*b+b;
			}else{
				end=str.length();
			}
			String temp=str.substring(begin,end);
			if(temp.length()==b){
				rt[i]=temp;
			}
			else{
				StringBuilder sb=new StringBuilder();
				sb.append(temp);
				for(int j=0;j<b-temp.length();j++){
					sb.append("0");
				}
				rt[i]=sb.toString();
				
			}
					
		}
		return rt;
	}
	
	/*
		拼音转数字 
		输入是一个只包含拼音的字符串，请输出对应的数字序列。转换关系如下： 
		描述： 拼音 yi er san si wu liu qi ba jiu 
		阿拉伯数字 1 2 3 4 5 6 7 8 9 
		输入字符只包含小写字母，所有字符都可以正好匹配
		
		运行时间限制：无限制 
		内存限制： 无限制 
		输入： 一行字符串，长度小于1000 
		输出： 一行字符（数字）串 
		样例输入： yiersansi 
		样例输出： 1234
	 */
	
	public static String PinYinToNum(String str){
		StringBuffer sb=new StringBuffer();
		int i=0;
		int len=str.length();
		while(i<len){
			switch(str.charAt(i)){
				case 'y':
					sb.append("1");
					i=i+2;
					break;
				case 'e':
					sb.append("2");
					i=i+2;
					break;
				case 's':
					if(str.charAt(i+1)=='a'){
						sb.append("3");
						i=i+3;
						break;
					}else{
						sb.append("4");
						i=i+2;
						break;
					}
				case 'w':
					sb.append("5");
					i=i+2;
					break;
				case 'l':
					sb.append("6");
					i=i+3;
					break;
				case 'q':
					sb.append("7");
					i=i+2;
					break;
				case 'b':
					sb.append("8");
					i=i+2;
					break;
				case 'j':
					sb.append("9");
					i=i+3;
					break;
			}
		}
		return sb.toString();
	}
	
	/*
	 	去除重复字符并排序 
		运行时间限制：无限制 
		内容限制： 无限制 
		输入： 字符串 
		输出： 去除重复字符并排序的字符串 
		样例输入： aabcdefff 
		样例输出： abcdef
	 */
	public static String strSet(String str){
		//java char是16為 可以存儲256個元素
		// 每一個位置元素可轉換成對應的assic碼,
		// 每一個字符也對應一個assic碼，
		//assic碼可變成對應的int
		//若字符出現，則字符所在位置的元素標示為1，否則為0
		//最後輸出char中值為1的下標，根據assic轉換為相應的字符
		char [] ch=new char[255];
		char [] in=str.toCharArray();
		for(int i=0;i<in.length;i++){
			int temp=in[i];
			if(ch[temp]==0)//這一步標示出現過的字符
				ch[temp]=(char)1;//注意此處做強制類型轉換 否則可能會亂碼
		}
		//接下來輸出出現過的字符
		StringBuilder sb=new StringBuilder();
		for(int j=0;j<ch.length;j++){
			if(ch[j]==1)
				sb.append(String.valueOf((char)j));
		}	
		
		return sb.toString();
	}

	/*
	 * 
		第三题：等式变换
		输入一个正整数X，在下面的等式左边的数字之间添加+号或者-号，使得等式成立。
		1 2 3 4 5 6 7 8 9 = X
		比如：
		12-34+5-67+89 = 5
		1+23+4-5+6-7-8-9 = 5
		请编写程序，统计满足输入整数的所有整数个数。
		输入：       正整数，等式右边的数字
		输出：       使该等式成立的个数
		样例输入：5
		样例输出：21
	 *
	 */
	public static void equealX(int goal){
		//0代表空，1代表+，2代表-,總共有8個空位，每個空位有三種選擇，則總共有3^8方種方案，分別對應00000000~22222222
		//组合这6561种情况 
		//10进制转三进制
		List<String> list=new ArrayList<>();
		for(int i=0;i<6561;i++){
			//一直除3，直到商为0，然后组合所有余数
			StringBuilder sb =new StringBuilder();
			int quotient=i/3;
			sb.append(String.valueOf(i%3));
			while(quotient!=0){
				sb.append(String.valueOf(quotient%3));
				quotient=quotient/3;
			}
			sb.reverse();
			StringBuilder sb2=new  StringBuilder();
			if(sb.length()<8){
				int len=sb.length();
				for(int j=0;j<8-len;j++){
					sb2.append("0");
				}
			}
			sb2.append(sb);
			list.add(sb2.toString());
			
		}
		List<String> rt=new ArrayList<>();
		for(String str:list){
			//根據i值生成表達式，前面不夠的補零
			//該表達式如果滿足要求則記錄
			Map<String,Object> map=calcu(str);
			if(Long.valueOf(map.get("total").toString())==Long.valueOf(goal)){
				System.out.println(map.get("total"));
				System.out.println(map.get("rt"));
				rt.add(map.get("rt").toString());
			}
		}
		System.out.println("總共有："+rt.size()+"種方案");
	}
	private static Map<String,Object> calcu(String symbol){
		int [] num=new int[]{1,2,3,4,5,6,7,8,9};
		StringBuilder sb=new StringBuilder();
		sb.append("1");
		long total=0;
		int i=0;
		while(i<8){
			switch(symbol.charAt(i)){
			case '0':
				sb.append(""+num[i+1]);
				//total=Integer.valueOf(total+""+num[i+1]);
				i++;
				break;
			case '1':
				sb.append("+"+num[i+1]);
				//total=total+num[i+1];
				i++;
				break;
			case '2':
				sb.append("-"+num[i+1]);
				//total=total-num[i+1];
				i++;	
				break;
			}
		}
		String [] s1=sb.toString().split("\\+");
		for(String ss:s1){
			String [] s2=ss.split("-");
			total+=Integer.valueOf(s2[0]);
			for(int j=1;j<s2.length;j++){
				total-=Integer.valueOf(s2[j]);
			}
		}
		Map<String,Object> map=new HashMap<>();
		map.put("total", total);
		map.put("rt", sb.toString());
		return map;
	}
	/**
	 * 
	 * @param cur 	当前要处理的数
	 * @param x 	给定的X值
	 * @param sum	已经累加的值	
	 * @param temp	等待累加的值
	 * @param equal 等待累加的等式
	 */
	//存储等式
	public static List<String> equals = new ArrayList<String>();
	public static void equealX2(int cur,int x, int sum, int temp, String equal) {
		if(cur == 10) {//从2开始处理 ，一直往上叠加,当为10时，表明前面9个数都列进来了，
			if(sum + temp == x) 
				equals.add(equal + " + " + temp + " = " + x);
			if(sum - temp == x)
				equals.add(equal + " - " + temp + " = " + x);
			return;
		}
		//不加符号
		equealX2(cur + 1, x, sum, temp * 10 + cur, equal);
		//添加＋
		//注意1前面没有符号
		if(equal != "")
			equealX2(cur + 1, x, sum + temp, cur, equal + " + " + temp);
		else
			equealX2(cur + 1, x, sum + temp, cur, equal + temp);
		//添加－
		if(equal != "")
			equealX2(cur + 1, x, sum - temp, cur, equal + " - " + temp);
	}
	
	//动态规划
	//动态方程（有点难理解）：当前种类=符号位加号+符号为减号+没有符号的种类
	//dp(before,des,n,ex)= dp(before - 1, before, res + des,1) + dp(before - 1, before, res - des,1) + dp(before - 1, before*pow(10, ex)+des, res,ex+1);
	// before: 需要判定的符号前面的数字的个数，初始为8
	// des: 需要判定的符号后面的数字，初始为9
	// res:方程右边的结果
	// ex:阶乘数，因为符号有三种可能，加号，减号，或者没有，如果没有，那么ex就用于计算当前值
	public static int equealX3(int before,int des, int res, int ex) {
		
		if(before == 0){
			if(des==res){
				return 1;
			}else{
				return 0;
			}
		}else{
			return equealX3(before-1, before, res+des, 1)+
					equealX3(before-1, before, res-des, 2)+
					equealX3(before-1, (int)(before*Math.pow(10, ex)), res, ex+1);
		}
		
	}
	
	
	/*
		 输入一个整数，输出整数的位数，倒序输出它的每一位数字（数字
	
		之间空格分开）和倒序的连续数值，而且题目限定了输入的整数不超过五位数。
		
		题目所给例子：
		
		输入：-12345
		
		输出：-5 4 3 2 1
		
		    -54321
	 */
	public static void reverseNumbers(int num){
		
		if(num>99999) return ;
		String temp=String.valueOf(num);
		StringBuilder sb=new StringBuilder();
		List<String> list=new ArrayList<>();
		int i=0;
		boolean flag=false;
		while(i<temp.length()){
			char ch =temp.charAt(i);
			if(temp.charAt(i)=='-'){
				flag=true;
				i++;
				continue;
			}
			sb.append(ch);
			list.add(String.valueOf(ch));
			i++;
		}
		if(flag){
			System.out.println("位数："+(i-1));
			System.out.println("结果1："+"-"+sb.reverse().toString());
			System.out.println("结果2：");
			for(int j=list.size()-1;j>=0;j--){
				if(j==list.size()-1) System.out.print("-"+list.get(j)+" ");
				else{
					System.out.print(list.get(j)+" ");
				}
			}
		}else{
			System.out.println(sb.reverse().toString());
			System.out.println("结果1："+sb.reverse().toString());
			System.out.println("结果2：");
			for(int j=list.size()-1;j>=0;j--){
				System.out.print(list.get(j)+" ");
			}
		}
	}
	
	
	/*
			 输入一连串整数，整数之间以空格分开,，再输入一个整数表示
		
			按照固定规则排序后的位置，然后输出重新排序后对应的这个位置的整数。题目
			
			所给排序规则为：按照每个整数的后三位进行比较大小，按从小到大排序，不足
			
			三位的按原数值参与比较。倘若按规则比较两个数值相同，则顺序为输入时的相对
			
			位置。
			
			题目所给例子：
			
			输入：1223 22 3232 2016 
			
			        ： 3
			
			输出：1223
	 */
	public static void sortNum(int [] nums,int p){
		//先写一个排序算法
		//获取整数的后三位
		int temp[] =new int[nums.length];
		for(int i=0;i<nums.length;i++){
			String str=String.valueOf(nums[i]);
			int len=str.length();
			if(len>3)
				temp[i]=Integer.valueOf(str.substring(len-3,len));
			else
				temp[i]=Integer.valueOf(str);
		}
		bubbleSort(temp);//根据后三位排序
		System.out.println(temp[p-1]);
		String rtstr=String.valueOf(temp[p-1]);
		for(int i=0;i<nums.length;i++){
			String str=String.valueOf(nums[i]);
			if(str.lastIndexOf(rtstr)>0){
				System.out.println(nums[i]); 
			}
		}
		
	}
	
	private static void bubbleSort(int [] nums){
		int len=nums.length;
		for(int i=0;i<len-1;i++){
			for(int j=0;j<len-1-i;j++){
				if(nums[j+1]<nums[j]){
					int temp=nums[j];
					nums[j]=nums[j+1];
					nums[j+1]=temp;
				}
			}
		}
		System.out.println(Arrays.toString(nums));
		
	}
	/*
	 * 给定一个字符串，里边可能包含"()"、"[]"、"{}"三种括号，
	 * 请编写程序检查该字符串中的括号是否成对出现，且嵌套关系正确。
	 *  输出：
	 *	true： 若括号成对出现且嵌套关系正确，或该字符串中无括号字符； 
	 *  false：若未正确使用括号字符。 实现时无需考虑非法输入。
	 */
	public static boolean charac(String str){
		
		//利用栈先进后出的特性
		Stack<Character> stack = new Stack<>();
		int i=0;
		while(i<str.length()){
			char ch =str.charAt(i);
			if(ch=='('||ch=='['||ch=='{')
				stack.push(ch);
			else
				if(ch==')')
					if(stack.peek()=='(')
						stack.pop();
				if(ch==']')
					if(stack.peek()=='[')
						stack.pop();
				if(ch=='}')
					if(stack.peek()=='}')
						stack.pop();
			i++;
		}
		boolean flag=false;
		if(stack.isEmpty()) flag=true;
		return flag;
	}
	
	public static void main(String[] args) {
		
		/*String str="abcd12#%XYZ";
		System.out.println(changeStr(str));*/
		
		
	/*	int []w =new int[]{2,2,6,5,4};
		int []v =new int[]{6,3,5,4,6};
		int [][] rt=backpack_solution(10, 5, w, v);
		int len=rt.length;
		for(int i=0;i<len;i++){
			for(int j=0;j<rt[i].length;j++){
				System.out.println("i="+i+",j="+j+"總價值："+rt[i][j]);
			}
		}
		System.out.println(rt[len-1][rt[len-1].length-1]);*/
//		Scanner in = new Scanner(System.in);
		/*equealX2(2, 5, 0, 1, "");
		System.out.println(equals.size());
		for (int i = 0; i < equals.size(); i++) {
			System.out.println(equals.get(i));
		}*/
		
		System.out.println(equealX3(8, 9, 5, 1));
		
		/*String str="123456789";
		String [] rt=trans(str, 8);
		for(int i=0;i<rt.length;i++){
			System.out.println(rt[i]);
		}*/
		
		/*String str="yiersansi";
		System.out.println(PinYinToNum(str));*/
		
		/*String str="aabcdefff";
		System.out.println(strSet(str));*/
		/*long curr=System.currentTimeMillis();
		equealX(5);
		long end =System.currentTimeMillis();
		System.out.println("耗时："+(end-curr));*/
		
		//reverseNumbers(-12345);
		
		/*int nums[] =new int[]{1223,22,3232,2016};
		sortNum(nums, 3);*/
	}
}
