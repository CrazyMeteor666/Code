package costom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writetofiles2 {
	public static void main(String[] args) throws IOException {
		File file =new File("D:\\GitHub\\test.txt");
		FileWriter writer=new FileWriter(file);
		writer.write("123123");
		writer.flush();
		System.out.println("done");
	}
}
