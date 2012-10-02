package ex02;

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalWatch extends Frame implements Runnable, ActionListener {
	private static final long serialVersionUID = 1L;
	private int hour;           //�������ϐ���錾
	private int minute;           //�������ϐ���錾
	private int second;           //�b�����ϐ���錾

	private Thread th;
	private PropertyDialog dialog = null;
	private Menu menuBox;
	private MenuItem menuProperty;
	private Image imageBuffer;
	private Graphics graphicBuffer;


	private String fontType = "DIALOG";
	private int fontSize = 48;
	private Color fontColor = Color.BLACK;
	private Color backgroundColor = Color.white;

	private int windowX = 400;
	private int windowY = 200;

	private String timeString;
	private MenuBar menuBar;

	// �t�H���g�̃f�t�H���g�̐ݒ�
	private Font fontSetting = new Font("TimesRoman", Font.PLAIN, 48);

	public DigitalWatch(String title) {
		//�^�C�g��
		super(title);

		addWindowListener(new CurrentWindowAdapter());
		// ���j���[�o�[���쐬����
		menuBar = new MenuBar();
		setMenuBar(menuBar);        

		menuBox = new Menu("���j���[");
		menuBox.addActionListener(this);
		menuBar.add(menuBox);

		menuProperty = new MenuItem("�v���p�e�B");
		menuBox.add(menuProperty);

		// �_�C�A���O�𐶐�����
		if (dialog == null) {
			dialog = new PropertyDialog(this);
		}

	}

	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY); //������
		minute = calendar.get(Calendar.MINUTE);      //������
		second= calendar.get(Calendar.SECOND);       //�b����
		return padZero(hour) + ":" + padZero(minute) + ":" + padZero(second);
	}

	public String padZero(int val) {
		if(val < 10){
			return "0" + val;
		} else {
			return "" + val;
		}
	}

	public void paint(Graphics g) {
		timeString = getTime();
		
		// �E�B���h�E�T�C�Y�̌v�Z
		windowX = calXSize();
		windowY = calYSize();
		setSize(windowX, windowY);

		imageBuffer = createImage(windowX, windowY);
		graphicBuffer = imageBuffer.getGraphics();

        graphicBuffer.setColor(backgroundColor);
        graphicBuffer.fillRect(0, 0, windowX, windowY);

        fontSetting = new Font(fontType, Font.PLAIN, fontSize);
        graphicBuffer.setFont(fontSetting);   
        graphicBuffer.setColor(fontColor);    
        graphicBuffer.drawString(timeString, 0, graphicBuffer.getFontMetrics().getAscent() + getInsets().top - getInsets().bottom);
		
		g.drawImage(imageBuffer, 0, 0,  this);
	}

	public int calXSize() {
		int x  = graphicBuffer.getFontMetrics().stringWidth(timeString);
		x += getInsets().left;
		x += getInsets().right;
		return x;
	}
	
	public int calYSize() {
		int y  = graphicBuffer.getFontMetrics().getAscent();
		y += graphicBuffer.getFontMetrics().getDescent();
		y += getInsets().top;
		return y;
	}
	

	public void update(Graphics g) {
		paint(g);
	}


	public void run() {
		while(true) {
			getTime();

			// �ĕ`��
			repaint();

			try {
				th.sleep(1000); // �X���[�v1�b
			} catch(InterruptedException e) {
				;
			}
		}
	}

	/**
	 * �t�H���g�^�C�v�擾
	 * @return �t�H���g�^�C�v
	 */
	public String getFontType() {
		return fontType;
	}


	/**
	 * �t�H���g�^�C�v�̃Z�b�g
	 * @param fontType �t�H���g�^�C�v
	 */
	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	/**
	 * �t�H���g�T�C�Y�̎擾
	 * @return �t�H���g�T�C�Y
	 */
	public Integer getFontSize() {
		return fontSize;
	}

	/**
	 * �t�H���g�T�C�Y�̃Z�b�g
	 * @param fontSize �t�H���g�T�C�Y
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * �t�H���g�J���[�̎擾
	 * @return �t�H���g�J���[
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * �t�H���g�J���[�̃Z�b�g
	 * @param fontColor �t�H���g�J���[
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * �w�i�F���擾���܂��B
	 * @return �w�i�F
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * �w�i�F���Z�b�g���܂�
	 * @param backgroundColor �w�i�F
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	/**
	 * @param args
	 */
	 public static void main(String[] args) {
		 DigitalWatch window = new DigitalWatch("�f�W�^�����v");
		 window.setSize(400, 200);
		 window.setResizable(false);
		 window.setVisible(true);
		 
		 window.th = new Thread(window);


		 window.imageBuffer = window.createImage(400, 200);
		 window.graphicBuffer = window.imageBuffer.getGraphics();

		 // �X���b�h�X�^�[�g
		 window.th.start();

	}


	 public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand() == "�v���p�e�B") {
			 dialog.setVisible(true);
		 } 
		 // if else 
	 }
	 
	
	public class CurrentWindowAdapter extends WindowAdapter {
			public void windowClosing(WindowEvent e) {   //�~�������ꂽ�Ƃ��̏���
				System.exit(0);
			}
	 }

}
