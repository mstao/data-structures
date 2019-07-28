package me.mingshan.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mingshan
 */
public class Test {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Student s1 = new Student();
        s1.setId("111");
        s1.setName("qq");
        s1.setClassName("c1");


        Student s2 = new Student();
        s2.setId("2222");
        s2.setName("qq12");
        s2.setClassName("c1");

        Student s3 = new Student();
        s3.setId("3333");
        s3.setName("ww");
        s3.setClassName("c3");

        students.add(s1);
        students.add(s2);
        students.add(s3);

        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (Student student : students) {
            String s = map1.get(student.getName());
            if (s == null) {
                map1.put(student.getName(), student.getClassName());
            } else if (!s.equals(student.getClassName())) {
                throw new IllegalArgumentException("1, name = " + student.getName() + ", className = " + student.getClassName());
            }

            String s4 = map2.get(student.getClassName());
            if (s4 == null) {
                map2.put(student.getClassName(), student.getName());
            } else if (!s4.equals(student.getName())) {
                throw new IllegalArgumentException("2, name = " + student.getName() + ", className = " + student.getClassName());
            }
        }
    }


    static class Student {
        private String id;
        private String name;
        private String className;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
