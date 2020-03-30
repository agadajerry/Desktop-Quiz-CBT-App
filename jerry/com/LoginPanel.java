package quiz.jerry.com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JFrame{
	
	private  JButton userLoginButton, regBtn, cancelBtn;
	private JPanel centerPanel, westPanel;
	private JLabel logo, userLabel,passLabel;
	private JTextField userField,passField;
	public static ArrayList<String> name;
	public  LoginPanel() {
		
		setSize(new Dimension(800,600));
		
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	setBackground(Color.WHITE);

		intialisUI();
	}

	private void intialisUI() {
		 setSize(new Dimension(800,600));
		 //setModal(true);

		JPanel eastPanel = new JPanel(new GridLayout(0,1));
		userLabel = new JLabel("User Name");
		userField = new JTextField();
		passField = new JTextField();
		passLabel = new JLabel("Password");
		
		JPanel eastSub2Panel = new JPanel(new GridLayout(0,1));
		userField.setPreferredSize(new Dimension(200, 30));
		userField.setFont(new Font("Algerian", 1, 24));
		
		passField.setPreferredSize(new Dimension(200, 30));
		passField.setFont(new Font("David", 1, 24));
		
		eastSub2Panel.add(userLabel);
		eastSub2Panel.add(userField);
		eastSub2Panel.add(new JLabel(""));
		eastSub2Panel.add(passLabel);
		eastSub2Panel.add(passField);
		eastSub2Panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		eastSub2Panel.setBackground(Color.WHITE);
		eastPanel.add(eastSub2Panel);
		
		
		JPanel eastSubPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20,20,20,20);
		gbc.gridx=0;
		gbc.gridy=0;
		userLoginButton = new JButton("Login");
		userLoginButton.addActionListener(new LoginListener());
		eastSubPanel.add(userLoginButton,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		
		cancelBtn = new JButton("Cancel");
		eastSubPanel.add(cancelBtn,gbc);
		cancelBtn.addActionListener(new Back2AdminLogin());
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		regBtn = new JButton("Register");
		
		
		eastSubPanel.add(regBtn,gbc);
		eastSubPanel.setBorder(new EmptyBorder(70, 70, 70, 70));
		eastSubPanel.setBackground(Color.WHITE);


		eastPanel.add(eastSubPanel);
		//
		
		ImageIcon icon = new ImageIcon(getClass().getResource
				("/com/all_images/cmp_operator.JPG"));
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		logo = new JLabel(icon);
		westPanel.add(logo);
		
		add(eastPanel,BorderLayout.EAST);
		add(westPanel,BorderLayout.WEST);
		setVisible(true);
		setResizable(false);

	}
	
	private class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			name = new ArrayList<>();
			name.add(userField.getText());
			
			MyQuiz mq = new MyQuiz();
			mq.setVisible(true);
			dispose();

		}
		
	}
	private class Back2AdminLogin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AdminUserPanel adminU = new AdminUserPanel();
			adminU.setVisible(true);
			dispose();		}
		
	}
}
