import java.util.*;

import SecondTimePackage.*;
import SecondTimePackage.Hard.CircusPerson;
import SecondTimePackage.Hard.Name;
import SecondTimePackage.Moderate.Person;

public class SecondMain {
	
	public static void main(String[] args) {
		Recursion r = new Recursion();
		Moderate m = new Moderate();
		Solution s = new Solution();
		Hard h = new Hard();

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


		// int[] arr = {1,2,4,7,10,11,7,12,6,7,16,18,19};
		// int[] arr1 = {1,2,4,7,10,11,7,12,6,7,16,19,18};
		// int[] result = m.subSortRange(arr);
		// System.out.println(result[0] + ", " + result[1]);


		// int[] arr = {2,-8,3,-2,4,-10};
		// int[] arrPos = {1,2,3,4,5,6};
		// int[] arrNeg = {-5,-2,-3,-4,-9};
		// int[] arrZero = {0,0,0};
		// int[] res = m.largestInterval(arrZero);
		// System.out.println(res[0] + ", " + res[1]);


		// int[][] map = {
		// 	{0,2,1,0},
		// 	{0,1,0,1},
		// 	{1,0,0,1},
		// 	{0,1,0,1}
		// };
		// HashSet<Integer> result = m.pondSize(map);
		// for (Integer p : result) {
		// 	System.out.println(p);
		// }

		// ArrayList<String> validWords = new ArrayList<>();
		// Collections.addAll(validWords, new String[] {"apple", "tree", "used", "pink"});

		// for (String word : m.T9("8733", validWords)) {
		// 	System.out.println(word);
		// }

		// System.out.println(m.calculator("2*3+5/4*3+15"));

		// m.masterMind("RGBY", "GGRR");

		// HashMap<Integer, Integer> count = new HashMap<>();
		// for (int i = 0; i < 7000; i++) {
		// 	int num = m.rand7();
		// 	count.put(num, count.getOrDefault(num, 0) + 1);
		// }

		// for (int key : count.keySet()) {
		// 	System.out.println(key + ": " + count.get(key));
		// }



		// Name[] names = {
		// 	h.new Name("John", 15), h.new Name("Jon", 12), h.new Name("Chris", 13), 
		// 	h.new Name("Kris", 4), h.new Name("Christopher", 19), h.new Name("Sam", 12)
		// };

		// ArrayList<String[]> synonyms = new ArrayList<>();
		// synonyms.add(new String[] {"Jon", "John"});
		// synonyms.add(new String[] {"John", "Johnny"});
		// synonyms.add(new String[] {"Chris", "Kris"});
		// synonyms.add(new String[] {"Chris", "Christopher"});

		// h.revisedList(names, synonyms);



		// CircusPerson[] people = { 
		// 	h.new CircusPerson(65,100), h.new CircusPerson(70,150), h.new CircusPerson(56,90),
		// 	h.new CircusPerson(75,190), h.new CircusPerson(60,95), h.new CircusPerson(68,110)
		// };

		// ArrayList<CircusPerson> result = h.circusTower(people);
		// for (CircusPerson p : result) {
		// 	System.out.printf("(%d, %d) ", p.h, p.w);
		// }
		// System.out.println();

		


		System.out.println("--------------------------------------------------");
	}

}
