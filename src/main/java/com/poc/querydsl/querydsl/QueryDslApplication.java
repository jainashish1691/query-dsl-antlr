package com.poc.querydsl.querydsl;

import com.poc.querydsl.querydsl.entity.Person;
import com.poc.querydsl.querydsl.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class QueryDslApplication implements CommandLineRunner {

  private final PersonService personService;

  public static void main(String[] args) {
    SpringApplication.run(QueryDslApplication.class, args);
  }

  @Override
  public void run(String... args) {
    List<Person> list = personService.searchPerson(
        "((firstname = 'ASHISH' AND surname = 'JAIN') OR (address = 'USA' AND surname = 'JAIN')) AND id = '1'");
    log.info("result {} ", list);

  }
}
