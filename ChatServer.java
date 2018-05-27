import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {
	public static void main(String[] args) {
		new Server().connect();
	}
}

class Server {
	boolean started = false;
	boolean bConnect = false;
	ArrayList<Client> clients = new ArrayList<Client>();

	public void connect() {
		Socket socket = null;
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
				socket = serverSocket.accept();
				System.out.println("A client connected");
				Client client = new Client(socket);
				new Thread(client).start();
				clients.add(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	class Client implements Runnable {
		private Socket socket;
		private DataInputStream dataInputStream = null;
		private boolean bConnect = false;
		private DataOutputStream dataOutputStream = null;

		public Client(Socket socket) {
			this.socket = socket;
			try {
				bConnect = true;
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void send(String string) {
			try {
				dataOutputStream.writeUTF(string);
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

					for (int i = 0; i < clients.size(); i++) {
						Client client = clients.get(i);
						client.send(string);
					}
					/*
					 * for(Iterator<Client> iterator = clients.iterator();iterator.hasNext();) {
					 * Client client = iterator.next(); client.send(string); }
					 * 
					 * Iterator< Client> iterator = clients.iterator(); while(iterator.hasNext()) {
					 * Client client = iterator.next(); client.send(string); }
					 */
				}
				// dataInputStream.close();

			} catch (EOFException e) {
				System.out.println("client close");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (dataInputStream != null) {
						dataInputStream.close();
					}
					if (dataOutputStream != null) {
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
