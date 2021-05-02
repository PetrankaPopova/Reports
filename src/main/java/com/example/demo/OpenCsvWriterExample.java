package com.example.demo;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCsvWriterExample {
    public static void main(String[] args) throws IOException {

        List<String[]> csvData = createCsvDataSimple();

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
            writer.writeAll(csvData);
        }

    }

    private static List<String[]> createCsvDataSimple() {
        String [] header = {"Result"};
        String[] header1 = {"Name"  , "Score"};
        String[] record1 = {"John Smith", "12.5"};
        String[] record2 = {"David Prowess", "12.5"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(header1);
        list.add(record1);
        list.add(record2);

        return list;
    }
}
