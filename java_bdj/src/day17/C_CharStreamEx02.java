package day17;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class C_CharStreamEx02 {

	public static void main(String[] args) {
		// 한글이나 한자도 출력이 가능한 문자열 기반 스트림
		// 자바의 char는 2바이트이기 때문
		String fileName = "src/day17/charStream.txt";
		
		try (FileWriter fw = new FileWriter(fileName)) {
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (FileReader fr = new FileReader(fileName)) {
			while (fr.ready()) {
				char ch = (char)fr.read();
				System.out.print(ch);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
