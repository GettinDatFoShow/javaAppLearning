/*
 *This is a client socket class that connects to the server socket
 * on a pre-specified of 1776 ( I call it the Freedom Port), supplying a name of 'localhost'
 * once the socket is accepted by the server a PrintStream object is created with
 * the socket.getOutputStream() as a parameter, following this creation, a message is sent
 * to the server. 
 * Then, an InputStreamReader object is created using the socket as its parameter.
 * After this, a BufferedReader object is created using the InputStreamReader object as the parameter.
 * The socket is now ready listen for messages sent through the socket from the client.
 * there is a while loop once the readline() function is called appon the bufferedReader object
 * becuase the readline() will stop at the \n character. If it doesnt keep looping it will not be
 * able to read out multiple lines from the server. 
 * a while loop is created for the bufferedReader to be able to read multiple lines so that
 * it does not terminate until a specified message is received. In this case the specified message is 
 * a 'new line' character. 
 */

import java.io.*;
import java.net.*;

/**
 *
 * @author Robert Morris
 */
public class ClientSock {
    
   public static void main(String [] args) throws Exception{
       
       ClientSock client = new ClientSock();
       client.run();
       
   } 
    
   public void run() throws Exception{
       String messageIn = null;
       Socket socket = new Socket("localhost", 1776);
       PrintStream pStream = new PrintStream(socket.getOutputStream());
       pStream.println("I NEED TO CONNECT NOWWWWW!!!!");
       
       InputStreamReader inRead = new InputStreamReader(socket.getInputStream());
       BufferedReader bRead = new BufferedReader(inRead);
       
       
       while((messageIn = bRead.readLine()) != null){
            
           if (messageIn.length() <= 2){
                break;
            }
              System.out.println(messageIn);
       }
       socket.close();
              
   }
    
}
