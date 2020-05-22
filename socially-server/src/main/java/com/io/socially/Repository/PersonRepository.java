package com.io.socially.Repository;

import com.io.socially.model.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonInfo, Long> {
//    PersonRepository personRepository;
//    PersonInfo personInfo;

    List<PersonInfo> findAll();

//    PersonInfo findById(@Param("id")int id);

    Optional<PersonInfo> findById(Long id);

    @Query("SELECT p FROM PersonInfo p where p.parentId >:id ORDER BY p.parentId")
    List<PersonInfo> findByParentId(@Param("id") int id);

    PersonInfo save(PersonInfo personInfo);
}
