import java.util.*;
import java.io.*;
import com.github.mustachejava.*;

public class MyClass {
    public static void main(String args[]) {
        Map<String, Object> params = new HashMap<>();
        params.put("t1", 1);
        params.put("t2", 1);
        params.put("t3", "SELECT...");
        String s = "{{#t1}}{{{t3}}}{{/t1}}{{^t1}}{{#t2}}{{{t3}}}{{/t2}}{{/t1}}";

        System.out.println(executar(s, params));
    }
    
    public static String executar(final String template, final Map<String, Object> parametros) {
        final StringWriter writer = new StringWriter();
        final MustacheFactory mf = new DefaultMustacheFactory();
        final Mustache mustache = mf.compile(new StringReader(template), "template");
        mustache.execute(writer, parametros);
        return writer.toString();
    }
}

