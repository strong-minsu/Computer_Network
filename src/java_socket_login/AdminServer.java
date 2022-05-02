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
			System.out.println("������ ��ٸ��� �ֽ��ϴ�.....");
			socket = listener.accept();
			System.out.println("����Ǿ����ϴ�.");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine();
				String []strinput = inputMessage.split(" ",3); //������ �������� ID, PW ������ ����
				String []admin = new String[2];
				
				int i=0;
				for (String a : strinput) {
					admin[i] = a;
					i++;
				}
				
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("Ŭ���̾�Ʈ���� ������ �����Ͽ����ϴ�.");
					break;
				}
				
				System.out.print("�޾ƿ� ID, Password : ");
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
				System.out.println("client�� ä���� ������ �߻��߽��ϴ�.");
			}
		}
	}	
}
