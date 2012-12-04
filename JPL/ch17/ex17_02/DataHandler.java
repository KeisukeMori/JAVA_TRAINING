package ch17.ex17_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DataHandler {
	private WeakReference<File> lastFile; // 弱参照
	private WeakReference<byte[]> lastData;
	
	byte[] readFile(File file){
		byte[] data;
		
		if(file.equals(lastFile)){
			data = lastData.get();
			if(data != null){
				return data;
			}
		}
		
		data = readByteFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	/**
	 * ファイルの読み込みを行います
	 * @param file
	 * @return
	 */
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
		return null;
	}
}
