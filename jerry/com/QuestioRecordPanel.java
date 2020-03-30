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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import net.proteanit.sql.DbUtils;

public class QuestioRecordPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> subjCombo;
	private JLabel[] optionLabel = new JLabel[5];
	private JTextField[] optionTextField = new JTextField[5];
	private JTextField qNoField;
	private JTextArea questionArea;
	private JButton[] sqlButton = new JButton[6];
	private JPanel sqlBtnPanel[] = new JPanel[6];
	private JTable table;
	private JLabel subjectNameLabel;
	private DefaultTableModel model = new DefaultTableModel();

	public QuestioRecordPanel() {
		setSize(new Dimension(1200, 800));
		setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(6, 8));
		intialiseUi();
	}

	private void intialiseUi() {
		JLabel cbtLabel = new JLabel("<html>MANAGE QUESTION'S RECORD<hr/></html>");
		cbtLabel.setFont(new Font("David", 1, 24));
		cbtLabel.setForeground(Color.BLUE);

		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(cbtLabel);
		northPanel.setBackground(Color.WHITE);
		northPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JButton deleteAllQBtn = new JButton("Delete All Questions");
		deleteAllQBtn.setPreferredSize(new Dimension(180, 30));
		deleteAllQBtn.setFont(new Font("David", 1, 16));
		northPanel.add(deleteAllQBtn);
		add(northPanel, BorderLayout.NORTH);

		JPanel westPanel = new JPanel(new BorderLayout(4, 4));
		JPanel centerPanel = new JPanel();
		JPanel soutOfWestPanel = new JPanel(new GridLayout(0, 1));
		centerPanel.setLayout(new FlowLayout());
		// westPanel.setBorder(new LineBorder(Color.BLACK, 3));
		westPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		String[] subjList = { "English", "Physics", "Mathematics", "Biology" };
		subjCombo = new JComboBox<>(subjList);
		subjCombo.setEditable(true);
		AutoCompleteDecorator.decorate(subjCombo);
		subjCombo.addActionListener(new SelectedSubject());

		JLabel selectLabel = new JLabel("Select Subject");
		JPanel northOfCenterPanel = new JPanel(new FlowLayout());
		northOfCenterPanel.add(selectLabel);
		northOfCenterPanel.add(subjCombo);

		JLabel qNoLabel = new JLabel("Question No");
		qNoField = new JTextField(5);
		qNoField.setPreferredSize(new Dimension(50, 30));

		northOfCenterPanel.add(qNoLabel);
		northOfCenterPanel.add(qNoField);
		// northOfCenterPanel.setBackground(Color.WHITE);
		westPanel.add(northOfCenterPanel, BorderLayout.NORTH);
		//
		JLabel quesLabel = new JLabel("Question");
		questionArea = new JTextArea("  ");
		JScrollPane qScroll = new JScrollPane(questionArea);
		qScroll.setPreferredSize(new Dimension(490, 70));
		questionArea.setFont(new Font("David", 1, 16));
		questionArea.setLineWrap(true);
		centerPanel.add(quesLabel);
		centerPanel.add(qScroll);
		westPanel.add(centerPanel, BorderLayout.CENTER);

		for (int j = 0; j < optionTextField.length; j++) {
			optionTextField[j] = new JTextField(50);
			optionLabel[j] = new JLabel();
			optionTextField[j].setPreferredSize(new Dimension(200, 30));
			optionTextField[j].setFont(new Font("David", 1, 16));

			soutOfWestPanel.add(optionLabel[j]);
			soutOfWestPanel.add(optionTextField[j]);

		}

		optionLabel[0].setText("A");
		optionLabel[1].setText("B");
		optionLabel[2].setText("C");
		optionLabel[3].setText("D");
		optionLabel[4].setText("ANS");

		// soutOfWestPanel.setBackground(Color.WHITE);
		westPanel.add(soutOfWestPanel, BorderLayout.SOUTH);
		add(westPanel, BorderLayout.CENTER);
		//
		JPanel southPanel = new JPanel(new FlowLayout());

		for (int i = 0; i < sqlButton.length; i++) {
			sqlButton[i] = new JButton();

			sqlButton[i].setPreferredSize(new Dimension(100, 60));
			sqlButton[i].setFont(new Font("David", 1, 16));
			sqlButton[i].setBackground(Color.WHITE);
			sqlButton[i].addActionListener(new SqlButtonListener());
			sqlBtnPanel[i] = new JPanel();
			sqlBtnPanel[i].add(sqlButton[i]);
			sqlBtnPanel[i].setPreferredSize(new Dimension(150, 80));
			southPanel.setBorder(new LineBorder(Color.BLACK, 3));
			southPanel.setBackground(Color.WHITE);
			southPanel.add(sqlBtnPanel[i]);

		}
		//
		sqlBtnPanel[0].setBackground(Color.RED);
		sqlBtnPanel[1].setBackground(Color.GREEN);
		sqlBtnPanel[2].setBackground(Color.ORANGE);
		sqlBtnPanel[3].setBackground(Color.CYAN);
		sqlBtnPanel[4].setBackground(Color.MAGENTA);
		sqlBtnPanel[5].setBackground(Color.LIGHT_GRAY);

		sqlButton[0].setText("Back");
		sqlButton[1].setText("Save");
		sqlButton[2].setText("Modify");
		sqlButton[3].setText("Open");
		sqlButton[4].setText("Delete");
		sqlButton[5].setText("Clear");

		add(southPanel, BorderLayout.SOUTH);

		// Table to the east panel

		String columnName[] = { "ID", "Question", "OptionA", "OptionB", "OptionC", "OptionD", "Answer" };
		table = new JTable();

		table = new JTable();
		// table.setShowVerticalLines(false);
		table.setBackground(Color.ORANGE);
		table.addMouseListener(new TableMouseClick());
		// table.setBackground(Color.RED);
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnName);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(600, 400));

		JPanel eastPanel = new JPanel(new FlowLayout());

		eastPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		subjectNameLabel = new JLabel("Subject Name");
		eastPanel.add(subjectNameLabel);
		eastPanel.add(scroll);
		add(eastPanel, BorderLayout.EAST);

		setVisible(true);
	}// end of initialiseUI method

	private class SqlButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {

			JButton sqlButtons = (JButton) ev.getSource();

			if (sqlButtons.getActionCommand().equals("Back")) {

				AdminControlPanel adminU = new AdminControlPanel();
				adminU.setVisible(true);
				dispose();
			}

			if (sqlButtons.getActionCommand().equals("Save")) {

				tableShowingData(); // Display text field entering in the table

				saveToDataBase((String) subjCombo.getSelectedItem());
			}
			if (sqlButtons.getActionCommand().equals("Modify")) {
				modifyQuestions((String) subjCombo.getSelectedItem());
			}
		}

		private void saveToDataBase(String tableName) {

			// add a condition for empty textfield before sql querry

			if (qNoField.getText().isEmpty() || optionTextField[0].getText().toString().trim().isEmpty()
					|| optionTextField[1].getText().toString().trim().isEmpty()
					|| optionTextField[2].getText().toString().trim().isEmpty()
					|| optionTextField[3].getText().toString().trim().isEmpty()
					|| optionTextField[4].getText().toString().isEmpty()
					|| questionArea.getText().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "One of the text Fields is empty. No data is inserted to DB");

			} else {

				int qNo = Integer.parseInt(qNoField.getText().toString().trim());
				String question = questionArea.getText().toString().trim();

				PreparedStatement ps = null;
				String query = "Insert into " + tableName + " (ID, Question, OptionA, OptionB,"
						+ " OptionC, OptionD, Answer) Values(?,?,?,?,?,?,?)";

				try {

					ps = DBconnect.getConnection().prepareStatement(query);

					ps.setInt(1, qNo);
					ps.setString(2, question);
					ps.setString(3, optionTextField[0].getText().toString().trim());
					ps.setString(4, optionTextField[1].getText().toString().trim());
					ps.setString(5, optionTextField[2].getText().toString().trim());
					ps.setString(6, optionTextField[3].getText().toString().trim());
					ps.setString(7, optionTextField[4].getText().toString().trim());

					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,
							"Question is saved to " + subjCombo.getSelectedItem() + " Table Successfully..");

					for (int i = 0; i < optionTextField.length; i++) {
						optionTextField[i].setText(null);
					}
					questionArea.setText(null);
					qNoField.setText(null);
					ps.close();
					questionIDIncrease();//increase the id when  anew data is saved

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "The question number already exist.\nYou can"
							+ " only modify the question\n" + ex.getMessage());
				}

			}
		}

		// add textfield value to table
		private void tableShowingData() {

			if (qNoField.getText().isEmpty() || optionTextField[0].getText().toString().trim().isEmpty()
					|| optionTextField[1].getText().toString().trim().isEmpty()
					|| optionTextField[2].getText().toString().trim().isEmpty()
					|| optionTextField[3].getText().toString().trim().isEmpty()
					|| optionTextField[4].getText().toString().isEmpty()
					|| questionArea.getText().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "One of the text Fields is empty");

			} else {
				model.addRow(new String[] { qNoField.getText(), questionArea.getText().toString().trim(),
						optionTextField[0].getText(), optionTextField[1].getText(), optionTextField[2].getText(),
						optionTextField[3].getText(), optionTextField[4].getText() });
			}
		}

		// Modification of questions and answer by database query, update
		private void modifyQuestions(String tableName) {

			if (qNoField.getText().isEmpty() || optionTextField[0].getText().toString().trim().isEmpty()
					|| optionTextField[1].getText().toString().trim().isEmpty()
					|| optionTextField[2].getText().toString().trim().isEmpty()
					|| optionTextField[3].getText().toString().trim().isEmpty()
					|| optionTextField[4].getText().toString().isEmpty()
					|| questionArea.getText().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "One of the text Fields is empty. No data is inserted to DB");

			} else {

				int qNo = Integer.parseInt(qNoField.getText().toString().trim());
				String question = questionArea.getText().toString().trim();
				PreparedStatement ps = null;
				String query = "Update " + tableName + " SET Question =?, OptionA =?, OptionB =?,"
						+ " OptionC =?, OptionD =?, Answer =? Where ID= ?";

				try {
					ps = DBconnect.getConnection().prepareStatement(query);

					ps.setString(1, question);
					ps.setString(2, optionTextField[0].getText().toString().trim());
					ps.setString(3, optionTextField[1].getText().toString().trim());
					ps.setString(4, optionTextField[2].getText().toString().trim());
					ps.setString(5, optionTextField[3].getText().toString().trim());
					ps.setString(6, optionTextField[4].getText().toString().trim());
					ps.setInt(7, qNo);

					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,
							"Question is Updated to " + subjCombo.getSelectedItem() + " Table Successfully..");

					for (int i = 0; i < optionTextField.length; i++) {
						optionTextField[i].setText(null);
					}
					questionArea.setText(null);
					qNoField.setText(null);
					ps.close();
					questionIDIncrease();//increase the id when  anew data is saved

				} catch (SQLException exc) {

				}
			}
		}

	}

	private class SelectedSubject implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			model.setRowCount(0);
			if (subjCombo.getSelectedIndex() != -1) {

				subjectNameLabel.setForeground(Color.RED);
				subjectNameLabel.setText(subjCombo.getSelectedItem().toString() + " \nQuestions -->");

				populateTable(subjCombo.getSelectedItem().toString());

				// question id increase
				questionIDIncrease();
			}
		}

	}

	// populate JTable with data from database
	private void populateTable(String tableName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select * From " + tableName;

		try {
			ps = DBconnect.getConnection().prepareStatement(query);

			rs = ps.executeQuery();

			// table.setModel(DbUtils.resultSetToTableModel(rs));

			while (rs.next()) {

				int qNo = rs.getInt("ID");
				String question = rs.getString("Question");
				String optionA = rs.getString("OptionA");
				String optionB = rs.getString("OptionB");
				String optionC = rs.getString("OptionC");
				String optionD = rs.getString("OptionD");
				String answer = rs.getString("Answer");
				model.addRow(new Object[] { qNo, question, optionA, optionB, optionC, optionD, answer });
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	private class TableMouseClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int i = table.getSelectedRow();
			qNoField.setText(model.getValueAt(i, 0).toString());
			questionArea.setText(model.getValueAt(i, 1).toString());
			optionTextField[0].setText(model.getValueAt(i, 2).toString());
			optionTextField[1].setText(model.getValueAt(i, 3).toString());
			optionTextField[2].setText(model.getValueAt(i, 4).toString());
			optionTextField[3].setText(model.getValueAt(i, 5).toString());
			optionTextField[4].setText(model.getValueAt(i, 6).toString());

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private void questionIDIncrease() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlQ = "Select ID from " + subjCombo.getSelectedItem().toString() + " " + " ORDER By ID DESC limit 1;";
		try {
			ps = DBconnect.getConnection().prepareStatement(sqlQ);
			rs = ps.executeQuery();
			while (rs.next()) {
				int qId = rs.getInt("ID");
				qNoField.setText("" + (1 + qId));
			}
			rs.close();
			ps.close();
			DBconnect.getConnection().close();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

	}

}
