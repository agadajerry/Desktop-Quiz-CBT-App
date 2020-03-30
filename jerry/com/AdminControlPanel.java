package quiz.jerry.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AdminControlPanel extends JFrame {
	
	private JButton []buttons = new JButton[4];
	JPanel btnPanel[] = new JPanel[4];
	public AdminControlPanel() {
		setSize(new Dimension(800,600));
		
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		intialiseUI();
	}

	private void intialiseUI() {
		
		JLabel cbtLabel = new JLabel("<html>ADMINSTRATOR CONTROL PANEL<hr/></html>");
		cbtLabel.setFont(new Font("David", 1, 24));
		cbtLabel.setForeground(Color.BLUE);
		
		JPanel northPanel = new JPanel();
		northPanel.add(cbtLabel);
		northPanel.setBackground(Color.WHITE);
		northPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(northPanel,BorderLayout.NORTH);
		
	JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	southPanel.setBorder(new LineBorder(Color.BLACK, 4));

		
		for(int i=0;i<buttons.length;i++) {
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(160, 60));
			buttons[i].setFont(new Font("David", 1, 16));
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new AdminButtonListener());
			btnPanel[i] = new JPanel();
			btnPanel[i].add(buttons[i]);
			btnPanel[i].setBackground(Color.RED);
			btnPanel[i].setPreferredSize(new Dimension(180,80));
			southPanel.add(btnPanel[i]);	
		}
		btnPanel[0].setBackground(Color.ORANGE);
		btnPanel[1].setBackground(Color.MAGENTA);
		btnPanel[2].setBackground(Color.GREEN);
		btnPanel[3].setBackground(Color.CYAN);

		buttons[0].setText("Back");
		buttons[1].setText("Students Record");
		buttons[2].setText("Questions Record");
		buttons[3].setText("Change Password");
		
		add(southPanel,BorderLayout.SOUTH);
		
		JPanel centerPanel = new JPanel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/com/all_images/admin.jpeg"));
		centerPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel logo = new JLabel(icon);
		centerPanel.add(logo);
		centerPanel.setBackground(Color.WHITE);
		add(centerPanel,BorderLayout.CENTER);
		
		setVisible(true);
		
	}
	private class AdminButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ev) {

			JButton button = (JButton)ev.getSource();
			if(button.getActionCommand().equals("Back")) {
				AdminUserPanel adminU = new AdminUserPanel();
				adminU.setVisible(true);
				dispose();
			}
			if(button.getActionCommand().equals("Questions Record")) {
				QuestioRecordPanel qRecord =  new QuestioRecordPanel();
				qRecord.setVisible(true);
				dispose();
			}
		}
		
	}

}
