package com.s03sdbctojpa;

import com.s03sdbctojpa.entity.Person;
import com.s03sdbctojpa.repository.PersonJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
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