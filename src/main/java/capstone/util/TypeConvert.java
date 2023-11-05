package capstone.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.XML;

public class TypeConvert {
    public static String xmlToJsonString(String xmlString){
        try{
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object json = mapper.readValue(jsonObject.toString(), Object.class);
            String output = mapper.writeValueAsString(json);
            return output;
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("to JsonString converting error");
    }

    public static Map<String, Object> JsonStringToJson(String jsonString){
        try{
            return new ObjectMapper().readValue(jsonString, HashMap.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        throw  new RuntimeException("to Json converting error");
    }
}
