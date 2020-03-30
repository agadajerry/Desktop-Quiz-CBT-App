package quiz.jerry.com;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyQuiz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JRadioButton classRadio[] = new JRadioButton[6];
	private ButtonGroup group = new ButtonGroup();

	private JScrollPane scroll;
	private JPanel subjectPanel;
	public static CardLayout cardL;
	public static JPanel mainPanel;
	private ButtonGroup qGroup = new ButtonGroup();
	public static ArrayList<SubjectList> subjName;
	private static ArrayList<ClassName> levelOfClass;
	public JRadioButton[] subjCheck = new JRadioButton[10];
	private JButton startBtn;
	private static ArrayList<Questions> questList;
	private DefaultComboBoxModel<String> subjectModel = new DefaultComboBoxModel<String>();

	public MyQuiz() {
		setTitle("My Quiz Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1200, 700));
		setLocationRelativeTo(null);
		initialiseUi();

		this.setVisible(true);

	}

	private void initialiseUi() {

		// create a container for holding all panels

		Container mainContainer = this.getContentPane();
		// mainContainer.setLayout(new BorderLayout(6, 8));
		mainContainer.setBackground(Color.WHITE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 118, 255)));

		// ----------------------------------------------------------------------------------------

		// add(new QuestionAndAnswerPanel());

		subjectPanel = new JPanel(new BorderLayout());// holds all panel to allow region
		subjectPanel.setBackground(Color.WHITE);
		// bottom panels
		JPanel bottomPanel1 = new JPanel();
		bottomPanel1.setLayout(new BoxLayout(bottomPanel1, BoxLayout.X_AXIS));
		bottomPanel1.setBorder(new LineBorder(Color.BLACK, 4));
		bottomPanel1.setBackground(Color.LIGHT_GRAY);

		startBtn = new JButton("Start");
		JButton backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(200, 50));
		backBtn.setFont(new Font("David", 1, 24));
		startBtn.setPreferredSize(new Dimension(200, 50));
		startBtn.setFont(new Font("David", 1, 24));
		startBtn.addActionListener(new StartButtonListener());
		backBtn.addActionListener(new BackHomeListener());

		bottomPanel1.add(backBtn);
		bottomPanel1.add(Box.createHorizontalGlue());
		bottomPanel1.add(startBtn);

		JPanel westPanel = new JPanel(new GridLayout(0, 1));
		// westPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		westPanel.setBorder(new LineBorder(Color.BLACK, 3));

		JPanel radioButtonPanel = new JPanel(new GridBagLayout());
		radioButtonPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 0, 0)));

		GridBagConstraints gbc = new GridBagConstraints();

		for (int i = 0; i < classRadio.length; i++) {
			classRadio[i] = new JRadioButton();
			group.add(classRadio[i]);
			classRadio[i].setFont(new Font("David", 1, 24));

		}

		JLabel selectLabel = new JLabel("Select Your Class", SwingConstants.CENTER);
		// selectLabel.setPreferredSize(new Dimension(140, 50));
		selectLabel.setFont(new Font("David", 1, 26));

		westPanel.add(selectLabel);

		gbc.gridx = 0;
		gbc.gridy = 1;
		classRadio[0].setText("JSS1");
		radioButtonPanel.add(classRadio[0], gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		classRadio[1].setText("JSS2");
		radioButtonPanel.add(classRadio[1], gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		classRadio[2].setText("JSS3");
		radioButtonPanel.add(classRadio[2], gbc);
		// ss classes buttons
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0.01;
		classRadio[3].setText("SS1");
		radioButtonPanel.add(classRadio[3], gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		classRadio[4].setText("SS2");
		radioButtonPanel.add(classRadio[4], gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		classRadio[5].setText("SS3");
		radioButtonPanel.add(classRadio[5], gbc);
		westPanel.add(radioButtonPanel);

		// North Panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.setBorder(new LineBorder(Color.BLACK, 3));
		northPanel.setBackground(Color.WHITE);

		// northPanel.add(Box.createHorizontalGlue());
		JLabel cbtLabel = new JLabel("GOVERNMENT SECONDARY SCHOOL, UGBUGBU-OWUKPA CBT", SwingConstants.CENTER);
		cbtLabel.setFont(new Font("David", 1, 24));
		cbtLabel.setForeground(Color.BLUE);

		northPanel.add(cbtLabel);

		// center panel

		// panel that holds questions field, subject button etc
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(4, 6));
		centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel eastPanel = new JPanel(new GridLayout(0, 1));
		scroll = new JScrollPane(eastPanel);

		for (int i = 0; i < subjCheck.length; i++) {
			subjCheck[i] = new JRadioButton();
			qGroup.add(subjCheck[i]);
			eastPanel.add(subjCheck[i]);
		}
		subjCheck[0].setText("Physics");
		subjCheck[1].setText("Chemistry");
		subjCheck[2].setText("Biology");
		subjCheck[3].setText("Mathematics");
		subjCheck[4].setText("Economics");
		subjCheck[5].setText("Commerce");
		subjCheck[6].setText("Agricultural Science");
		subjCheck[7].setText("CRK");
		subjCheck[8].setText("Government");
		subjCheck[9].setText("English");
		// ------------------------------------------------------------------------

		JTextArea instructLabel = new JTextArea("\n\n\n\nNow you" + " are logged in.\n Select your class and subject"
				+ " you are asked to take...\n" + "You cannot go back again until the exam period elapse.\n"
				+ " Logout if you dont" + "want to continue with the test.\nBe informed that the test will come to "
				+ "an end when the time elapsed.\n" + " Good Luck!");
		instructLabel.setFont(new Font("David", 1, 24));
		instructLabel.setLineWrap(true);
		instructLabel.setSize(600, 70);
		instructLabel.setEditable(false);

		JPanel instuctPanel = new JPanel();
		instuctPanel.add(instructLabel);
		// qPanel.setBorder(new LineBorder(Color.BLACK, 3));
		instuctPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		instuctPanel.setBackground(Color.WHITE);

		subjectPanel.add(instuctPanel, BorderLayout.CENTER);
		subjectPanel.add(westPanel, BorderLayout.WEST);
		// subjectPanel.add(centerPanel, BorderLayout.CENTER);
		subjectPanel.add(bottomPanel1, BorderLayout.SOUTH);
		subjectPanel.add(northPanel, BorderLayout.NORTH);
		subjectPanel.add(scroll, BorderLayout.EAST);

		mainPanel = new JPanel();
		cardL = new CardLayout();
		mainPanel.setLayout(cardL);
		// mainPanel.add(new QuestionAndAnswerPanel(), "panel1");
		mainPanel.add(subjectPanel, "panel2");

		cardL.show(mainPanel, "panel2");

		mainContainer.add(mainPanel);

		// createSubjectArray();
	}// end of initaliseUi

	private class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (!(subjCheck[0].isSelected()   
					|| subjCheck[1].isSelected()   
					|| subjCheck[2].isSelected()  
					|| subjCheck[3].isSelected()  
					|| subjCheck[4].isSelected()  
					||subjCheck[5].isSelected()  
					|| subjCheck[6].isSelected()  
					|| subjCheck[7].isSelected() 
					|| subjCheck[8].isSelected() 
					|| subjCheck[9].isSelected()  
					|| classRadio[0].isSelected() 
					|| classRadio[1].isSelected()  
					|| classRadio[2].isSelected()  
					|| classRadio[3].isSelected()   
					|| classRadio[4].isSelected() 
					|| classRadio[5].isSelected())) {
				JOptionPane.showMessageDialog(null, "Check Your class And subject \nYou are to take the exam");
			} else {

				// save selected subject in arraylist
				createSubjectArray();
			classLevel();

				for (JRadioButton cBox : subjCheck) {

					if (cBox.isSelected()) {
						LoadQuestByBtn1 loadQ = new LoadQuestByBtn1();

						questList = loadQ.allBtn1Question("Select * From " + cBox.getText());

					}

				}
				QuestionAndAnswerPanel adminU = new QuestionAndAnswerPanel(questList, subjName, levelOfClass);
				adminU.setVisible(true);
				dispose();
			}

		}

	}

	private void createSubjectArray() {
		subjName = new ArrayList<>();

		for (JRadioButton cBox : subjCheck) {
			if (cBox.isSelected()) {

				subjName.add(new SubjectList(cBox.getText()));

			}
		}

	}

	private void classLevel() {
		levelOfClass = new ArrayList<>();

		for (int j = 0; j < classRadio.length; j++) {
			if (classRadio[j].isSelected()) {
				levelOfClass.add(new ClassName(classRadio[j].getText()));

			}
		}

	}

	private class BackHomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AdminUserPanel adminU = new AdminUserPanel();
			adminU.setVisible(true);
			dispose();
		}

	}
}
