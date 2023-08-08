package com.hibernate;

import com.hibernate.entity.Person;
import com.hibernate.repository.PersonJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {

    @Autowired
    private PersonJpaRepository personJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    @Profile("mysqldb")
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data-mysqldb-no-create-table.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
    @Bean
    @Profile("h2")
    public DataSourceInitializer dataSourceInitializerH2(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data-h2.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Override
    public void run(final String... args) throws Exception {
        //find all
        log.info("All users -> {}");
        personJpaRepository.findAll().forEach(System.out::println);
        log.info("All users by custom Mapper-> {}");

        //find by id
        int lastId = personJpaRepository.findAll().get(personJpaRepository.findAll().size() - 1).getId();
        log.info("User id 10001 -> {}", personJpaRepository.findById(lastId));

        //delete by id
        log.info("Deleting {} -> No of Rows Deleted - 1",lastId );
        personJpaRepository.deleteById(lastId);

        //update
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "1980-08-02 20:21:04";
        Person person1 = new Person(4, "Update", "Update",
                Timestamp.valueOf(LocalDateTime.parse(str, formatter)));
        log.info("Updating 10003 -> {}", personJpaRepository.updatePerson(person1));

        //insert
        Person person = new Person("Insert", "Insert", Timestamp.valueOf(LocalDateTime.now()));
        log.info("Inserting -> {}", personJpaRepository.insertPerson(person));
    }

}
