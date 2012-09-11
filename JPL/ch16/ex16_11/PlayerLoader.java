package ch16.ex16_11;

import java.io.FileInputStream;
import java.io.IOException;

public class PlayerLoader extends ClassLoader{
	public Class<?> findClass(String name) throws ClassNotFoundException{
		try {
			System.out.println(name);
			byte[] buf = bytesForClass(name);
			
			return defineClass("ch16.ex16_11." + name, buf, 0, buf.length);
		}
		catch(IOException e){
			throw new ClassNotFoundException(e.toString());
		}
	}
	
	protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException{
		FileInputStream in = null;
		try {
			System.out.println(name);
//			if (name == "JPL.ch16.ex16_11.Player") {
//				in = new FileInputStream("././player/" + name + ".class");
//			}
			
			in = new FileInputStream("./player/" + name + ".class");
			int length = in.available();
			if(length == 0){
				throw new ClassNotFoundException(name);
			}
			byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		}
		finally{
			if(in != null){
				in.close();
			}
		}
	}

}
