package ru.sbrf;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ru.sbrf.RandomValueUtils.*;
import static ru.sbrf.Utils.buildFromFile;

public class Main {

	private static Resource TYPE_MAPPING = new ClassPathResource("TYPE_MAPPING.txt");
	private static Resource HEADER_TEMPLATE = new ClassPathResource("HEADER_TEMPLATE.txt");
	private static Resource OUTPUT = new ClassPathResource("OUTPUT.txt");

    public static void main(String[] args) throws IOException {
	//String inputPath = args[0];
		List<ColumnDataType> typeMapping = getColumnDataTypes(Utils.buildFromFile(TYPE_MAPPING));


    }
    public static List<ColumnDataType> getColumnDataTypes(List<CSVRecord> csvRecordList){
    	List<ColumnDataType> result = new LinkedList<>();
		String field;
		ValueType type;
		for (CSVRecord csvRecord : csvRecordList){
    		field = csvRecord.get("FIELD");
    		try{
				type = ValueType.valueOf(csvRecord.get("TYPE"));
			} catch (IllegalArgumentException e) {
    			continue;
			}
    		result.add(new ColumnDataType(field, type));
		}
		return result;
	}
}
