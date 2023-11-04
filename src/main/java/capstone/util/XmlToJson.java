package capstone.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {
    public static void xmlToJson(String xmlString){
        try{
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object json = mapper.readValue(jsonObject.toString(), Object.class);
            String output = mapper.writeValueAsString(json);
            System.out.println(output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
