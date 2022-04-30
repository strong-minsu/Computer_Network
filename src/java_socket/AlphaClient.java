package java_socket;
import java.io.*;
import java.net.*;
import java.util.*;

public class AlphaClient {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);
		try {
			socket = new Socket("localhost", 9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true) {
				System.out.print("��ȯ��ų ���� �Է� >> ");
				String outputMessage = scanner.nextLine();
				if(outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage+"\n");
					out.flush();
					break;
				}
				out.write(outputMessage+"\n");
				out.flush();
				String inputMessage = in.readLine();
				System.out.println("��ȯ ���>> "+ inputMessage);
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
