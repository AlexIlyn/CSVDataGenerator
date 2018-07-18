package ru.sbrf;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.*;
import org.springframework.core.io.Resource;

public class Utils {
	public static List<CSVRecord> buildFromFile(Resource resource) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(resource.getFile().toString()))) {
			CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';');
			CSVParser csvParser = new CSVParser(reader, format);
			return csvParser.getRecords();
		}
	}
}
