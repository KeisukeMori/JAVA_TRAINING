package interpret;

import java.lang.reflect.*;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -1836727108622520391L;

	// クラス検索用
	private JLabel searchLabel;
	private JTextField searchText;
	private JButton searchButton;

	// コンストラクタ用
	private JButton conClearBtn;  
	private JList conList;      
	private DefaultListModel conModel;
	private JScrollPane conScroll; 
	private JButton selectConBtn; 

	// オブジェクト一覧用
	private JLabel objLabel;
	private JButton objClearBtn;
	private JList  objList; 
	private DefaultListModel objModel;
	private JScrollPane objScroll;

	// メソッド呼び出し用
	private JButton methodBtn; 
	private JList methodList;      
	private DefaultListModel methodModel;
	private JScrollPane methodScroll; 
	private JButton runMethodBtn;
	private JTextField runMethodText;

	// フィールド呼び出し用
	private JButton fieldBtn;
	private JList fieldList;
	private DefaultListModel fieldModel;
	private JScrollPane fieldScroll; 
	private JButton setFieldBtn;
	private JTextField setFieldText;
	private JButton getFieldBtn;
	private JLabel fieldValueLabel;          

	
	// オブジェクトパラメータ用
	private JLabel paramLabel;          
	private JLabel objNameLabel;     
	private JTextField objNameText;  
	private JLabel paramConstLabel; 
	private JTextField paramTextFiled;   

	// オブジェクト生成ボタン
	private JButton createObjectBtn; 

	// 配列生成用ボタン	
	private JTextField arrayNameField; 
	private JTextField arrayNumField;  
	private JButton arrayBtn;
	private DefaultTableModel arrayTableModel;  
	private String[] arrayModelName = {"arrayIndex", "value"}; 
	private JTable arrayTable;       
	private JScrollPane arrayScroll; 
	private JButton getArrayBtn;
	
	// 配列オブジェクト設定用ボタン
	private JTextField objField; 
	private JButton objSetBtn;   

	private GridBagLayout layout;
	private GridBagConstraints constraints;

	private static final Font commonFont = new Font("Arial", Font.PLAIN, 14);
	private List<Method> methodListNum;
	private Oparator classOperator;

	public MainWindow(){
		super("MainWindow");
		classOperator = new Oparator(this);
		setBounds(0, 0, 1000, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// レイアウトの設定
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();

		createComponent();

		// 共通設定 
		constraints.insets = new Insets(0, 0, 0, 0);
		//constraints.gridwidth = GridBagConstraints.REMAINDER;

		addComponent(searchLabel, 0, 0, 1, 1);
		addComponent(searchText, 0, 1, 3, 1);
		addComponent(searchButton, 3, 1, 1, 1);

		addComponent(selectConBtn, 0, 3, 1, 1);
		addComponent(conClearBtn, 1, 3, 1, 1);
		addComponent(conScroll, 0, 4, 3, 7);

		addComponent(objLabel, 4, 0, 1, 1);
		addComponent(objScroll, 4, 1, 3, 3);
		addComponent(paramConstLabel, 4, 4, 1, 1);
		addComponent(paramTextFiled, 4, 5, 3, 1);
		addComponent(objNameLabel, 4, 6, 1, 1);
		addComponent(objNameText, 4, 7, 3, 1);		
		addComponent(createObjectBtn, 4, 8, 1, 1);

		addComponent(methodBtn, 8, 1, 1, 1);
		addComponent(runMethodText, 8, 3, 3, 1);
		addComponent(runMethodBtn, 9, 1, 1, 1);
		addComponent(methodScroll, 8, 4, 3, 14);


		addComponent(fieldBtn, 13, 1, 1, 1);
		addComponent(fieldScroll, 13, 4, 3, 14);
		addComponent(setFieldBtn, 14, 1, 1, 1);
		addComponent(setFieldText, 13, 3, 3, 1);
		addComponent(getFieldBtn, 13, 19, 1, 1);
		addComponent(fieldValueLabel, 13, 20, 3, 1);

		
		addComponent(arrayScroll, 0, 13, 3, 5);		
		addComponent(arrayNameField, 0, 19, 3, 1);
		addComponent(arrayNumField, 0, 20, 3, 1);
		addComponent(arrayBtn, 0, 21, 1, 1);
		addComponent(getArrayBtn, 1, 21, 1, 1);

		addComponent(objField, 4, 19, 3, 1);
		addComponent(objSetBtn, 4, 20, 1, 1);

		new MessageWindow();

		searchButton.addActionListener(this);
		conClearBtn.addActionListener(this);
		selectConBtn.addActionListener(this);
		methodBtn.addActionListener(this);
		runMethodBtn.addActionListener(this);
		fieldBtn.addActionListener(this);
		setFieldBtn.addActionListener(this);
		objClearBtn.addActionListener(this);
		createObjectBtn.addActionListener(this);
		arrayBtn.addActionListener(this);
		getArrayBtn.addActionListener(this);
		objSetBtn.addActionListener(this);
		getFieldBtn.addActionListener(this);
		setVisible(true);
	}

	private void createComponent() {

		// オブジェクト生成用 
		createObjectBtn = new JButton("create");
		// クラス検索用
		searchLabel = new JLabel("class");
		searchLabel.setFont(commonFont);
		searchText = new JTextField();
		searchText.setText("java.awt.Frame");
		searchText.setPreferredSize(new Dimension(130, 30));
		searchButton = new JButton("Search");

		// コンストラクタ一覧表示用

		conClearBtn = new JButton("clear");
		conModel = new DefaultListModel();
		conList = new JList(conModel);
		conList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		conScroll = new JScrollPane();
		conScroll.getViewport().setView(conList);
		conScroll.setPreferredSize(new Dimension(130, 130));
		selectConBtn = new JButton("select");

		// オブジェクト一覧表示用 
		objLabel = new JLabel("objects");
		objLabel.setFont(commonFont);
		objClearBtn = new JButton("clear");
		objModel = new DefaultListModel();
		objList = new JList(objModel);
		objList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		objScroll = new JScrollPane();
		objScroll.getViewport().setView(objList);
		objScroll.setPreferredSize(new Dimension(130, 60));

		// パラメータ表示用
		paramLabel = new JLabel("parameter");
		paramLabel.setFont(commonFont);
		objNameLabel = new JLabel("object name");
		objNameLabel.setFont(commonFont);
		objNameText = new JTextField();
		objNameText.setPreferredSize(new Dimension(130, 30));
		paramConstLabel = new JLabel("constructor");
		paramConstLabel.setFont(commonFont);
		paramTextFiled = new JTextField();
		paramTextFiled.setPreferredSize(new Dimension(130, 30));

		// メソッド表示用
		methodBtn = new JButton("methods");
		methodModel = new DefaultListModel();
		methodList = new JList(methodModel);
		methodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		methodScroll = new JScrollPane();
		methodScroll.getViewport().setView(methodList);
		methodScroll.setPreferredSize(new Dimension(200, 260));
		runMethodBtn = new JButton("run");

		runMethodText = new JTextField();
		runMethodText.setText("1,1");
		runMethodText.setPreferredSize(new Dimension(200, 30));

		//フィールド表示
		fieldBtn = new JButton("field");
		fieldModel = new DefaultListModel();
		fieldList = new JList(fieldModel);
		fieldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fieldScroll = new JScrollPane();
		fieldScroll.getViewport().setView(fieldList);
		fieldScroll.setPreferredSize(new Dimension(200, 260));
		setFieldBtn = new JButton("set fields");
		fieldValueLabel = new JLabel();
		
		setFieldText = new JTextField();
		setFieldText.setText("test");
		setFieldText.setPreferredSize(new Dimension(200, 30));
		
	    getFieldBtn = new JButton("get field");

		// 配列生成用 
		arrayBtn = new JButton("array");
		arrayNameField = new JTextField();
		arrayNameField.setPreferredSize(new Dimension(130, 30));
		arrayNumField = new JTextField();
		arrayNumField.setPreferredSize(new Dimension(130, 30));
		arrayTableModel = new DefaultTableModel(new String[0][2], arrayModelName);
		arrayTable = new JTable(arrayTableModel);
		arrayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		arrayTable.setDefaultEditor(Object.class, null);
		arrayTable.setGridColor(Color.LIGHT_GRAY);
		arrayTable.setShowHorizontalLines(true);
		arrayTable.setShowVerticalLines(false);
		arrayScroll = new JScrollPane();
		arrayScroll.getViewport().setView(arrayTable);
		arrayScroll.setPreferredSize(new Dimension(130, 120));
		DefaultTableColumnModel columnModel
		= (DefaultTableColumnModel)arrayTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(50);
		getArrayBtn = new JButton("get");

		// 配列設定用
		objField = new JTextField();
		objField.setPreferredSize(new Dimension(130, 30));
		objSetBtn = new JButton("set");
	}

	private void addComponent(Component com, int x, int y, int width, int height) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		layout.setConstraints(com, constraints);
		add(com);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// 検索ボタン
		if (source == searchButton) { 
			classOperator.searchButton(searchText.getText());

			// コンストラクタクリアボタン	
		} else if (source == conClearBtn) {
			conModel.clear();
			conList.ensureIndexIsVisible(conModel.getSize() - 1);
			classOperator.constClearButton();
			// コンストラクタ選択ボタン	
		} else if (source == selectConBtn) {
			if (conList.isSelectionEmpty()) {
				System.out.println("コンストラクタを選択してください");
			} else {
				System.out.println(conList.getSelectedValue());
				classOperator.selectButton((String)conList.getSelectedValue());
			}
			// オブジェクトクリアボタン	
		} else if (source == objClearBtn) {
			objModel.clear();
			objList.ensureIndexIsVisible(objModel.getSize() - 1);
			classOperator.objectClearButton();
			// メソッドボタン	
		} else if (source == methodBtn) {
			methodModel.clear();
			classOperator.methodBtn();

		} else if (source == runMethodBtn) {
			classOperator.methodRunButton();

			// フィールド呼び出しボタン
		} else if (source == fieldBtn) {
			fieldModel.clear();
			classOperator.fieldBtn();
			// フィールド設定ボタン
		} else if (source == setFieldBtn) {
			classOperator.fieldSetButton();
			
	    } else if (source == getFieldBtn) {
	        if (fieldList.isSelectionEmpty()) {
	          System.out.println("フィールドを選択してください");
	          return;
	        } else {
	          classOperator.fieldValueGetButton();
	        }
			// オブジェクト生成ボタン
		} else if (source == createObjectBtn) { 
			if (classOperator.createButton()) {
				String objName = objNameText.getText();
				objModel.addElement(objName);
				objList.ensureIndexIsVisible(objModel.getSize() - 1);
				System.out.println("オブジェクト  "  + objName +  "生成");
			}
			// 配列生成ボタン
		} else if (source == arrayBtn) {
			if (classOperator.createAryButton()) {
				String aryName = arrayNameField.getText();
				objModel.addElement(aryName);
				objList.ensureIndexIsVisible(objModel.getSize() - 1);
				System.out.println("配列 \"" + aryName + "\" を生成しました");
			}
			//配列取得ボタン
		} else if (source == getArrayBtn) {
			classOperator.arrayButton();
			//オブジェクト設定
		} else if (source == objSetBtn) { // オブジェクトセットボタン
			int selectedRow = arrayTable.getSelectedRow();
			if (selectedRow == -1) {
				System.out.println("配列を選択してください");
				return;
			} else {
				classOperator.setArrayButton();
			}
		}
	}
	public int getSelectedRowIndex() {
		return arrayTable.getSelectedRow();
	}

	public String getParam() {
		return objField.getText();
	}

	public void setObjectTable(String objName, Object[] objects) {
		arrayTableModel.setRowCount(0);

		String[][] tableData = new String[objects.length][2];
		for (int i = 0; i < objects.length; ++i) {
			tableData[i][0] = objName + "[" + String.valueOf(i) + "]";
			if (objects[i] == null) {
				tableData[i][1] = "null";
			} else {
				tableData[i][1] = objects[i].toString();
			}
		}

		for (int i = 0; i < tableData.length; ++i) {
			arrayTableModel.insertRow(i, tableData[i]);
		}
	}

	public void printConstructorList (Constructor<?>[] cons) {
		for (Constructor<?> con : cons) {
			conModel.addElement(con.toString());
			conList.ensureIndexIsVisible(conModel.getSize() - 1);
		}
	}

	public void printConstructor (String constName) {
		conModel.addElement(constName);
		conList.ensureIndexIsVisible(conModel.getSize() - 1);
	}

	public void printMethodList (List<Method> lists) {
		methodListNum = lists;
		for (int i = 0; i < lists.size(); i++) {
			conModel.addElement(lists.get(i).toString());
			conList.ensureIndexIsVisible(conModel.getSize() - 1);
		}
	}

	public void printMethodList (Method[] lists) {
		for (int i = 0; i < lists.length; i++) {
			methodModel.addElement(lists[i].toString());
			methodList.ensureIndexIsVisible(methodModel.getSize() - 1);
		}
	}

	public void printMethod(String method) {
		methodModel.addElement(method);
		methodList.ensureIndexIsVisible(methodModel.getSize() - 1);
	}

	public void printField(String field) {
		fieldModel.addElement(field);
		fieldList.ensureIndexIsVisible(fieldModel.getSize() - 1);
	}

	public void addObject() {

	}

	public void printConstLabel(String constName) {
		paramConstLabel.setText(constName);
	}

	public String getObjectName() {
		return objNameText.getText();
	}

	public String getClassName() {
		return searchText.getText();
	}

	public String getSelectedObject() {
		return (String)objList.getSelectedValue();
	}

	public String getConstName() {
		return (String)conList.getSelectedValue();
	}

	public String getParamName() {
		return paramTextFiled.getText();
	}

	public String getMethodName() {
		return (String)methodList.getSelectedValue();
	}

	public String getRunMethodParams() {
		return runMethodText.getText();
	}

	public String getFieldName() {
		return (String)fieldList.getSelectedValue();
	}

	public String getFieldValue() {
		return setFieldText.getText();
	}

	  public void setFieldLabel(String value) {
		    fieldValueLabel.setText(value);
		  }
	
	public String getAryObjName() {
		return arrayNameField.getText();
	}

	public String getAryNumStr() {
		return arrayNumField.getText();
	}

	public void setArrayTable(String objName, Object[] objects) {
		arrayTableModel.setRowCount(0);

		String[][] tableData = new String[objects.length][2];
		for (int i = 0; i < objects.length; ++i) {
			tableData[i][0] = objName + "[" + String.valueOf(i) + "]";
			if (objects[i] == null) {
				tableData[i][1] = "null";
			} else {
				tableData[i][1] = objects[i].toString();
			}
		}

		for (int i = 0; i < tableData.length; ++i) {
			arrayTableModel.insertRow(i, tableData[i]);
		}
	}

	public void addObjectName(String objName) {
		objModel.addElement(objName);
		objList.ensureIndexIsVisible(objModel.getSize() - 1);
		System.out.println("オブジェクト " + objName + "生成");
	}

	class MyMouseListener extends MouseAdapter implements MouseListener {

	}
}
