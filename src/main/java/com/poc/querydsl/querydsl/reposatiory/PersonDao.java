package com.poc.querydsl.querydsl.reposatiory;


import com.poc.querydsl.querydsl.entity.Person;
import com.poc.querydsl.querydsl.entity.QPerson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PersonDao {

  private final EntityManager entityManager;

  public void test() {
    final JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    final List<Person> ashish = queryFactory.select(QPerson.person).from(QPerson.person)
        .where(QPerson.person.firstname.eq("ASHISH")).fetch();
    System.out.println(ashish);
  }
}
