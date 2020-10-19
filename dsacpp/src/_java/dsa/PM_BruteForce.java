/******************************************************************************************
 * Data Structures in C++
 * ISBN: 7-302-33064-6 & 7-302-33065-3 & 7-302-29652-2 & 7-302-26883-3
 * Junhui DENG, deng@tsinghua.edu.cn
 * Computer Science & Technology, Tsinghua University
 * Copyright (c) 2003-2019. All rights reserved.
 ******************************************************************************************/

/*
 * 串模式匹配：蛮力算法
 * 若返回位置i > length(T) - length(P)，则说明失配
 * 否则，i为匹配位置
 */
import dsa.*;
import java.io.*;

public class PM_BruteForce {
   //////////////////////////////////////////////////////////////////////////
   // T: 0     1     .     .     .     i     i+1   .     .     .     i+j   .     .     n-1
   //    --------------------|-------------------|------------
   // P:                               0     1     .     .     .     j     .     .
   //                                  |-------------------|
   //////////////////////////////////////////////////////////////////////////
   public static int PM(String T, String P) {
      int      i;//模式串相对于文本串的起始位置
      int      j;//模式串当前字符的地址
      for (i = 0; i <= T.length() - P.length(); i++) { //文本串从第i个字符起，与
         for (j = 0; j < P.length(); j++) { //模式串的当前字符逐次比较
            if (T.charAt(i + j) != P.charAt(j))   break; //若失配，模式串右移一个字符
         }
         if (j >= P.length()) break;//找到匹配子串
      }
      return(i);
   }
}