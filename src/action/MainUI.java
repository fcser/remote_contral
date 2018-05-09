package action;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class MainUI extends JFrame{
  //private Socket socket=null;
	public MainUI(){
		//this.socket=socket;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
		ChooseUI pane2=new ChooseUI();
        getContentPane().add(pane2, java.awt.BorderLayout.PAGE_START);
        this.setVisible(true);
	}
}
