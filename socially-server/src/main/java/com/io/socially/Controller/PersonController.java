package com.io.socially.Controller;


import com.io.socially.Repository.PersonRepository;
import com.io.socially.model.PersonInfo;
import com.io.socially.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@CrossOrigin("*")
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;
    PersonService personService = new PersonService();
    ResponseEntity responseEntity;

    @PostMapping("/create")
    public ResponseEntity<PersonInfo> createPersonInfo(@RequestBody PersonInfo personInfo) {
        try{
            responseEntity =new ResponseEntity<PersonInfo>(personRepository.save(personInfo), HttpStatus.CREATED);
            return responseEntity;
        }
        catch (Exception e){
            return responseEntity = new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonInfo>> getAllInfo(){
        List<PersonInfo> personInfoList = new ArrayList<>();
        List<PersonInfo> resultList = new ArrayList<>();

//        try{
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
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
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


}
