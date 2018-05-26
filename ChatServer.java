import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.sql.Date;

public class ChatServer {
	boolean started = false;

	public static void main(String[] args) {
		new ChatServer().connect();
	}

	public void connect() {
		Socket socket = null;
		DataInputStream dataInputStream = null;
		String string = null;
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
				bConnect = true;
				dataInputStream = new DataInputStream(socket.getInputStream());
				while (bConnect) {
					string = dataInputStream.readUTF();
					System.out.println(string);
				}
				// dataInputStream.close();

			}

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
