package interpret;


import java.lang.reflect.Constructor;
import java.util.HashMap;

public class ConstructorType {
  private HashMap<String, Constructor<?> > constructors;

  public ConstructorType() {
    constructors = new HashMap<String, Constructor<?> >();
  }

  public void saveConstructors(Constructor<?>[] cons) {
    for (Constructor<?> con : cons) {
      constructors.put(con.toString(), con);
    }
  }

  public Constructor<?> getConstructor(String key) {
    return constructors.get(key);
  }

  public void clearConstructors() {
    constructors.clear();
  }
}

