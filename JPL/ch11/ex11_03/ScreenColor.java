package ch11.ex11_03;

public class ScreenColor {
	private Object value;
	
	public ScreenColor(Object value) {
		this.setValue(value);
	}
	
	public Boolean equals(ScreenColor color) {
		return (value.equals(color.getValue()));
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public int hashCode() {
		return value.hashCode();
	}
}
