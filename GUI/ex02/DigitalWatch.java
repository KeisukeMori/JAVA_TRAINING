package ex02;

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalWatch extends Frame implements Runnable, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private int hour;           //時を入れる変数を宣言
	private int minute;           //分を入れる変数を宣言
	private int second;           //秒を入れる変数を宣言

	private Thread th;
	private PropertyDialog dialog = null;
	private Menu menuBox;
	private MenuItem menuProperty;
	private Image imageBuffer;
	private Graphics graphicBuffer;
	private PopupMenu popUp;


	private String fontType = "DIALOG";
	private int fontSize = 48;
	private Color fontColor = Color.BLACK;
	private Color backgroundColor = Color.white;

	private int windowX = 400;
	private int windowY = 200;

	private String timeString;
	private MenuBar menuBar;

	// フォントのデフォルトの設定
	private Font fontSetting = new Font("TimesRoman", Font.PLAIN, 48);

	public DigitalWatch(String title) {
		//タイトル
		super(title);

		addWindowListener(new CurrentWindowAdapter());
		// メニューバーを作成する
		menuBar = new MenuBar();
		setMenuBar(menuBar);        

		menuBox = new Menu("メニュー");
		menuBox.addActionListener(this);
		menuBar.add(menuBox);

		menuProperty = new MenuItem("プロパティ");
		menuBox.add(menuProperty);

		// ダイアログを生成する
		if (dialog == null) {
			dialog = new PropertyDialog(this);
		}
        /* ポップアップメニューを作成する */
        popUp = new PopupMenu();
        popUp.add("次回をこうご期待ください");
        add(popUp);
		
		addMouseListener(this);

	}

	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY); //時を代入
		minute = calendar.get(Calendar.MINUTE);      //分を代入
		second= calendar.get(Calendar.SECOND);       //秒を代入
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
		
		// ウィンドウサイズの計算
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
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		while(true) {
			getTime();

			// 再描画
			repaint();

			try {
				th.sleep(1000); // スリープ1秒
			} catch(InterruptedException e) {
				;
			}
		}
	}

	/**
	 * フォントタイプ取得
	 * @return フォントタイプ
	 */
	public String getFontType() {
		return fontType;
	}


	/**
	 * フォントタイプのセット
	 * @param fontType フォントタイプ
	 */
	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	/**
	 * フォントサイズの取得
	 * @return フォントサイズ
	 */
	public Integer getFontSize() {
		return fontSize;
	}

	/**
	 * フォントサイズのセット
	 * @param fontSize フォントサイズ
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * フォントカラーの取得
	 * @return フォントカラー
	 */
	public Color getFontColor() {
		return fontColor;
	}

	/**
	 * フォントカラーのセット
	 * @param fontColor フォントカラー
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * 背景色を取得します。
	 * @return 背景色
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * 背景色をセットします
	 * @param backgroundColor 背景色
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	/**
	 * @param args
	 */
	 public static void main(String[] args) {
		 DigitalWatch window = new DigitalWatch("デジタル時計");
		 window.setSize(400, 200);
		 window.setResizable(false);
		 window.setVisible(true);
		 
		 window.th = new Thread(window);


		 window.imageBuffer = window.createImage(400, 200);
		 window.graphicBuffer = window.imageBuffer.getGraphics();

		 // スレッドスタート
		 window.th.start();

	}

	 @Override
	 public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand() == "プロパティ") {
			 dialog.setVisible(true);
		 } 
		 // if else 
	 }
	 
	
	public class CurrentWindowAdapter extends WindowAdapter {
			public void windowClosing(WindowEvent e) {   //×を押されたときの処理
				System.exit(0);
			}
	 }
	@Override
	public void mouseClicked(MouseEvent e) {
		popUp.show(this, 100, 100);
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
