package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    /**
     Notes:
       private static ObjectMapper mapper = new ObjectMapper();
       If we create object like above, Java will load the class first and then create the object.
       But if we create the object without initializing and then create a static block to initialize it,
          the object will be ready together with the class. Static block will be executed before everything inside the class.
     */

    //1.Method: It is used to convert Json Data to Java Object. De-Serialization Method
    public static <T> T convertJsonToJava(String json, Class<T> cls){ //1)json data 2)Java object(any java obj)

        T javaResult = null;

        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("Json couldn't be converted to Java Object " + e.getMessage());
        }
        return javaResult;
    }

    /**
       <T> T ==>> Generic Method (instead of creating "public static HashMap<String, Object>convertJsonToJava(String json){}")
       If we type the method generic, we can convert the json data any data type like Set, Queue, HashMap
       HashMap<String, Object> if we create method in this way, the method will return only HashMap, so we can only produce HahMap
     */


    //2. Method: It is used to convert Java Object to Json Data. Serialization Method
    public static String convertJavaToJson(Object obj){

        String jsonResult = null;

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java object couldn't be convert to Json " + e.getMessage());
        }
        return jsonResult;
    }


}






















