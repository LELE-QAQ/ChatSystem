import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.sql.Date;

import com.sun.tools.classfile.Annotation.element_value;

public class ChatServer {

	public static void main(String[] args) {
		new Server().connect();
	}
}

class Server {
	boolean started = false;

	public void connect() {
		Socket socket = null;
		DataInputStream dataInputStream = null;
		ServerSocket serverSocket = null;
		try {

			serverSocket = new ServerSocket(8888);

		} catch (BindException e) {
			System.out.println("port is using");
			System.out.println("please close the client");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try

		{
			started = true;
			while (started) {
				boolean bConnect = false;
				socket = serverSocket.accept();
				System.out.println("A client connected");
				Client client = new Client(socket);
				new Thread(client).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class Client implements Runnable {
		private Socket socket;
		private DataInputStream dataInputStream = null;
		private boolean bConnect = false;

		public Client(Socket socket) {
			this.socket = socket;
			try {
				bConnect = true;
				dataInputStream = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (bConnect) {
					String string = dataInputStream.readUTF();
					System.out.println(string);
				}
				// dataInputStream.close();y

			} catch (EOFException e) {
				System.out.println("client close");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (dataInputStream != null) {
						dataInputStream.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
