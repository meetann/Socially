package com.io.socially.service;

import com.io.socially.model.PersonInfo;

import java.util.List;

public class PersonService {

    public void setChildren(PersonInfo person, List<PersonInfo> personList){
        for(PersonInfo p: personList){
            if(p.getParentId()==person.getID()){
                setChildren(p,personList);
                person.addChild(p);
            }
        }
    }

}
