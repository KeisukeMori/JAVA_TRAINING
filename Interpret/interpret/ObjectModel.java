package interpret;

import java.util.HashMap;

public class ObjectModel {
  private static HashMap<String, Object> objects;

  public ObjectModel() {
    objects = new HashMap<String, Object>();
  }

  
  
  public void saveObject(String key, Object obj) {
		System.out.println("saveObject key" + key );  
    objects.put(key, obj);
  }

  public static Object getObject(String key) {
		System.out.println("key" + key );  
    return objects.get(key);
  }

  public boolean isEntried(String key) {
    return objects.containsKey(key);
  }

  public void clearObjects() {
    objects.clear();
  }
}


