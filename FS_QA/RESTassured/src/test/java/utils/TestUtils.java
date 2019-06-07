package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import githubIssues.TestRestAssured;
import org.apache.commons.io.IOUtils;

import java.io.IOException;


public class TestUtils {
    public static String getResourceAsString(String path){
        try {
            return IOUtils.toString(TestRestAssured.class.getClassLoader().getResource(path), "UTF-8");
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
