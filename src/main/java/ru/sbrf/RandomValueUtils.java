package ru.sbrf;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandomValueUtils {
	public static String getRandomFormatedDate(String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(getRandomDate());
	}
	public static Date getRandomDate() {

		Date randomDate;

		GregorianCalendar gc = new GregorianCalendar();

		int year = getRandomIntInRange(1900, 2010);

		gc.set(gc.YEAR, year);

		int dayOfYear = getRandomIntInRange(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);
		randomDate = gc.getTime();
		return  randomDate;
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
	public static BigDecimal getRandomBigDecimal(double min, double max){
		return new BigDecimal(getRandomDoubleInRange(min,max));
	}
}
