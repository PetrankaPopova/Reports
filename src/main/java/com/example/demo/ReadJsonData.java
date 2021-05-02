package com.example.demo;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJsonData {
    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("example.json"));

            JSONArray jsonObjects =  (JSONArray) obj;

            for (Object o : jsonObjects) {
                JSONObject jsonObject = (JSONObject) o;
                String name = (String) jsonObject.get("name");
                System.out.println(name);

                Long totalSales = (Long)jsonObject.get("totalSales");
                System.out.println(totalSales);

                Long salesPeriod = (Long) jsonObject.get("salesPeriod");
                System.out.println(salesPeriod);

                Double exp = (Double) jsonObject.get("experienceMultiplier");
                System.out.println(exp);
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
