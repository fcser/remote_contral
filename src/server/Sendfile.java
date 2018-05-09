package server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * @version :v1.0.0
 * @description :
 * @author: zym
 * @date: 2017��10��31������6:42:42
 */
public class Sendfile extends Thread{
	ServerSocket ss_file=null;
	Socket s_file=null;
	FileOutputStream fos=null;
	DataInputStream dis =null;
	DataOutputStream doc=null ;
	OutputStream os=null;
	public Sendfile()
	{
		try {
			ss_file=new ServerSocket(29999);
			s_file=ss_file.accept();
			System.out.println("�ļ��������ӳɹ�");
			this.start();
			//doc = new DataOutputStream(new BufferedOutputStream(s_file.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		try {
			//�����Ƿ���Ŀ¼���ļ�
			os=s_file.getOutputStream();
			PrintStream ps=new PrintStream(os);
			//doc = new DataOutputStream(new BufferedOutputStream(os));
		String path="D:\\xiazai";
    	File f=new File(path);
    	if(!f.exists()){
    		System.out.println("�ļ�������");
    		f.mkdir();
    		return;
    	}
    	File fa[]=f.listFiles();
    	for(int i=0;i<fa.length;i++){
    		File fs=fa[i];
    		if(fs.isDirectory()){
    			ps.println(fs.getName()+"��Ŀ¼��");
    		}
    		else{
    			ps.println(fs.getName());
    			System.out.println(fs.getName());
    		}
    	}
    	//����Ľ��ܷ����������ļ�������
    	dis=new DataInputStream(s_file.getInputStream());
    	String file_path=dis.readUTF();
    	sendFile(path+File.separatorChar+file_path);
    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Getfile().getFileName();
	}*/
	public void sendFile(String path) throws IOException{
		File file = new File(path);

		FileInputStream fis=new FileInputStream(file);
	doc = new DataOutputStream(new BufferedOutputStream(os));
     //�����ļ�������
		byte[] buf = new byte[2048];
		int num = fis.read(buf);
		while (num != (-1)) {
			doc.write(buf, 0, num);
			doc.flush();// 
			num = fis.read(buf);		
			}
		fis.close();
	doc.close();
		s_file.close();
	}
    public  void getFileName(){
    	String path="D:/test";
    	File f=new File(path);
    	if(!f.exists()){
    		System.out.println("�ļ�������");
    		f.mkdir();
    		return;
    	}
    	File fa[]=f.listFiles();
    	for(int i=0;i<fa.length;i++){
    		File fs=fa[i];
    		if(fs.isDirectory()){
    			System.out.println(fs.getName()+"��Ŀ¼��");
    		}
    		else{
    			System.out.println(fs.getName());
    		}
    	}
    }

}
