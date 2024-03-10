package com.poc.querydsl.querydsl.service;

import com.poc.querydsl.SearchStringLexer;
import com.poc.querydsl.SearchStringParser;
import com.poc.querydsl.querydsl.entity.Person;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  List<Person> searchPerson(final String searchString) {
    final SearchStringLexer lexer = new SearchStringLexer(CharStreams.fromString(searchString));
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final SearchStringParser parser = new SearchStringParser(tokens);
    final ParseTree tree = parser.searchString();
    return null;

  }
}
