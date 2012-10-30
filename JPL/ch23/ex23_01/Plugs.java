package ch23.ex23_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author kmori
 *
 */
public class Plugs {

	public static void main(String [] args){
		Plugs p = new Plugs();
		try {
			Process proc = Runtime.getRuntime().exec("ping www.google.co.jp");
			p.plugTogether(System.in, proc.getOutputStream());
			p.plugTogether(System.out, proc.getInputStream());
			p.plugTogether(System.err, proc.getErrorStream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		p.shutdown();
	}
	
	
	
	private ExecutorService executorSrv = Executors.newCachedThreadPool();
	
	public Future<Void> plugTogether(InputStream in, OutputStream out){
		return executorSrv.submit(new Plug(in, out));
	}
	
	public Future<Void> plugTogether(OutputStream out, InputStream in){
		return plugTogether(in, out);
	}
	
	private class Plug implements Callable<Void> {
		InputStream in;
		OutputStream out;
		private Plug(InputStream in, OutputStream out){
			this.in = in;
			this.out = out;
		}
		public Void call() throws Exception{
			while(true){
				while(in.available() > 0){
					out.write(in.read());
				}
				out.flush();
			}
		}
	}
	
	public void shutdown(){
		executorSrv.shutdownNow();
	}
	
	private void debug(String str){
		System.out.println();
	}
}
