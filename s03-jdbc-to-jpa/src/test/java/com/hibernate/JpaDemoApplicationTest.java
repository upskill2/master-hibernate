package com.hibernate;

import com.hibernate.entity.Person;
import com.hibernate.repository.PersonJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class JpaDemoApplicationTest {

    @MockBean
    PersonJpaRepository personJpaRepository;

    @Test
    public void contextLoads() {

        when(personJpaRepository.findAll()).thenReturn(Collections.singletonList(new Person()));
        when(personJpaRepository.findById(anyInt())).thenReturn(new Person());

    }

}