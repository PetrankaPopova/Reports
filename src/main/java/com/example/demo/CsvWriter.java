package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class CsvWriter {

    private Map<String, String> map = new HashMap<>();

    public void convertJsonToCSV() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("example.json"));
            Object obj2 = parser.parse(new FileReader("reportDefinition.json"));


            JSONArray jsonObjects = (JSONArray) obj;
            JSONArray jsonObjects2 = (JSONArray) obj2;

            for (Object o : jsonObjects) {
                JSONObject jsonObject = (JSONObject) o;

                for (Object o1 : jsonObjects2) {
                    JSONObject jsonObjectNew = (JSONObject) o1;

                    String name = (String) jsonObject.get("name");

                    Long totalSales = (Long) jsonObject.get("totalSales");

                    Long salesPeriod = (Long) jsonObject.get("salesPeriod");

                    Double exp = (Double) jsonObject.get("experienceMultiplier");

                    Long topPerformers = (Long) jsonObjectNew.get("topPerformersThreshold");

                    Boolean useExprience = (Boolean) jsonObjectNew.get("useExprienceMultiplier");

                    Long periodLimit = (Long) jsonObjectNew.get("periodLimit");

                    if (salesPeriod <= periodLimit) {

                        double score;
                        if (!useExprience) {
                            score = totalSales / salesPeriod * exp;
                        } else {
                            score = totalSales / salesPeriod;
                        }

                        createCsvDataSimple(map, name, score);


                        String result = "Name  ,Score";
                        String eol = System.getProperty("line.separator");

                        try (Writer writer = new FileWriter("test.csv")) {
                            writer.append(result)
                                    .append("\n");

                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                writer.append(entry.getKey())
                                        .append(", ")
                                        .append(entry.getValue())
                                        .append(eol);

                            }
                        } catch (IOException ex) {
                            ex.printStackTrace(System.err);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        CsvWriter writer = new CsvWriter();
        writer.convertJsonToCSV();

    }

    private void createCsvDataSimple(Map<String, String> map, String name, double score) {
        map.put(name, String.valueOf(score));
    }

}



