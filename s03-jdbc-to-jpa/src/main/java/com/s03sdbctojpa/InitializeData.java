package com.s03sdbctojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Component
@Profile ("h2")
public class InitializeData {

 /*   @Autowired
    private DataSource dataSource;

    @EventListener (ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource ("data-h2.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }

    @PreDestroy
    public void dropTables(){
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource ("drop-tables.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }*/
}