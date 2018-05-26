import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] args) {
		try {
			new ClientFrame().launch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientFrame extends Frame {

	Frame frame = new Frame("ChatClient");
	TextField textField = new TextField(30);
	TextArea tArea = new TextArea("", 20, 20);
	Socket socket = null;
	DataOutputStream dataOutputStream = null;

	public void launch() {
		setLocation(200, 200);
		setVisible(true);
		// setResizable(false);
		add(textField, "South");
		add(tArea, "North");
		pack();
		textField.addActionListener(new TFListener());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});

		connect();

	}

	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 8888);
			System.out.println("Connect");
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			dataOutputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String string = textField.getText().trim();
			tArea.setText(string);
			textField.setText("");
			try {
				dataOutputStream.writeUTF(string);
				dataOutputStream.flush();

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}