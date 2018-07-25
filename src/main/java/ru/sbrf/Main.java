package ru.sbrf;

import org.apache.commons.csv.CSVUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	private static Resource TYPE_MAPPING = new ClassPathResource("TYPE_MAPPING.txt");
	private static Resource HEADER_TEMPLATE = new ClassPathResource("HEADER_TEMPLATE.txt");
	private static Path OUTPUT = Paths.get("DRPA_VALID_AGR_CRED.txt");
	private static Resource GENERATED_FILES = new ClassPathResource("generatedFiles");

	private static final String DEFAULT_CUST_ID = "0123456789";

    public static void main(String[] args) throws IOException, InterruptedException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST.txt"));
		CSV_TestDataBuilder agrCredDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED.txt"));
		custDataBuilder.buildRandomRecord().setValueOfFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID).setValueOfFieldsWithType(ValueType.DECIMAL, "5").generate();
		agrCredDataBuilder.buildRandomRecord().setValueOfFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID).setValueOfFieldsWithType(ValueType.DECIMAL, "5").generate();
    }
}
