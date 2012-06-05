package ex02;

import java.awt.*;
import java.awt.event.*;

public class PropertyDialog extends Dialog implements ActionListener, ItemListener {

	Panel propertyPanel = new Panel();

	String defaultFontColor;
	String defaultBackgroundColor;

	Choice choiceFontType = new Choice();
	Choice choiceFontSize = new Choice();
	Choice choiceFontColor = new Choice();
	Choice choiceBackgroundColor = new Choice();
	String tmpFontType = "DIALOG";
	int tmpFontSize = 10;
	Color tmpFontColor = Color.black;
	Color tmpBackgroundColor = Color.white;

	Button CancelButton = new Button("キャンセル");
	Button OKButton = new Button("OK");

	DigitalWatch digitalWatch;

	public PropertyDialog(Frame owner) {

		super(owner);

		digitalWatch = (DigitalWatch)owner;

		// 現在の時計の設定を取得する
		tmpFontType = digitalWatch.getFontType();
		tmpFontSize = digitalWatch.getFontSize();
		tmpFontColor = digitalWatch.getFontColor();
		tmpBackgroundColor = digitalWatch.getBackgroundColor();

		setLayout(new BorderLayout());
		setTitle("プロパティ");
		setSize(400, 150);
		setResizable(false);

		propertyPanel.setLayout(new GridLayout(5,2));

		this.add(propertyPanel, BorderLayout.NORTH);


		// リスナー登録
		choiceFontType.addItemListener(this);
		choiceFontSize.addItemListener(this);
		choiceFontColor.addItemListener(this);
		choiceBackgroundColor.addItemListener(this);
		CancelButton.addActionListener(this);
		OKButton.addActionListener(this);

		// フォントタイプ
		propertyPanel.add(new Label("フォントタイプ"));
		choiceFontType.add("DIALOG");
		choiceFontType.add("SERIF");
		choiceFontType.add("MONOSPACED");
		choiceFontType.select(digitalWatch.getFontType());
		propertyPanel.add(choiceFontType);

		// フォントサイズ
		propertyPanel.add(new Label("フォントサイズ "));
		choiceFontSize.add("10");
		choiceFontSize.add("20");
		choiceFontSize.add("40");
		choiceFontSize.add("80");
		choiceFontSize.add("120");
		choiceFontSize.select(digitalWatch.getFontSize().toString());
		propertyPanel.add(choiceFontSize);


		if (Color.black == digitalWatch.getFontColor()) {
			defaultFontColor = "black";
		} else if (Color.red == digitalWatch.getFontColor()) {
			defaultFontColor = "red";
		} else if (Color.green == digitalWatch.getFontColor()) {
			defaultFontColor = "green";
		} else if (Color.blue == digitalWatch.getFontColor()) {
			defaultFontColor = "blue";
		} else {
			defaultFontColor = "black";
		}

		// フォントカラー
		propertyPanel.add(new Label("フォントカラー "));
		choiceFontColor.add("黒");
		choiceFontColor.add("赤");
		choiceFontColor.add("緑");
		choiceFontColor.add("青");
		choiceFontColor.add("白");
		choiceFontColor.select(defaultFontColor);
		propertyPanel.add(choiceFontColor);


		if (Color.white == digitalWatch.getBackgroundColor()) {
			defaultBackgroundColor = "white";
		} else if (Color.black == digitalWatch.getBackgroundColor()) {
			defaultBackgroundColor = "black";
		} else if (Color.blue == digitalWatch.getBackgroundColor()) {
			defaultBackgroundColor = "blue";
		} else {
			defaultBackgroundColor = "white";
		}

		// 背景色
		propertyPanel.add(new Label("背景色 "));
		choiceBackgroundColor.add("白");
		choiceBackgroundColor.add("黒");
		choiceBackgroundColor.add("青");
		choiceBackgroundColor.select(defaultBackgroundColor);
		propertyPanel.add(choiceBackgroundColor);

		// キャンセルボタン
		propertyPanel.add(CancelButton);
		// OKボタン
		propertyPanel.add(OKButton);

		// ダイアログボックスを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);        	
			}
		});
	}

	private static final long serialVersionUID = 3853419917132576660L;

	@Override
	public void itemStateChanged(ItemEvent e) {
		//フォントタイプ
		if (choiceFontType == e.getSource()) {
			tmpFontType = e.getItem().toString();
		}
		//フォントサイズ
		else if (choiceFontSize == e.getSource()) {
			tmpFontSize = Integer.parseInt(e.getItem().toString());
		}
		//フォントカラー
		else if (choiceFontColor == e.getSource()) {
			if ("黒" == e.getItem()) {
				tmpFontColor = Color.black;
			} else if ("赤" == e.getItem()) {
				tmpFontColor = Color.red;
			} else if ("緑" == e.getItem()) {
				tmpFontColor = Color.green;
			} else if ("青" == e.getItem()) {
				tmpFontColor = Color.blue;
			} else if ("白" == e.getItem()) {
				tmpFontColor = Color.WHITE;
			} else {
				tmpFontColor = Color.black;
			}
		}
		else if (choiceBackgroundColor == e.getSource()) {
			if ("白" == e.getItem()) {
				tmpBackgroundColor = Color.white;
			} else if ("黒" == e.getItem()) {
				tmpBackgroundColor = Color.black;
			} else if ("青" == e.getItem()) {
				tmpBackgroundColor = Color.blue;
			} else {
				tmpBackgroundColor = Color.white;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if ("キャンセル" == e.getActionCommand()) {
			setVisible(false);
		}
		else if ("OK" == e.getActionCommand()) {
			digitalWatch.setFontType(tmpFontType);
			digitalWatch.setFontSize(tmpFontSize);
			digitalWatch.setFontColor(tmpFontColor);
			digitalWatch.setBackgroundColor(tmpBackgroundColor);
			setVisible(false);
		}
	}
}
