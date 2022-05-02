package java_socket_login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminServer {
	public static void main(String[] args) {
		DBConnection connection = new DBConnection();
		
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		try {
			listener = new ServerSocket(9999);
			System.out.println("연결을 기다리고 있습니다.....");
			socket = listener.accept();
			System.out.println("연결되었습니다.");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine();
				String []strinput = inputMessage.split(" ",3); //공백을 기준으로 ID, PW 나눠서 저장
				String []admin = new String[2];
				
				int i=0;
				for (String a : strinput) {
					admin[i] = a;
					i++;
				}
				
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 연결을 종료하였습니다.");
					break;
				}
				
				System.out.print("받아온 ID, Password : ");
				System.out.println(inputMessage);
				
				boolean sucess_admin = connection.isAdmin(admin[0], admin[1]);
				
				out.write(sucess_admin + "\n");
				out.flush();
			}
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if(socket != null) socket.close();
				if(listener != null) listener.close();
			}catch(IOException e) {
				System.out.println("client와 채팅중 오류가 발생했습니다.");
			}
		}
	}	
}
