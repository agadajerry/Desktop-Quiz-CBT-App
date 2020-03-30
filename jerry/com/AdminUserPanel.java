package quiz.jerry.com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminUserPanel extends JFrame {

	private JButton userLoginButton, adminButton;
	private JPanel eastPanel, westPanel;
	private JLabel logo;
	public static CardLayout cLayout;
	public static JPanel allPanel;

	public AdminUserPanel() {
		setSize(new Dimension(800, 600));
		setResizable(false);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBackground(Color.WHITE);
		JPanel regLoginPanel = new JPanel(new BorderLayout());
		JLabel cbtLabel = new JLabel("<html>GOVERNMENT SECONDARY SCHOOL," + " UGBUGBU-OWUKPA [CBT]<hr/></html>");
		cbtLabel.setFont(new Font("David", 1, 24));
		cbtLabel.setForeground(Color.BLUE);

		JPanel northPanel = new JPanel();
		northPanel.add(cbtLabel);
		northPanel.setBackground(Color.WHITE);
		northPanel.setBorder(new EmptyBorder(70, 70, 70, 70));
		regLoginPanel.add(northPanel, BorderLayout.NORTH);

		userLoginButton = new JButton("USER");
		userLoginButton.addActionListener(new UserLoginListener());
		adminButton = new JButton("ADMINISTRATOR");
		adminButton.addActionListener(new AdminListener());
		ImageIcon icon = new ImageIcon(getClass().getResource("/com/all_images/online.JPG"));
		logo = new JLabel(icon);

		eastPanel = new JPanel(new GridLayout(0, 1));
		eastPanel.setBackground(Color.WHITE);
		eastPanel.add(new JLabel(""));
		eastPanel.add(new JLabel(""));
		eastPanel.add(userLoginButton);
		eastPanel.add(new JLabel(""));
		eastPanel.add(new JLabel(""));

		eastPanel.add(adminButton);
		eastPanel.add(new JLabel(""));
		eastPanel.add(new JLabel(""));
		eastPanel.setBorder(new EmptyBorder(70, 70, 70, 70));

		regLoginPanel.add(eastPanel, BorderLayout.EAST);
		//
		westPanel = new JPanel();
		westPanel.add(logo);
		westPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
		westPanel.setBackground(Color.WHITE);

		regLoginPanel.add(westPanel, BorderLayout.WEST);

		allPanel = new JPanel();
		cLayout = new CardLayout();
		allPanel.setLayout(cLayout);
		allPanel.add(regLoginPanel, "panelregLogin");
		// allPanel.add(new AdminPanel(),"admin");
		// allPanel.add(new LoginPanel(),"login");

		cLayout.show(allPanel, "panelregLogin");
		add(allPanel);

		setVisible(true);

	}

	private class UserLoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// cLayout.show(allPanel, "login");
			LoginPanel lPanel = new LoginPanel();
			lPanel.setVisible(true);
			dispose();

		}

	}// end of login class

	private class AdminListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			AdminPanel admin = new AdminPanel();
			admin.setVisible(true);
			dispose();
		}

	}

	public static void main(String[] args) {
		new AdminUserPanel();
	}
}
