package com.io.socially.Controller;


import com.io.socially.Repository.PersonRepository;
import com.io.socially.model.Person;
import com.io.socially.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

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
        List<Person> personList = new ArrayList<>();

        try{
            personInfoList = personRepository.findAll();
            if(personInfoList.isEmpty()){
                return new ResponseEntity<>(personInfoList,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personInfoList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
