package ch17.ex17_04;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	final Thread reaper;
	boolean shutdown = false;
	
	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
		reaper = new ReaperThread();
		reaper.start();
	}
	
	public synchronized void shutdown() {
		if(!shutdown){
			shutdown = true;
			reaper.interrupt();
		}
	}
	
	public synchronized Resource getResource(Object key) {
		if(shutdown) {
			throw new IllegalStateException();
		}
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}
	
	public static void main(String[] args) {
		int SIZE = 1000000;
		ResourceManager manager = new ResourceManager();
		String key;
		
		Resource[] resArr = new Resource[SIZE];
		for(int i = 0; i < SIZE; i++){
			key = String.valueOf("str:"+i);
			resArr[i] = manager.getResource(key);
			resArr[i].use(key, new Integer(i));
			key = null;
		}
		
		for(int i = SIZE; i < SIZE / 2; i++) {
			resArr[i].release();
		}
		System.out.println("gc Run.");
		Runtime.getRuntime().gc();
		manager.shutdown();
	}
	
	public class ResourceImpl implements Resource {

		SoftReference<Object> keyRef;
		boolean needsRelease = false;
		
		ResourceImpl(Object key) {
			this.keyRef = new SoftReference(key);
			//setting resource
			needsRelease = true;
		}

		public void use(Object key, Object... args) {
			if(keyRef.get() != null && !keyRef.get().equals(key)) {
				throw new IllegalArgumentException("wrong key");
			}
		}


		public void release() {
			if(needsRelease) {
				needsRelease = false;
				//release
				keyRef.clear();
			}
		}
	}
	
	public class ReaperThread extends Thread {
		
		public ReaperThread() {
			super();
			setDaemon(false);
		}
		
		public void run() {
			System.out.println("start");
			// 割り込まれるまで実行
			while(!refs.isEmpty() || !shutdown) {
				try{
					Reference<?> ref = queue.remove();
					Resource res = null;
					synchronized(ResourceManager.this) {
						res = refs.get(ref);
						refs.remove(ref);
						System.out.println("thread clear");
					}
					res.release();
					ref.clear();
				}
				catch(InterruptedException e) {
					// 何もしない
				}
			}
			System.out.println("thread　end");
		}

	}
}
