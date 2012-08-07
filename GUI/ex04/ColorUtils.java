package ex04;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

class ColorUtils {
  private static final String[] colorStrings;

  static {
    Class<Color> c = Color.class;
    Field[] fields = c.getFields();
    Set<String> colorStrSet = new TreeSet<String>();
    for (Field field : fields) {
      if (field.getDeclaringClass() == Color.class) {
        if (Character.isUpperCase(field.getName().charAt(0))) {
          colorStrSet.add(field.getName());
        }
      }
    }

    colorStrings = new String[colorStrSet.size()];
    int index = 0;
    for (String colorStr : colorStrSet) {
      colorStrings[index++] = colorStr;
    }
  }


  static String[] getColorStrings() {
    return colorStrings;
  }

  static Color getColorInstance(String colorName) {
    Color returnColor = null;

    for (String colorString : colorStrings) {
      if (colorString.equals(colorName)) {
        Class<Color> colorClass = Color.class;
        try {
          Field filed = colorClass.getField(colorName);
          returnColor = (Color)filed.get(Color.WHITE);
        } catch (NoSuchFieldException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

    return returnColor;
  }

  static String getStringFromColor(Color c) {
    String returnColor = null;
    for (int i = 0; i < colorStrings.length; ++i) {
      if (getColorInstance(colorStrings[i]).equals(c)) {
    	  returnColor = colorStrings[i];
        break;
      }
    }
    return returnColor;
  }
}
