package quiz.jerry.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class QuestionAndAnswerPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel northPanel, bottomPanel, centerPanel;
	private JTextArea qLabel;
	private JRadioButton[] radioBtn = new JRadioButton[4];
	private ButtonGroup group = new ButtonGroup();
	private JLabel subjInProgress;
	private JLabel minLabel, secLabel;
	private JLabel qAnswered, scoredLabel;
	private int questionId;
	private int questionCountTotal = 0;
	private int score, wrongAnswer;
	private Questions currentQ;
	JButton calculatorBtn, next, finish, previousBtn, subBtn;
	Questions questObj;
	private JProgressBar progressB;
	private ArrayList<JButton> subjButton = new ArrayList<>();

	private Timer timer;
	private ArrayList<Questions> questlist;
	private ArrayList<SubjectList> subjName1;
	private ArrayList<ClassName> levelOfClass1;
	private HashMap<Integer, CorrectAnswer> correctScore = new HashMap<>();
	private int min, sec, progressValue;
	private String myAns;

	public QuestionAndAnswerPanel(ArrayList<Questions> questList, ArrayList<SubjectList> subjName,
			ArrayList<ClassName> levelOfClass) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1200, 700));
		setLocationRelativeTo(null);
		questlist = questList;
		subjName1 = subjName;
		levelOfClass1 = levelOfClass;

		timing();

		intialiseUI();
	}

	private void intialiseUI() {
		// single panel that contain all panels
		setLayout(new BorderLayout());
		// allPanel.setPreferredSize(new Dimension(1200,700));
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(3, 1));
		northPanel.setBorder(new LineBorder(Color.BLACK, 3));
		northPanel.setBackground(Color.WHITE);

		//

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		calculatorBtn = new JButton("Calculator");
		btnPanel.add(calculatorBtn);
		btnPanel.add(Box.createHorizontalGlue());
		calculatorBtn.setPreferredSize(new Dimension(140, 50));
		calculatorBtn.setFont(new Font("David", 1, 18));

		JLabel cbtLabel = new JLabel("GOVERNMENT SECONDARY SCHOOL, UGBUGBU-OWUKPA " + "COMPUTER BASE TEST",
				SwingConstants.CENTER);
		cbtLabel.setFont(new Font("David", 1, 24));
		cbtLabel.setForeground(Color.BLUE);
		northPanel.add(cbtLabel);
		northPanel.add(btnPanel);

		JPanel examTimePanel = new JPanel();
		examTimePanel.setLayout(new BoxLayout(examTimePanel, BoxLayout.X_AXIS));
		examTimePanel.setBackground(Color.WHITE);

		subjInProgress = new JLabel("");
		subjInProgress.setForeground(Color.GREEN);
		subjInProgress.setFont(new Font("David", 1, 38));

		scoredLabel = new JLabel("Scores");

		examTimePanel.add(subjInProgress);

		examTimePanel.add(Box.createHorizontalGlue());

		examTimePanel.add(scoredLabel);
		examTimePanel.add(Box.createHorizontalGlue());

		minLabel = new JLabel("1");
		minLabel.setForeground(Color.RED);
		minLabel.setFont(new Font("David", 1, 32));
		examTimePanel.add(minLabel);

		JLabel dotLabel = new JLabel(":");
		dotLabel.setForeground(Color.RED);
		dotLabel.setFont(new Font("David", 1, 32));

		examTimePanel.add(dotLabel);
		secLabel = new JLabel("0");
		secLabel.setForeground(Color.RED);
		secLabel.setFont(new Font("David", 1, 32));
		examTimePanel.add(secLabel);

		// examTimePanel.setBorder(new LineBorder(Color.BLACK, 3));
		examTimePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPanel.add(examTimePanel);
		add(northPanel, BorderLayout.NORTH);

		// ----------------------------------------------------------------------------------------

		// panel that holds questions field, subject button etc
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(4, 6));
		centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel welcomePanel = new JPanel(new GridLayout(4, 1));
		JPanel qPanel = new JPanel();

		// welcomePanel.add(examTimePanel);

		welcomePanel.setBorder(new LineBorder(Color.BLACK, 3));
		welcomePanel.setBackground(Color.lightGray);

		centerPanel.add(welcomePanel, BorderLayout.NORTH);

		JPanel qAndOptionPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		qAndOptionPanel.setBorder(new LineBorder(Color.BLACK, 3));
		qLabel = new JTextArea("");
		qLabel.setFont(new Font("David", 1, 16));
		qLabel.setLineWrap(true);
		qLabel.setSize(600, 80);
		qLabel.setEditable(false);

		qPanel = new JPanel();

		qPanel.add(qLabel);
		// qPanel.setBorder(new LineBorder(Color.BLACK, 3));
		qPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		qPanel.setBackground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 0;
		qAndOptionPanel.add(qPanel, gbc);

		// Radio Panel
		JPanel radioPanel = new JPanel(new GridLayout(7, 1));
		radioPanel.setBackground(Color.WHITE);
		radioPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
		// radioPanel.setBorder(new LineBorder(Color.BLACK, 4));
		radioPanel.add(new JLabel("<html>Choose An Options<hr/></html>"));
		for (int i = 0; i < radioBtn.length; i++) {
			radioBtn[i] = new JRadioButton();
			radioPanel.add(radioBtn[i]);
			group.add(radioBtn[i]);

		}
		radioBtn[0].setText("Nigeria");
		radioBtn[1].setText("Africa");
		radioBtn[2].setText("Libya");
		radioBtn[3].setText("America");

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		qAndOptionPanel.add(radioPanel, gbc);
		centerPanel.add(qAndOptionPanel, BorderLayout.CENTER);

		//
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		// bottomPanel.setBorder(new EmptyBorder(20,20,20,20));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		next = new JButton("Next");
		previousBtn = new JButton("Previous");
		finish = new JButton("Finish");
		previousBtn.addActionListener(new PreviousQuestion());
		previousBtn.setPreferredSize(new Dimension(120, 30));
		previousBtn.setFont(new Font("David", 1, 18));
		previousBtn.setEnabled(false);

		finish.setPreferredSize(new Dimension(100, 30));
		finish.setFont(new Font("David", 1, 18));
		finish.addActionListener(new FinishButtonListener());

		next.setPreferredSize(new Dimension(100, 30));
		next.setFont(new Font("David", 1, 18));
		next.addActionListener(new NextQuestionListener());

		progressB = new JProgressBar();
		// progressB.setValue(100);
		progressB.setToolTipText("Progress Bar");
		progressB.setPreferredSize(new Dimension(100, 30));
		progressB.setForeground(Color.GREEN);
		progressB.setBorder(new EmptyBorder(2, 2, 2, 2));

		bottomPanel.add(previousBtn);
		bottomPanel.add(next);

		bottomPanel.add(progressB);

		qAnswered = new JLabel("0/100");
		qAnswered.setForeground(Color.RED);
		bottomPanel.add(qAnswered);

		bottomPanel.add(finish);

		radioPanel.add(bottomPanel);
		radioPanel.add(new JLabel(""));

		// image panel
		JPanel centerWestPanel = new JPanel();

		// subjectButton

		for (int i = 0; i < subjName1.size(); i++) {

			subBtn = new JButton();
			subBtn.setText(subjName1.get(i).getSubjectName());
			// subBtn.addActionListener(new SubjectListListener());
			centerWestPanel.add(subBtn);
			subjButton.add(subBtn);

		}

		centerWestPanel.setBackground(Color.WHITE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/com/all_images/student_cmp.jpeg"));

		JLabel logo = new JLabel(icon);
		centerWestPanel.add(logo);
		centerPanel.add(centerWestPanel, BorderLayout.WEST);

		add(centerPanel, BorderLayout.CENTER);
		// add(bottomPanel, BorderLayout.SOUTH);
		setVisible(true);

		nextQuestion(questionId);
	}// end of initialize method

	// next questions method
	private void nextQuestion(int questionId) {
		for (int i = 0; i < radioBtn.length; i++) {
			radioBtn[i].setForeground(Color.RED);
			group.clearSelection();

		}

		questionCountTotal = questlist.size();

		// Collections.shuffle(questList);
		if (questionId < questionCountTotal) {
			currentQ = questlist.get(questionId);
			qLabel.setText(currentQ.getEngQuestion());

			radioBtn[0].setText(currentQ.getOptionA());
			radioBtn[1].setText(currentQ.getOptionB());
			radioBtn[2].setText(currentQ.getOptionC());
			radioBtn[3].setText(currentQ.getOptionD());

			// questionId++;
			qAnswered.setText((questionId+1) + "/" + questionCountTotal);

			

		} else {
			finishQuiz();
		}

		// name of selected subject and the class of student
		subjInProgress.setText(
				"Exam on " + subjName1.get(0).getSubjectName() + " - " + levelOfClass1.get(0).getLevelOfClass());

	}

	// this method close the quiz interface
	private void finishQuiz() {
		qLabel.setText("No more questions On this subject.");
		for (int i = 0; i < radioBtn.length; i++) {
			radioBtn[i].setText("null");
		}

	}

	private class PreviousQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {

			JButton prevBtn = (JButton) ev.getSource();
			if (prevBtn.getActionCommand().equals("Previous")) {
				myPreviousQ();
				updateButtonState();
			}

		}

	}

	private class NextQuestionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {

			JButton nextBtn = (JButton) ev.getSource();
			if (nextBtn.getActionCommand().equals("Next")) {

				if (radioBtn[0].isSelected() || radioBtn[1].isSelected() || radioBtn[2].isSelected()
						|| radioBtn[3].isSelected()) {

					saveSelectedAnswer();

				} else {
					JOptionPane.showMessageDialog(null, "Please select an answer");
				}

				// button state. Check questions number. which disable buttons
				updateButtonState();
			}

		}

	}

	private void saveSelectedAnswer() {

		for (int i = 0; i < radioBtn.length; i++) {

			if (radioBtn[i].isSelected()) {

				myAns = radioBtn[i].getText();

				correctScore.put(questionId, new CorrectAnswer(myAns, questionId));

			}
		}

		questionId++;
		nextQuestion(questionId);
		
		// progress bar progress
					progressValue = progressB.getValue()+(100/questlist.size());

					if (progressValue > progressB.getMaximum()) {
						progressValue = progressB.getMaximum();
					}
					progressB.setValue(progressValue);

	}

	private void timing() {

		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				min = Integer.parseInt(minLabel.getText());
				sec = Integer.parseInt(secLabel.getText());

				if (sec == 0) {
					sec = 60;
					min--;
				}

				if (min < 0) {

					min = 0;
					sec = 0;

					AdminUserPanel mq = new AdminUserPanel();
					mq.setVisible(true);
					dispose();

					TimeOffPanel timeOff = new TimeOffPanel(score, subjName1, questionCountTotal);
					timeOff.setVisible(true);

					timer.stop();
				} else {
					sec--;
					secLabel.setText("" + sec);
					minLabel.setText("" + min);
				}

			}

		});
		timer.start();
	}

	private class FinishButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			for (int i = 0; i < correctScore.size(); i++) {
				System.out.println(correctScore.get(i).getCorrectAns() + ", " + correctScore.get(i).getQuestionNo());

				if ((questlist.get(i).getAnswer()).equals(correctScore.get(i).getCorrectAns())) {
					score++;
				}
				
			}
			System.out.println("correct score mark " + score + "\n wrong answers " + wrongAnswer);

		}

	}

	private void updateButtonState() {
		if (questionId >= 1) {
			next.setEnabled(true);
			previousBtn.setEnabled(true);
		} 
		if (questionId == questionCountTotal) {
			next.setEnabled(false);
		} 
		if (questionId == 0) {
			previousBtn.setEnabled(false);
		}
	}

	private void myPreviousQ() {

		questionId = questionId - 1;
		nextQuestion(questionId);
		
		progressValue = progressB.getValue() -(100/questlist.size());

		if (progressValue > progressB.getMaximum()) {
			progressValue = progressB.getMaximum();
		}
		progressB.setValue(progressValue);
		qAnswered.setText(questionId + "/" + questionCountTotal);


	}
}
