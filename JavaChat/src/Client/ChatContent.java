package Client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Messenger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.border.CompoundBorder;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.Locale;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.Point;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;

public class ChatContent extends JPanel {
	
	FlowLayout flowLayout;
	JLabel lblName;
	JLabel lblContent;

	public ChatContent(String name, String content ) {
		
		setBackground(Color.WHITE);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		lblContent = new JLabel("Xin Chao");
		
		if (name.equals("") || name.equals("Server")) {
			if (name.equals("Server")) {
				flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
				lblContent.setBackground(Color.WHITE);
				lblContent.setFont(new Font("Tahoma", Font.ITALIC, 12));
				setMaximumSize(new Dimension(32767, 30));
				setBorder(new EmptyBorder(2, 0, 2, 0));
				lblContent.setBorder(new EmptyBorder(5, 0, 5, 0));
			} else {
				flowLayout = new FlowLayout(FlowLayout.RIGHT, 0, 0);
				lblContent.setBackground(new Color(0, 153, 255));
				lblContent.setForeground(new Color(255, 255, 255));
				lblContent.setFont(new Font("Tahoma", Font.BOLD, 14));
				setMaximumSize(new Dimension(32767, 40));
				setBorder(new EmptyBorder(5, 0, 5, 10));
				lblContent.setBorder(new EmptyBorder(10, 10, 10, 10));
			}
			add(lblContent);
			
		} else {
			JPanel panel = new JPanel();
			flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
			setBorder(new EmptyBorder(5, 10, 5, 0));
			setMaximumSize(new Dimension(32767, 70));
			lblContent.setBackground(Color.WHITE);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			lblContent.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			JLabel txtName = new JLabel();
			txtName.setOpaque(true);
			txtName.setBackground(Color.WHITE);
			txtName.setHorizontalAlignment(SwingConstants.LEFT);
			txtName.setBorder(new EmptyBorder(0, 0, 3, 0));
			txtName.setFont(new Font("Tahoma", Font.BOLD, 15));
			txtName.setText(name);
			panel.add(txtName);
			
			lblName = new JLabel("K");
			lblName.setOpaque(true);
			lblName.setBackground(new Color(255, 0, 0));
			lblName.setAlignmentY(Component.TOP_ALIGNMENT);
			lblName.setPreferredSize(new Dimension(40, 40));
			lblName.setMaximumSize(new Dimension(40, 40));
			lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setText((name.equals("")) ? "" : name.substring(0, 1).toUpperCase());
			add(lblName);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(10, 0));
			add(lblNewLabel_1);
			
			panel.setOpaque(false);
			panel.setBorder(null);
			panel.setBorder(new EmptyBorder(1, 1, 1, 1));
			panel.setBackground(Color.WHITE);
			add(panel);
			panel.add(lblContent);
		}
		setLayout(flowLayout);
		lblContent.setOpaque(true);
		lblContent.setText(content);
		
	}
	
	public ChatContent (String name, byte[] img) {
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(32767, 70));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		lblContent = new JLabel();
		lblName = new JLabel("K");
		
		if (name.equals("")) {
			flowLayout = new FlowLayout(FlowLayout.RIGHT, 0, 0);
			lblName.setVisible(false);
			setBorder(new EmptyBorder(5, 0, 5, 10));
		} else {	
			flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
			setBorder(new EmptyBorder(5, 10, 5, 0));
		}
		setLayout(flowLayout);
		lblName.setOpaque(true);
		lblName.setPreferredSize(new Dimension(40, 40));
		lblName.setMaximumSize(new Dimension(40, 40));
		lblName.setBorder(new EmptyBorder(9, 13, 9, 13));
		lblName.setBackground(new Color(255, 0, 51));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setText((name.equals("")) ? "" : name.substring(0, 1).toUpperCase());
		add(lblName);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBorder(new EmptyBorder(2, 5, 2, 5));
		add(lblNewLabel);
		
		lblContent.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContent.setHorizontalAlignment(SwingConstants.LEFT);
		lblContent.setIcon(reIcon(img));
		add(lblContent);
	}
	
	public ChatContent( Messenger messenger ) {
		setBackground(new Color(245, 245, 245));
		setMaximumSize(new Dimension(32767, 50));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		lblContent = new JLabel("Xin Chao");
		lblName = new JLabel("K");
		
		flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		setBorder(new EmptyBorder(5, 10, 5, 0));
		lblContent.setBackground(new Color(245, 245, 245));
		setLayout(flowLayout);
		lblName.setOpaque(true);
		lblName.setPreferredSize(new Dimension(40, 40));
		lblName.setMaximumSize(new Dimension(40, 40));
		lblName.setBorder(new EmptyBorder(9, 12, 9, 12));
		lblName.setBackground(new Color(255, 0, 51));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setText(messenger.getName().substring(0, 1).toUpperCase());
		add(lblName);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBorder(new EmptyBorder(2, 2, 2, 2));
		add(lblNewLabel);
		
		lblContent.setBorder(new EmptyBorder(11, 15, 11, 15));
		lblContent.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContent.setForeground(new Color(0, 0, 0));
		lblContent.setOpaque(false);
		lblContent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContent.setHorizontalAlignment(SwingConstants.LEFT);
		lblContent.setText(messenger.getContent());
		add(lblContent);
	}
	
	public ChatContent(String name,String fileName, byte[] fileSent) {
		setBorder(new EmptyBorder(0, 5, 0, 0));
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(32767, 51));
		
		
		lblName = new JLabel("K");
		lblContent = new JLabel();
		
		if (name.equals("")) {
			flowLayout = new FlowLayout(FlowLayout.RIGHT, 0, 0);
			lblName.setVisible(false);
			lblContent.setBackground(new Color(0, 153, 255));
			setBorder(new EmptyBorder(5, 0, 5, 10));
			add(lblContent);
		} else {	
			flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
			setBorder(new EmptyBorder(5, 10, 5, 0));
			
			lblName.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblName.setBorder(new EmptyBorder(0, 0, 0, 0));
			lblName.setOpaque(true);
			lblName.setBackground(new Color(255, 0, 0));
			lblName.setPreferredSize(new Dimension(40, 40));
			lblName.setMaximumSize(new Dimension(40, 40));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblName.setText((name.equals("")) ? "" : name.substring(0, 1).toUpperCase());
			add(lblName);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(10, 0));
			add(lblNewLabel_1);
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBorder(new EmptyBorder(1, 1, 1, 1));
			panel.setBackground(Color.WHITE);
			panel.setPreferredSize(new Dimension(275, 50));
			add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			JLabel txtName = new JLabel("Khoa");
			txtName.setOpaque(true);
			txtName.setBackground(Color.WHITE);
			txtName.setHorizontalAlignment(SwingConstants.LEFT);
			txtName.setBorder(new EmptyBorder(0, 0, 3, 0));
			txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtName.setText(name);
			panel.add(txtName);
			panel.add(lblContent);
		}
		setLayout(flowLayout);
		
		
		lblContent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblContent.setText("<HTML><U>"+fileName+"</U></HTML>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblContent.setText("<HTML><p>"+fileName+"</p></HTML>");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFileChooser chooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
					chooser.setSelectedFile(new File(fileName));
					chooser.setAcceptAllFileFilterUsed(false);
					int result = chooser.showSaveDialog(null);
					if (result == chooser.APPROVE_OPTION) { 
						String newFile = chooser.getSelectedFile().getAbsolutePath();
						newFile = (newFile.endsWith(fileName.substring(fileName.indexOf(".")))) ? newFile : newFile+".txt";
						File file = new File(newFile);
						if (!file.exists()) {
							OutputStream fs = new FileOutputStream(file);
							fs.write(fileSent);
							fs.close();
							JOptionPane.showMessageDialog(null, "Tải file thành công");
						}
						else {
							JOptionPane.showMessageDialog(null, "Tải file tồn tại vui lòng đổi tên");
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		lblContent.setBackground(Color.WHITE);
		lblContent.setIcon(new ImageIcon("img\\icons8-download-18.png"));
		lblContent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblContent.setOpaque(true);
		lblContent.setText("<HTML><p>"+fileName+"</p></HTML>");
		lblContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblContent.setFont(new Font("Tahoma", Font.BOLD, 12));

	}
	
	public ImageIcon reIcon(byte[] image) {
		ImageIcon img = new ImageIcon(image);
		return img;
	}

}

