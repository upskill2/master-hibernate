package com.hibernate;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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