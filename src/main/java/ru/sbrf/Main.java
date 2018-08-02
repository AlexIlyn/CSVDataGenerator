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

	/* Коды справочника "Статус кредитного договора */
	private static final String STATUS_CLOSED = "CLOSE"; // Закрыт
	private static final String STATUS_WORK = "WORK"; // Работает
	private static final String STATUS_WITHDRAWN = "D_OUT_BAL"; // Снят с баланса
	private static final String STATUS_INVALID = "INVALIDSTATUS"; // Не учитываемый статус

	private static final String DEFAULT_CUST_ID = "0123456789";

	public static void main(String[] args) throws IOException {
		//generateFileForReferenceTest();
		//generateDublicatesAgrCred();
		//generateSingleAgrProvis();
		//generateAgrProvisForUpdate();
		//generateAgrCredForOSZ();
		//generateAgrCredForOSZ_Update();
		generateSecondCustFile();
		//generateAgrCredForUpdate();
		//generateSingleAgrCred();
	}

	private static void generateAgrCredForOSZ_Update() throws IOException {
		CSV_TestDataBuilder agrCredUpdDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_CALCULATE_OSZ_UPDATE.txt"));
		agrCredUpdDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("debt_rub", "300000")
				.setAllRecordsFieldValue("gregor_dt", "20101005")
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_CLOSED)
				.setLastRecordFieldValue("agr_cred_id", "123")
				.setLastRecordFieldValue("host_agr_cred_id", "123")
				.copyLastRecord()
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_WORK)
				.setLastRecordFieldValue("agr_cred_id", "456")
				.setLastRecordFieldValue("host_agr_cred_id", "456")
				.copyLastRecord()
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_WITHDRAWN)
				.setLastRecordFieldValue("agr_cred_id", "789")
				.setLastRecordFieldValue("host_agr_cred_id", "789")
				.copyLastRecord()
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_INVALID)
				.setLastRecordFieldValue("agr_cred_id", "147")
				.setLastRecordFieldValue("host_agr_cred_id", "147")
				.generate();
	}

	private static void generateAgrCredForOSZ() throws IOException {
		CSV_TestDataBuilder agrCredUpdDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_CALCULATE_OSZ.txt"));
		agrCredUpdDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("debt_rub", "250000")
				.setAllRecordsFieldValue("gregor_dt", "20101001")
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_CLOSED)
				.setLastRecordFieldValue("agr_cred_id", "123")
				.setLastRecordFieldValue("host_agr_cred_id", "123")
				.copyLastRecord()
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_WORK)
				.setLastRecordFieldValue("agr_cred_id", "456")
				.setLastRecordFieldValue("host_agr_cred_id", "456")
				.copyLastRecord()
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_WITHDRAWN)
				.setLastRecordFieldValue("agr_cred_id", "789")
				.setLastRecordFieldValue("host_agr_cred_id", "789")
				.copyLastRecord()
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_INVALID)
				.setLastRecordFieldValue("agr_cred_id", "147")
				.setLastRecordFieldValue("host_agr_cred_id", "147")
				.generate();
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

	private static void generateSecondCustFile() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST_GSZ.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.copyLastRecord()
				.setLastRecorFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID + 1)
				.setLastRecordFieldValue("full_name", RandomValueUtils.getRandomValueByValueType(ValueType.COMPANY_NAME))
				.setLastRecordFieldValue("inn_num", RandomValueUtils.getRandomValueByValueType(ValueType.BIGINT))
				.setLastRecordFieldValue("ogrn_num", RandomValueUtils.getRandomValueByValueType(ValueType.BIGINT))
				.copyLastRecord()
				.setLastRecorFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID + 2)
				.setLastRecordFieldValue("full_name", RandomValueUtils.getRandomValueByValueType(ValueType.COMPANY_NAME))
				.setLastRecordFieldValue("inn_num", RandomValueUtils.getRandomValueByValueType(ValueType.BIGINT))
				.setLastRecordFieldValue("ogrn_num", RandomValueUtils.getRandomValueByValueType(ValueType.BIGINT))
				.generate();
		CSV_TestDataBuilder agrCredDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_GSZ.txt"));
		agrCredDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("debt_rub", "100000")
				.setAllRecordsFieldValue("gregor_dt", "20101001")
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_CLOSED)
				.setLastRecordFieldValue("agr_cred_id", "123")
				.setLastRecordFieldValue("host_agr_cred_id", "123")
				.copyLastRecord()
				.setLastRecorFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID + 1)
				.copyLastRecord()
				.setLastRecorFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID + 2)
				.generate();
		CSV_TestDataBuilder agrCredDataBuilderUpd = new CSV_TestDataBuilder("DRPA", "AGRCRED", Paths.get("DRPA_AGRCRED_GSZ_UPDATE.txt"));
		agrCredDataBuilderUpd.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("debt_rub", "200000")
				.setAllRecordsFieldValue("gregor_dt", "20101001")
				.setLastRecordFieldValue("agr_cred_stts_type_cd", STATUS_CLOSED)
				.setLastRecordFieldValue("agr_cred_id", "123")
				.setLastRecordFieldValue("host_agr_cred_id", "123")
				.generate();
	}

	private static void generateSingleAgrProvis() throws IOException {
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

	private static void generateAgrProvisForUpdate() throws IOException {
		CSV_TestDataBuilder custDataBuilder = new CSV_TestDataBuilder("DRPA", "CUST_LEGAL_ENTITY", Paths.get("DRPA_CUST.txt"));
		CSV_TestDataBuilder agrProvisUpdateInitDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRPROVIS", Paths.get("DRPA_AGRPROVIS_UPDATE_INIT.txt"));
		CSV_TestDataBuilder agrProvisUpdateSetDataBuilder = new CSV_TestDataBuilder("DRPA", "AGRPROVIS", Paths.get("DRPA_AGRPROVIS_UPDATE_SET.txt"));
		custDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.generate();
		String AGR_COLLAT_ID = RandomValueUtils.getRandomValueByValueType(ValueType.BIGINT);
		String HOST_AGR_COLLAT_ID = RandomValueUtils.getRandomValueByValueType(ValueType.BIGINT);
		agrProvisUpdateInitDataBuilder.buildEmptyRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldValue("agr_collat_id", AGR_COLLAT_ID)
				.setAllRecordsFieldValue("host_agr_collat_id", HOST_AGR_COLLAT_ID)
				.generate();
		agrProvisUpdateSetDataBuilder.buildRandomRecord()
				.setAllRecordsFieldsWithType(ValueType.CUST_ID, DEFAULT_CUST_ID)
				.setAllRecordsFieldsToRandom(ValueType.DECIMAL, ValueType.TINYINT)
				.setAllRecordsFieldValue("agr_collat_id", AGR_COLLAT_ID)
				.setAllRecordsFieldValue("host_agr_collat_id", HOST_AGR_COLLAT_ID)
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
