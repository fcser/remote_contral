package action;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class Getfile extends JFrame implements Runnable, ActionListener {

	private String id;
	private Socket s_file;
	private JPanel jpl=new JPanel();
	//private JTextArea jta=new JTextArea(16,40);
	//private JComboBox<String> cbhome=new JComboBox<String>();
	private JTextField jtf=new JTextField(20);
	private JButton jbt=new JButton("下载");
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private InputStream in =null;
	
	public Getfile(String id)
	{
		this.id=id;
		try {
			s_file=new Socket(id,29999);
			System.out.println("开启文件下载段");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jpl.setLayout(new FlowLayout());
		//jbt.addActionListener(this);
		jpl.add(jtf);
		jpl.add(jbt);
		this.add(jpl);
		this.setTitle("文件下载");
		this.setSize(300,200);
		this.setVisible(true);
		//new Thread(this).start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			//String str=(String) cbhome.getSelectedItem();
			String str=jtf.getText();
			dos=new DataOutputStream(s_file.getOutputStream());
			dos.writeUTF(str);
			dos.flush();
			String dirurl=getFile();
			in = s_file.getInputStream();
			dis=new DataInputStream(in);
			String fileName=dis.readUTF();
			File dir=new File(dirurl);
				if(!dir.exists()){
					dir.mkdir();
				}
				File file=new File(dir.getAbsolutePath()+File.separatorChar+fileName);
				fos=new FileOutputStream(file);
				byte[] buf = new byte[2048];
				int num = 0;
				while ((num=dis.read(buf)) != (-1)) {
					fos.write(buf, 0, num);
					fos.flush();
				}
				JOptionPane.showMessageDialog(null, "文件发送成功");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally{
			
			try {
				s_file.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		jbt.addActionListener(this);
		/*try {
			in = s_file.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
		  dis=new DataInputStream(in);
		String strs;
		System.out.println("可下载目录");
		while((strs=dis.readUTF())!=null){
			//jta.append(strs+"\n");
			System.out.println(strs);
			cbhome.addItem(strs);
		} 
		//br.close();
		System.out.println("文件可下载目录");
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("utf有错误");
			e1.printStackTrace();
		} */
		//dis=new DataInputStream(in);
	}
	public String getFile() {

	       String path=null;
		JFileChooser jc = new JFileChooser();

		if (jc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			path = jc.getSelectedFile().getAbsolutePath();

		}

		return path;

	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

new Getfile("a");

	}*/

}
