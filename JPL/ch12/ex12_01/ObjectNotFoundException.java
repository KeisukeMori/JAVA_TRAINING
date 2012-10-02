package ch12.ex12_01;

public class ObjectNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	String name = null;
	
	public ObjectNotFoundException(String name){
		super(null, null);
		this.name = name;
	}
	
	public ObjectNotFoundException(String name, Throwable t){
		super(null, t);
		this.name = name;
	}	
	
	public ObjectNotFoundException(String name, String msg, Throwable t){
		super(msg, t);
		this.name = name;
	}
		
	public ObjectNotFoundException(Throwable t){
		super(t);
	}
	
	@Override
	public String toString(){
		return "name:" + name + " is not found." ;
	}
}
