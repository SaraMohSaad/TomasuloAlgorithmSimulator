import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	ArrayList<String> Instructions;

	public ReadFile(String fileName) {
		Instructions = new ArrayList<String>();
		Instructions = readFile(Instructions, fileName);
	}

	public ArrayList<String> readFile(ArrayList<String> instructions, String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()) {
				instructions.add(sc.nextLine());
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instructions;
	}
//	public static void main(String[]args) {
//		   ReadFile r = new ReadFile("Instructions.txt");
//		   System.out.print(r.Instructions);
//		}
}
