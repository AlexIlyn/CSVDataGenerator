package ru.sbrf;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ru.sbrf.RandomValueUtils.*;

public class Main {

	private static Resource TYPE_MAPPING = new ClassPathResource("TYPE_MAPPING.txt");
	private static Resource HEADER_TEMPLATE = new ClassPathResource("HEADER_TEMPLATE.txt");
	private static Resource OUTPUT = new ClassPathResource("OUTPUT.txt");

    public static void main(String[] args) throws IOException {
    	CSV_TestDataBuilder testDataBuilder = new CSV_TestDataBuilder(TYPE_MAPPING, HEADER_TEMPLATE, OUTPUT);
		testDataBuilder.buildRandomRecords(2).generate();

    }
}
