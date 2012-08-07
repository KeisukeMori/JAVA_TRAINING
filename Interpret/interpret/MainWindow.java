package interpret;

import java.lang.reflect.*;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -1836727108622520391L;
	private ObjectModel objectModel;

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
	private JTable fieldtable;
	private JScrollPane fieldScroll; 
	private JButton setFieldBtn;
	// パラメータ用
	private JLabel paramLabel;          
	private JLabel objectNameLabel;     
	private JTextField objectNameText;  
	private JLabel paramConstLabel; 
	private JTextField paramTextFiled;  

	// オブジェクト生成ボタン
	private JButton createObjectBtn; 
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

		objectModel = new ObjectModel();
		// レイアウトの設定
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();

		createComponent();

		// 共通設定 
		constraints.insets = new Insets(1, 1, 1, 1);
		constraints.gridwidth = GridBagConstraints.REMAINDER;

		addComponent(searchLabel, 0, 1, 1, 1);
		addComponent(searchText, 0, 2, 4, 1);
		addComponent(searchButton, 4, 2, 1, 1);

	
		addComponent(constClearButton, 4, 3, 1, 1);
		addComponent(constScroll, 0, 4, 5, 8);
		addComponent(selectConstBtn, 4, 12, 1, 1);


		addComponent(objectLabel, 7, 1, 1, 1);

		addComponent(methodBtn, 18, 1, 1, 1);
		addComponent(runMethodBtn, 19, 1, 1, 1);
		addComponent(methodScroll, 18, 2, 5, 8);
		addComponent(runMethodText, 18, 11, 5, 1);

		addComponent(objectClearBtn, 10, 1, 1, 1);

		addComponent(objectScroll, 7, 2, 4, 3);

		addComponent(paramLabel, 7, 6, 2, 1);
		addComponent(objectNameLabel, 7, 7, 2, 1);
		addComponent(objectNameText, 7, 8, 4, 1);
		addComponent(paramConstLabel, 7, 9, 2, 1);
		addComponent(paramTextFiled, 7, 10, 4, 1);

		addComponent(createObjectBtn, 9, 12, 1, 1);

		addComponent(fieldBtn, 24, 1, 1, 1);
		addComponent(setFieldBtn, 25, 1, 1, 1);
		addComponent(fieldtable, 24, 3, 5, 8);

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

		setVisible(true);
	}

	private void createComponent() {

		// オブジェクト生成用 
		createObjectBtn = new JButton("create");
		// クラス検索用
		searchLabel = new JLabel("class");
		searchLabel.setFont(commonFont);
		searchText = new JTextField();
		searchText.setText("java.lang.String");
		searchText.setPreferredSize(new Dimension(150, 20));
		searchButton = new JButton("search");

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
		objectScroll.setPreferredSize(new Dimension(200, 40));

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
		fieldBtn = new JButton("fields");

		runMethodText = new JTextField();
		runMethodText.setText("1,1");
		runMethodText.setPreferredSize(new Dimension(150, 20));

		//フィールド表示
		fieldBtn = new JButton("field");
		fieldtable = new JTable(20, 2);
		fieldScroll = new JScrollPane();
		fieldScroll.getViewport().setView(fieldtable);
		fieldScroll.setPreferredSize(new Dimension(180, 300));
		setFieldBtn = new JButton("set fields");
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
			// 未完成
			Object obj = ObjectModel.getObject(objectNameText.getText());
			System.out.println("obj" + obj );
			Oparator.getMethods(obj);

		} else if (source == runMethodBtn) {
			Object obj = ObjectModel.getObject(objectNameText.getText());
			System.out.println("obj" + obj );
			System.out.println("getSelectedIndex" + methodList.getSelectedIndex() );
			obj.getClass().getMethods();
			Method method = methodListNum.get(methodList.getSelectedIndex());
			String text = runMethodText.getText();
			String[] textParams = text.split(",");

			Class[] clazz = method.getParameterTypes();
			Object[] params = new Object[clazz.length];

			try {
				for(int i = 0; i < clazz.length; i++) {
					String tos = clazz[i].toString();
					if(tos.matches(".*String.*")) {
						params[i] = textParams[i];
					} else if(tos.matches(".*boolean.*")) {
						params[i] = new Boolean(textParams[i]);
					} else if(tos.matches(".*int.*")) {
						params[i] = new Integer(textParams[i]);
					} else if(tos.matches(".*byte.*")) {
						params[i] = new Byte(textParams[i]);
					} else if(tos.matches(".*char.*")) {
						params[i] = new Character(textParams[i].charAt(0));
					} else if(tos.matches(".*short.*")) {
						params[i] = new Short(textParams[i]);
					} else if(tos.matches(".*long.*")) {
						params[i] = new Long(textParams[i]);
					} else if(tos.matches(".*float.*")) {
						params[i] = new Float(textParams[i]);
					} else if(tos.matches(".*double.*")) {
						params[i] = new Double(textParams[i]);
					} else if(textParams[i].matches(".*\\:.*")){
						String clasType = textParams[i].split("\\:")[0];
						String f = textParams[i].split("\\:")[1];
						params[i] = Class.forName(clasType).getDeclaredField(f).get(null);
					}
				}
			} catch(Exception ex) {
				ex.getStackTrace();
			}
			try {
				classOperator.runMethod(obj, method, params);
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			// フィールド呼び出しボタン
		} else if (source == fieldBtn) {
			Object obj = ObjectModel.getObject(objectNameText.getText());
			List firldLists = classOperator.getFields(obj);
			List valueLists = classOperator.getValues(obj);
			for (int i = 0; i < firldLists.size(); i++) {
				fieldtable.setValueAt(firldLists.get(i), i, 0);
				fieldtable.setValueAt(valueLists.get(i), i, 1);
			}
		} else if (source == setFieldBtn) {
			
			
			
			// オブジェクト生成ボタン
		} else if (source == createObjectBtn) { 
			if (classOperator.createButton()) {
				String objName = objectNameText.getText();
				objects.addElement(objName);
				objectList.ensureIndexIsVisible(objects.getSize() - 1);
				System.out.println("オブジェクト  "  + objName +  "生成");
			}
		}
	}

	public void printConstructorList (Constructor<?>[] cons) {
		for (Constructor<?> con : cons) {
			constructors.addElement(con.toString());
			constList.ensureIndexIsVisible(constructors.getSize() - 1);
		}
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

	public void addObject() {

	}

	public void printConstLabel(String constName) {
		paramConstLabel.setText(constName);
	}

	public String getObjectName() {
		return objectNameText.getText();
	}

	public String getConstName() {
		return (String)constList.getSelectedValue();
	}

	public String getParamName() {
		return paramTextFiled.getText();
	}

	class MyMouseListener extends MouseAdapter implements MouseListener {

	}
}
