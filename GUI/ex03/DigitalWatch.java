package ex03;

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalWatch extends Window implements Runnable, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private int hour;           //�������ϐ���錾
	private int minute;           //�������ϐ���錾
	private int second;           //�b�����ϐ���錾

	private Thread th;
	private Menu menuFont, menuFontSize, menuTextColor, menuBackgroundColor;
	private MenuItem menuFontPlain, menuFontBold, menuFontItalic, exit;
	private static String fontName = "Default";
	private static int fontStyle = Font.PLAIN;
	private int fontSize = 48;
	private Color fontColor = Color.BLACK;
	private Color backgroundColor = Color.white;
	private Point eventLocationOnScreen;

	private String timeString;
	//���j���[�t�H���g�A�C�e��
	PopupMenu pop = new PopupMenu("Property");

	// 
	public DigitalWatch() {
		//�^�C�g��
		super(new Frame());
		setSize(300, 300);
		//���j���[���ڂ̐���
		menuFont = new Menu("�t�H���g");
		menuFontSize = new Menu("�t�H���g�T�C�Y");
		menuTextColor = new Menu("�����F");
		menuBackgroundColor = new Menu("�w�i�F");
		exit = new MenuItem("����");
		//���j���[�ւ̒ǉ�
		pop.add(menuFont);
		pop.add(menuFontSize);
		pop.add(menuTextColor);
		pop.add(menuBackgroundColor);
		pop.add(exit);

		setStyle();
		setFontSize();
		setFontColor();
		setBackGroundColor();
		setExit();

		add(pop);
		/* add right click event */
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("mouse pressed");
					eventLocationOnScreen = e.getLocationOnScreen();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("mouse released");
					eventLocationOnScreen = null;
				}
			}
		});
		addMouseMotionListener(this);
		setVisible(true);
		th = new Thread(this);
		th.start();
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
		Image imageBuffer = createImage(getWidth(), getHeight());
		Graphics2D buf = (Graphics2D)imageBuffer.getGraphics();

		buf.setFont(new Font(fontName, fontStyle, fontSize));   

		FontMetrics metrics = buf.getFontMetrics();
		Insets insets = getInsets();
		int strWidth = metrics.stringWidth(timeString);
		int strHeight = metrics.getDescent() + metrics.getAscent();
		int width = strWidth + insets.left + insets.right;
		int height = strHeight + insets.top;
		setSize(width, height);

		setBackground(backgroundColor);
		buf.setColor(fontColor);
		setWindowLocation(getLocation());

		int x = 0;
		int y = metrics.getAscent();

		buf.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		buf.drawString(timeString, x, y);
		g.drawImage(imageBuffer, insets.left, insets.top, this);
	}


	public void update(Graphics g) {
		paint(g);
	}


	public void run() {
		while(true) {
			// �ĕ`��
			repaint();

			try {
				Thread.sleep(500); // �X���[�v1�b
			} catch(InterruptedException e) {}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new DigitalWatch();
	}

	private void setWindowLocation(Point newLocation) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		if (newLocation.x < 0)
			newLocation.x = 0;
		if (newLocation.y < 0)
			newLocation.y = 0;
		if (newLocation.x + getSize().width > screen.width)
			newLocation.x = screen.width - getSize().width;
		if (newLocation.y + getSize().height > screen.height)
			newLocation.y = screen.height - getSize().height;

		setLocation(newLocation);
	}

	public void mouseDragged(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
			System.out.println("mouse pressed");
			if (eventLocationOnScreen == null) {
				eventLocationOnScreen = e.getLocationOnScreen();
			}
			int dx = e.getXOnScreen() - eventLocationOnScreen.x;
			int dy = e.getYOnScreen() - eventLocationOnScreen.y;

			eventLocationOnScreen = e.getLocationOnScreen();

			Point newLocation = getLocation();
			newLocation.translate(dx, dy);

			setWindowLocation(newLocation);
		}
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void setStyle() {
		//�X�^�C��
		menuFontPlain = new MenuItem("�W��");
		menuFontBold = new MenuItem("����");
		menuFontItalic = new MenuItem("�Α�");
		menuFont.add(menuFontPlain);
		menuFont.add(menuFontBold);
		menuFont.add(menuFontItalic);
		menuFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontName = e.getActionCommand();
				if (fontName.equals("�W��")) {
					fontStyle = Font.PLAIN;
				} else if (fontName.equals("����")) {
					fontStyle = Font.BOLD;
				} else if (fontName.equals("�Α�")) {
					fontStyle = Font.ITALIC;
				}
				repaint();
			}
		});
	}

	public void setFontSize() {
		//�t�H���g�T�C�Y
		menuFontSize.add(new MenuItem("10"));
		menuFontSize.add(new MenuItem("20"));
		menuFontSize.add(new MenuItem("40"));
		menuFontSize.add(new MenuItem("80"));
		menuFontSize.add(new MenuItem("120"));
		menuFontSize.add(new MenuItem("240"));	
		menuFontSize.addActionListener(new ActionListener() {
			// �t�H���g�T�C�Y�̐ݒ�
			public void actionPerformed(ActionEvent e) {
				fontSize = Integer.valueOf(e.getActionCommand());
				repaint();
			}
		});
	}

	public void setFontColor() {
		//�����F
		menuTextColor.add(new MenuItem("Black"));
		menuTextColor.add(new MenuItem("Blue"));
		menuTextColor.add(new MenuItem("Cyan"));
		menuTextColor.add(new MenuItem("DarkGray"));
		menuTextColor.add(new MenuItem("Gray"));
		menuTextColor.add(new MenuItem("Green"));
		menuTextColor.add(new MenuItem("LightGray"));
		menuTextColor.add(new MenuItem("Magenta"));
		menuTextColor.add(new MenuItem("Orange"));
		menuTextColor.add(new MenuItem("Pink"));
		menuTextColor.add(new MenuItem("Red"));
		menuTextColor.add(new MenuItem("White"));
		menuTextColor.add(new MenuItem("Yellow"));
		menuTextColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Black")) {
					fontColor = Color.BLACK;
				} else if (e.getActionCommand().equals("Blue")) {
					fontColor = Color.BLUE;
				} else if (e.getActionCommand().equals("Red")) {
					fontColor = Color.RED;
				} else if (e.getActionCommand().equals("Cyan")) {
					fontColor = Color.CYAN;
				} else if (e.getActionCommand().equals("DarkGray")) {
					fontColor = Color.DARK_GRAY;
				} else if (e.getActionCommand().equals("Gray")) {
					fontColor = Color.GRAY;
				} else if (e.getActionCommand().equals("Green")) {
					fontColor = Color.GREEN;
				} else if (e.getActionCommand().equals("LightGray")) {
					fontColor = Color.LIGHT_GRAY;
				} else if (e.getActionCommand().equals("Magenta")) {
					fontColor = Color.MAGENTA;
				} else if (e.getActionCommand().equals("Orange")) {
					fontColor = Color.ORANGE;
				} else if (e.getActionCommand().equals("Pink")) {
					fontColor = Color.PINK;
				} else if (e.getActionCommand().equals("White")) {
					fontColor = Color.WHITE;
				} else if (e.getActionCommand().equals("Yellow")) {
					fontColor = Color.YELLOW;
				}                
				repaint();
			}
		});
	}

	public void setBackGroundColor() {
		//�w�i�F
		menuBackgroundColor.add(new MenuItem("Black"));
		menuBackgroundColor.add(new MenuItem("Blue"));
		menuBackgroundColor.add(new MenuItem("Cyan"));
		menuBackgroundColor.add(new MenuItem("DarkGray"));
		menuBackgroundColor.add(new MenuItem("Gray"));
		menuBackgroundColor.add(new MenuItem("Green"));
		menuBackgroundColor.add(new MenuItem("LightGray"));
		menuBackgroundColor.add(new MenuItem("Magenta"));
		menuBackgroundColor.add(new MenuItem("Orange"));
		menuBackgroundColor.add(new MenuItem("Pink"));
		menuBackgroundColor.add(new MenuItem("Red"));
		menuBackgroundColor.add(new MenuItem("White"));
		menuBackgroundColor.add(new MenuItem("Yellow"));
		menuBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Black")) {
					backgroundColor = Color.BLACK; 
				} else if (e.getActionCommand().equals("Blue")) {
					backgroundColor = Color.BLUE;
				} else if (e.getActionCommand().equals("Red")) {
					backgroundColor = Color.RED;
				} else if (e.getActionCommand().equals("Cyan")) {
					backgroundColor = Color.CYAN;
				} else if (e.getActionCommand().equals("DarkGray")) {
					backgroundColor = Color.DARK_GRAY;
				} else if (e.getActionCommand().equals("Gray")) {
					backgroundColor = Color.GRAY;
				} else if (e.getActionCommand().equals("Green")) {
					backgroundColor = Color.GREEN;
				} else if (e.getActionCommand().equals("LightGray")) {
					backgroundColor = Color.LIGHT_GRAY;
				} else if (e.getActionCommand().equals("Magenta")) {
					backgroundColor = Color.MAGENTA;
				} else if (e.getActionCommand().equals("Orange")) { 
					backgroundColor = Color.ORANGE;
				} else if (e.getActionCommand().equals("Pink")) {
					backgroundColor = Color.PINK;
				} else if (e.getActionCommand().equals("White")) {
					backgroundColor = Color.WHITE;
				} else if (e.getActionCommand().equals("Yellow")) {
					backgroundColor = Color.YELLOW;
				}
				repaint();
			}
		});
	}

	public void setExit() {
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}



	public class CurrentWindowAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {   //�~�������ꂽ�Ƃ��̏���
			System.exit(0);
		}
	}

}
