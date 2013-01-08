package ex04.swing;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


class DigitalWatch extends JFrame {
	private static final long serialVersionUID = -5780612053215771853L;
	private static final Font DEFAULT_CLOCK_FONT = new Font("Arial", Font.BOLD, 40);

	private DateFormat sdf;
	private MainPanel mainPanel;
	private PropertyData propertyData;
	private PropertyDialog propertyDialog;
	private ClockMenuBar menuBar;
	private JMenu menu;
	private JMenuItem propertyItem;
	private JMenuItem exitItem;
	//private PropertyDialog dialog;

	private boolean isFontChanged;

	DigitalWatch() {
		super("DigitalWatch");
		setSize(300, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		isFontChanged = true;
		sdf = new SimpleDateFormat("HH:mm:ss");

		propertyData = new PropertyData(DEFAULT_CLOCK_FONT, Color.BLACK, Color.WHITE);
		propertyDialog = new PropertyDialog(this, propertyData);
		menuBar = new ClockMenuBar(propertyDialog);
		setJMenuBar(menuBar);

		mainPanel = new MainPanel(getSize());
		getContentPane().add(mainPanel);

		Timer timer = new Timer(true);
		timer.schedule(new PaintTimer(), 0, 500);

		setVisible(true);
	}

	void changeFont() {
		isFontChanged = true;
	}

	private class ClockMenuBar extends javax.swing.JMenuBar {

		private static final long serialVersionUID = 1L;

		ClockMenuBar(PropertyDialog dialog) {
			dialog = dialog;
			menu = new JMenu("Menu");
			propertyItem = new JMenuItem("Property");
			exitItem = new JMenuItem("Exit");

			add(menu);
			menu.add(propertyItem);
			menu.add(exitItem);

			MenuItemListener menuItemListener = new MenuItemListener();
			propertyItem.addActionListener(menuItemListener);
			exitItem.addActionListener(menuItemListener);
		}
	}
	private class MenuItemListener implements ActionListener {
		//	@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == propertyItem) {
				propertyDialog.setVisible(true);
			} else if (source == exitItem) {
				System.exit(0);
			}
		}
	}



	private class MainPanel extends JPanel {
		private static final long serialVersionUID = -3865314627366195394L;

		MainPanel(Dimension d) {
			super();
			setPreferredSize(d);
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(propertyData.getBackGroundColor());

			addMouseListener(new DoubleClickListener());
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
			int height = strHeight + insets.top + menuBar.getHeight();
			setSizeOneTime(width, height);

			int x = (getWidth() / 2) - (strWidth / 2);
			int y = metrics.getAscent() + (getHeight() - metrics.getAscent()) / 2;
			g2.drawString(time_str, x, y);
		}

		private void setSizeOneTime(int width, int height) {
			if (!isFontChanged)
				return;

			DigitalWatch.this.setMinimumSize(new Dimension(width, height));
			DigitalWatch.this.setSize(new Dimension(width, height));
			isFontChanged = false;
		}

		private class DoubleClickListener extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						Color fontColor = propertyData.getFontColor();
						Color backGroundColor = propertyData.getBackGroundColor();
						propertyData.setFontColor(reverseColor(fontColor));
						propertyData.setBackGroundColorValue(reverseColor(backGroundColor));
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						if (menuBar.isVisible()) {
							menuBar.setVisible(false);
						} else {
							menuBar.setVisible(true);
						}
					}
				}
			}

			private Color reverseColor(Color color) {
				return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
			}
		}
	}

	private class PaintTimer extends TimerTask {
		@Override
		public void run() {
			repaint();
		}
	}

	public static void main(String args[]) {
		new DigitalWatch();

	}
}
