package com.shawf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ShawfBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShawfBlogApplication.class, args);
		//new ShawfBlogApplication().test();
	}
	private void test(){
		Scanner scanner = new Scanner(System.in);
		String str=scanner.nextLine();
		String num[]=str.split(" ");
		int []flag=new int[24];
		for(int i=0;i<num.length;i++){
			int a=Integer.parseInt(num[i]);
			flag[a]=1;
		}
		for(int i=0;i<flag.length;i++){
			if(flag[i]==0){
				System.out.println(i);
			}
		}
	}



}
