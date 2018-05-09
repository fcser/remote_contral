package server;

import java.io.File;
/**
 * 
 * @version :v1.0.0
 * @description :
 * @author: zym
 * @date: 2017��10��31������2:51:45
 */
public class Getfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Getfile().getFileName();
	}
    public  void getFileName(){
    	String path="D:/test";
    	File f=new File(path);
    	if(!f.exists()){
    		System.out.println("�ļ�������");
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
