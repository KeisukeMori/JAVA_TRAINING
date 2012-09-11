package interpret;

import java.lang.reflect.*;
import java.util.HashMap;

public class FieldType {

  private HashMap<String, Field> fields;

  public FieldType() {
    fields = new HashMap<String, Field>();
  }

  public void saveField(String fieldName, Field field) {
    fields.put(fieldName, field);
  }

  public Field getField(String fieldName) {
    return fields.get(fieldName);
  }

  public void clearFields() {
    fields.clear();
  }
}
