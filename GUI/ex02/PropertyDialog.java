package ex02;

import java.awt.*;
import java.awt.event.*;


public class PropertyDialog extends Dialog implements ActionListener, ItemListener {

    Panel propertyPanel = new Panel();


    Choice choiceFontType = new Choice();
    Choice choiceFontSize = new Choice();
    Choice choiceFontColor = new Choice();
    Choice choiceBackgroundColor = new Choice();

    String defaultFontColor;
    String defaultBackgroundColor;

    String tmpFontType = "TimesRoman";
    int tmpFontSize = 10;
    Color tmpFontColor = Color.black;
    Color tmpBackgroundColor = Color.white;

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
                setSize(300, 180);
                setResizable(false);

                propertyPanel.setLayout(new GridLayout(5,2));

                this.add(propertyPanel, BorderLayout.NORTH);
  //              this.add(bottomPanel, BorderLayout.SOUTH);
              
              

                // ���X�i�[�o�^
                choiceFontType.addItemListener(this);
                choiceFontSize.addItemListener(this);
                choiceFontColor.addItemListener(this);
                choiceBackgroundColor.addItemListener(this);
                OKButton.addActionListener(this);

                // �t�H���g�^�C�v
                propertyPanel.add(new Label("�t�H���g�^�C�v"));
                choiceFontType.add("TimesRoman");
                choiceFontType.add("Serif");
                choiceFontType.add("Monospaced");

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


                // �t�H���g�J���[�̏����I��l��String�Ŏ擾����
                if (Color.black == digitalWatch.getFontColor())
                {
                    defaultFontColor = "black";
                } else if (Color.red == digitalWatch.getFontColor())
                {
                    defaultFontColor = "red";
                } else if (Color.green == digitalWatch.getFontColor())
                {
                    defaultFontColor = "green";
                } else if (Color.blue == digitalWatch.getFontColor())
                {
                    defaultFontColor = "blue";
                } else
                {
                    defaultFontColor = "black";
                }

                // �t�H���g�J���[
                propertyPanel.add(new Label("�t�H���g�J���[ "));
                choiceFontColor.add("black");
                choiceFontColor.add("red");
                choiceFontColor.add("green");
                choiceFontColor.add("blue");
                choiceFontColor.select(defaultFontColor);
                propertyPanel.add(choiceFontColor);


                // �w�i�F�̏����I��l��String�Ŏ擾����
                if (Color.white == digitalWatch.getBackgroundColor())
                {
                    defaultBackgroundColor = "white";
                } else if (Color.black == digitalWatch.getBackgroundColor())
                {
                    defaultBackgroundColor = "black";
                } else if (Color.orange == digitalWatch.getBackgroundColor())
                {
                    defaultBackgroundColor = "blue";
                } else
                {
                    defaultBackgroundColor = "white";
                }

                // �w�i�F
                propertyPanel.add(new Label("�w�i�F "));
                choiceBackgroundColor.add("white");
                choiceBackgroundColor.add("black");
                choiceBackgroundColor.add("blue");
                choiceBackgroundColor.select(defaultBackgroundColor);
                propertyPanel.add(choiceBackgroundColor);

                // OK�{�^��
   //             bottomPanel.add(OKButton);

                // �_�C�A���O�{�b�N�X�����Ƃ�
                addWindowListener(new WindowAdapter()
                {
                        public void windowClosing(WindowEvent e)
                        {
                                setVisible(false);        	
                        }
                });
        }

        private static final long serialVersionUID = 3853419917132576660L;

        @Override
        public void actionPerformed(ActionEvent e) {
                if ("OK" == e.getActionCommand())
                {
                	digitalWatch.setFontType(tmpFontType);
                	digitalWatch.setFontSize(tmpFontSize);
                	digitalWatch.setFontColor(tmpFontColor);
                	digitalWatch.setBackgroundColor(tmpBackgroundColor);
                    setVisible(false);
                }
        }

        @Override
        public void itemStateChanged(ItemEvent e)
        {
            // �t�H���g�^�C�v���I�����ꂽ�ꍇ
            if (choiceFontType == e.getSource())
            {
                tmpFontType = e.getItem().toString();
            }
            else if (choiceFontSize == e.getSource())
            {
                tmpFontSize = Integer.parseInt(e.getItem().toString());
            }
            else if (choiceFontColor == e.getSource())
            {
                if ("black" == e.getItem())
                {
                    tmpFontColor = Color.black;
                }
                else if ("red" == e.getItem())
                {
                    tmpFontColor = Color.red;
                }
                else if ("green" == e.getItem())
                {
                    tmpFontColor = Color.green;
                }
                else if ("blue" == e.getItem())
                {
                    tmpFontColor = Color.blue;
                }
                else
                {
                    tmpFontColor = Color.black;
                }
            }
            else if (choiceBackgroundColor == e.getSource())
            {
                if ("white" == e.getItem())
                {
                    tmpBackgroundColor = Color.white;
                }
                else if ("black" == e.getItem())
                {
                    tmpBackgroundColor = Color.black;
                }
                else if ("orange" == e.getItem())
                {
                    tmpBackgroundColor = Color.orange;
                }
                else
                {
                    tmpBackgroundColor = Color.white;
                }
            }

        }
}































