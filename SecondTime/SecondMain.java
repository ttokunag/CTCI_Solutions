import java.util.*;

import SecondTimePackage.*;
import SecondTimePackage.Moderate.Person;

public class SecondMain {
	
	public static void main(String[] args) {
		Recursion r = new Recursion();
		Moderate m = new Moderate();
		Solution s = new Solution();

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

		// Random rand = new Random();
		// int size = 1000;
		// Person[] people = new Person[size];
		// for (int i = 0; i < size; i++) {
		// 	int year1 = rand.nextInt(4000);
		// 	int year2 = rand.nextInt(4000);
		// 	int birthYear = (year1 <= year2) ? year1 : year2;
		// 	int deathYear = (year1 >= year2) ? year1 : year2;
		// 	people[i] = m.new Person(birthYear, deathYear);
		// }

		// System.out.println(m.livingPeople(people));
		// System.out.println(s.maxAliveYear(people, 0, 3000));


		// double[][] s1 = {{3,-3},{1,-1},{1,-3},{3,-1}};
		// double[][] s2 = {{4,0},{4,1},{5,1},{5,0}};
		// System.out.println(m.bisectSquares(s1, s2));


		int[] arr = {1,2,4,7,10,11,7,12,6,7,16,18,19};
		int[] arr1 = {1,2,4,7,10,11,7,12,6,7,16,19,18};
		int[] result = m.subSortRange(arr1);
		System.out.println(result[0] + ", " + result[1]);


		System.out.println("--------------------------------------------------");
	}

}
