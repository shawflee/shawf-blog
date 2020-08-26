package com.shawf;


import com.shawf.entity.Tag;

import java.util.*;
import javax.swing.tree.TreeNode;


/**
 * @author shawf_lee
 * @date 2020/5/16 16:15
 * Content:
 */
public class Test0 {
    //static int[] a = new int[]{0,0,0};
    public static void main(String[] args) {
        //String str = "PAYPALISHIRING";
        //int num=3;
        // convert(str,num);

//        String s="abc";
//        String t="ahbgdc";
//        if(isSubsequence(s,t)) {
//            System.out.println("true");
//        }else {
//            System.out.println("false");
//        }
        //int[] a2 = new int[]{0,0,0};
        //new Test0().arrayTest(a2);
//        List<Integer> a2 = new ArrayList<>();
//        a2.add(0);
//        a2.add(0);
//        new Test0().listTest(a2);
//        for(int a1 : a2){
//            System.out.println("a1 = " + a1);
//        }

        String a2 = "0";
        String a1 = a2;
       // new Test0().strTest(a1);
        a1 = "1";
        System.out.println(a2);
        System.out.println(a1);


    }

    private void strTest(String a2) {
        a2 = "1";
    }

    public  static Long A(){
        Tag tag=new Tag();
        tag.setId(1L);
        B(tag);
        return  tag.getId();
//      System.out.printf(a);

    }

    private static void B(Tag tag) {
        tag.setId(2L);
    }

    public static String convert(String s, int numRows) {
        if(s==null||numRows==0){
            return null;
        }
        if(numRows==1){
            return s;
        }
        StringBuilder res=new StringBuilder();
        List<String> resTemp=new ArrayList<>();
        for(int i=0;i<numRows;i++){
            resTemp.add("");
        }
        int t=2*numRows-2;
        for(int j=0; j<s.length(); j++){
            int temp=j%t;
            if(temp<numRows){
                resTemp.add(temp,resTemp.get(temp)+String.valueOf(s.charAt(j)));
            }else{
                resTemp.add(t-temp,resTemp.get(t-temp)+String.valueOf(s.charAt(j)));
            }
        }
        for(String list:resTemp){
            System.out.println(list);
            res.append(list);
        }
        return res.toString();
    }

    //s是否是t的子序列
    public static boolean isSubsequence(String s, String t) {
        for(int i=0,j=0;i<s.length()&&j<t.length();){
            if(s.charAt(i)==t.charAt(j)){
                i++;
            }
            j++;
            if(i==s.length()){
                return true;
            }
        }
        return false;
    }

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int ldepth=maxDepth(root.getChildAt(0));
        int rdepth=maxDepth(root.getChildAt(1));
        return Math.max(ldepth,rdepth)+1;
    }

    public void arrayTest(int [] a){
        a[0] = 1;
        a[1] = 1;
    }

    public void listTest(List<Integer> a){
        a.set(0,1);
        a.set(1,1);
    }
}
