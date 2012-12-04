package ex03.swing;

import java.awt.event.*;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class PropertyMenu extends JPopupMenu implements ActionListener {
	private static final long serialVersionUID = -5886033453588110137L;

	private DigitalWatch parentWindow;
	private JMenu fontMenu;
	private JMenu fontSizeMenu;
	private JMenu fontColorMenu;
	private JMenu backGroundColorMenu;
	private JMenuItem exitMenu;

	private JMenuItem[] fontMenuItems;
	private JMenuItem[] fontSizeItems;
	private JMenuItem[] fontColorItems;
	private JMenuItem[] backColorItems;

	PropertyMenu(DigitalWatch parent) {
		this.parentWindow = parent;

		fontMenu = new JMenu("Font");
		add(fontMenu);

		fontSizeMenu = new JMenu("FontSize");
		add(fontSizeMenu);

		fontColorMenu = new JMenu("FontColor");
		add(fontColorMenu);

		backGroundColorMenu = new JMenu("BackgroundColor");
		add(backGroundColorMenu);

		exitMenu = new JMenuItem("Exit");
		add(exitMenu);

		addItems();

		fontSizeMenu.addActionListener(this);
		exitMenu.addActionListener(this);
	}
	
	private void addItems() {
		// font
		fontMenuItems = new JMenuItem[PropertyData.fontSet.length];
		for (int i = 0; i < fontMenuItems.length; ++i) {
			fontMenuItems[i] = new JMenuItem(PropertyData.fontSet[i]);
			fontMenu.add(fontMenuItems[i]);
			fontMenuItems[i].addActionListener(this);
		}

		// fontsize
		fontSizeItems = new JMenuItem[PropertyData.fontSizes.length];
		for (int i = 0; i < fontSizeItems.length; ++i) {
			fontSizeItems[i] = new JMenuItem(PropertyData.fontSizes[i]);
			fontSizeMenu.add(fontSizeItems[i]);
			fontSizeItems[i].addActionListener(this);
		}

		// fontcolor
		fontColorItems = new JMenuItem[PropertyData.colorSet.length];
		for (int i = 0; i < fontColorItems.length; ++i) {
			fontColorItems[i] = new JMenuItem(PropertyData.colorSet[i]);
			fontColorMenu.add(fontColorItems[i]);
			fontColorItems[i].addActionListener(this);
		}

		// backfontcolor
		backColorItems = new JMenuItem[PropertyData.colorSet.length];
		for (int i = 0; i < backColorItems.length; ++i) {
			backColorItems[i] = new JMenuItem(PropertyData.colorSet[i]);
			backGroundColorMenu.add(backColorItems[i]);
			backColorItems[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == exitMenu)
			System.exit(0);

		for (JMenuItem fontItem : fontMenuItems) {
			if (source == fontItem) {
				parentWindow.changeFont(fontItem.getText());
			}
		}

		for (JMenuItem fontSizeItem : fontSizeItems) {
			if (source == fontSizeItem) {
				int fontSize = Integer.valueOf(fontSizeItem.getText());
				parentWindow.changeFontSize(fontSize);
			}
		}

		for (JMenuItem fontColorItem : fontColorItems) {
			if (source == fontColorItem) {
				parentWindow.changeFontColor(fontColorItem.getText());
			}
		}

		for (JMenuItem backColorItem : backColorItems) {
			if (source == backColorItem) {
				parentWindow.changeBackColor(backColorItem.getText());
			}
		}
	}
}

