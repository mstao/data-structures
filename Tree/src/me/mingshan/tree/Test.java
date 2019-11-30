package me.mingshan.tree;

public class Test {


    @org.junit.Test
    public void test1() {
        //var a = "Date,Friendly URL Performance,Visits,Page Views,Form Success (s37),Add to Cart,Revenue,Orders,Bounces,Unique Visitors,Entries,Registration (s89)".startsWith("Date");

        var s = "﻿计划名称,单元名称创意名称标题名称推广";
        var b = s.startsWith("计划名称");

        //System.out.println("a："+ a);
        System.out.println("b："+ b);
    }
}
