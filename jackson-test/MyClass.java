import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.HashMap;


public class MyClass {
    public static void main(String args[]) {
        var mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        var val2 = new MyValue();
        val2.name = "Teste2";
        val2.age = 36;
        val2.date = new Date();
        val2.list = Arrays.asList(1L,"teste2",3F);
        val2.dec = new BigDecimal(0.3454);
        val2.map = new HashMap();
        val2.map.put("","");
        val2.map.put("1",1);
        val2.map.put("date",new Date());
        
        var val3 = new MyValue();
        val3.name = "Teste3";
        val3.age = 36;
        val3.date = new Date();
        val3.list = Arrays.asList(1L,"teste3",3F);
        val3.dec = new BigDecimal(0.3454);
        val3.map = new HashMap();
        val3.map.put("val2",val2);
        val3.map.put("2.3",2.3);
        
        var val4 = new MyValue();
        val4.name = "Teste4";
        val4.age = 36;
        val4.date = new Date();
        val4.list = Arrays.asList(1L,"teste4",3F);
        val4.dec = new BigDecimal(0.3454);
        
        var val = new MyValue();
        val.name = "Teste";
        val.age = 36;
        val.date = new Date();
        val.list = Arrays.asList(1L,"teste",3F,val2);
        val.dec = new BigDecimal(0.3454);
        val.map = new HashMap();
        val.map.put("val2",val2);
        val.map.put("val3",val3);
        val.map.put("val4",val4);
        
        var map = new HashMap();
        map.put("val2",val2);
        map.put("val3",val3);
        map.put("2.3",2.3);
        map.put("val4",val4);
        map.put("t","t");
        
        var x = 10;
        var y = 25;
        Integer z = x + y;
        try {
            System.out.println(mapper.writeValueAsString(x));
            System.out.println(mapper.writeValueAsString(y));
            System.out.println(mapper.writeValueAsString(z));
            System.out.println(mapper.writeValueAsString(val));
            System.out.println(mapper.writeValueAsString("x"));
            System.out.println(mapper.writeValueAsString(12L));
            System.out.println(mapper.writeValueAsString(135F));
            System.out.println(mapper.writeValueAsString(map));
        } catch (Exception e) {
	    	e.printStackTrace();
        }
    }
}