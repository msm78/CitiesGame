package by.htp.citiesgame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static final int SERVER_PORT = 8081;
	// private static final String SERVER_HOST = "192.168.100.24";
	private static final String SERVER_HOST = "localhost";

	public static void main(String[] args) throws UnknownHostException {
		 //GrabCities gc = new GrabCities();
		 //System.out.println(gc.getCity(3));

		System.out.println("Start client");
		Cities ct = new Cities();
		//System.out.println(ct.getCity(7));

		Socket clientSocket = null;
		try {
			clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			DataInputStream in = new DataInputStream(is);
			DataOutputStream out = new DataOutputStream(os);
			
			String inStr = null;
			String outStr = null;
			String winner = "";
			//Cities ct = new Cities();
			boolean game = true;
			while(game){
				inStr = in.readUTF(); //
				if("Game over".equals(inStr)) {
					winner = "Client";
					game = false;
					break;
				} else {
					System.out.println("Сервер: " + inStr);
				}
				
				outStr = ct.getCity(inStr);
				if("Not answer".equals(outStr)) {
					out.writeUTF("Game over");
					out.flush();
					winner = "Server";
					game = false;
					break;
				} else {
					out.writeUTF(outStr);
					out.flush();
					System.out.println("Клиент: " + outStr);
				}
				Thread.sleep(10);
			}
			System.out.println("\nGame over!");
			System.out.println(winner + " win!");
			Thread.sleep(1000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
