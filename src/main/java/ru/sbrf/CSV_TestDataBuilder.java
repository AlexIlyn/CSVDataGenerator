package ru.sbrf;

import org.apache.commons.csv.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.sbrf.RandomValueUtils.getRandomValueByValueType;

public class CSV_TestDataBuilder {
	private Path OUTPUT;
	//private Resource OUTPUT_RES;

	private Map<ValueType, List<Integer>> valueTypeListMap;
	private Map<String, ValueType> typeMapping;
	private Map<String, Integer> headersMap;
	private String[] headersArray;
	private Resource TYPE_MAPPING_RES;

	private List<String[]> records;

	public CSV_TestDataBuilder(String caseType, String caseSubType, Path OUTPUT) throws IOException {
		TYPE_MAPPING_RES = new ClassPathResource(String.format("%s\\%s\\TYPE_MAPPING.txt", caseType, caseSubType));
		this.OUTPUT = OUTPUT.toAbsolutePath();
		typeMapping = initColumnDataTypesMap(TYPE_MAPPING_RES);
		records = new ArrayList<>();
		//TODO System.out.println(new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "\\VERYNEW_NEW.NEW"));
	}

	public CSV_TestDataBuilder buildRandomRecords(int records) {

		for (int i = 0; i < records; i++) {
			buildRandomRecord();
		}
		return this;
	}

	public CSV_TestDataBuilder buildRandomRecord() {
		String[] result_array = new String[headersMap.size()];
		for (String header : headersArray) {
			if (typeMapping.get(header) != null) {
				result_array[headersMap.get(header)] = getRandomValueByValueType(typeMapping.get(header));
			}
		}
		records.add(result_array);
		return this;
	}

	public CSV_TestDataBuilder setValueOfFieldsWithType(ValueType valueType, String value){
		for (int index : valueTypeListMap.get(valueType)){
			for (String[] record : records){
				record[index] = value;
			}
		}
		return this;
	}

	public void generate() throws IOException {
		try (
				BufferedWriter writer = Files.newBufferedWriter(OUTPUT);

				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';').withEscape(' ').withQuoteMode(QuoteMode.NONE).withTrim())
//						.withHeader(headersArray)
		) {
			csvPrinter.printRecord(headersArray);
			for (String[] record : records) {
				csvPrinter.printRecord(record);
			}
			csvPrinter.flush();
		}
	}

	public CSV_TestDataBuilder destroy() throws IOException {
		File file =  new File(OUTPUT.toString());
		if(file.exists()) file.delete();
		records.clear();
		return this;
	}

	private Map<String, ValueType> initColumnDataTypesMap(Resource resource) throws IOException {
		valueTypeListMap = new HashMap<>();
		headersArray = CSVUtils.getHeaders(resource);
		headersMap = CSVUtils.getHeadersMap(resource);
		Map<String, ValueType> columnDataTypesMap = new HashMap<>();
		CSVRecord csvRecord = CSVUtils.buildFromFile(resource).get(0);
		String field;
		ValueType type;
		for (int i = 0; i < headersArray.length; i++) {
			field = headersArray[i];
			try {
			type = ValueType.valueOf(csvRecord.get(headersArray[i]));
			} catch (IllegalArgumentException e) {
				continue;
			}
			columnDataTypesMap.put(field, type);
			valueTypeListMap.computeIfAbsent(type, k -> new ArrayList<>());
			valueTypeListMap.get(type).add(i);
		}
		return columnDataTypesMap;
	}
}
