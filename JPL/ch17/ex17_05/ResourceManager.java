package ch17.ex17_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	boolean shutdown = false;
	
	public static void main(String[] args){
		int SIZE = 100;
		ResourceManager manager = new ResourceManager();
		String key;
		
		Resource[] resArr = new Resource[SIZE];
		for(int i = 0; i < SIZE; i++){
			key = String.valueOf("str: "+i);
			resArr[i] = manager.getResource(key);
			resArr[i].use(key, new Integer(i));
			key = null;
		}
		
		for(int i = SIZE; i < SIZE / 2; i++){
			resArr[i].release();
		}
		System.out.println("gc");
		Runtime.getRuntime().gc();
		manager.shutdown();
	}
	
	public ResourceManager(){
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
	}
	
	public synchronized void shutdown(){
		if(!shutdown){
			shutdown = true;
		}
		List<Reference<?>> refArr = new ArrayList(refs.keySet() );
		for(int i = 0; i < refArr.size(); i++){
			Reference<?> ref = refArr.get(i);
			Resource res = null;
			res = refs.get(ref);
			refs.remove(ref);
			res.release();
			ref.clear();
		}
	}
	
	public synchronized Resource getResource(Object key){
		if(shutdown){
			throw new IllegalStateException();
		}
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}
	
	public class ResourceImpl implements Resource {

		SoftReference<Object> keyRef;
		boolean needsRelease = false;
		
		ResourceImpl(Object key){
			this.keyRef = new SoftReference(key);
			//setting resource
			needsRelease = true;
		}

		public void use(Object key, Object... args) {
			if(keyRef.get() != null && !keyRef.get().equals(key)){
				throw new IllegalArgumentException("wrong key");
			}
		}


		public void release() {
			if(needsRelease){
				needsRelease = false;
				System.out.println("Resouce release :　" + keyRef.get());
				//release
				keyRef.clear();
			}
		}
	}
}
