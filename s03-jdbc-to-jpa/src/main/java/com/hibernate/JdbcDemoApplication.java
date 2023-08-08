package com.hibernate;

import com.hibernate.dao.PersonJdbcDao;
import com.hibernate.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

//@SpringBootApplication
@Slf4j
public class JdbcDemoApplication implements CommandLineRunner {
    @Autowired
    private PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(JdbcDemoApplication.class, args);
    }

    @Bean
    @Profile("mysqldb")
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data-mysqldb.sql"));
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
        personJdbcDao.findAll().forEach(System.out::println);
        log.info("All users by custom Mapper-> {}");
        personJdbcDao.findAllByCustomMapper().forEach(System.out::println);

        //find by id
        int lastId = personJdbcDao.findAll().get(personJdbcDao.findAll().size() - 1).getId();
        log.info("User id 10001 -> {}", personJdbcDao.findById(lastId));

        //find by name and location
        log.info("User name and location -> {}", personJdbcDao.findByNameAndLocation("Taras", "Kyiv"));

        //delete by id
        log.info("Deleting 10002 -> No of Rows Deleted - {}", personJdbcDao.deleteById(2));

        //delete by id or name
        log.info("Deleting 10003 -> No of Rows Deleted - {}", personJdbcDao.deleteByIdOrName(3, "Taras"));

        //update

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "1980-08-02 20:21:04";
        Person person1 = new Person(lastId, "Update", "Update",
                Timestamp.valueOf(LocalDateTime.parse(str, formatter)));
        log.info("Updating 10003 -> {}", personJdbcDao.updatePerson(person1));

        //insert
        Person person = new Person("Insert", "Insert", Timestamp.valueOf(LocalDateTime.now()));
        log.info("Inserting -> {}", personJdbcDao.insertPerson(person));
    }

}
