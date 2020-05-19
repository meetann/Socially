package com.io.socially.Repository;

import com.io.socially.model.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonInfo, Long> {
//    PersonRepository personRepository;
//    PersonInfo personInfo;

    List<PersonInfo> findAll();

    Optional<PersonInfo> findById(Long id);

    PersonInfo save(PersonInfo personInfo);
}
