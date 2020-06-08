package com.io.socially.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class Person {


    private long ID;

    private String name;

    private int age;

    private int parentId;

    private List<PersonInfo> children;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<PersonInfo> getChildren() {
        return children;
    }

    public void setChildren(List<PersonInfo> children) {
        this.children = children;
    }

    public Person() {
    }
}