package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChooseUI extends JPanel implements ActionListener{
	/*private DataInputStream dis=null;
	private ObjectOutputStream oos=null;
	private Socket img;*/
	//private JButton bt1=new JButton("连接");
	private JButton bt2=new JButton("文件下载");
	private JButton bt3=new JButton("文件传输");
	private JButton bt4=new JButton("关机");
	private JButton bt5=new JButton("重启");
	private JButton bt6=new JButton("DOS命令");
     public ChooseUI(){
    	 //JPanel pane2=new JPanel();
    	 
    	 
        // this.add(bt1);
         this.add(bt2);
         this.add(bt3);
         this.add(bt6);
         this.add(bt4);
         this.add(bt5);
        // bt1.setEnabled(false);
        // bt1.addActionListener(this);
         bt2.addActionListener(this);
         bt3.addActionListener(this);
         bt4.addActionListener(this);
         bt5.addActionListener(this);
         bt6.addActionListener(this);
     }
     
     public void actionPerformed(ActionEvent e)
     {
    	 if(e.getSource()==bt2)
    	 {
    		try {
    			BufferedReader br=Client.demand("请求文件下载");
    			String str=br.readLine();
    			System.out.println(str);
				if(str.equals("同意连接")){
				Getfile a=new Getfile(Client.id);
					new Thread(a).start();
				}else{
					JOptionPane.showMessageDialog(null, "对方拒绝了你的下载文件请求");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("文件传输出错");
				e1.printStackTrace();
			}
    		
    	 }
    	 else if(e.getSource()==bt3)
    	 {
    		try {
    			BufferedReader br=Client.demand("请求文件传输");
    			String str=br.readLine();
    			System.out.println(str);
				if(str.equals("同意连接")){
					FileThread a=new FileThread(Client.id);
					new Thread(a).start();
				}else{
					JOptionPane.showMessageDialog(null, "对方拒绝了你的文件传输请求");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("文件传输出错");
				e1.printStackTrace();
			}
    		
    	 }
    	 
    	 else if(e.getSource()==bt4){
    		 try {
				Client.demand("关机"); 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	 }
    	 else if(e.getSource()==bt5){
    		 try {
    			 
    			// bt4.setText("取消");
    			 //bt4.setEnabled(true);
				Client.demand("重启");
    			 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	 }
    	 else if(e.getSource()==bt6){
    		 try {
				Client.demand("命令行");
				DOSThread a=new DOSThread(Client.id);
				new Thread(a).start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		 
    	 }
     }
}
