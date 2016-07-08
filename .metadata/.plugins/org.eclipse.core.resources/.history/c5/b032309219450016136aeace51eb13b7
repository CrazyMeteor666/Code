package costom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class writetofiles {
	public static void main(String[] args) throws IOException {
		//创建文件夹
		File f=new File("d:\\GitHub\\test");
		if (!f.exists()) {
			f.mkdirs();
		}
		//创建文件
		File file=new File("d:\\GitHub\\test.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter writer=new FileWriter(file);
		writer.write("123123");
		writer.flush();   //这行才是真正的输出到文件，别忘了
		System.out.println("done");
	}
}
