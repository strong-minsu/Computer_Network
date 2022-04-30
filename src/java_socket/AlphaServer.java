package java_socket;
import java.io.*;
import java.net.*;
import java.util.*;


public class AlphaServer {
	//알바벳 변경 함수
	public static String exchage_alpa(String s)  {
		String res="";
		int c;
		for(int i=0;i<s.length();i++) {
			c = (int)s.charAt(i);
			if((65<=c) && (c <=90)) {
				res +=(char)(c+32);
			}
			else if((97<=c)&&(c<=122)) {
				res +=(char)(c-32);
			}
			else
				res +=(char)c;
		}
		return res;
	}

	public static void main(String[] args) {
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
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 연결을 종료하였습니다.");
					break;
				}
				System.out.print("받은 메시지: ");
				System.out.println(inputMessage);
				String alpha = exchage_alpa(inputMessage);
				out.write(alpha + "\n");
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
