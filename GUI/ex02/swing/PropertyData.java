package ex02.swing;

import java.awt.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class PropertyData {
	public static final String[] fontSet;
	public static final String[] colorSet;
	public static final String[] fontSizes =
	{"10", "20", "40", "80", "160", "320"};

	private Font font;
	private Color fontColor;
	private Color backGroundColor;

	PropertyData(Font font, Color fontColor, Color backGroundColor) {
		this.font = font;
		this.fontColor = fontColor;
		this.backGroundColor = backGroundColor;
	}
	
	static {
		fontSet = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		//リフレクション
		List<Field> colorFields = new ArrayList<Field>(Arrays.asList(Color.class.getFields()));
		Iterator<Field> it = colorFields.iterator();
		while (it.hasNext()) {
			Field field = it.next();
			if ( !(field.getType().equals(Color.class)
					&& Character.isUpperCase(field.getName().charAt(0)))){
				it.remove();
			}
		}
		colorSet = new String[colorFields.size()];
		for (int i = 0; i < colorFields.size(); ++i)
			colorSet[i] = colorFields.get(i).getName();
	}

	Font getFont() {
		return font;
	}

	void setFontName(String fontName) {
		int fontStyle = font.getStyle();
		int fontSize  = font.getSize();
		font = new Font(fontName, fontStyle, fontSize);
	}

	void setFontSize(int fontSize) {
		String fontName = font.getName();
		int fontStyle = font.getStyle();
		font = new Font(fontName, fontStyle, fontSize);
	}

	Color getFontColor() {
		return fontColor;
	}

	void setFontColorName(String colorName) {
		fontColor = getColorObj(colorName);
	}

	void setFontColor(Color color) {
		fontColor = color;
	}

	Color getBackGroundColor() {
		return backGroundColor;
	}

	void setBackGroundColorName(String colorName) {
		backGroundColor = getColorObj(colorName);
	}

	void setBackGroundColorValue(Color color) {
		backGroundColor = color;
	}

	
	Color getColorObj(String colorName) {
		Color value = null;

		for (String colorStr : colorSet) {
			if (colorStr.equals(colorName)) {
				Class<Color> colorClass = Color.class;
				try {
					Field field = colorClass.getField(colorName);
					value = (Color)field.get(Color.WHITE);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}
}
