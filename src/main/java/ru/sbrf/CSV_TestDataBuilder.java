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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.sbrf.RandomValueUtils.getRandomValueByValueType;

public class CSV_TestDataBuilder {

	private Resource TYPE_MAPPING;
	private Resource HEADER_TEMPLATE;
	private Resource OUTPUT;

	private Map<String, ValueType> typeMapping;
	private Map<String, Integer> headersMap;
	private String[] headersArray;

	private List<String[]> records;

	public CSV_TestDataBuilder(Resource TYPE_MAPPING, Resource HEADER_TEMPLATE, Resource OUTPUT) throws IOException {
		this.TYPE_MAPPING = TYPE_MAPPING;
		this.HEADER_TEMPLATE = HEADER_TEMPLATE;
		this.OUTPUT = OUTPUT;
		typeMapping = getColumnDataTypesMap(Utils.buildFromFile(TYPE_MAPPING));
		headersMap = CSVUtils.getHeadersMap(HEADER_TEMPLATE);
		headersArray = CSVUtils.getHeaders(HEADER_TEMPLATE);
		records = new ArrayList<>();
	}

	public CSV_TestDataBuilder buildRandomRecords(int records) {

		for (int i = 0; i < records; i++) {
			buildNewRandomRecord();
		}
		return this;
	}

	public CSV_TestDataBuilder buildNewRandomRecord() {
		String[] result_array = new String[headersMap.size()];
		for (String header : headersArray) {
			if (typeMapping.get(header) != null) {
				result_array[headersMap.get(header)] = getRandomValueByValueType(typeMapping.get(header));
			}
		}
		records.add(result_array);
		return this;
	}

	public void generate() throws IOException {

		try (
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT.getFile().toString()));

				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headersArray).withDelimiter(';'))
		) {
			for (String[] record : records) {
				csvPrinter.printRecord(record);
			}
			csvPrinter.flush();
		}
	}

	public static Map<String, ValueType> getColumnDataTypesMap(List<CSVRecord> csvRecordList) {
		Map<String, ValueType> result = new HashMap<>();
		String field;
		ValueType type;
		for (CSVRecord csvRecord : csvRecordList) {
			field = csvRecord.get("FIELD");
			try {
				type = ValueType.valueOf(csvRecord.get("TYPE"));
			} catch (IllegalArgumentException e) {
				continue;
			}
			result.put(field, type);
		}
		return result;
	}
}
