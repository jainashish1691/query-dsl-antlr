package com.poc.querydsl.querydsl;

import com.poc.querydsl.querydsl.reposatiory.PersonDao;
import com.poc.querydsl.querydsl.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryDslApplication implements CommandLineRunner {

  @Autowired
  private PersonDao personDao;

  @Autowired
  private PersonService personService;

  public static void main(String[] args) {
    SpringApplication.run(QueryDslApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    personService.searchPerson(
        "((firstname = 'ASHISH' AND surname = 'JAIN') OR (address = 'USA' AND surname = 'JAIN')) AND id = '1'");
  }
}
