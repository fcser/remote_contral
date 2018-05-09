package action;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DOSThread extends JFrame implements Runnable,ActionListener{

	private JTextField text=new JTextField(20);     //输入框
	private JButton start=new JButton("开始");
	private JLabel label=new JLabel("DOS命令：");
	private JTextArea content=new JTextArea(16,60); //显示纯文本的多行区域
    private JProgressBar jProgressBar1;
	private String path;
	private PrintWriter out = null;
	private JPanel jpl=new JPanel();
	private Socket s_file=null;
	public DOSThread(String id){
		try {
			s_file=new Socket(id,29999);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scroll = new JScrollPane(content);	
		   jpl.setLayout(new FlowLayout());
			jpl.add(label);
			jpl.add(text);
			jpl.add(start);
			jpl.add(scroll);
			//jpl.add(start);
			this.add(jpl);
			this.setSize(500,400);
			 this.setVisible(true);
			 new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		start.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str=text.getText();
		if(is_chinese(str)){
			JOptionPane.showMessageDialog(null, "输入格式错误请重新输入");
			return;
		}
			try {
				DataOutputStream dos=new DataOutputStream(s_file.getOutputStream());
				dos.writeUTF(str);
				dos.flush();
				InputStream in=s_file.getInputStream(); 
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				String strs;
				while((strs=br.readLine())!=null){
					content.append(strs+"\n");
				}	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("出错");
			}
		}
		

	public boolean is_chinese(String dos){
		boolean a=false;
		if(dos.getBytes().length==dos.length()){
			 a=false;
		 }
		 else{
			 a=true;
		 }
		return a;
	}
	
}
