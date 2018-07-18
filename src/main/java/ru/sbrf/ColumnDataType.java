package ru.sbrf;

public class ColumnDataType {
	private String field;
	private ValueType type;

	public ColumnDataType(String key, ValueType type) {
		this.field = key;
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public ValueType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "ColumnDataType{" +
				"field='" + field + '\'' +
				", type=" + type +
				'}';
	}
}
