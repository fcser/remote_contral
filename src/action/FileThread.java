package action;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileThread extends JFrame implements Runnable{
   private String id;
   private Socket s_file;
   private String path;
   FileInputStream fis =null;
   DataOutputStream doc =null;
	public FileThread(String id){
		this.id=id;
		try {
			s_file=new Socket(id,29999);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String st = getFile();

		if (!st.equals("")) {

			try{

				File file = new File(st);

				 fis=new FileInputStream(file);

				OutputStream netOut = s_file.getOutputStream();
				doc = new DataOutputStream(new BufferedOutputStream(netOut));
               doc.writeUTF(file.getName());
              //创建文件缓冲区
				byte[] buf = new byte[2048];
				int num = fis.read(buf);
				while (num != (-1)) {
					doc.write(buf, 0, num);
					doc.flush();// 
					num = fis.read(buf);		

					}

				

				fis.close();

				netOut.close();

				doc.close();
				s_file.close();
				JOptionPane.showMessageDialog(null, "文件传输成功");
			}catch(Exception e){
				
			}
		}
	

	}
	//获取文件路径
	public String getFile() {

       
		JFileChooser jc = new JFileChooser();

		if (jc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			path = jc.getSelectedFile().getAbsolutePath();

		}

		return path;

	}

}
