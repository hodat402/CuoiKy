package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import KetNoiDataBase.JDBCconnection;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Sign extends JFrame {

	private JPanel contentPane;
	private JTextField tfregisuser;

	public Connection cont = JDBCconnection.getJDBCConnection(); 
	private JPasswordField tfregispass;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign frame = new Sign();
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
	public Sign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfregisuser = new JTextField();
		tfregisuser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfregisuser.setBounds(196, 45, 180, 32);
		contentPane.add(tfregisuser);
		tfregisuser.setColumns(10);
		
		JLabel lblRegisuser = new JLabel("Username");
		lblRegisuser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegisuser.setBounds(73, 48, 85, 29);
		contentPane.add(lblRegisuser);
		
		JLabel lblRegispass = new JLabel("Password");
		lblRegispass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegispass.setBounds(73, 103, 85, 29);
		contentPane.add(lblRegispass);
		
		JButton btnRegis = new JButton("Sign up");
		btnRegis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				validatepass();
			}
		});
		btnRegis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegis.setBounds(170, 212, 89, 30);
		contentPane.add(btnRegis);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login newFrame = new Login();
				newFrame.setVisible(true); 
				
				// cái này là tắt frame trước đó.
				dispose();	
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(170, 253, 89, 32);
		contentPane.add(btnBack);
		
		tfregispass = new JPasswordField();
		tfregispass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfregispass.setBounds(196, 102, 180, 32);
		contentPane.add(tfregispass);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(73, 164, 110, 29);
		contentPane.add(lblConfirmPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(196, 161, 180, 32);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("SIGN UP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(159, 11, 74, 23);
		contentPane.add(lblNewLabel);
	}
	public void submitdatatodatabase() {
		
		try {
			String sql = "INSERT INTO users(username, password) VALUES (?,?)";
			PreparedStatement ps = cont.prepareStatement(sql);
			ps.setString(1, tfregisuser.getText());
			ps.setString(2, tfregispass.getText());
			
			int i = ps.executeUpdate();
			
			if(i>0) {
				JOptionPane.showMessageDialog(Sign.this, "Tạo tài khoản thành công, Đăng nhập thôi");
			}
			
			tfregisuser.setText("");
			tfregispass.setText("");
			passwordField.setText("");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void validatepass(){
		
		String pass1 = tfregispass.getText();
		String pass2 = passwordField.getText();
		
		if(!pass1.equals(pass2)) {
			JOptionPane.showMessageDialog(Sign.this, "Mật khẩu không trùng");
		}else {
			submitdatatodatabase();
		}
		
	}
}
