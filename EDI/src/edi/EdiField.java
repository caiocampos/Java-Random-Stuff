package edi;

public class EdiField extends EdiParseable {
	private boolean number = false;
	private int length = 0;
	private String value = "";

	public EdiField() {
	}

	public EdiField(int length) {
		this.length = length;
	}

	public EdiField(int length, boolean isNumber) {
		this.length = length;
		this.number = isNumber;
	}

	public EdiField(int length, String value) {
		this.length = length;
		this.value = value;
	}

	public EdiField(int length, String value, boolean isNumber) {
		this.length = length;
		this.value = value;
		this.number = isNumber;
	}

	public boolean isNumber() {
		return number;
	}

	public void setNumber(boolean number) {
		this.number = number;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getParsed() {
		return getFormatedValue();
	}

	public String getFormatedValue() {
		return EdiUtils.format(value, length, number);
	}

}
