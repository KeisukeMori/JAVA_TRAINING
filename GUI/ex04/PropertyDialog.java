package ex04;

import java.awt.*;
import java.awt.event.*;

class PropertyDialog extends Dialog implements ActionListener, ItemListener {

  private Label fontLabel;
  private Label fontSizeLabel;
  private Label fontColorLabel;
  private Label backColorLabel;

  private Button okButton;
  private Button cancelButton;

  private List fontList;
  private List fontSizeList;
  private List fontColorList;
  private List backColorList;

  private GridBagLayout layout;
  private GridBagConstraints constraints;

  private PropertyData savedPropery;

  private Font dialogFont = new Font("Serif", Font.BOLD, 14);

  PropertyDialog(Frame owner, String title) {
    super(owner, title, true);
    setSize(300, 500);
    setLocationRelativeTo(null);
    setResizable(false);

    /* close window */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
       setVisible(false);
      }
    });

    /* setting layout */
    layout = new GridBagLayout();
    setLayout(layout);
    constraints = new GridBagConstraints();

    fontLabel = new Label("フォント");
    fontSizeLabel = new Label("フォントサイズ");
    fontColorLabel = new Label("フォント色");
    backColorLabel = new Label("背景色");

    fontList = new List(5, false);
    for (String font : PropertyData.fontSet) {
      fontList.add(font);
    }

    fontSizeList = new List(5, false);
    for (String fontSize : PropertyData.fontSize) {
      fontSizeList.add(fontSize);
    }

    fontColorList = new List(5, false);
    for (String fontColor : PropertyData.colorString) {
      fontColorList.add(fontColor);
    }

    backColorList = new List(5, false);
    for (String backColor : PropertyData.colorString) {
      backColorList.add(backColor);
    }

    okButton = new Button("OK");
    cancelButton = new Button("キャンセル");
  } // end PropertyDialog()

  void init() {
    /* label setting */
    fontLabel.setFont(dialogFont);
    fontSizeLabel.setFont(dialogFont);
    fontColorLabel.setFont(dialogFont);
    backColorLabel.setFont(dialogFont);

    /* constraints common setting */
    constraints.insets = new Insets(5, 10, 5, 10);


    /* label setting */
    constraints.weightx = 0.4;
    constraints.gridwidth = GridBagConstraints.RELATIVE;
    constraints.anchor = GridBagConstraints.EAST;
    addComponent(fontLabel, 0, 1);
    addComponent(fontSizeLabel, 0, 2);
    addComponent(fontColorLabel, 0, 3);
    addComponent(backColorLabel, 0, 4);

    /* list setting */
    constraints.weightx = 0.6;
    constraints.gridwidth = GridBagConstraints.RELATIVE;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 10, 5, 50);
    addComponent(fontList, 1, 1);
    addComponent(fontSizeList, 1, 2);
    addComponent(fontColorList, 1, 3);
    addComponent(backColorList, 1, 4);

    /* button setting */
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.EAST;
    constraints.insets = new Insets(5, 0, 0, 0);
    Panel buttonPanel = new Panel();
    addComponent(buttonPanel, 0, 5);

    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);

    /* set up listeners */
    fontList.addItemListener(this);
    fontSizeList.addItemListener(this);
    fontColorList.addItemListener(this);
    backColorList.addItemListener(this);
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
  }

  @Override
  public void setVisible(boolean isVisible) {
    if (isVisible) {
//      ajustLocation();
    	settingValue();
    }

    super.setVisible(isVisible);
  }

//  private void ajustLocation() {
//    DigitalWatch clock = (DigitalWatch)getParent();
//    Point clockLocation = clock.getLocation();
//    Dimension clockSize = clock.getSize();
//    clockLocation.translate(clockSize.width + 10, 0);
//
//    setLocation(clockLocation);
//  }
  
  private void settingValue() {
      DigitalWatch degitalWatch = (DigitalWatch)getParent();
      savedPropery = degitalWatch.getCurrentProperty();
      fontList.select(savedPropery.fontValue());
      fontList.makeVisible(savedPropery.fontValue());
      fontSizeList.select(savedPropery.fontSizeValue());
      fontSizeList.makeVisible(savedPropery.fontSizeValue());
      fontColorList.select(savedPropery.fontColorValue());
      fontColorList.makeVisible(savedPropery.fontColorValue());
      backColorList.select(savedPropery.backColorValue());
      backColorList.makeVisible(savedPropery.backColorValue());
  }

  private void addComponent(Component component, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    layout.setConstraints(component, constraints);
    add(component);
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == okButton) {
      setVisible(false);
    } else if (obj == cancelButton) {
      DigitalWatch digitalWatch = (DigitalWatch)getParent();
      digitalWatch.getProperty(savedPropery);
      setVisible(false);
    }
  }

	private void addLabel(Label label, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 100.0;
		gbc.weighty = 100.0;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(label, gbc);
		add(label);
		}
  
  public void itemStateChanged(ItemEvent e) {
    Object obj = e.getSource();

    DigitalWatch parent = (DigitalWatch)getParent();

    if (obj == fontList) {
      String fontName = fontList.getSelectedItem();
      parent.changeWatchFont(fontName);
    } else if (obj == fontSizeList) {
      int fontSize = Integer.parseInt(fontSizeList.getSelectedItem());
      parent.changeWatchFontSize(fontSize);
    } else if (obj == fontColorList) {
      String fontColorName = fontColorList.getSelectedItem();
      parent.changeFontColor(fontColorName);
    } else if (obj == backColorList) {
      String backColorName = backColorList.getSelectedItem();
      parent.changeBackColor(backColorName);
    } else {
      // this never occurs
      throw new InternalError();
    }
  }
}
