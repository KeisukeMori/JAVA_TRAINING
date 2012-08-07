package interpret;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 標準出力を表示クラス
 */
class MessageWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3941456441208116678L;

	private JLabel title;
	private JTextArea outputArea;
	private JTextArea errArea; 
	private JScrollPane outScrollPane;
	private JScrollPane errScrollPane;
	private JButton outputClearButton;
	private JButton errClearButton;
	private JButton allClearButton;

	private GridBagLayout layout;
	private GridBagConstraints constraints;

	MessageWindow() {
		super("Message");
		setLayout(null);
		setBounds(0, 500, 1000, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// レイアウト
		layout = new GridBagLayout();
		setLayout(layout);
		constraints = new GridBagConstraints();

		// コンポーネントの生成
		title = new JLabel("message");
		title.setFont(new Font("Arial", Font.BOLD, 20));

		createComponent();

		JTextAreaStream outStream = new JTextAreaStream(outputArea);
		JTextAreaStream errStream = new JTextAreaStream(errArea);
		PrintStream outPStream = new PrintStream(outStream, true); 
		PrintStream errPStream = new PrintStream(errStream, true); 
		System.setOut(outPStream);
		System.setErr(errPStream);

		/* コンポーネントの追加 */
		constraints.insets = new Insets(1, 1, 1, 1);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		addComponent(outputClearButton, 0, 0, 1, 1);
		addComponent(outScrollPane, 0, 1, 5, 7);
		addComponent(errClearButton, 6, 0, 1, 1);
		addComponent(errScrollPane, 6, 1, 5, 7);
		addComponent(allClearButton, 12, 0, 1, 1);
		System.out.println("message");

		outputClearButton.addActionListener(this);
		errClearButton.addActionListener(this);
		allClearButton.addActionListener(this);

		setVisible(true);
	}

	private void createComponent() {
		title = new JLabel("message");
		title.setFont(new Font("Arial", Font.BOLD, 20));

		outputClearButton = new JButton("clear");
		errClearButton = new JButton("clear");
		allClearButton = new JButton("All clear");
		outputArea = new JTextArea();
		outputArea.setLineWrap(true);
		errArea = new JTextArea();
		outputArea.setLineWrap(true);

		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		outputArea.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		errArea.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		outputArea.setEditable(false);
		errArea.setEditable(false);
		outScrollPane = new JScrollPane(outputArea);
		errScrollPane = new JScrollPane(errArea);
		outScrollPane.setPreferredSize(new Dimension(400, 190));
		errScrollPane.setPreferredSize(new Dimension(400, 190));
	}

	private void addComponent(Component com, int x, int y, int width, int height) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		layout.setConstraints(com, constraints);
		add(com);
	}

	public class JTextAreaStream extends OutputStream {
		private JTextArea textArea;
		private ByteArrayOutputStream byteOut;

		public JTextAreaStream(JTextArea area) {
			textArea = area;
			byteOut = new ByteArrayOutputStream();
		}

		@Override
		public void write(int b) throws IOException {
			byteOut.write(b);
		}

		@Override
		public void flush() throws IOException {
			// Swing のイベントスレッドにのせる
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textArea.append(byteOut.toString());
					byteOut.reset();
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == outputClearButton) {
			outputArea.setText("");
		} else if (source == errClearButton) {
			errArea.setText("");
		} else if (source == allClearButton) {
			outputArea.setText("");
			errArea.setText("");
		}
	}

}

