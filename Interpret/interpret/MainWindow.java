package interpret;

import java.lang.reflect.*;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -1836727108622520391L;

	// クラス検索用
	private JLabel searchLabel;
	private JTextField searchText;
	private JButton searchButton;
	
	// コンストラクタ用
	private JButton constClearButton;  
	private JList constList;      
	private DefaultListModel constructors;
	private JScrollPane constScroll; 
	private JButton selectConstBtn; 

	// オブジェクト一覧用
	private JLabel objectLabel;
	private JButton objectClearBtn;
	private JList  objectList; 
	private DefaultListModel objects;
	private JScrollPane objectScroll;
	
	// メソッド呼び出し用
	private JButton methodBtn; 
	private JList methodList;      
	private DefaultListModel methods;
	private JScrollPane methodScroll; 
	private JButton runMethodBtn;
	private JTextField runMethodText;
	
	// フィールド呼び出し用
	private JButton fieldBtn;
	private JList fieldList;
	private DefaultListModel fields;
	private JScrollPane fieldScroll; 
	private JButton setFieldBtn;
	private JTextField setFieldText;
	
	// パラメータ用
	private JLabel paramLabel;          
	private JLabel objectNameLabel;     
	private JTextField objectNameText;  
	private JLabel paramConstLabel; 
	private JTextField paramTextFiled;  

	// オブジェクト生成ボタン
	private JButton createObjectBtn; 
	
	// 配列生成用ボタン
	private JButton arrayBtn;
	
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
		constraints.insets = new Insets(1, 1, 1, 1);
		constraints.gridwidth = GridBagConstraints.REMAINDER;

		addComponent(searchLabel, 0, 0, 1, 1);
		addComponent(searchText, 0, 2, 4, 1);
		addComponent(searchButton, 4, 2, 1, 1);

	
		addComponent(constClearButton, 4, 3, 1, 1);
		addComponent(constScroll, 0, 4, 5, 8);
		addComponent(selectConstBtn, 3, 3, 1, 1);


		addComponent(objectLabel, 7, 1, 1, 1);

		addComponent(methodBtn, 18, 2, 1, 1);
		addComponent(runMethodBtn, 19, 2, 1, 1);
		addComponent(methodScroll, 18, 3, 5, 8);
		addComponent(runMethodText, 18, 11, 4, 2);

//		addComponent(objectClearBtn, 10, 1, 1, 1);

		addComponent(objectScroll, 7, 3, 4, 2);

//		addComponent(paramLabel, 7, 10, 4, 1);
		addComponent(objectNameLabel, 7, 10, 2, 1);
		addComponent(objectNameText, 7, 11, 4, 1);
		addComponent(paramConstLabel, 7, 8, 2, 1);
		addComponent(paramTextFiled, 7, 9, 4, 1);
		
