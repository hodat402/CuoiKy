package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import KetNoiDataBase.JDBCconnection;
import Storage.UserInfor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField tfpass;
	private JTextField tfuser;

	/**
	 * Launch the application.
	 */
	public Connection cont = JDBCconnection.getJDBCConnection();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDangNhap = new JButton("LOGIN");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validatauser();
			}
		});
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangNhap.setBounds(165, 170, 89, 34);
		contentPane.add(btnDangNhap);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(70, 48, 61, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(70, 110, 85, 26);
		contentPane.add(lblPassword);
		
		
		JButton btnSignup = new JButton("Sign up");
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignup.setBounds(165, 215, 89, 35);
		btnSignup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Sign newFrame = new Sign();
				newFrame.setVisible(true); 
				
				// cái này là tắt frame trước đó.
				dispose();
				//JOptionPane.showMessageDialog(Login.this, "mở qua frame khác nhé");

			}
		});
		contentPane.add(btnSignup);
		
		tfpass = new JPasswordField();
		tfpass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfpass.setBounds(165, 110, 165, 34);
		contentPane.add(tfpass);
		
		tfuser = new JTextField();
		tfuser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfuser.setBounds(165, 48, 165, 34);
		contentPane.add(tfuser);
		tfuser.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(165, 11, 61, 26);
		contentPane.add(lblNewLabel);
	}
	
	public void validatauser() {
		String p = tfpass.getText();
		String u = tfuser.getText();
		
		try {
			String sql = "SELECT username,password from users WHERE username='"+u+"' and password ='"+p+"'";
			
			Statement st= cont.createStatement();
			
			ResultSet set= st.executeQuery(sql);
			
			if(!set.next())
			{
				JOptionPane.showMessageDialog(Login.this, "Sai thông tin đăng nhập");
			}else
			{
				UserInfor.sessionUsername = u;
				Client newFrame = new Client();
				newFrame.setVisible(true);
				dispose();

			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
