package ex02.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

class PropertyDialog extends JDialog {

	private static final long serialVersionUID = -7013056218854402641L;
	private DigitalWatch digitalWatch;
	private PropertyData propertyData;

	// font
	private JLabel fontLabel;
	private DefaultComboBoxModel fontModel;
	private JComboBox fontComboBox;
	// fontsize
	private JLabel fontSizeLabel;
	private DefaultComboBoxModel fontSizeModel;
	private JComboBox fontSizeComboBox;
	// color
	private JLabel colorLabel;
	private DefaultTableModel colorModel;
	private JTable colorTable;
	private JScrollPane colorScroll;
	// backgroundcolor
	private JLabel backGroundLabel;
	private DefaultTableModel backGroundModel;
	private JTable backGroundTable;
	private JScrollPane backGroundScroll;
	// ok
	private JButton okButton;

	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private static final Font commonFont = new Font("Arial", Font.PLAIN, 20);

	PropertyDialog(DigitalWatch ownerWindow, PropertyData propertyData) {
		super(ownerWindow);
		this.digitalWatch = ownerWindow;
		this.propertyData = propertyData;
		setModal(true);
		setTitle("Property");
		setSize(500,400);
		setResizable(false);
		setLocationRelativeTo(null);
		
		int colorLen = PropertyData.colorSet.length;
		String[][] tableData = new String[colorLen][1];
		for (int i = 0; i < colorLen; i++) {
			tableData[i][0] = PropertyData.colorSet[i];
		}

		fontLabel = new JLabel("font");
		fontLabel.setFont(commonFont);
		fontModel = new DefaultComboBoxModel(PropertyData.fontSet);
		fontComboBox   = new JComboBox(fontModel);
		fontComboBox.setPreferredSize(new Dimension(185, 20));

		fontSizeLabel = new JLabel("fontsize");
		fontSizeLabel.setFont(commonFont);
		fontSizeModel = new DefaultComboBoxModel(PropertyData.fontSizes);
		fontSizeComboBox   = new JComboBox(fontSizeModel);
		fontSizeComboBox.setPreferredSize(new Dimension(185, 20));

		colorLabel = new JLabel("color");
		colorLabel.setFont(commonFont);
		colorModel = new DefaultTableModel(tableData, new String[]{""});
		colorTable = new JTable(colorModel);
		colorTable.setTableHeader(null);
		colorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorTable.getSelectionModel().addListSelectionListener(new SelectFontColorListener());
		colorTable.getColumnModel().getColumn(0).setCellRenderer(new ColorTableCellRenderer());
		colorTable.setDefaultEditor(Object.class, null);
		colorScroll = new JScrollPane(colorTable);
		colorScroll.setPreferredSize(new Dimension(185, 100));

		backGroundLabel = new JLabel("backgroundcolor");
		backGroundLabel.setFont(commonFont);
		backGroundModel = new DefaultTableModel(tableData, new String[]{""});
		backGroundTable = new JTable(backGroundModel);
		backGroundTable.setTableHeader(null);
		backGroundTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		backGroundTable.getSelectionModel().addListSelectionListener(new SelectBackGroundListener());
		backGroundTable.getColumnModel().getColumn(0).setCellRenderer(new ColorTableCellRenderer());
		backGroundTable.setDefaultEditor(Object.class, null);
		backGroundScroll = new JScrollPane(backGroundTable);
		backGroundScroll.setPreferredSize(new Dimension(185, 100));

		okButton = new JButton("OK");

		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);

		addParts();
		PropertyActionListener listener = new PropertyActionListener();
		fontComboBox.addActionListener(listener);
		fontSizeComboBox.addActionListener(listener);
		okButton.addActionListener(listener);
	}

	private void addComponent(Component comp, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		layout.setConstraints(comp, constraints);
		add(comp);
	}

	private void addParts() {
		constraints.anchor = GridBagConstraints.NORTHWEST;
		addComponent(fontLabel, 0, 0);
		addComponent(fontComboBox, 1, 0);
		addComponent(fontSizeLabel, 0, 1);
		addComponent(fontSizeComboBox, 1, 1);
		addComponent(colorLabel, 0, 2);
		addComponent(colorScroll, 1, 2);
		addComponent(backGroundLabel, 0, 3);
		addComponent(backGroundScroll, 1, 3);
		addComponent(okButton, 1, 4);
	}
	
	// colorラベルおよび背景色を設定
	public class ColorTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1601402918980924648L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
			Color color = propertyData.getColorObj(PropertyData.colorSet[row]);
			label.setBackground(color);
			label.setFont(new Font("Arial", Font.BOLD, 12));

			return label;
		}
	}
	
	private class SelectFontColorListener implements ListSelectionListener {
		//    @Override
		public void valueChanged(ListSelectionEvent e) {
			int selectedVal = colorTable.convertRowIndexToModel(colorTable.getSelectedRow());
			String colorName = (String)colorModel.getValueAt(selectedVal, 0);
			propertyData.setFontColorName(colorName);
		}
	}

	private class SelectBackGroundListener implements ListSelectionListener {
		//    @Override
		public void valueChanged(ListSelectionEvent e) {
			int selectedVal = backGroundTable.convertRowIndexToModel(backGroundTable.getSelectedRow());
			String colorName = (String)backGroundModel.getValueAt(selectedVal, 0);
			propertyData.setBackGroundColorName(colorName);
		}
	}

	private class PropertyActionListener implements ActionListener {
		// @Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == okButton) {
				PropertyDialog.this.setVisible(false);
			} else if (source == fontComboBox) {
				String fontName = (String)fontComboBox.getSelectedItem();
				propertyData.setFontName(fontName);
				digitalWatch.changeFont();
			} else if (source == fontSizeComboBox) {
				int fontSize = Integer.valueOf((String)fontSizeComboBox.getSelectedItem());
				propertyData.setFontSize(fontSize);
				digitalWatch.changeFont();
			}
		}
	}

}
