package com.poc.querydsl.querydsl.helper;

import com.poc.querydsl.SearchStringBaseVisitor;
import com.poc.querydsl.SearchStringParser;
import com.poc.querydsl.querydsl.entity.QPerson;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchStringVisitor extends SearchStringBaseVisitor<BooleanExpression> {

  @Override
  public BooleanExpression visitSearchString(SearchStringParser.SearchStringContext ctx) {

    return visit(ctx.expression());
  }

  @Override
  public BooleanExpression visitExpression(SearchStringParser.ExpressionContext ctx) {
    return visit(ctx.logicalExpression());
  }

  @Override
  public BooleanExpression visitLogicalExpression(SearchStringParser.LogicalExpressionContext ctx) {
    BooleanExpression left = visit(ctx.atom(0));
    if (ctx.logicalOp() != null && ctx.atom(1) != null) {
      BooleanExpression right = visit(ctx.atom(1));
      if ("AND".equals(ctx.logicalOp().get(0).getText())) {
        return left.and(right);
      } else if ("OR".equals(ctx.logicalOp().get(0).getText())) {
        return left.or(right);
      }
    }
    return left;
  }

  @Override
  public BooleanExpression visitAtom(SearchStringParser.AtomContext ctx) {
    if (ctx.expression() != null) {
      return visit(ctx.expression());
    } else {
      return visit(ctx.comparison());
    }
  }

  @Override
  public BooleanExpression visitComparison(SearchStringParser.ComparisonContext ctx) {
    final String field = ctx.field().getText();
    final String value = ctx.value().getText().replaceAll("'", "");
    final String operator = ctx.operator().getText();
    log.info("{} {} {}", field, operator, value);
    final QPerson entity = QPerson.person;
    final StringExpression fieldExpression = Expressions.stringPath(entity, field);
    return switch (operator) {
      case "=" -> fieldExpression.eq(value);
      case "<" -> fieldExpression.lt(value);
      case ">" -> fieldExpression.gt(value);
      // Add other comparison operators as needed
      default -> null;
    };
  }

}
