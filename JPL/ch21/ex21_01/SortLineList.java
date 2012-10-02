package ch21.ex21_01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SortLineList {
	LinkedList<String> stringLineList = new LinkedList();
	
	public SortLineList() {
		
	}
	
	// print
	public void printLines() {
		for(String line : stringLineList) {
			System.out.println(line);
		}
	}
	
	public void readFile(File file) {
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null) {
				addList(line);
			}
		}
		catch( IOException e) {
			e.printStackTrace();
		}
		finally {
			if(reader != null) {
				try {
					reader.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void addList(String line) {
		String prev = "";
		for(ListIterator<String> it = stringLineList.listIterator(); it.hasNext();) {
			String current = it.next();
			if(line.compareTo(prev) >= 0 && line.compareTo(current) < 0) {
				it.previous();
				it.add(line);
				return;
			}
			current = prev;
		}
		stringLineList.addLast(line);
	}
	
	public void clear() {
		stringLineList.clear();
	}
}
