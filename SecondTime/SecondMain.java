import java.util.ArrayList;

import SecondTimePackage.*;

public class SecondMain {
	
	public static void main(String[] args) {
		Recursion r = new Recursion();
		Moderate m = new Moderate();

		System.out.println("\n--------------------------------------------------");

		// ArrayList<String> perm = r.permWithoutDups("abc");
		// ArrayList<String> permDups = r.permWithDups("aba");
		// for (String str : permDups) {
		// 	System.out.println(str);
		// }

		// r.paren(3);

		int[] arr = {4, 2, -9};
		m.numSwapper(arr, 0, 2);
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();

		System.out.println("--------------------------------------------------");
	}

}
