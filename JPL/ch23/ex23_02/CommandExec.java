package ch23.ex23_02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExec {

	public static void main(String[] args) {
		String[] result = CommandExec.process("ipconfig", "", "");
		for(int i = 0; i < result.length; i++){
			
			System.out.print(String.format("%3d:%s%n", i, result[i]));
		}

	}
	
	public static String[] process(String command, String opt, String dir ){
		try{
			String[] cmdarray = {command, opt, dir};
			Process p = Runtime.getRuntime().exec(cmdarray);
			InputStream out = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(out);
			BufferedReader bufReader = new BufferedReader(reader);
			
			List<String> lists = new ArrayList<String>();
			String line;
			while((line = bufReader.readLine()) != null){
				lists.add(line);
			}
			if(p.waitFor() != 0){
				throw new IllegalStateException(String.valueOf(p.exitValue() ) );
			}
			return lists.toArray(new String[lists.size()]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
