package com.poc.querydsl.querydsl.reposatiory;


import com.poc.querydsl.querydsl.entity.Person;
import com.poc.querydsl.querydsl.entity.QPerson;
import com.querydsl.core.types.dsl.BooleanExpression;
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


  public List<Person> search(BooleanExpression predicate) {
    final JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    return queryFactory.select(QPerson.person).from(QPerson.person).where(predicate)
        .fetch();

  }
}
