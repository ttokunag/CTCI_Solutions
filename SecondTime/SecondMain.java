import java.util.*;

import SecondTimePackage.*;
import SecondTimePackage.Moderate.People;

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


		// System.out.println(r.coins(20050));


		// int size = 4;
		// r.printQueens(size, 0, new int[size]);


		// System.out.println(r.countEvalExpressions("1^0|0|1", false));
		// System.out.println(r.countEvalExpressions("0&0&0&1^1|0", true));


		// System.out.println(m.factorialZerosOne(23));


		// System.out.println(m.smallestDifference(new int[]{1,3,14,11,2}, new int[]{23,127,235,19,8}));


		// char[][] board = {
		// 	{'o','x','o','o'},
		// 	{'x','x','x','o'},
		// 	{'o','o','x','o'},
		// 	{'o','o','x','o'}
		// };

		// System.out.println(m.ticTacWin(board));


		// System.out.println(m.englishInt(1234567891));

		People[] people = {
			m.new People(1920, 1930), m.new People(1900, 1920), 
			m.new People(1921, 1935), m.new People(1910, 1925), 
			m.new People(1905, 1915)
		};

		System.out.println(m.livingPeople(people));

		System.out.println("--------------------------------------------------");
	}

}
