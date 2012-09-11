package ch20.ex20_11;

import java.io.File;

import java.io.FilenameFilter;

public class FileFilterPrefix implements FilenameFilter{

	private File file; // file
	private String prefix; // 接尾語
	
	public FileFilterPrefix(File directory, String prefix){
		file = directory;
		this.prefix = prefix;
	}

	public boolean accept(File dir, String name) {
		return name.endsWith(prefix);
	}
	
	public void printFileList(){
		String[] filenames = file.list(this);
		for(String name: filenames){
			System.out.println(name);
		}
	}



}
