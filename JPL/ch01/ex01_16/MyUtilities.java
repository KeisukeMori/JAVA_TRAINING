package ch01.ex01_16;

import java.io.FileInputStream;
import java.io.IOException;

public class MyUtilities {
	public double[] getDataSet(String setName) throws BadDataSetException
	{
		String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException();
		} finally {
			try {
				if (in != null){
					in.close();
				}
			} catch (IOException e) {
				System.out.println(file + e.getMessage());
			}
		}
	}
	public double[] readDataSet(FileInputStream file){
		double[] fileList = null;
			//何らかの処理
			return fileList;
	}
}
