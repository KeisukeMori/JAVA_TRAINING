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

                // 現在の時計の設定を取得する
                tmpFontType = digitalWatch.getFontType();
                tmpFontSize = digitalWatch.getFontSize();
                tmpFontColor = digitalWatch.getFontColor();
                tmpBackgroundColor = digitalWatch.getBackgroundColor();

                setLayout(new BorderLayout());
                setTitle("プロパティ");
                setSize(300, 180);
                setResizable(false);

                propertyPanel.setLayout(new GridLayout(5,2));

                this.add(propertyPanel, BorderLayout.NORTH);
  //              this.add(bottomPanel, BorderLayout.SOUTH);
              
              

                // リスナー登録
                choiceFontType.addItemListener(this);
                choiceFontSize.addItemListener(this);
                choiceFontColor.addItemListener(this);
                choiceBackgroundColor.addItemListener(this);
                OKButton.addActionListener(this);

                // フォントタイプ
                propertyPanel.add(new Label("フォントタイプ"));
                choiceFontType.add("TimesRoman");
                choiceFontType.add("Serif");
                choiceFontType.add("Monospaced");

                choiceFontType.select(digitalWatch.getFontType());
                propertyPanel.add(choiceFontType);

                // フォントサイズ
                propertyPanel.add(new Label("フォントサイズ "));
                choiceFontSize.add("10");
                choiceFontSize.add("20");
                choiceFontSize.add("40");
                choiceFontSize.add("80");
                choiceFontSize.add("120");
                choiceFontSize.select(digitalWatch.getFontSize().toString());
                propertyPanel.add(choiceFontSize);


                // フォントカラーの初期選択値をStringで取得する
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

                // フォントカラー
                propertyPanel.add(new Label("フォントカラー "));
                choiceFontColor.add("black");
                choiceFontColor.add("red");
                choiceFontColor.add("green");
                choiceFontColor.add("blue");
                choiceFontColor.select(defaultFontColor);
                propertyPanel.add(choiceFontColor);


                // 背景色の初期選択値をStringで取得する
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

                // 背景色
                propertyPanel.add(new Label("背景色 "));
                choiceBackgroundColor.add("white");
                choiceBackgroundColor.add("black");
                choiceBackgroundColor.add("blue");
                choiceBackgroundColor.select(defaultBackgroundColor);
                propertyPanel.add(choiceBackgroundColor);

                // OKボタン
   //             bottomPanel.add(OKButton);

                // ダイアログボックスを閉じるとき
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
            // フォントタイプが選択された場合
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































