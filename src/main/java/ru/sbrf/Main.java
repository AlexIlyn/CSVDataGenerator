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

	private static final String DEFAULT_CUST_ID = "10950276948";

    public static void main(String[] args) throws IOException, InterruptedException {
    	CSV_TestDataBuilder testDataBuilder = new CSV_TestDataBuilder("DRPA", "AGR_CRED", OUTPUT);
		//CSVUtils.generateDataTypeMaping(TYPE_MAPPING, HEADER_TEMPLATE, Paths.get("OUTPUT_MAPING.txt"));
		//testDataBuilder.buildRandomRecords(5).setValueOfFieldsWithType(ValueType.DATE, "INVALID_DATE").buildRandomRecords(5).generate();
		//testDataBuilder.buildRandomRecord().setValueOfFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID).setValueOfFieldsWithType(ValueType.DECIMAL, "5").generate();
		File nf = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\VERYNEW_NEW.NEW");
		nf.createNewFile();


//		System.out.println("SLEEPING");
//		Thread.sleep(10000);
//		testDataBuilder.destroy();
//		System.out.println("OUTPUT_DESTROYED");

    }
}
