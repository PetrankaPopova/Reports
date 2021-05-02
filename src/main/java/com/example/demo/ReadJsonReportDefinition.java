package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJsonReportDefinition {
    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("reportDefinition.json"));

            JSONArray jsonObjects =  (JSONArray) obj;

            for (Object o : jsonObjects) {
                JSONObject jsonObject = (JSONObject) o;
                Long topPerformers = (Long) jsonObject.get("topPerformersThreshold");
                System.out.println(topPerformers);

                Boolean useExprience = (Boolean) jsonObject.get("useExprienceMultiplier");
                System.out.println(useExprience);

                Long periodLimit = (Long) jsonObject.get("periodLimit");
                System.out.println(periodLimit);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
