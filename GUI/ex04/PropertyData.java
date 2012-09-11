package ex04;

import java.awt.*;
import java.io.*;

class PropertyData implements Serializable {
	private static final long serialVersionUID = 5972770213603221677L;
	private Font font;
	private Color fontColor;
	private Color backColor;

	static final String[] fontSize = {"10", "20", "40", "80", "160", "320"};
	static final String[] colorString = ColorUtils.getColorStrings();
	static final String[] fontSet = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	// コンストラクタ
	PropertyData() {
		font = new Font("Default", Font.PLAIN, 30);
		fontColor = Color.BLACK;
		backColor = Color.WHITE;
	}

	PropertyData(PropertyData propertyData) {
		font = propertyData.font;
		fontColor = propertyData.fontColor;
		backColor = propertyData.backColor;
	}

	void setFont(Font font) {
		this.font = font;
	}

	Font getFont() {
		return font;
	}

	void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	Color getFontColor() {
		return fontColor;
	}

	void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	Color getBackColor() {
		return backColor;
	}

	int fontValue() {
		return getIdxValue(font.getName(), fontSet);
	}

	int fontSizeValue() {
		return getIdxValue(String.valueOf(font.getSize()), fontSize);
	}

	int fontColorValue() {
		return getIdxValue(ColorUtils.getStringFromColor(fontColor), colorString);
	}

	int backColorValue() {
		return getIdxValue(ColorUtils.getStringFromColor(backColor), colorString);
	}

	private int getIdxValue(String key, String[] targets) {
		int index = -1;
		for (int i = 0; i < targets.length; ++i) {
			if (key.equals(targets[i])) {
				index = i;
				break;
			}
		}
		return index;
	}

	static byte[] propertyToByte(PropertyData propertyData) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

		try {
			ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
			objOut.writeObject(propertyData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteOut.toByteArray();
	}

	static PropertyData byteToProperty(byte[] bytes) {
		PropertyData propertyData = null;
		ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);


		try {
			ObjectInputStream objIn = new ObjectInputStream(byteIn);
			propertyData = (PropertyData)objIn.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return propertyData;
	}
}
