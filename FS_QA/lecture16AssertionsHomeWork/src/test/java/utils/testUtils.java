package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import ru.cft.CafeTests;

import java.io.IOException;


public class testUtils {
    public static String getResourceAsString(String path){
        try {
            return IOUtils.toString(CafeTests.class.getClassLoader().getResource(path), "UTF-8");
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static JsonObject resourceToJSONObject(String path){
        String string = getResourceAsString(path);
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(string);
    }
}
