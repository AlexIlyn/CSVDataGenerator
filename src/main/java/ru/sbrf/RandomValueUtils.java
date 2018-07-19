package ru.sbrf;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RandomValueUtils {
	private static Random r = new Random();
	private static final String AGR_CRED_TYPE_EXCLUDE_1010 = "-1010";
	private static final String EKS_AGR_CRED_TYPE_ID_EXCLUDE = "-1007";
	private static final List<String> AGR_CRED_STTS_TYPE_ID_EXCLUDE = Arrays.asList(null, "", "-1", "36206", "74006",
		"60506", "74106", "5906", "33506", "45406", "49806", "44706", "47006", "42606", "45306", "47506", "74406",
		"74306", "49306", "36106", "76406", "76906", "74206", "75906", "44006", "55206", "70906", "75406", "38806",
		"74806", "55306", "76806", "52106", "45606", "45206", "51106", "82806", "44806", "66606", "47706", "34006",
		"80806", "35206", "51206", "76606", "64206", "46506", "53206", "36506", "37306", "78806");

	public static String getRandomFormatedDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(getRandomDate());
	}

	public static Date getRandomDate() {

		Date randomDate;

		GregorianCalendar gc = new GregorianCalendar();

		int year = getRandomIntInRange(1960, 2018);

		gc.set(Calendar.YEAR, year);

		int dayOfYear = getRandomIntInRange(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		randomDate = gc.getTime();
		return randomDate;
	}

	public static int getRandomIntInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		//r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static double getRandomDoubleInRange(double min, double max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		//r = new Random();
		return min + (max - min) * r.nextDouble();
	}

	public static String getRandomFlag() {

		//r = new Random();
		Boolean bool = r.nextBoolean();
		return bool ? "Y" : "N";
	}

	public static String getRandomAGR_CRED_STTS_TYPE_ID(){
		int rnd_id = getRandomIntInRange(0, AGR_CRED_STTS_TYPE_ID_EXCLUDE.size());
		return AGR_CRED_STTS_TYPE_ID_EXCLUDE.get(rnd_id);
	}

	public static String getRandomValueByValueType(ValueType valueType) {
		switch (valueType) {
			case AGRNUM:
				return getRandomIntInRange((int) Math.pow(10, 7), (int) Math.pow(10, 8) - 1) + "/" + getRandomIntInRange((int) Math.pow(10, 7), (int) Math.pow(10, 8) - 1);
			case DATE:
				return getRandomFormatedDate("yyyy-mm-dd");
			case FLAG:
				return getRandomFlag();
			case TINYINT:
				return Integer.toString(getRandomIntInRange(0, 20));
			case SMALLINT:
				return Integer.toString(getRandomIntInRange(0, Byte.MAX_VALUE));
			case BIGINT:
				return Integer.toString(getRandomIntInRange((int) Math.pow(10, 9), (int) Math.pow(10, 10) - 1));
			case DECIMAL:
				return Double.toString(getRandomDoubleInRange(0, Double.MAX_VALUE));
			case CURRENCY_ID:
				return Integer.toString(37107);
			case CURRENCY_CD:
				return Integer.toString(810);
			case AGR_CRED_STTS_TYPE_ID:
				return getRandomAGR_CRED_STTS_TYPE_ID();
			default:
				return null;
		}
	}
}
