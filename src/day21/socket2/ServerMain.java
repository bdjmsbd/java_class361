package day21.socket2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerMain {

	public static void main(String[] args) {

		String fileName = "src/day21/socket2/server.txt";
		int port = 8080;

		Scanner sc = new Scanner(System.in);

		try {
			ServerSocket sS = new ServerSocket(port);
			while (true) {
				Socket s = sS.accept();

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

				String type = ois.readUTF();
				if (type.equals("save")) {
					receive(ois, fileName);
//					list = (List<Contact>) ois.readObject();
//					save(list, fileName);
					System.out.println("저장 완료");
				} else if (type.equals("load")) {
					send(oos, fileName);
					System.out.println("불러오기 완료");
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private static void send(ObjectOutputStream oos, String fileName) {
		List<Contact> list = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream fois = new ObjectInputStream(fis);
			list = (List<Contact>) fois.readObject();
			System.out.println(list);
			oos.writeObject(list);
			oos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				oos.writeObject(new ArrayList<Contact>());
				oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private static void receive(ObjectInputStream ois, String fileName) {
		List<Contact> list = null;

		try {
			list = (List<Contact>) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream foos = new ObjectOutputStream(fos);

			foos.writeObject(list);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
