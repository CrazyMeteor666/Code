package costom;

public class sleep {
	public static void main(long args) {
		try {
			Thread.sleep(args);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}

}
