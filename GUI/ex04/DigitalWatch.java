package ex04;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.prefs.*;

class DigitalWatch extends Frame implements ActionListener, MouseMotionListener, Runnable {
	private DateFormat dateFormat;
	private Thread thread;
	private PropertyData propertyData;
	private MenuItem propertyMenu;
	private MenuItem exitMenu;

	static final String PRE_PROPERTY_KEY = "Property";
	static final String PRE_POINT_KEY = "Location";

	private Point watchPosition;
	private Preferences preference;
	
	private MenuBar menuBar;
	private Menu menu;
	private PropertyDialog propertyDialog;
//	private PropertyPopupMenu pop;

	DigitalWatch() {
		super("DigitalWatch");
		setResizable(false);
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		setSize(100,100);

		// 閉じる処理
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveProperty();
				System.exit(0);
			}
		});
		// メニューバー
		addMenu();

		// 設定値取得
		preference = Preferences.userNodeForPackage(getClass());
		try {
			Object obj = Preference.getObject(preference, PRE_PROPERTY_KEY);
			if (obj != null) {
				propertyData = (PropertyData)obj;
			} else {
				propertyData = new PropertyData();
			}
			obj = Preference.getObject(preference, PRE_POINT_KEY);
			if (obj != null) {
				setLocation((Point)obj);
			} else {
				setLocationRelativeTo(null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		propertyDialog = new PropertyDialog(this, "property");
		propertyDialog.init();

//		pop = new PropertyPopupMenu();
//		pop.init();
//		add(pop);

		// マウスイベント
		addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				/* right click event */
//				if (e.getButton() == MouseEvent.BUTTON3) {
//					pop.show(e.getComponent(), e.getX(), e.getY());
//				}
//			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					watchPosition = e.getLocationOnScreen();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					watchPosition = null;
				}
			}
		});

		addMouseMotionListener(this);

		setBackground(propertyData.getBackColor());
		setVisible(true);

		thread = new Thread(this);
		thread.start();
	}

	// 表示
	public void paint(Graphics g) {
		Image imageBuffer = createImage(getWidth(), getHeight());
		Graphics2D buffer = (Graphics2D)imageBuffer.getGraphics();

		Calendar calendar = Calendar.getInstance();
		String time_str = dateFormat.format(calendar.getTime());

		buffer.setFont(propertyData.getFont());
		FontMetrics metrics = buffer.getFontMetrics();
		Insets insets = getInsets();
		int strWidth = metrics.stringWidth(time_str);
		int strHeight = metrics.getDescent() + metrics.getAscent();
		int width = strWidth + insets.left + insets.right;
		int height = strHeight + insets.top;
		setSize(width, height);

		int x = 0;
		int y = metrics.getAscent();

//		buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		buffer.setColor(propertyData.getFontColor());
		buffer.drawString(time_str, x, y);
		setBackground(propertyData.getBackColor());

		g.drawImage(imageBuffer, insets.left, insets.top, this);
	}

	public void run() {
		while (true) {
			//再描画
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 更新
	public void update(Graphics g) {
		paint(g);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == propertyMenu) {
			propertyDialog.setVisible(true);
		} else if (source == exitMenu) {
			saveProperty();
			System.exit(0);
		} else {
			// nothing to do
		}
	}
	
	private void addMenu() {
		menuBar = new MenuBar();
		menu = new Menu("menu");
		setMenuBar(menuBar);
		menu.addActionListener(this);
		menuBar.add(menu);
		propertyMenu = new MenuItem("property");
		exitMenu = new MenuItem("close");
		propertyMenu.addActionListener(this);
		exitMenu.addActionListener(this);
		menu.add(propertyMenu);
		menu.add(exitMenu);
	}


	void getProperty(PropertyData property) {
		this.propertyData = property;
	}

	PropertyData getCurrentProperty() {
		return new PropertyData(propertyData);
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
			if (watchPosition == null)
				watchPosition = e.getLocationOnScreen();

			int dx = e.getXOnScreen() - watchPosition.x;
			int dy = e.getYOnScreen() - watchPosition.y;

			watchPosition = e.getLocationOnScreen();

			Point newLocation = getLocation();
			newLocation.translate(dx, dy);

			setLocation(newLocation);
		}
	}
	
	void changeWatchFont(String fontName) {
		setWatchFont(fontName, propertyData.getFont().getStyle(), propertyData.getFont().getSize());
	}

	void changeWatchFontSize(int fontSize) {
		setWatchFont(propertyData.getFont().getFontName(), propertyData.getFont().getStyle(), fontSize);
	}

	void changeFontColor(String fontColorName) {
		propertyData.setFontColor(ColorUtils.getColorInstance(fontColorName));
	}

	void changeBackColor(String backColorName) {
		propertyData.setBackColor(ColorUtils.getColorInstance(backColorName));
	}


	private void setWatchFont(String name, int style, int size) {
		propertyData.setFont(new Font(name, style, size));
	}
	
	void saveProperty() {
		try {
			Preference.putObject(preference, PRE_PROPERTY_KEY, propertyData);
			Preference.putObject(preference, PRE_POINT_KEY, getLocation());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DigitalWatch();
	}
}
