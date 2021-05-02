package com.example.demo;

import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class csv {
    public static void main(String[] args) throws Exception {
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


                        // System.out.println("Result");
                        //  System.out.println("Name   , Score");
                        //  System.out.printf("%s %.1f %n", name, score);


//                        Map<String, Double> mapCsvData = createCsvDataSimple(name, score);
//                        StringWriter sw = new StringWriter();
//                        CsvMapWriter writer = new CsvMapWriter(sw, CsvPreference.STANDARD_PREFERENCE);
//                        String [] headers = { "Name" , "Score" };
//                        writer.writeHeader(headers);
//                        writer.write(mapCsvData,headers);
//                        writer.close();
//
//                        System.out.println(sw.toString());
                        Map<String, String> mapCsvData = createCsvDataSimple(name, score);
                        //Map<String, String> map = new HashMap<>();
                        // map.put(name, String.valueOf(score));
                        System.out.println(mapCsvData);
                        System.out.println(mapCsvData.size());

                            String result = "Name  ,Score";
                            String eol = System.getProperty("line.separator");
                            try (Writer writer = new FileWriter("test.csv")) {
                                for (Map.Entry<String, String> entry : mapCsvData.entrySet()) {
                                    writer.append(result)
                                            .append('\n')
                                            .append(entry.getKey())
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

//        List<String[]> csvData = createCsvDataSimple();
//
//        // default all fields are enclosed in double quotes
//        // default separator is a comma
//        try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
//            writer.writeAll(csvData);
//        }

    }
//
   private static Map<String, String> createCsvDataSimple(String name, double score) {
        Map<String,String>map = new HashMap<>();
        if (!map.containsKey(name)){
            map.put(name,String.valueOf(score));
        }else{
            map.put(name, String.valueOf(1));

        }

        return map;
    }



//    private static List<String[]> createCsvDataSimple() {
//        String[] header = {"Result"};
//        String[] header1 = {"Name"  , "Score"};
//        String[] record1 = {"John Smith", "12.5"};
//        String[] record2 = {"David Prowess", "12.5"};
//
//        List<String[]> list = new ArrayList<>();
//        list.add(header);
//        list.add(header1);
//        list.add(record1);
//        list.add(record2);
//
//        return list;
    }



