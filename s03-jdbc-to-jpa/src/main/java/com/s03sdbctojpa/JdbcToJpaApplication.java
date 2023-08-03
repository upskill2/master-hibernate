package com.s03sdbctojpa;

import com.s03sdbctojpa.dao.PersonJdbcDao;
import com.s03sdbctojpa.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@Slf4j
public class JdbcToJpaApplication implements CommandLineRunner {
    @Autowired
    private PersonJdbcDao personJdbcDao;

    public static void main (String[] args) {
        SpringApplication.run (JdbcToJpaApplication.class, args);
    }

    @Bean
    @Profile ("mysqldb")
    public DataSourceInitializer dataSourceInitializer (@Qualifier ("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator ();
        resourceDatabasePopulator.addScript (new ClassPathResource ("/data-mysqldb.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer ();
        dataSourceInitializer.setDataSource (dataSource);
        dataSourceInitializer.setDatabasePopulator (resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    @Profile ("h2")
    public DataSourceInitializer dataSourceInitializerH2 (@Qualifier ("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator ();
        resourceDatabasePopulator.addScript (new ClassPathResource ("/data-h2.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer ();
        dataSourceInitializer.setDataSource (dataSource);
        dataSourceInitializer.setDatabasePopulator (resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Override
    public void run (final String... args) throws Exception {
        log.info ("All users -> {}");
        personJdbcDao.findAll ().forEach (System.out::println);
        log.info ("User id 10001 -> {}", personJdbcDao.findById (personJdbcDao.findAll ().get (0).getId ()));
        log.info ("User name and location -> {}", personJdbcDao.findByNameAndLocation ("Taras", "Kyiv"));
        log.info ("Deleting 10002 -> No of Rows Deleted - {}", personJdbcDao.deleteById (2));
        log.info ("Deleting 10003 -> No of Rows Deleted - {}", personJdbcDao.deleteByIdOrName (3, "Taras"));

        Person person = new Person (5, "Taras", "Kyiv", Timestamp.valueOf (LocalDateTime.now ()));
        log.info ("Inserting 10004 -> {}", personJdbcDao.insertPerson (person));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss");
        String str = "1980-08-02 20:21:04";
        Person person1 = new Person (personJdbcDao.findAll ().get (1).getId (), "Olia", "Zhytomyr",
                Timestamp.valueOf (LocalDateTime.parse (str, formatter)));
        log.info ("Updating 10003 -> {}", personJdbcDao.updatePerson (person1));

    }


}
