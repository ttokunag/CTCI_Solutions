package SecondTimePackage;

import java.util.*;

import SecondTimePackage.Moderate.Person;

public class Solution {

    // public class Person {
    //     int birth;
    //     int death;
    //     public Person(int birth, int death) {
    //         this.birth = birth;
    //         this.death = death;
    //     }
    // }

    public int maxAliveYear(Person[] people, int min, int max) {
		int maxAlive = 0;
		int maxAliveYear = min;
		
		for (int year = min; year <= max; year++) {
			int alive = 0;
			for (Person person : people) {
				if (person.birth <= year && year <= person.death) {
					alive++;
				}
			}
			if (alive > maxAlive) {
				maxAlive = alive;
				maxAliveYear = year;
			}
		}

		return maxAliveYear;
	}
}