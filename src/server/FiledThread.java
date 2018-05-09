package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FiledThread extends Thread {
	ServerSocket ss_file=null;
	Socket s_file=null;
	FileOutputStream fos=null;
	DataInputStream dis =null;
	public FiledThread()
	{
		try {
			ss_file=new ServerSocket(29999);
			s_file=ss_file.accept();
			System.out.println("�ļ��������ӳɹ�");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			

			dis = new DataInputStream(new BufferedInputStream(s_file.getInputStream()));
		String fileName=dis.readUTF();
		File dir=new File("D:\\test");
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
			//JOptionPane.showMessageDialog(null, "����ܵ���һ���ļ�");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if(fos!=null){
					fos.close();
				}
				if(dis!=null){
					dis.close();
				}
				s_file.close();
			}catch(Exception e){}
		}
	}
}
