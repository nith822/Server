package server;

import java.io.*;
import java.net.*;


public class FTPServer {

	private int portNumber;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private OutputStream os;
	private FileInputStream fis;
    private BufferedInputStream bis;
    public final static String FILE_TO_SEND = "C:\\Users\\Harvey\\Desktop\\serverprogramming\\server\\hello.txt";
    private File myFile;
    
	public FTPServer(int portNumber) throws IOException {
		this.portNumber = portNumber;
		this.myFile = new File(FILE_TO_SEND);
		this.serverSocket = new ServerSocket(portNumber);
		this.clientSocket = serverSocket.accept();
		this.os = clientSocket.getOutputStream();
		this.fis = new FileInputStream(myFile);
		this.bis = new BufferedInputStream(fis);
	}
	
	protected void send() throws IOException {
		byte [] myByteArray  = new byte [(int)myFile.length()];
		bis.read(myByteArray,0,myByteArray.length);
        System.out.println("Sending " + FILE_TO_SEND + " (" + myByteArray.length + " bytes)");
        os.write(myByteArray,0,myByteArray.length);
        os.flush();
        System.out.println("Done.");
	}
	
	public static void main(String[]args) throws IOException {
		
		if(args.length != 1) {
			System.err.println("Usage: java FTPServer <port number>");
			System.exit(1);
		}
		
		FTPServer server = new FTPServer(Integer.parseInt(args[0]));
		server.send();
	}
}
