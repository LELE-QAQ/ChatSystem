import  java.awt.*;
import  java.awt.event.*;

public class ChatClient{
	public static void main(String[] args)
	{
		new ClientFrame().launch();
	}
}

class ClientFrame extends Frame{
	
	Frame frame = new Frame("ChatClient");
	TextField textField = new TextField(30);
	TextArea tArea = new TextArea("",80,100);
	ScrollPane scrollPane = new ScrollPane();
    
	public void launch()
	{
		setLocation(200,200);
		setVisible(true);
		setResizable(false);
		scrollPane.add(tArea);
		add(textField,"South");
		add(scrollPane,"North");
		pack();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}