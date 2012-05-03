package ch01.ex01_14;

public class DoublePinWalkman extends Walkman{
	private Pin subPin;
	
	public DoublePinWalkman() {
		super();
	}
	
	public void setSubPin(Pin pin) {
		this.subPin = pin;
	}
	public Pin getSubPin() {
		return subPin;
	}
	;
}
