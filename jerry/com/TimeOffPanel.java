package quiz.jerry.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TimeOffPanel extends JDialog {

	private JLabel totalScoreLabel, subjectArea, logo,candidateLabel;
	private int totalScor;
	private ArrayList<SubjectList> SubjectTaking;
	private JButton exitButton;
	private int totalQuestion;
	private ArrayList<String> myName = LoginPanel.name;

	public TimeOffPanel(int score, ArrayList<SubjectList> subjName1, int questionCountTotal) {

		setSize(new Dimension(600, 300));
		setTitle("RESULT NOTIFICATION");
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setModal(true);
		
		totalScor = score;
		SubjectTaking = subjName1;
		totalQuestion = questionCountTotal;
		
		intialiseUi();
	}

	private void intialiseUi() {


		JPanel candidateNamePanel = new JPanel();
		candidateNamePanel.setBackground(Color.WHITE);
		candidateLabel = new JLabel("<html> Candidate Name: " +myName.toString()+"<hr/> </html>");
		candidateNamePanel.add(candidateLabel);
		candidateLabel.setFont(new Font("Algerian", 1, 24));
		candidateLabel.setForeground(Color.RED);
		
		//
		totalScoreLabel = new JLabel("Total Score: " + ((totalScor*100)/totalQuestion)+"%");
		totalScoreLabel.setFont(new Font("David", 1, 26));
		totalScoreLabel.setForeground(Color.GREEN);

		subjectArea = new JLabel("subject Taking: " + SubjectTaking.get(0).getSubjectName());
		subjectArea.setFont(new Font("David", 1, 26));
		subjectArea.setForeground(Color.GREEN);
		
		 JLabel totalQuestLabel = new JLabel("Total Question: "+totalQuestion);
		 totalQuestLabel.setForeground(Color.GREEN);
		 totalQuestLabel.setFont(new Font("David", 1, 26));
		JPanel scorePanel = new JPanel(new GridLayout(0, 1));
		scorePanel.add(subjectArea);
		scorePanel.add(totalQuestLabel);
		scorePanel.add(totalScoreLabel);
		JLabel wrongAnsLabel = new JLabel("Total Failed Marks: "
							+(((totalQuestion-totalScor)*100)/totalQuestion)+"%");
		wrongAnsLabel.setFont(new Font("David", 1, 26));
		wrongAnsLabel.setForeground(Color.GREEN);
		
		scorePanel.add(wrongAnsLabel);
		scorePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		scorePanel.setBackground(Color.WHITE);

		ImageIcon icon = new ImageIcon(getClass().getResource("/com/all_images/score.jpg"));

		logo = new JLabel(icon);

		add(candidateNamePanel,BorderLayout.NORTH);
		add(scorePanel, BorderLayout.CENTER);
		add(logo, BorderLayout.WEST);

		setVisible(true);
	}
}
