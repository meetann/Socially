package com.io.socially.Controller;


import com.io.socially.Repository.PersonRepository;
import com.io.socially.model.Person;
import com.io.socially.model.PersonInfo;
import com.io.socially.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;
    PersonService personService = new PersonService();
    ResponseEntity responseEntity;
    PersonInfo person = new PersonInfo();
    PersonInfo personResult;

    @PostMapping("/create")
    public ResponseEntity<PersonInfo> createPersonInfo(@RequestBody PersonInfo personInfo) {
        try{
            person = personRepository.save(personInfo);
            if(person.getParentId() == 0) {
                person.setPath(String.valueOf(person.getID()));
            }
            else {
                personResult = personRepository.findById(person.getParentId());
                person.setPath(personResult.getPath()+"->"+ person.getID());
            }
            responseEntity =new ResponseEntity<PersonInfo>(personRepository.save(person), HttpStatus.CREATED);

            return responseEntity;
        }
        catch (Exception e){
            return responseEntity = new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getChildrenById/{id}")
    public ResponseEntity<List<PersonInfo>> getChildrenById(@PathVariable long id){
        List<PersonInfo> personInfoList = new ArrayList<>();
//        try{
            personInfoList = personRepository.findAllChildren(id);
            String[] path;
        boolean match = false;
            List<PersonInfo> resultList = new ArrayList<>();
        List<PersonInfo> childList = new ArrayList<>();
        PersonInfo parent = new PersonInfo();
        Map<Long,PersonInfo> map = new HashMap<>();

        //Save all the person from the personInfoList into the map
            for(PersonInfo personInfo: personInfoList){
                map.put(personInfo.getID(),personInfo);
            }

            //Split path and create path array
            for(PersonInfo personInfo: personInfoList){
                path = personInfo.getPath().split("->");

                //Create "parent" of current personInfo
                long parentId = personInfo.getParentId();
                if(parentId!=0) {
                    parent = map.get(parentId);

                    //Check if parentId exists in path array
                    for (String s : path) {
                        if (Integer.parseInt(s) == personInfo.getParentId()) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    //childList contains potential children of parent
                    if(match){
                        childList.add(personInfo);
//                   map.put(parentId,parent);
//                   map.put(personInfo.getID(),personInfo);
                        parent.setChildren(childList);
                    }
                    else{
                        childList = new ArrayList<>();
                    }

                        }
            }
            return new ResponseEntity<>(resultList, HttpStatus.OK) ;
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/getAllPersonInfo")
    public ResponseEntity<List<PersonInfo>> getAllPersonInfo() {
        List<PersonInfo> personInfoList = new ArrayList<>();
        try{
        personInfoList = personRepository.findAll();
        return new ResponseEntity<>(personInfoList, HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //******************************** DON'T GO BEYOND THIS ******************************************

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<PersonInfo>> getById(@PathVariable long id){
        List<PersonInfo> personInfoList = new ArrayList<>();
        try {
            personInfoList = personRepository.findAll();
            List<PersonInfo> parentList = new ArrayList<>();
            for(PersonInfo p : personInfoList){
//                System.out.println(p.getName());
                if(p.getID()==id){
                    personService.setChildren(p, personInfoList);
                    parentList.add(p);
                }
            }
            return new ResponseEntity<>(parentList, HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<PersonInfo>> getAllInfo(){
        List<PersonInfo> personInfoList = new ArrayList<>();
        List<PersonInfo> resultList = new ArrayList<>();

        try{
            personInfoList = personRepository.findAll();

            for(PersonInfo p : personInfoList){
                if(p.getParentId()==0) {
                    personService.setChildren(p, personInfoList);
                    resultList.add(p);
                }
            }
            if(personInfoList.isEmpty()){
                return new ResponseEntity<>(resultList,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByParentId/{parentId}")
    public ResponseEntity<List<PersonInfo>> getByParentId(@PathVariable int parentId){
        List<PersonInfo> personInfoList = new ArrayList<>();
        try {
            personInfoList = personRepository.findAll();
            List<PersonInfo> parentList = new ArrayList<>();
            for(PersonInfo p : personInfoList){
//                System.out.println(p.getName());
                if(p.getParentId()==parentId){
                    personService.setChildren(p, personInfoList);
                    parentList.add(p);
            }
            }
            return new ResponseEntity<>(parentList, HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getAllPerson")
    public ResponseEntity<List<PersonInfo>> findAllPerson(){
        List<PersonInfo> personInfoList = new ArrayList<>();
        List<PersonInfo> resultList = new ArrayList<>();

            personInfoList = personRepository.findAllPerson(0);
            return new ResponseEntity<>(personInfoList, HttpStatus.OK);

    }

}
