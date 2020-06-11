package com.example.gongxiang.bean;

/**
 * 学生实体类
 * @author : autumn_leaf
 */
public class Userfile {

    //学号
    private String stuNumber;
    //姓名
    private String stuName;
    private String sex;
    private String name;
    private byte[] picture;

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex(){return sex;}

    public void setSex(String sex){this.sex=sex;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
