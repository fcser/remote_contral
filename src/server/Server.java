package server;
/**
 * ���������������ڽ��ܿͻ�������
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Server extends JFrame implements Runnable,ActionListener {

	private Socket s=null;
	
	private ServerSocket ss=null;
	
	private String str=null;
	private JButton bt1=new JButton("�鿴�����ص��ļ�");
	private JButton bt2=new JButton("�鿴�û��ϴ��ļ�");
	private JPanel jpl=new JPanel();
	//private ArrayList<ClientThread> clients=new ArrayList<ClientThread>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
          Server s=new Server();
	}
	public Server() throws Exception{
		this.setTitle("��������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpl.add(bt1);
		jpl.add(bt2);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(jpl);
		this.setSize(300,150);
		this.setVisible(true);
		ss=new ServerSocket(9999);
		new Thread(this).start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==bt2){
			File dir=new File("D:\\test");
			if(!dir.exists()){
				dir.mkdir();
			}
			try {
				java.awt.Desktop.getDesktop().open(dir);//��dirĿ¼
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			File dir=new File("D:\\xiazai");
			if(!dir.exists()){
				dir.mkdir();
			}
			try {
				java.awt.Desktop.getDesktop().open(dir);//��dirĿ¼
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
        	   while(true){
        		   s=ss.accept();
        			System.out.println("������������");
        			CommendThread ct=new CommendThread(s);
        			ct.start();
        	   }  
				} catch (IOException e1) {
					try {
						s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				}
	}
	//����ִ�з���
	class CommendThread extends Thread{
		private Socket s=null;
		public CommendThread(Socket s){
			this.s=s;
		}
		public void run(){
			try{
	        	   while(true){
	        		   DataInputStream dis=new DataInputStream(s.getInputStream());
						 action(dis);
	        		  
	        	   }  
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						try {
							s.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				}
		}
		public void action(DataInputStream dis) throws IOException
		{
			str=dis.readUTF();
			System.out.println(str+"hahahh");
			if(str.equals("����Զ�̿��ƿ���")){
				int result=JOptionPane.showConfirmDialog(null,s.getInetAddress().getHostAddress()+str,"ȷ�Ͽ�",JOptionPane.YES_NO_OPTION);
	 			 if(result==JOptionPane.YES_OPTION)
	 			 {
	 				 OutputStream os=s.getOutputStream();
	 					PrintStream ps=new PrintStream(os);
	 					ps.println("ͬ������");
	 					//ps.close();
	 					Contral contral=new Contral();
	 					contral.start();
	 			 }
	 			 else{
	 				 OutputStream os=s.getOutputStream();
						PrintStream ps=new PrintStream(os);
						ps.println("�ܾ�����");
						//ps.close();
						s.close();
	 			 }
	 	   
			}
			else if(str.equals("�����ļ�����")){
				int result=JOptionPane.showConfirmDialog(null,s.getInetAddress().getHostAddress()+str,"ȷ�Ͽ�",JOptionPane.YES_NO_OPTION);
				 if(result==JOptionPane.YES_OPTION)
				 {
					 OutputStream os=s.getOutputStream();
						PrintStream ps=new PrintStream(os);
						ps.println("ͬ������");
						//ps.close();
						FiledThread f=new FiledThread();
						f.start();
				 }
				 else{
					 OutputStream os=s.getOutputStream();
						PrintStream ps=new PrintStream(os);
						ps.println("�ܾ�����");
						//ps.close();
						
				 }
			}
			else if(str.equals("�����ļ�����")){
				int result=JOptionPane.showConfirmDialog(null,s.getInetAddress().getHostAddress()+str,"ȷ�Ͽ�",JOptionPane.YES_NO_OPTION);
				 if(result==JOptionPane.YES_OPTION)
				 {
					 OutputStream os=s.getOutputStream();
						PrintStream ps=new PrintStream(os);
						ps.println("ͬ������");
						//ps.close();
						Sendfile f=new Sendfile();
						//f.start();
				 }
				 else{
					 OutputStream os=s.getOutputStream();
						PrintStream ps=new PrintStream(os);
						ps.println("�ܾ�����");
						//ps.close();
						
				 }
			}
			 else if(str.equals("�ػ�")){
				 try{ 
					 Runtime.getRuntime().exec("shutdown -s -t 60"); 
					 JOptionPane.showMessageDialog(null, "��������60���ػ�");
					 }catch(Exception ex){}
			 }
			 
			 else if(str.equals("����")){
				 try{ 
					 Runtime.getRuntime().exec("shutdown -r"); 
					 JOptionPane.showMessageDialog(null, "��������");
					 }catch(Exception ex){}
			 }
			 else if(str.equals("������")){
				 DOSThread f=new DOSThread();
					
			 }
		}
	}
	
        		

}

