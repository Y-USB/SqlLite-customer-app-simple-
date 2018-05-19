package com.example.yusub_x.finalwork;

public class model {
    private String name;
    private String surname;
    private int userID;
    private String father;
    private int age;
    private String gender;
    private String education;
    private String operator;
    private String number;
    public model(){

    }


    public void setFather(String father) {
        this.father = father;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFather() {
        return father;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getEducation() {
        return education;
    }

    public String getGender() {
        return gender;
    }

    public String getNumber() {
        return number;
    }

    public String getOperator() {
        return operator;
    }

}
