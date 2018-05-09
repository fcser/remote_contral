package server;
/*
 * Զ�̿����߳�
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Contral extends Thread {
    private Socket s_forimg=null;
    private ServerSocket ss_forimg=null;
	public Contral(){
	   try {
		ss_forimg=new ServerSocket(19999);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		try {
			s_forimg=ss_forimg.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("��Ļ���Ӵ�");
	}
	public void run(){
//InputStream in;
		try {
			 
		
        ObjectInputStream ois = new ObjectInputStream(s_forimg.getInputStream());
         
        OutputStream oss = s_forimg.getOutputStream();
        DataOutputStream dos = new DataOutputStream(oss);
       // U.debug("socket open stream ok!");
         
        ControlThread cont = new ControlThread(ois);
        cont.start();//���������߳�
        CaptureThread capt = new CaptureThread(dos);
        capt.start();//������Ļ�����߳�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException{
		s_forimg.close();
	}
}
