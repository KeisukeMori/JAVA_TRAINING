package ch20.ex20_07;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.io.DataInputStream;

public class Attr {
	private final String name;
	private Object value = null;

	// 状態読み込み
	public Attr(DataInputStream in) throws IOException {
		name = in.readUTF();
		value = in.readUTF();
	}
	
	public Attr(String name){
		this.name = name;
	}

	public Attr(String name, Object value){
		this(name);
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public Object getValue(){
		return value;
	}

	public Object setValue(Object newValue){
		Object oldValue = value;
		this.value = newValue;
		return oldValue;
	}

	public String toString(){
		return name + "='" + value + "'";
	}
	
	// 内容書き込み
	public void writeToStream(DataOutputStream out) throws IOException{
		out.writeUTF(name);
		out.writeUTF(value.toString());
	}
}
