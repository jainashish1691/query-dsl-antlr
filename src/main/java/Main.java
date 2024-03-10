
import com.poc.querydsl.SearchStringLexer;
import com.poc.querydsl.SearchStringParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.boot.CommandLineRunner;


public class Main {

  public static void main(String[] args) {
    final String javaClassContent = "a = '1' AND b < '2' OR c > '3'";
    SearchStringLexer lexer = new SearchStringLexer(CharStreams.fromString(javaClassContent));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    SearchStringParser parser = new SearchStringParser(tokens);
    ParseTree tree = parser.searchString();
/*
    SearchStringVisitor visitor = new SearchStringVisitor();
*/
/*
    Predicate predicate = visitor.visit(tree);
*/


  }


}
