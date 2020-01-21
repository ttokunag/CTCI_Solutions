import java.util.ArrayList;

import SecondTimePackage.*;

public class SecondMain {
	
	public static void main(String[] args) {
		Recursion r = new Recursion();

		System.out.println("\n--------------------------------------------------");

		ArrayList<String> perm = r.permWithoutDupsRec("abc");
		for (String str : perm) {
			System.out.println(str);
		}

		System.out.println("--------------------------------------------------");
	}

}