//		addComponent(paramLabel, 7, 6, 2, 1);
//		addComponent(objectNameLabel, 7, 7, 2, 1);
//		addComponent(objectNameText, 7, 8, 4, 1);
//		addComponent(paramConstLabel, 7, 9, 2, 1);
//		addComponent(paramTextFiled, 7, 10, 4, 1);

		addComponent(createObjectBtn, 7, 12, 1, 1);

		addComponent(fieldBtn, 24, 2, 1, 1);
		addComponent(setFieldBtn, 25, 2, 1, 1);
		addComponent(fieldScroll, 24, 3, 5, 8);
		addComponent(setFieldText, 24, 11, 4, 2);
		
		addComponent(arrayBtn, 25, 13, 1, 1);
		
		new MessageWindow();

		searchButton.addActionListener(this);
		constClearButton.addActionListener(this);
		selectConstBtn.addActionListener(this);
		methodBtn.addActionListener(this);
		runMethodBtn.addActionListener(this);
		fieldBtn.addActionListener(this);
		setFieldBtn.addActionListener(this);
		objectClearBtn.addActionListener(this);
		createObjectBtn.addActionListener(this);
		arrayBtn.addActionListener(this);

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
		searchText.setPreferredSize(new Dimension(140, 20));
		searchButton = new JButton("Search");

		// コンストラクタ一覧表示用

		constClearButton = new JButton("clear");
		constructors = new DefaultListModel();
		constList = new JList(constructors);
		constList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		constScroll = new JScrollPane();
		constScroll.getViewport().setView(constList);
		constScroll.setPreferredSize(new Dimension(200, 200));
		selectConstBtn = new JButton("select");

		// オブジェクト一覧表示用 
		objectLabel = new JLabel("objects");
		objectLabel.setFont(commonFont);
		objectClearBtn = new JButton("clear");
		objects = new DefaultListModel();
		objectList = new JList(objects);
		objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		objectScroll = new JScrollPane();
		objectScroll.getViewport().setView(objectList);
		objectScroll.setPreferredSize(new Dimension(200, 20));

		// パラメータ表示用
		paramLabel = new JLabel("parameter");
		paramLabel.setFont(commonFont);
		objectNameLabel = new JLabel("object name");
		objectNameLabel.setFont(commonFont);
		objectNameText = new JTextField();
		objectNameText.setPreferredSize(new Dimension(200, 40));
		paramConstLabel = new JLabel("constructor");
		paramConstLabel.setFont(commonFont);
		paramTextFiled = new JTextField();
		paramTextFiled.setPreferredSize(new Dimension(200, 40));

		// メソッド表示用
		methodBtn = new JButton("methods");
		methods = new DefaultListModel();
		methodList = new JList(methods);
		methodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		methodScroll = new JScrollPane();
		methodScroll.getViewport().setView(methodList);
		methodScroll.setPreferredSize(new Dimension(200, 200));
		runMethodBtn = new JButton("run methods");

		runMethodText = new JTextField();
		runMethodText.setText("1,1");
		runMethodText.setPreferredSize(new Dimension(140, 20));

		//フィールド表示
		fieldBtn = new JButton("field");
		fields = new DefaultListModel();
		fieldList = new JList(fields);
		fieldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fieldScroll = new JScrollPane();
		fieldScroll.getViewport().setView(fieldList);
		fieldScroll.setPreferredSize(new Dimension(200, 200));
		setFieldBtn = new JButton("set fields");
		
		setFieldText = new JTextField();
		setFieldText.setText("test");
		setFieldText.setPreferredSize(new Dimension(140, 20));
		
		// 配列生成用 
		arrayBtn = new JButton("array");
	}

	private void addComponent(Component com, int x, int y, int width, int height) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		layout.setConstraints(com, constraints);
		add(com);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// 検索ボタン
		if (source == searchButton) { 
			classOperator.searchButton(searchText.getText());

			// コンストラクタクリアボタン	
		} else if (source == constClearButton) {
			constructors.clear();
			constList.ensureIndexIsVisible(constructors.getSize() - 1);
			classOperator.constClearButton();
			// コンストラクタ選択ボタン	
		} else if (source == selectConstBtn) {
			if (constList.isSelectionEmpty()) {
				System.out.println("コンストラクタを選択してください");
			} else {
				System.out.println(constList.getSelectedValue());
				classOperator.selectButton((String)constList.getSelectedValue());
			}
			// オブジェクトクリアボタン	
		} else if (source == objectClearBtn) {
			objects.clear();
			objectList.ensureIndexIsVisible(objects.getSize() - 1);
			classOperator.objectClearButton();
			// メソッドボタン	
		} else if (source == methodBtn) {
			methods.clear();
			classOperator.methodBtn();

		} else if (source == runMethodBtn) {
			classOperator.methodRunButton();

			// フィールド呼び出しボタン
		} else if (source == fieldBtn) {
			fields.clear();
			classOperator.fieldBtn();
		} else if (source == setFieldBtn) {
			classOperator.fieldSetButton();
			
			// オブジェクト生成ボタン
		} else if (source == createObjectBtn) { 
			if (classOperator.createButton()) {
				String objName = objectNameText.getText();
				objects.addElement(objName);
				objectList.ensureIndexIsVisible(objects.getSize() - 1);
				System.out.println("オブジェクト  "  + objName +  "生成");
			}
		} else if (source == arrayBtn) {
			
		}
	}

	public void printConstructorList (Constructor<?>[] cons) {
		for (Constructor<?> con : cons) {
			constructors.addElement(con.toString());
			constList.ensureIndexIsVisible(constructors.getSize() - 1);
		}
	}
	
	  public void printConstructor (String constName) {
		    constructors.addElement(constName);
		    constList.ensureIndexIsVisible(constructors.getSize() - 1);
		  }

	public void printMethodList (List<Method> lists) {
		methodListNum = lists;
		for (int i = 0; i < lists.size(); i++) {
			constructors.addElement(lists.get(i).toString());
			constList.ensureIndexIsVisible(constructors.getSize() - 1);
		}
	}

	public void printMethodList (Method[] lists) {
		for (int i = 0; i < lists.length; i++) {
			methods.addElement(lists[i].toString());
			methodList.ensureIndexIsVisible(methods.getSize() - 1);
		}
	}

	  public void printMethod(String method) {
		    methods.addElement(method);
		    methodList.ensureIndexIsVisible(methods.getSize() - 1);
		  }
	  
	  public void printField(String field) {
		    fields.addElement(field);
		    fieldList.ensureIndexIsVisible(fields.getSize() - 1);
		  }
	
	public void addObject() {

	}

	public void printConstLabel(String constName) {
		paramConstLabel.setText(constName);
	}

	public String getObjectName() {
		return objectNameText.getText();
	}
	
	  public String getSelectedObject() {
		    return (String)objectList.getSelectedValue();
		  }

	public String getConstName() {
		return (String)constList.getSelectedValue();
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

	class MyMouseListener extends MouseAdapter implements MouseListener {

	}
}
