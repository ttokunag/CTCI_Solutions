import java.util.*;

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

		// int[] arr = {4, 2, -9};
		// m.numSwapper(arr, 0, 2);
		// for (int num : arr) {
		// 	System.out.print(num + " ");
		// }
		// System.out.println();

		// String str = "This is one of the most interesting books in this world. This's a book you must read";
		// System.out.println(m.occurrenceOfWords(str, "This"));
		// System.out.println(m.occurrenceOfWords(str, "That"));

		System.out.println(r.coins(20050));

		System.out.println("--------------------------------------------------");
	}

}
