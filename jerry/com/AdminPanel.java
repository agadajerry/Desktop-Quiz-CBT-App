package quiz.jerry.com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class AdminPanel extends JFrame {
	private  JLabel userLabel, passLabel, logo;
	private JButton adminLogin,  cancelBtn;
	private JTextField userField, adminPassField;
	
	public AdminPanel() {
		setSize(new Dimension(800,600));
		
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	setBackground(Color.WHITE);

		
		JLabel cbtLabel = new JLabel("<html>ADMINSTRATOR LOGIN<hr/></html>");
		cbtLabel.setFont(new Font("David", 1, 24));
		cbtLabel.setForeground(Color.BLUE);
		
		JPanel northPanel = new JPanel();
		northPanel.add(cbtLabel);
		northPanel.setBackground(Color.WHITE);
		northPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		//
		
		JPanel eastPanel = new JPanel(new GridLayout(0,1));
		userLabel = new JLabel("User Name");
		userField = new JTextField();
		adminPassField = new JTextField();
		passLabel = new JLabel("Password");
		
		JPanel eastSub2Panel = new JPanel(new GridLayout(0,1));
		userField.setPreferredSize(new Dimension(200, 30));
		userField.setFont(new Font("David", 1, 24));
		
		adminPassField.setPreferredSize(new Dimension(200, 30));
		adminPassField.setFont(new Font("David", 1, 24));
		
		eastSub2Panel.add(userLabel);
		eastSub2Panel.add(userField);
		eastSub2Panel.add(new JLabel(""));
		eastSub2Panel.add(passLabel);
		eastSub2Panel.add(adminPassField);
		eastSub2Panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		eastSub2Panel.setBackground(Color.WHITE);
		eastPanel.add(eastSub2Panel);
		
		
		JPanel eastSubPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20,20,20,20);
		gbc.gridx=0;
		gbc.gridy=0;
		adminLogin = new JButton("Login");
		adminLogin.addActionListener(new LoginListener());
		eastSubPanel.add(adminLogin,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		
		cancelBtn = new JButton("Cancel");
		eastSubPanel.add(cancelBtn,gbc);
		eastSubPanel.setBackground(Color.WHITE);
		cancelBtn.addActionListener(new Back2LoginAdmin());
		

		eastPanel.add(eastSubPanel);
		//
		
		ImageIcon icon = new ImageIcon(getClass().getResource
				("/com/all_images/cmp_operator.JPG"));		
		logo = new JLabel(icon);
		JPanel logoPanel = new JPanel();
		logoPanel.add(logo);
		logoPanel.setBackground(Color.WHITE);
		
		add(eastPanel,BorderLayout.EAST);
		add(logoPanel,BorderLayout.CENTER);
		add(northPanel,BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	private class Back2LoginAdmin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			AdminUserPanel adminU = new AdminUserPanel();
			adminU.setVisible(true);
			dispose();
		}
		
	}
	private class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			AdminControlPanel adminControl = new AdminControlPanel();
			adminControl.setVisible(true);
			dispose();
		}
		
	}

}
