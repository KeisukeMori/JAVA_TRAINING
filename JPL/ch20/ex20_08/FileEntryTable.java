package ch20.ex20_08;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileEntryTable {

	private static final String ENTRY_STRING = "%%";
	private File file;
	private List<Long> indexList;
	
	public void createEntry(File file) throws FileNotFoundException, IOException{
		this.file = file;
		if(!file.isFile() || !file.canRead()){
			throw new IllegalStateException("file open failed.");
		}
		RandomAccessFile rndFile = new RandomAccessFile(file, "r");
		indexList = new ArrayList<Long>();
		indexList.add((long)0);
		try{
			byte b = 0;
			long index;
			boolean regist = false;
			while(true){
				b = rndFile.readByte();
				if(b == ENTRY_STRING.charAt(0)){
					regist = true;
					for(int i = 1; i < ENTRY_STRING.length(); i++){
						if(!(b == ENTRY_STRING.charAt(i))){
							regist = false;
						}
					}
					if(regist){
						indexList.add(rndFile.getFilePointer());
					}
				}
			}
		}
		catch(EOFException e){
			;
		}
		finally{
			if(rndFile != null){
				rndFile.close();
			}
		}
	}
	
	public String getEntryStrAtRnd() throws FileNotFoundException, IOException{
		if(indexList == null){
			throw new IllegalStateException("index list haven't been created yet.");
		}
		RandomAccessFile rndFile = new RandomAccessFile(file, "r");
		
		Random rnd = new Random();
		int indexNum = rnd.nextInt(indexList.size() -1);
		long from = indexList.get(indexNum);
		long to = indexList.get(indexNum + 1);
		
		byte[] buf = new byte[(int)(to - from) -1];
 		rndFile.seek(from);
		rndFile.read(buf, 0, buf.length);
		
		return new String(buf, "UTF-8");
	}
	
}
