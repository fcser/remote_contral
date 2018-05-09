package action;

import java.net.Socket;
import java.util.ArrayList;

public class Mngsocket {
  public static ArrayList<Socket> sockets=new ArrayList<Socket>();
 // public static ArrayList<ClientThread> clients=new ArrayList<ClientThread>();
  public static void add_socket(Socket s)
  {
	  sockets.add(s);
  }
  public static void del_socket(Socket s){
	  sockets.remove(s);
  }
 
}
