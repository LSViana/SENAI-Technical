package br.senai.sp.informatica.oop.mathematics;
public final class Mathematics {
	/**
	 * This method returns the highest value between the received values
	 * @param values The set of values of the same type to be processed
	 * @return The highest value found among the values
	 */
	public static <T extends Comparable, Number> T highest(T... values) {
		T highest = values[0];
		for (int i = 1; i < values.length; i++) {
			if(values[i].compareTo(highest) > 0)
				highest = values[i];
		}
		return highest;
	}
	public static <T extends Number> T sum(T... values) {
		Double sum = values[0].doubleValue();
		for (int i = 1; i < values.length; i++) {
			sum += values[i].doubleValue();
		}
		return (T) sum;
	}
	public static <T extends Number> int squareRoot(T value) {
		Double _value = value.doubleValue();
		int counter = 0;
		int carrier = 1;
		while (_value >= 0) {
			_value -= carrier;
			carrier += 2;
			counter++;
		}
		return counter - 1;
	}
	public static <T extends Number> double average(T... values) {
		Double sum = sum(values).doubleValue();
		return sum / values.length;
	}
}
