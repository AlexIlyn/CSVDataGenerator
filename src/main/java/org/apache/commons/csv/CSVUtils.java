package org.apache.commons.csv;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtils {

	public static String[] getHeaders(Resource resource) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(resource.getFile().toString()))) {
			CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';');
			CSVParser csvParser = new CSVParser(reader, format);
			return csvParser.getRecords().get(0).values();
		}
	}

	public static Map<String, Integer> getHeadersMap(Resource resource) throws IOException {
		String[] headers = getHeaders(resource);
		Map<String, Integer> result = new HashMap<>();
		for (int i = 0; i < headers.length; i++) {
			result.put(headers[i], i);
		}
		return  result;
	}
}
