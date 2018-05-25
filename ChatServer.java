import java.io.IOException;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) throws Exception
	{
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket =null;
		
		while(true)
		{
			socket = serverSocket.accept();
			System.out.println("A Client Connect");
		}
	}
}
