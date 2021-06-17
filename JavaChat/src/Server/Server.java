package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;

import Model.Messenger;
import Model.User;
import Server.Server.Manageuser;

public class Server {
	
	ServerSocket serverSocket;
	Vector<String> users = new Vector<String>();
	Vector<Manageuser> clients = new Vector<Manageuser>();
	
	public static void main(String[] args) throws Exception {
		 new Server().createserver();
	}
	
	public void createserver() throws Exception {
        serverSocket = new ServerSocket(1201);
        JOptionPane.showMessageDialog(null, "Server started", "Server", JOptionPane.INFORMATION_MESSAGE);
        while (true) {
            Socket client = serverSocket.accept();
            if (client.isConnected()) {
				System.out.println("Co nguoi ket noi");
				Manageuser c = new Manageuser(client);
			}
        }
    }
	
    public void sendtoall(Messenger message) throws IOException {
        for (Manageuser c : clients) {
        	if(!c.user.getName().equals(message.getName())) {
        		c.sendMessage(message);
        	}
        }
    }
	
	class Manageuser extends Thread {
		
        User user;
        String id = String.valueOf(Calendar.getInstance().getTimeInMillis());
        
        public Manageuser(Socket client) throws Exception {
        	InputStream input = client.getInputStream();
        	ObjectInputStream objInput = new ObjectInputStream(input);
            Messenger messenger = (Messenger) objInput.readObject();
            this.user = new User(messenger.getContent(),id,client);
            clients.add(this);
            users.add(user.getName()); 
            sendtoall(new Messenger("Server", this.user.getName()+" đã tham gia vao nhóm chat", Event.NEW_USER));
            sendtoall(new Messenger("Server", users.toString() , Event.UPDATE_USER)); 
            start();
        }
        public void sendMessage(Messenger messenger) throws IOException {
            OutputStream ops = user.getSocket().getOutputStream();
            ObjectOutputStream ots = new ObjectOutputStream(ops);
            ots.writeObject(messenger);
            ots.flush();
            
        }
        @Override
        public void run() {
        	Messenger messenger = null;
            try {
                while (true) {
                	if (!user.getSocket().isClosed()) {
                		InputStream input = user.getSocket().getInputStream();
                    	ObjectInputStream objInput = new ObjectInputStream(input);
                    	messenger = (Messenger) objInput.readObject();
                        if (messenger.getEvent() != null) {
                        	switch (messenger.getEvent()) {
							case Event.DICONNECT:
                                clients.remove(this);
                                users.remove(messenger.getName());
                                sendtoall(new Messenger("Server", messenger.getName()+ " đã thoát",Event.IFM_DICONNECT));
                                sendtoall(new Messenger("Server", users.toString() , Event.UPDATE_USER));
								break;
							case Event.REPORT_LIMITED:
								clients.remove(this);
                                users.remove(messenger.getName());
                                sendtoall(new Messenger("Server", messenger.getName()+ " đã bị click vì đã vi phạm văn hóa Chat Room",Event.IFM_DICONNECT));
                                sendtoall(new Messenger("Server", users.toString() , Event.UPDATE_USER));
								break;
							}
                            break;
                        }
                        sendtoall(messenger); 
					}
                }
            } 
            catch (Exception ex) {
            	clients.remove(this);
                users.remove(user.getName());
                ex.printStackTrace();
            }
        }
    } 
}
