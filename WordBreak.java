package com.solution;

import java.util.ArrayList;

/**
 * @author shkstart
 * @create 2020-04-07 17:28
 */
public class WordBreak {
    public static void main(String[] args) {
        System.out.println(wordBreakRec("ilikesamsung"));
        System.out.println(wordBreakDP("ilikesamsung"));
        wordBreakPrintAll("ilikesamsung");
        System.out.println(wordBreakRec("samsungandmango"));
        System.out.println(wordBreakDP("samsungandmango"));
        wordBreakPrintAll("samsungandmango");
        System.out.println(wordBreakRec("samsungandmangok"));
        System.out.println(wordBreakDP("samsungandmangok"));
        wordBreakPrintAll("samsungandmangok");
    }
    //recursive
    public static boolean wordBreakRec(String s){
        int len = s.length();
        if(len == 0){
            return true;
        }

        // DFS
        // 尝试所有长度从1到大小的前缀
        for(int i=1; i<=len; i++){
            /* dictionaryContains的参数是s.substring（0，i）
               s.substring（0，i），它是长度为“i”的前缀（输入字符串的前缀）。
               我们首先检查当前前缀是否在字典中。然后我们递归地检查剩余的字符串s.substring（i），
               它是长度大小的后缀
            */
            if(dictionaryContains(s.substring(0, i)) && wordBreakRec(s.substring(i))){
                return true;
            }
        }

        // 如果我们试过所有的前缀但没有一个有效
        return false;
    }

    // 打印出所有组合，因为要打印出所有组合而不只是判断能否，所以只能用dfs
    public static void wordBreakPrintAll(String s){
        ArrayList<String> al = new ArrayList<String>();
        wordBreakRec2(s, al);
    }

    public static void wordBreakRec2(String s, ArrayList<String> al){
        int len = s.length();
        if(len == 0){
            System.out.println(al);
            return;
        }

        // DFS
        for(int i=1; i<=len; i++){
            String substr = s.substring(0, i);
            if(dictionaryContains(substr)){
                al.add(substr);
                wordBreakRec2(s.substring(i), al);
                al.remove(al.size()-1);
            }
        }
    }

    private static boolean dictionaryContains(String word){
        String[] dict = {"mobile","samsung","sam","sung","man","mango",
                "icecream","and","go","i","like","ice","cream"};
        for(int i=0; i<dict.length; i++){
            if(dict[i].equals(word)){
                return true;
            }
        }
        return false;
    }

    /*
       如果字符串可以分割成空格，则返回true，否则返回false
     */
    public static boolean wordBreakDP(String s){
        int len = s.length();
        if(len == 0){
            return true;
        }

        /*
          创建DP表以存储子问题的结果。如果s[0..i-1]可以分割成字典单词，则wb[i]值为true，否则为false
        */
        boolean[] wb = new boolean[len+1];
        for(int i=1; i<=len; i++){
            /*
              如果wb[i]为false，则检查当前前缀是否可以使其为true。当前前缀是“s.substring（0，i）”
            */
            if(wb[i]==false && dictionaryContains(s.substring(0, i))){
                wb[i] = true;
            }

            /*
               wb[i]为真，然后检查从第（i+1）个字符开始的所有子字符串并存储其结果。
            */
            if(wb[i] == true){
                if(i == len){		// If we reached the last prefix
                    return true;
                }
                for(int j=i+1; j<=len; j++){
                    /*
                     更新wb[j]如果为false并且可以更新，请注意传递给dictionaryContents（）
                     的参数是从索引“i”到索引“j-1”的子字符串
                    */
                    if(wb[j]==false && dictionaryContains(s.substring(i, j))){
                        wb[j] = true;
                    }
                    if(j==len && wb[j]==true){	// If we reached the last character
                        return true;
                    }
                }
            }
        }
        /*
           如果我们试过所有的前缀但没有一个有效
         */
        return false;
    }
}
