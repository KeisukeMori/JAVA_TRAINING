package ch21.ex21_02;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class DataHandler {
	private WeakReference<File> lastFile;
	private WeakHashMap<byte[], File> weakMap;
	
	byte[] readFile(File file){
		byte[] data;
		
		if(file.equals(lastFile)){
			data = convertByteArray(weakMap.keySet().toArray(new Byte[]{}));
			if(data != null){
				return data;
			}
		}
		data = readByteFromFile(file);
		lastFile = new WeakReference<File>(file);
		weakMap.put(data, file);
		return data;
	}

	private byte[] readByteFromFile(File file) {
		FileInputStream stream;
		ArrayList<Byte> byteArr = new ArrayList<Byte>();
		try{
			stream = new FileInputStream(file);
			int data;
			while((data = stream.read()) != -1){
				byteArr.add((byte)data);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		byte [] ret = convertByteArray(byteArr.toArray(new Byte[]{}));
		weakMap.put(ret, file);
		return ret;
	}
	
	private byte[] convertByteArray(Byte[] array){
		byte [] ret = new byte[array.length];
		for(int i = 0; i < array.length; i++){
			ret[i] = array[i];
		}
		return ret;
	}
}
