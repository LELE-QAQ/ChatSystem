import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ChatServer {
	boolean started = false;
	//boolean bConnect = false;
	public static void main(String[] args) throws Exception
	{
		new ChatServer().connect();
	}
	public void connect() throws Exception
	{
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream dataInputStream =null;
		String string = null;
		started =true;
		while(started)
		{
			serverSocket = new ServerSocket(8888);
			socket = serverSocket.accept();
			System.out.println("A client connected");
			dataInputStream = new DataInputStream(socket.getInputStream()); 
			string = dataInputStream.readUTF();
			System.out.println(string);
			dataInputStream.close();
		}

	}
}

