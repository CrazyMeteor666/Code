package costom;
public class Test {
	public static StringBuilder getString(String s1,String s2,int l){
		StringBuilder sb=new StringBuilder();
		sb.append(s1).insert(l, s2); 
		return sb;
}
