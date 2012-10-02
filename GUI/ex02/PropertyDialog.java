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

	Button CancelButton = new Button("�L�����Z��");
	Button OKButton = new Button("OK");

	DigitalWatch digitalWatch;

	public PropertyDialog(Frame owner) {

		super(owner);

		digitalWatch = (DigitalWatch)owner;

		// ���݂̎��v�̐ݒ���擾����
		tmpFontType = digitalWatch.getFontType();
		tmpFontSize = digitalWatch.getFontSize();
		tmpFontColor = digitalWatch.getFontColor();
		tmpBackgroundColor = digitalWatch.getBackgroundColor();

		setLayout(new BorderLayout());
		setTitle("�v���p�e�B");
		setSize(400, 150);
		setResizable(false);

		propertyPanel.setLayout(new GridLayout(5,2));

		this.add(propertyPanel, BorderLayout.NORTH);


		// ���X�i�[�o�^
		choiceFontType.addItemListener(this);
		choiceFontSize.addItemListener(this);
		choiceFontColor.addItemListener(this);
		choiceBackgroundColor.addItemListener(this);
		CancelButton.addActionListener(this);
		OKButton.addActionListener(this);

		// �t�H���g�^�C�v
		propertyPanel.add(new Label("�t�H���g�^�C�v"));
		choiceFontType.add("DIALOG");
		choiceFontType.add("SERIF");
		choiceFontType.add("MONOSPACED");
		choiceFontType.select(digitalWatch.getFontType());
		propertyPanel.add(choiceFontType);

		// �t�H���g�T�C�Y
		propertyPanel.add(new Label("�t�H���g�T�C�Y "));
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

		// �t�H���g�J���[
		propertyPanel.add(new Label("�t�H���g�J���[ "));
		choiceFontColor.add("��");
		choiceFontColor.add("��");
		choiceFontColor.add("��");
		choiceFontColor.add("��");
		choiceFontColor.add("��");
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

		// �w�i�F
		propertyPanel.add(new Label("�w�i�F "));
		choiceBackgroundColor.add("��");
		choiceBackgroundColor.add("��");
		choiceBackgroundColor.add("��");
		choiceBackgroundColor.select(defaultBackgroundColor);
		propertyPanel.add(choiceBackgroundColor);

		// �L�����Z���{�^��
		propertyPanel.add(CancelButton);
		// OK�{�^��
		propertyPanel.add(OKButton);

		// �_�C�A���O�{�b�N�X�����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);        	
			}
		});
	}

	private static final long serialVersionUID = 3853419917132576660L;


	public void itemStateChanged(ItemEvent e) {
		//�t�H���g�^�C�v
		if (choiceFontType == e.getSource()) {
			tmpFontType = e.getItem().toString();
		}
		//�t�H���g�T�C�Y
		else if (choiceFontSize == e.getSource()) {
			tmpFontSize = Integer.parseInt(e.getItem().toString());
		}
		//�t�H���g�J���[
		else if (choiceFontColor == e.getSource()) {
			if ("��" == e.getItem()) {
				tmpFontColor = Color.black;
			} else if ("��" == e.getItem()) {
				tmpFontColor = Color.red;
			} else if ("��" == e.getItem()) {
				tmpFontColor = Color.green;
			} else if ("��" == e.getItem()) {
				tmpFontColor = Color.blue;
			} else if ("��" == e.getItem()) {
				tmpFontColor = Color.WHITE;
			} else {
				tmpFontColor = Color.black;
			}
		}
		else if (choiceBackgroundColor == e.getSource()) {
			if ("��" == e.getItem()) {
				tmpBackgroundColor = Color.white;
			} else if ("��" == e.getItem()) {
				tmpBackgroundColor = Color.black;
			} else if ("��" == e.getItem()) {
				tmpBackgroundColor = Color.blue;
			} else {
				tmpBackgroundColor = Color.white;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {

		if ("�L�����Z��" == e.getActionCommand()) {
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
