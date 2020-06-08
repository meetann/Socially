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
public interface PersonRepository extends JpaRepository<PersonInfo, Long> {
//    PersonRepository personRepository;
//    PersonInfo personInfo;

    List<PersonInfo> findAll();

    @Query("SELECT p FROM PersonInfo p where p.ID =:id")
    PersonInfo findById(@Param("id")long id);

    @Query(value = "select * from person_info where path LIKE :id%",nativeQuery = true)
    List<PersonInfo> findAllChildren(@Param("id")long id);

    @Query("SELECT p FROM PersonInfo p where p.parentId LIKE ':parentId%' ")
    PersonInfo findAllByParentId(@Param("parentId")long parentId);

    @Query("SELECT p FROM PersonInfo p where p.parentId >:id ORDER BY p.parentId")
    List<PersonInfo> findByParentId(@Param("id") int id);

    PersonInfo save(PersonInfo personInfo);

    @Query(value =
            "with recursive category_path (id, parent_id, name, age, path) as" +
                    "(select id, parent_id,name,age,name as path from person_info where parent_id = :id " +
                    "union all "+
                    "select p.id, p.parent_id, p.name, p.age, concat(ct.path, '->', p.name) "+
                    " from  category_path as ct" +
                    "  join person_info as p " +
                    "on  ct.id = p.parent_id " +
                    "  )"+
                    " select * from category_path" +
                    "  order by path", nativeQuery = true
    )
    List<PersonInfo> findAllPerson (@Param("id") int id);
}
