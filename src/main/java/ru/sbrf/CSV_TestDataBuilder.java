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

	private final List<String[]> records;

	public List<String[]> getRecords() {
		return this.records;
	}

	public CSV_TestDataBuilder(String caseType, String caseSubType, Path OUTPUT) throws IOException {
		TYPE_MAPPING_RES = new ClassPathResource(String.format("%s\\%s\\TYPE_MAPPING.txt", caseType, caseSubType));
		this.OUTPUT = Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().getPath().substring(1) + OUTPUT);
		typeMapping = initColumnDataTypesMap(TYPE_MAPPING_RES);
		records = new ArrayList<>();
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

	public CSV_TestDataBuilder buildEmptyRecord() {
		String[] result_array = new String[headersMap.size()];
		for (String header : headersArray) {
			if (typeMapping.get(header) != null) {
				result_array[headersMap.get(header)] = "";
			}
		}
		records.add(result_array);
		return this;
	}

	public CSV_TestDataBuilder setAllRecordsFieldsWithType(ValueType valueType, String value) {
		if (valueTypeListMap.get(valueType) == null) return this;
		for (int index : valueTypeListMap.get(valueType)) {
			for (String[] record : records) {
				record[index] = value;
			}
		}
		return this;
	}

	public CSV_TestDataBuilder setLastRecorFieldsWithType(ValueType fieldType, String value) {
		if (valueTypeListMap.get(fieldType) == null) return this;
		for (int index : valueTypeListMap.get(fieldType)) {
			records.get(records.size() - 1)[index] = value;
		}
		return this;
	}

	public CSV_TestDataBuilder setAllRecordsFieldsToRandom(ValueType fieldType, ValueType randomValueType) {
		if (valueTypeListMap.get(fieldType) == null) return this;
		for (int index : valueTypeListMap.get(fieldType)) {
			for (String[] record : records) {
				record[index] = RandomValueUtils.getRandomValueByValueType(randomValueType);
			}
		}
		return this;
	}

	public CSV_TestDataBuilder setLastRecorFieldsToRandom(ValueType fieldType, ValueType randomValueType) {
		if (valueTypeListMap.get(fieldType) == null) return this;
		for (int index : valueTypeListMap.get(fieldType)) {
			records.get(records.size() - 1)[index] = RandomValueUtils.getRandomValueByValueType(randomValueType);
		}
		return this;
	}

	public CSV_TestDataBuilder setAllRecordsFieldValue(String field, String value) {
		if (headersMap.get(field) == null) return this;
		for (String[] record : records) {
			record[headersMap.get(field)] = value;
		}
		return this;
	}

	public CSV_TestDataBuilder setAllRecordsFieldRandomValue(String field, ValueType valueType) {
		if (headersMap.get(field) == null) return this;
		for (String[] record : records) {
			record[headersMap.get(field)] = RandomValueUtils.getRandomValueByValueType(valueType);
		}
		return this;
	}

	public CSV_TestDataBuilder setLastRecordFieldValue(String field, String value) {
		if (headersMap.get(field) == null) return this;
		records.get(records.size() - 1)[headersMap.get(field)] = value;
		return this;
	}

	public CSV_TestDataBuilder copyLastRecord() {
		records.add(records.get(records.size() - 1).clone());
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
		File file = new File(OUTPUT.toString());
		if (file.exists()) file.delete();
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
