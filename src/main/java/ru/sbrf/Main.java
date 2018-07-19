package ru.sbrf;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	private static Resource TYPE_MAPPING = new ClassPathResource("TYPE_MAPPING.txt");
	private static Resource HEADER_TEMPLATE = new ClassPathResource("HEADER_TEMPLATE.txt");
	private static Path OUTPUT = Paths.get("OUTPUT.txt");

    public static void main(String[] args) throws IOException, InterruptedException {
    	CSV_TestDataBuilder testDataBuilder = new CSV_TestDataBuilder("DRPA", "AGR_CRED", OUTPUT);
		//CSVUtils.generateDataTypeMaping(TYPE_MAPPING, HEADER_TEMPLATE, Paths.get("OUTPUT_MAPING.txt"));
		testDataBuilder.buildRandomRecords(5).setValueOfFieldsWithType(ValueType.DATE, "INVALID_DATE").buildRandomRecords(5).generate();


//		System.out.println("SLEEPING");
//		Thread.sleep(10000);
//		testDataBuilder.destroy();
//		System.out.println("OUTPUT_DESTROYED");

    }
}
