package ch03.ex03_07;

public class ColorAttr extends Attr{
	private ScreenColor myColor;

	public ColorAttr(String name, Object value){
		super(name, value);
		decodeColor();
	}
	public ColorAttr(String name){
		this(name,"transparent");
	}	
	public ColorAttr(String name, ScreenColor value){
		super(name, value.toString());
		myColor = value;
	}
	@Override
	public Object setValue(Object newValue){
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}	
	public ScreenColor setValue(ScreenColor newValue){
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;	
		return oldValue;
	}
	public ScreenColor getColor(){
		return myColor;
	}
	protected void decodeColor(){
		if(getValue() == null){
			myColor = null;
		} else {
			myColor = new ScreenColor(getValue());
		}	
	}
	@Override
	public boolean equals(Object obj){
		if(this.getValue().equals(obj)){
			return true;
		} else { 
			return false;
		}
	}

	@Override
	public int hashCode(){
		return getValue().hashCode();
	}

	public static void main(String[] args){
		ColorAttr ca = new ColorAttr("test","red");
		ColorAttr ca2 = new ColorAttr("test","red");
		System.out.println(ca.hashCode());
		System.out.println(ca2.hashCode());
		System.out.println(ca.equals(ca2));	//等しくない	
	}
}
