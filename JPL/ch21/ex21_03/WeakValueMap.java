package ch21.ex21_03;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class WeakValueMap<K, V> implements Map<K, V>{

	private HashMap<K, WeakReference<V>> data  = new HashMap<K, WeakReference<V>>();

	public void clear() {
		data.clear();
	}

	public boolean containsKey(Object key) {
		return data.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return data.containsValue(value);
	}

	public Set<java.util.Map.Entry<K,V>> entrySet() {
		Set<Map.Entry<K, WeakReference<V>>> set = data.entrySet();
		HashMap<K, V> returnSetMap = new HashMap<K, V>();
		for(Map.Entry<K, WeakReference<V>> entry : set){
			if(entry.getValue().get() != null){
				returnSetMap.put(entry.getKey(), entry.getValue().get());
			}
		}
		if(!returnSetMap.isEmpty()){
			return returnSetMap.entrySet();
		}
		return null;
	}

	public V get(Object key) {
		return data.get(key).get();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public Set<K> keySet() {
		return data.keySet();
	}

	public WeakReference<V> put(K key, WeakReference<V> value) {
		return data.put(key, value);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()){
			data.put(entry.getKey(), new WeakReference<V>(entry.getValue()));
		}
	}

	public V remove(Object key) {
		WeakReference<V> val = data.remove(key);
		if(val != null){
			return val.get();
		}
		return null;
	}

	public int size() {
		return data.size();
	}

	public Collection<V> values() {
		Collection<WeakReference<V>> collection = data.values();
		LinkedList<V> retList = new LinkedList<V>(); 
		for(WeakReference<V> e : collection){
			if(e.get() != null){
				retList.add(e.get());
			}
		}
		if(retList.size() > 0){
			return retList;
		}
		return null;
	}


	public V put(K key, V value) {
		WeakReference<V> ret = data.put(key, new WeakReference<V>(value));
		return ret.get();
	}


	
	
}
