import  java.awt.*;
import  java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient{
	public static void main(String[] args) throws Exception
	{
		new ClientFrame().launch();
	}
}

class ClientFrame extends Frame{
	
	Frame frame = new Frame("ChatClient");
	TextField textField = new TextField(30);
	TextArea tArea = new TextArea("",20,20);
	Socket socket = null;
	DataOutputStream dataOutputStream = null;
	public void launch() throws Exception, Exception
	{
		setLocation(200,200);
		setVisible(true);
		//setResizable(false);
		add(textField,"South");
		add(tArea,"North");
		pack();
		textField.addActionListener(new TFListener());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		connect();
	}
	
	public void connect() throws Exception, IOException
	{
		socket = new Socket("127.0.0.1", 8888);
		System.out.println("Connect");
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}
	
	private class TFListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			String string = textField.getText().trim();
			tArea.setText(string); 
			textField.setText("");
			try {
				dataOutputStream.writeUTF(string);
				dataOutputStream.close();
				
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			
		}
	}
}