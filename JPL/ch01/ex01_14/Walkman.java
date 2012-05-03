package ch01.ex01_14;

class Walkman {
	
    private Pin pin = new Pin();
    private Object musicData;
    public void setPin(int pinNo) {
      this.pin.pinNo = pinNo;
    }
    public Pin getPin() {
      return pin;
    }
    public Object Play() {
    	
    	getPin();
    	return musicData;
    }
}

