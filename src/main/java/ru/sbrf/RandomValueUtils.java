package ru.sbrf;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandomValueUtils {
	public static String getRandomFormatedDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(getRandomDate());
	}

	public static Date getRandomDate() {

		Date randomDate;

		GregorianCalendar gc = new GregorianCalendar();

		int year = getRandomIntInRange(1960, 2010);

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

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static double getRandomDoubleInRange(double min, double max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}

	public static String getRandomFlag() {

		Random r = new Random();
		Boolean bool = r.nextBoolean();
		return bool ? "Y" : "N";
	}

	public static BigDecimal getRandomBigDecimal(double min, double max) {
		return new BigDecimal(getRandomDoubleInRange(min, max));
	}


	public static String getRandomValueByValueType(ValueType valueType) {
		switch (valueType) {
			case AGRNUM:
				return getRandomIntInRange((int) Math.pow(10, 7), (int) Math.pow(10, 8) - 1) + "/" + getRandomIntInRange((int) Math.pow(10, 7), (int) Math.pow(10, 8) - 1);
			case DATE:
				return getRandomFormatedDate("yyyy-mm-dd");
			case FLAG:
				return getRandomFlag();
			case STRTINYINT:
				return Integer.toString(getRandomIntInRange(0, 20));
			case STRSMALLINT:
				return Integer.toString(getRandomIntInRange(0, Byte.MAX_VALUE));
			case STRBIGINT:
				return Integer.toString(getRandomIntInRange((int) Math.pow(10, 9), (int) Math.pow(10, 10) - 1));
			case DECIMAL:
				return Double.toString(getRandomDoubleInRange(0, Double.MAX_VALUE));
			case CURRENCY_ID:
				return Integer.toString(37107);
			case CURRENCY_CD:
				return Integer.toString(810);
			default:
				return null;
		}
	}
}
