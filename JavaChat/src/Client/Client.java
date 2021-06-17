package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Model.Messenger;
import Model.User;
import Server.Event;
import Storage.UserInfor;

public class Client extends JFrame {

	private JPanel contentPane;
	Socket socket;
	JPanel pnlListUser;
	JPanel pnlChat;
	JLabel lblTM;
	String name;
	static MessagesThread messagesThread;
	public static boolean check = true;
	int count = 0;
	static JLabel lblVP;
	JDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void connection (String name) throws UnknownHostException, IOException {
		Messenger messenger = new Messenger(name,name);
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(messenger);
		objectOutputStream.flush();
	}
	
	public void register () {
		try {
			while (true) {
				name = JOptionPane.showInputDialog("Nhập biệt danh của bạn");
				if (name == null) {
					System.exit(0);
				} else if(!name.equals("")) {
					name = UserInfor.sessionUsername+"("+name+")";
					socket = new Socket("localhost",1201);
					connection(name);
					User user = new User(name, "", socket);
					Chat chatPanel = new Chat(user);
					JOptionPane.showMessageDialog(null,"Well come "+name);
					messagesThread = new MessagesThread(); 
					messagesThread.start();
					pnlChat.add(chatPanel,BorderLayout.CENTER);
					pnlChat.revalidate();
					break;
				} else {
					JOptionPane.showMessageDialog(null,"Biệt danh của bạn còn trống");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String upPerCase(String name) {
		String[] arr = name.split(" ");
		name = "";
		for (String x : arr) {
			name = name + (x.substring(0, 1).toUpperCase() + x.substring(1));
			name = name + " ";
		}
		return name;
	}


	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel.setForeground(new Color(34,112,147));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(683, 0, 40, 30);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblNewLabel);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(new Messenger(name,"", Event.DICONNECT));
					objectOutputStream.flush();
					messagesThread.stop();
					socket.close();
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblX.setBounds(733, 0, 34, 30);
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblX);
		
		pnlChat = new JPanel();
		pnlChat.setBounds(10, 76, 526, 425);
		pnlChat.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlChat);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(546, 76, 211, 425);
		contentPane.add(scrollPane);
		
		pnlListUser = new JPanel();
		pnlListUser.setBackground(new Color(245, 245, 245));
		pnlListUser.setLayout(new BoxLayout(pnlListUser, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(pnlListUser);
		
		JLabel lblNewLabel_1 = new JLabel("Chat Room");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(234, 0, 243, 50);
		contentPane.add(lblNewLabel_1);
		
		JPanel pnlName = new JPanel();
		pnlName.setBounds(11, 9, 200, 50);
		contentPane.add(pnlName);
		pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.X_AXIS));
		
		lblVP = new JLabel("1");
		lblVP.setForeground(new Color(51, 204, 51));
		lblVP.setHorizontalAlignment(SwingConstants.CENTER);
		lblVP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVP.setBounds(734, 36, 20, 14);
		contentPane.add(lblVP);
		
		lblTM = new JLabel("3");
		lblTM.setForeground(new Color(0, 204, 51));
		lblTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblTM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTM.setBounds(736, 57, 20, 14);
		lblTM.setText(String.valueOf(count));
		contentPane.add(lblTM);
		
		register();
		pnlName.add(new ChatContent(new Messenger(name.trim(), name.trim())));
		
		JLabel lblNewLabel_2 = new JLabel("Vi Phạm :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(648, 36, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Treo Máy :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(648, 57, 79, 14);
		contentPane.add(lblNewLabel_2_1);
		
		pnlName.revalidate();
		
//		setTimeOut();
	}
	
	private void setTimeOut() {
		JOptionPane jop = new JOptionPane("Bạn Đang Treo Máy", JOptionPane.WARNING_MESSAGE);
		dialog = jop.createDialog(null, "Cảnh báo");
		dialog.setModal(false);
        dialog.setVisible(false);
		TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            	System.out.println(dialog.isVisible());
                if (!check) {
                	if (dialog.isVisible()) {
						dialog.setVisible(false);
					}
                	dialog.setVisible(true);
                	count = count + 1;
                	if (count <=3) {
                		setColorLbl(count, lblTM);
					} else {
						try {
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
							objectOutputStream.writeObject(new Messenger(name,"", Event.DICONNECT));
							objectOutputStream.flush();
							messagesThread.stop();
							socket.close();
							System.exit(0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					check = false;
				}
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, 0, 15000);
	}
	
	public void setColorLbl(int i, JLabel lbl) {
		switch (i) {
		case 1:
			lbl.setForeground(new Color(255, 204, 51));
			lbl.setText(String.valueOf(i));
			break;
		case 2:
			lbl.setForeground(new Color(255, 204, 51));
			lbl.setText(String.valueOf(i));
			break;
		case 3:
			lbl.setForeground(new Color(255, 0, 0));
			lbl.setText(String.valueOf(i));
			break;
		}
	}
	
	
	class MessagesThread extends Thread {
        @Override
        public void run() {
            Messenger line;
            try {
                while(true) {
                	ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                	line = (Messenger) objectInputStream.readObject();
                	if(line.getName().equals("Server")) {
                		switch (line.getEvent()) {
						case Event.IFM_DICONNECT:
						case Event.NEW_USER:
							Chat.addMessenger(line);
							break;
						case Event.UPDATE_USER:
							String s = line.getContent().replace("[", "");
							s = s.replace("]", "");
							String[] list = s.split(",");
							pnlListUser.removeAll();
							for(String name : list) {
								ChatContent user = new ChatContent(new Messenger(name.trim(), name.trim()));
								pnlListUser.add(user);
								pnlListUser.revalidate();
							}
							break;
						default:
							break;
						}
                	} else {
                		Chat.addMessenger(line);
                	}
                }
            } catch(Exception ex) {
            	ex.printStackTrace();
            }
        }
    }
}
