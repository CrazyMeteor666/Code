package costom;

import java.io.File;
import java.io.IOException;

public class writetofiles {
	public static void main(String[] args) {
		File f=new File("d:\\GitHub\\test");
		if (!f.exists()) {
			f.mkdirs();
		}
		String fileName="d:\\GitHub\\test.txt";
		File file=new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("done");
	}
}