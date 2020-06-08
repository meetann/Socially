package com.io.socially.model;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PersonInfo")
public class PersonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private String name;

    private int age;

    private int parentId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    public List<PersonInfo> getChildren() {
        return children;
    }

    public void setChildren(List<PersonInfo> children) {
        this.children = children;
    }

    @Transient
    public List<PersonInfo> children;

//    public List<PersonInfo> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<PersonInfo> children) {
//        this.children = children;
//    }

    public long getID() {
        return ID;
    }

    public PersonInfo() {
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

//    public PersonInfo getParent() {
//        return parent;
//    }
//
//    public void setParent(PersonInfo parent) {
//        this.parent = parent;
//    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

//    public List<PersonInfo> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<PersonInfo> children) {
//        this.children = children;
//    }

    public void addChild(PersonInfo node){
        if(children==null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }

    public boolean hasChild(PersonInfo node){
        if(node.children.isEmpty()){
            return false;
        }
        else return true;
    }



}