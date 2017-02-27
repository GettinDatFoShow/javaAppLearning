/*
 * This is a Server Socket listening on port 1776 ( I call it the Freedom Port) 
 * Once a connection is heard from a client, a socket opened and accepted.
 * from the socket instance that is created, there are a few properties available.
 * The .getInetAdress() from the socket returns an InetAddress instance of the client on the
 * other side of the socket. we can then call the .getHostAddress on the InetAddress object
 * and obtain a string of the clients IPaddress.
 * Then, an InputStreamReader object is created using the socket as its parameter.
 * After this, a BufferedReader object is created using the InputStreamReader object as the parameter.
 * The socket is now ready listen for messages sent through the socket from the client.
 * there is a while loop once the readline() function is called appon the bufferedReader object
 * becuase the readline() will stop at the \n character. If it doesnt keep looping it will not be
 * able to read out multiple lines from the client.
 */

import java.io.*;
import java.net.*;
import java.util.Date;
/**
 *
 * @author Robert Morris
 */
public class ServerSock {
    
    public static void main(String[] args) throws Exception{
        
        ServerSock Server = new ServerSock();
        Server.run();
        
    }
    
    
    public void run() throws Exception{
        
        String messageIn = null;
        
        ServerSocket serverSocket = new ServerSocket(1776); 
        Socket socket = serverSocket.accept();
        // Information Regarding the Connection.
        InetAddress INA = socket.getInetAddress(); 
        String hostIP = INA.getHostAddress();
        Date date = new Date();
        InputStreamReader inRead = new InputStreamReader(socket.getInputStream());
        BufferedReader bRead = new BufferedReader(inRead);
        
        while (( messageIn = bRead.readLine()) != null){
            if (messageIn.length() <= 2){
                break;
            }
            System.out.println(messageIn);
            String messageOut = "Your IP address is: "+ hostIP + "\n"
                    + "It is now: " + date;
            PrintStream pStream = new PrintStream(socket.getOutputStream());
            pStream.println(messageOut);
            pStream.println("");
            break;
           
        }
        socket.close();
    }
    
}
