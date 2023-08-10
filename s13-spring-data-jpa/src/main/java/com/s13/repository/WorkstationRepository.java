package com.s13.repository;

import com.s13.entity.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Cacheable;

@RepositoryRestResource(path = "workstations")
@Cacheable
public interface WorkstationRepository extends JpaRepository<Workstation, Long> {
}
