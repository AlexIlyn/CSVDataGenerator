package ru.sbrf;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.sbrf.ValueType.BIGINT;

public class Main {

	private static Resource TYPE_MAPPING = new ClassPathResource("TYPE_MAPPING.txt");
	private static Resource HEADER_TEMPLATE = new ClassPathResource("HEADER_TEMPLATE.txt");
	private static Path OUTPUT = Paths.get("DRPA_VALID_AGR_CRED.txt");
	private static Resource GENERATED_FILES = new ClassPathResource("generatedFiles");

	private static final String DEFAULT_CUST_ID = "0123456789";

	public static void main(String[] args) throws IOException {
		//generateFileForReferenceTest();
		//generateDublicatesAgrCred();
		generateSingleAgrProvis();
		//generateAgrCredForUpdate();
		//generateSingleAgrCred();
	}

	private static void generateAgrCredForUpdate() throws IOException {
		CSV_TestDataBuilder agrCredEmptyDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_UPDATE_INIT.txt"));
		CSV_TestDataBuilder agrCredUpdDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_UPDATE_SET.txt"));
		agrCredUpdDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("agr_cred_id", "787878")
				.setAllRecordsFieldValue("host_agr_cred_id", "969696")
				.setAllRecordsFieldValue("gregor_dt", "20101010")
				.setAllRecordsFieldValue("agr_cred_type_id", "516516")
				.setAllRecordsFieldValue("agr_cred_stts_type_id", "100001")
				.generate();
		agrCredEmptyDataBuilder.buildEmptyRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldValue("agr_cred_id", "787878")
				.setAllRecordsFieldValue("host_agr_cred_id", "969696")
				.setAllRecordsFieldValue("gregor_dt", "20101010")
				.setAllRecordsFieldValue("agr_cred_type_id", "516516")
				.setAllRecordsFieldValue("agr_cred_stts_type_id", "100001")
				.generate();
	}

	private static void generateDublicatesAgrCred() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST.txt"));
		CSV_TestDataBuilder agrCredDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_DUBLICATES.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
		agrCredDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.copyLastRecord()
				.copyLastRecord()
				.copyLastRecord()
				.setAllRecordsFieldRandomValue("gregor_dt", ValueType.DATE_JOINED)
				.generate();
	}

	private static void generateAgrProvisForUpdate() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST.txt"));
		CSV_TestDataBuilder agrProvisDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRPROVIS", Paths.get("DRPA_AGRPROVIS.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
		agrProvisDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
	}

	private static void generateSingleAgrProvis() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST.txt"));
		CSV_TestDataBuilder agrProvisUpdateInitDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRPROVIS", Paths.get("DRPA_AGRPROVIS_UPD_INIT.txt"));
		CSV_TestDataBuilder agrProvisUpdateSetDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRPROVIS", Paths.get("DRPA_AGRPROVIS_UPD_SET.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
		agrProvisUpdateInitDataBuilder.buildEmptyRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.generate();
		agrProvisUpdateSetDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
	}

	private static void generateSingleAgrCred() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST.txt"));
		CSV_TestDataBuilder agrCredDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
		agrCredDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
	}

	public static void generateFileForReferenceTest() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST_REF.txt"));
		CSV_TestDataBuilder agrCredDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_REF.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
		agrCredDataBuilder
				//with eks_a_f26_agr_cred_type_id
				.buildRandomRecord()
				//no eks_a_f26_agr_cred_type_id
				.buildRandomRecord()
				.setLastRecordFieldValue("#eks_a_f26_agr_cred_type_id", "")
				//with eks_a_f26_agr_cred_type_id and agr_cred_type_id == -1006
				.buildRandomRecord()
				.setLastRecordFieldValue("agr_cred_type_id", "-1006")
				//no eks_a_f26_agr_cred_type_id and agr_cred_type_id == -1006
				.buildRandomRecord()
				.setLastRecordFieldValue("agr_cred_type_id", "-1006")
				.setLastRecordFieldValue("#eks_a_f26_agr_cred_type_id", "")

				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("mis_prod_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("agr_regls_type_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("agr_qlty_cat_type_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("agr_srv_qlty_type_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("issue_int_org_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("srv_int_org_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("agr_cred_stts_type_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("agr_cred_stts_type_cd", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("issue_crncy_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("issue_crncy_cd", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("crncy_id", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.setAllRecordsFieldValue("crncy_cd", RandomValueUtils.getRandomValueByValueType(BIGINT))
				.generate();
	}
}
