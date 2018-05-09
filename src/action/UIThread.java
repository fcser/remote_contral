package action;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JScrollPane;

import java.io.DataInputStream;

public class UIThread extends Thread {
	 //private ClientUI clientUI ;

private static Socket img;
private DataInputStream dis;
private ObjectOutputStream oos;
private String id;
public static ClientUI ui=null;
public UIThread(String id)
{
	this.id=id;
	try {
		img=new Socket(id,19999);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void Close_con() throws IOException{
	img.close();
}
public void run(){
	try{
		while(true){
			if(img==null){
				img=new Socket(id,19999);
			}
			oos=new ObjectOutputStream(img.getOutputStream());
			dis=new DataInputStream(img.getInputStream());
			try {
	            this.showClientUI();
	        } catch (Exception ex) {
	        }
		}
		
	}catch(Exception ex){
		
	}
	
}
public static void close_ui(){
	ui.dispose();
}
/**
 * 显示图形界面
 */
public void showClientUI() throws Exception, ClassNotFoundException {
	//JScrollPane scp;
     ui = new ClientUI(dis, oos,img);
    ui.updateSize(readServerSize());
    while(true) {
        byte[] imageData = readBytes();
        ui.update(imageData);
    }

}
/**
 * 读被控制段发送来的数据
 */
public byte[] readBytes() throws IOException, ClassNotFoundException {
	
    int len = dis.readInt();
    byte[] data = new byte[len];
    dis.readFully(data);
    return data;
}
/**
 * 读被控制端分辨率
 */
public Dimension readServerSize() {
    double height = 100;
    double width = 100;
    try {
        height = dis.readDouble();
        width = dis.readDouble();
    } catch (IOException e) {
    
    }
    return new Dimension((int)width, (int)height);
}

}


