package interpret;

import java.util.HashMap;

public class ObjectType {
  private HashMap<String, Object> objects;

  public ObjectType() {
    objects = new HashMap<String, Object>();
  }
  public void saveObject(String key, Object obj) {
		System.out.println("saveObject key" + key );  
    objects.put(key, obj);
  }

  public Object getObject(String key) {
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


