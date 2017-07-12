package by.htp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import by.htp.server.Cities;

public class Main {

	private static final int SERVER_PORT = 8081;
	//private static final String SERVER_HOST = "localhost";
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server is ready!");
			clientSocket = serverSocket.accept();
			System.out.println("Client conect.");
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			DataInputStream in = new DataInputStream(is);
			DataOutputStream out = new DataOutputStream(os);
			
			Cities ct = new Cities();
			String inStr = ct.getRandomCity();
			String outStr = null;
			boolean game = true;
			String winner = "";
			while(game){
				outStr = ct.getCity(inStr);
				if("Not answer".equals(outStr)) {
					out.writeUTF("Game over");
					out.flush();
					winner = "Client";
					game = false;
					break;
				} else {
					out.writeUTF(outStr);
					out.flush();
					System.out.println("Сервер: " + outStr);
				}
				
				inStr = in.readUTF();
				if("Game over".equals(inStr)) {
					winner = "Server";
					game = false;
					break;
				} else {
					System.out.println("Клиент: " + inStr);
				}
			}
			System.out.println("\nGame over!");
			System.out.println(winner + " win!");
			Thread.sleep(1000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
