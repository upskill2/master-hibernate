package com.s13.repository;

import com.s13.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource (path = "students")
public interface StudentDaoJPA extends PagingAndSortingRepository<Student, Long> {

    List<Student> findByFirstNameOrderByLastNameDesc (String firstName);

    @Query ("select s from Student s where s.firstName like '%John%'")
    List<Student> findByLikeName ();

    @Query (value = "SELECT s.id, s.email, s.first_name, s.last_name, s.passport_id, '1' as workstation_id  FROM Student s where s.first_name like '%John%'",
            nativeQuery =
            true)
    List<Student> findByLikeNameNative ();

}
