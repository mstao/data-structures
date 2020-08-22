package me.mingshan.tree;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.nio.charset.Charset;


public class Test {

//遍历文件

  public static void fileList(File file) {

    File rootFile = file;

    File[] files = rootFile.listFiles();

    if (files != null) {

      for (File f : files) {

        if (!f.isDirectory()) {

          codeConvert(f);

        }

        System.out.println(f.getPath());

        fileList(f);//递归调用子文件夹下的文件

      }

    }

  }

//另一种展示方式，自己玩的与实现功能无关

  public static void fileList1(File file, int node) {

    node++;

    File rootFile = file;

    File[] files = rootFile.listFiles();

    if (files != null) {

      for (File f : files) {

        for (int i = 0; i < node; i++) {

          if (i == node - 1) {

            System.out.print("|-");

          } else System.out.print(" ");

        }

        System.out.println(f.getName());

        fileList1(f, node);

      }

    }

  }


  public static void main(String[] args) {

    File file = new File("D:\\c\\demo");

    Test.fileList(file);

//GBKtoUTF8.codeConvert(file);

  }

  public static void codeConvert(File file) {

    try {

      BufferedReader br = new BufferedReader(new InputStreamReader

          (new FileInputStream(file), Charset.forName("GBK")));

      StringBuilder sb = new StringBuilder();

      String str;

      while ((str = br.readLine()) != null) {

        sb.append(str);

        sb.append("\n");

      }

      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter

          (new FileOutputStream(file), Charset.forName("UTF-8")));

      bw.write(sb.toString());

      bw.flush();

      bw.close();

//br.close();

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}