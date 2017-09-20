package client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FTPClient {

	private Socket serverSocket;
	private FileOutputStream fos;
    private BufferedOutputStream bos;
    private InputStream is;
    
    public final static String FILE_TO_RECEIVED = "C:\\Users\\Harvey\\Desktop\\serverprogramming\\client\\hello.txt";
    public final static int FILE_SIZE = 2000;
    //2000 bytes
	public FTPClient(String hostName, int portNumber) throws UnknownHostException, IOException {
		this.serverSocket = new Socket(hostName, portNumber);
		this.fos = new FileOutputStream(FILE_TO_RECEIVED);
		this.bos = new BufferedOutputStream(fos);
		this.is = serverSocket.getInputStream();
	}

	public void recieveFile() throws IOException {
		byte [] myByteArray  = new byte [FILE_SIZE];
        is.read(myByteArray, 0, (myByteArray.length));
		bos.write(myByteArray, 0 , myByteArray.length);
		bos.flush();
		System.out.println("File downloaded!");
	}
	
	public static void main(String[]args) throws IOException {
		if (args.length != 2) {
            System.err.println("Usage: java FTPClient <host name> <port number>");
            System.exit(1);
        }
		
		String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
		
		FTPClient client = new FTPClient(hostName, portNumber);
		client.recieveFile();
	}
}
