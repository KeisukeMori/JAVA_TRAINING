package ex03.swing;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JWindow;


class DigitalWatch extends JWindow {
	private static final long serialVersionUID = -5780612053215771853L;
	private static final Font DEFAULT_CLOCK_FONT = new Font("Arial", Font.BOLD, 40);

	private DateFormat sdf;
	private MainPanel mainPanel;
	private PropertyData propertyData;
//	private PropertyDialog propertyDialog;
	//	private ClockMenuBar menuBar;
//	private JMenu menu;
//	private JMenuItem propertyItem;
//	private JMenuItem exitItem;
	//private PropertyDialog dialog;
	private PropertyMenu pop;
	//private boolean isFontChanged;
	private Point watchPosition;

	DigitalWatch() {
		//super("DigitalWatch");
		setSize(500, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		//isFontChanged = true;
		sdf = new SimpleDateFormat("HH:mm:ss");

		propertyData = new PropertyData(DEFAULT_CLOCK_FONT, Color.BLACK, Color.WHITE);
		//propertyDialog = new PropertyDialog(this, propertyData);
		//menuBar = new ClockMenuBar(propertyDialog);
		//setJMenuBar(menuBar);

		mainPanel = new MainPanel(getSize());
		getContentPane().add(mainPanel);

		Timer timer = new Timer(true);
		timer.schedule(new PaintTimerTask(), 0, 500);
		pop = new PropertyMenu(this);


		addMouseListener(new MouseClickListener());
		addMouseMotionListener(new MouseMotionListener());

		setVisible(true);
	}

	//	void changeFont() {
	//	//	isFontChanged = true;
	//	}
	void changeFont() {
		propertyData.isFontChanged = true;
	}
	
	void changeFont(String fontName) {
		propertyData.setFontName(fontName);
	}

	void changeFontSize(int fontSize) {
		propertyData.setFontSize(fontSize);
	}

	void changeFontColor(String colorName) {
		propertyData.setFontColorName(colorName);
	}

	void changeBackColor(String colorName) {
		propertyData.setBackGroundColorName(colorName);
	}

	//	private class ClockMenuBar extends javax.swing.JMenuBar {
	//
	//		private static final long serialVersionUID = 1L;
	//
	//		ClockMenuBar(PropertyDialog dialog) {
	//			dialog = dialog;
	//			menu = new JMenu("Menu");
	//			propertyItem = new JMenuItem("Property");
	//			exitItem = new JMenuItem("Exit");
	//
	//			add(menu);
	//			menu.add(propertyItem);
	//			menu.add(exitItem);
	//
	//			MenuItemListener menuItemListener = new MenuItemListener();
	//			propertyItem.addActionListener(menuItemListener);
	//			exitItem.addActionListener(menuItemListener);
	//		}
	//	}
	//	private class MenuItemListener implements ActionListener {
	//		//	@Override
	//		public void actionPerformed(ActionEvent e) {
	//			Object source = e.getSource();
	//			if (source == propertyItem) {
	//				propertyDialog.setVisible(true);
	//			} else if (source == exitItem) {
	//				System.exit(0);
	//			}
	//		}
	//	}



	private class MainPanel extends JPanel {
		private static final long serialVersionUID = -3865314627366195394L;

		MainPanel(Dimension d) {
			super();
			setPreferredSize(d);
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(propertyData.getBackGroundColor());

			//addMouseListener(new DoubleClickListener());
		}

		@Override
		public void paintComponent(Graphics g) {
			setForeground(propertyData.getFontColor());
			setBackground(propertyData.getBackGroundColor());
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(propertyData.getFont());
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			Calendar calendar = Calendar.getInstance();
			String time_str = sdf.format(calendar.getTime());

			FontMetrics metrics = g2.getFontMetrics();
			Insets insets = DigitalWatch.this.getInsets();
			int strWidth = metrics.stringWidth(time_str);
			int strHeight = metrics.getDescent() + metrics.getAscent();
			int width = strWidth + insets.left + insets.right;
			int height = strHeight + insets.top;
			setResizeComponent(width, height);

			int x = (getWidth() / 2) - (strWidth / 2);
			int y = metrics.getAscent() + (getHeight() - metrics.getAscent()) / 2;
			g2.drawString(time_str, x, y);
		}

		private void setResizeComponent(int width, int height) {
			if (!propertyData.isFontChanged) {
				return;
			}
			DigitalWatch.this.setMinimumSize(new Dimension(width, height));
			DigitalWatch.this.setSize(new Dimension(width, height));
			propertyData.isFontChanged = false;
		}

		//		private class DoubleClickListener extends MouseAdapter {
		//			@Override
		//			public void mouseClicked(MouseEvent e) {
		//				if (e.getClickCount() >= 2) {
		//					if (e.getButton() == MouseEvent.BUTTON1) {
		//						Color fontColor = propertyData.getFontColor();
		//						Color backGroundColor = propertyData.getBackGroundColor();
		//						propertyData.setFontColor(reverseColor(fontColor));
		//						propertyData.setBackGroundColorValue(reverseColor(backGroundColor));
		//					} else if (e.getButton() == MouseEvent.BUTTON3) {
		//						if (menuBar.isVisible()) {
		//							menuBar.setVisible(false);
		//						} else {
		//							menuBar.setVisible(true);
		//						}
		//					}
		//				}
		//			}
		//
		//			private Color reverseColor(Color color) {
		//				return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
		//			}
		//		}
	}

	private class PaintTimerTask extends TimerTask {
		@Override
		public void run() {
			repaint();
		}
	}

	private class MouseClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				pop.show(e.getComponent(), e.getX(), e.getY());
			} 
		}
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
	}

	private class MouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
				if (watchPosition == null)
					watchPosition = e.getLocationOnScreen();

				int dx = e.getXOnScreen() - watchPosition.x;
				int dy = e.getYOnScreen() - watchPosition.y;

				watchPosition = e.getLocationOnScreen();

				Point newLocation = getLocation();
				newLocation.translate(dx, dy);

				calculateLocation(newLocation);
			}
		}
	}
	private void calculateLocation(Point newLocation) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		if (newLocation.x < 0) {
			newLocation.x = 0;
		}
		if (newLocation.y < 0) {
			newLocation.y = 0;
		}
		if (newLocation.x + getSize().width > screen.width) {
			newLocation.x = screen.width - getSize().width;
		}
		if (newLocation.y + getSize().height > screen.height) {
			newLocation.y = screen.height - getSize().height;
		}
		setLocation(newLocation);
	}
	public static void main(String args[]) {
		new DigitalWatch();

	}
}
