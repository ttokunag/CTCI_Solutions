import java.util.ArrayList;

import SecondTimePackage.*;

public class SecondMain {
	
	public static void main(String[] args) {
		Recursion r = new Recursion();

		System.out.println("\n--------------------------------------------------");

		// ArrayList<String> perm = r.permWithoutDups("abc");
		ArrayList<String> permDups = r.permWithDups("aba");
		for (String str : permDups) {
			System.out.println(str);
		}

		System.out.println("--------------------------------------------------");
	}

}
