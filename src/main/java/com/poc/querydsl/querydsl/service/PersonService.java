package com.poc.querydsl.querydsl.service;

import com.poc.querydsl.SearchStringLexer;
import com.poc.querydsl.SearchStringParser;
import com.poc.querydsl.querydsl.entity.Person;
import com.poc.querydsl.querydsl.helper.SearchStringVisitor;
import com.poc.querydsl.querydsl.reposatiory.PersonDao;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonDao personDao;

  public List<Person> searchPerson(final String searchString) {
    final SearchStringLexer lexer = new SearchStringLexer(CharStreams.fromString(searchString));
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final SearchStringParser parser = new SearchStringParser(tokens);
    final ParseTree tree = parser.searchString();
    final SearchStringVisitor visitor = new SearchStringVisitor();
    final BooleanExpression predicate = visitor.visit(tree);
    return personDao.search(predicate);
  }
}
