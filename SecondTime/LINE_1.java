/**
 * Theme: the number of multiples
 * 
 * Review:
 * 1. lcm with prime factors method
 * 2. should have prepared for VSC testing (set another args[] in main)
 * 3. implement in VSC, and then copy and paste to the answer
 */


import java.util.*;

class LINE_1 {
    public static HashMap<Integer, Integer> getPrimeFactors(int number) {
        int absNumber = Math.abs(number);
     
        HashMap<Integer, Integer> primeFactorsMap = new HashMap<Integer, Integer>();
     
        for (int factor = 2; factor <= absNumber; factor++) {
            while (absNumber % factor == 0) {
                Integer power = primeFactorsMap.get(factor);
                if (power == null) {
                    power = 0;
                }
                primeFactorsMap.put(factor, power + 1);
                absNumber /= factor;
            }
        }
     
        return primeFactorsMap;
    }

    static int lcm(LinkedList<Integer> nums, HashMap<Integer, HashMap<Integer, Integer>> factors) {
        HashMap<Integer, Integer> factorFreq = new HashMap<>();
        HashMap<Integer, Integer> factor = new HashMap<>();
        for (int num : nums) {
          factor = factors.get(num);
          for (int key : factor.keySet()) {
            int freq = factor.get(key);
            Integer currFreq = factorFreq.get(key);
            if (currFreq == null || freq > currFreq) {
              factorFreq.put(key, freq);
            }
          }
        }
    
        int lcm = 1;
        for (int key : factorFreq.keySet()) {
          lcm *= Math.pow(key, factorFreq.get(key));
        }
    
        return lcm;
    }

    static ArrayList<ArrayList<Integer>> numMultiples(
        LinkedList<Integer> nums, ArrayList<ArrayList<Integer>> result, 
        HashSet<String> visited, HashMap<Integer, HashMap<Integer, Integer>> factors, int n) {

        // base case
        if (nums.size() == 2) {
            int lcm = lcm(nums, factors);
            result.get(0).add(n / lcm);
            visited.add(listToString(nums));
            return result;
        }

        // recursive phase
        for (int i = 0; i < nums.size(); i++) {
            Integer curr = nums.remove(i);
            String key = listToString(nums);
            if (!visited.contains(key)) {
                numMultiples(nums, result, visited, factors, n);
                visited.add(key);
            }
            nums.add(i, curr);
        }

        result.get(nums.size()-2).add(n / lcm(nums, factors));

        return result;

    }

    static String listToString(LinkedList<Integer> ll) {
        StringBuilder str = new StringBuilder();
        for (Integer num : ll) {
        str.append(String.valueOf(num) + ",");
        }
        return str.toString();
    }

    public static void main(String[] args)  {
        System.out.println("----------------------------------------------------------------------------------------------------");

        int l = 10;
        int r = 724;
        int m = 3;
        int[] multiples = {7,39,51};

        HashMap<Integer, HashMap<Integer, Integer>> factors = new HashMap<>();
        LinkedList<Integer> nums = new LinkedList<>();
        for (int multiple : multiples) {
            HashMap<Integer, Integer> factor = getPrimeFactors(multiple);
            factors.put(multiple, factor);
            nums.add(multiple);
        }

        ArrayList<Integer> mult1 = new ArrayList<>();
        for (int multiple : multiples) {
            mult1.add(r / multiple);
        }
        ArrayList<ArrayList<Integer>> mult2 = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            mult2.add(new ArrayList<>());
        }
        ArrayList<ArrayList<Integer>> _mult2 = numMultiples(nums, mult2, new HashSet<>(), factors, r);

        ArrayList<ArrayList<Integer>> numMultiples = new ArrayList<>();
        numMultiples.add(mult1);
        for (ArrayList<Integer> al : mult2) {
            numMultiples.add(al);
        }

        int rResult = 0;
        for (int i = 0; i < numMultiples.size(); i++) {
            ArrayList<Integer> ith = numMultiples.get(i);
            if (i % 2 == 0) {
                for (Integer num : ith) {
                    rResult += num;
                }
            } else {
                for (Integer num : ith) {
                    rResult -= num;
                }
            }
        }


        //////
        mult1 = new ArrayList<>();
        for (int multiple : multiples) {
            mult1.add(l / multiple);
        }
        mult2 = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            mult2.add(new ArrayList<>());
        }
        _mult2 = numMultiples(nums, mult2, new HashSet<>(), factors, l);

        numMultiples = new ArrayList<>();
        numMultiples.add(mult1);
        for (ArrayList<Integer> al : mult2) {
            numMultiples.add(al);
        }

        int lResult = 0;
        for (int i = 0; i < numMultiples.size(); i++) {
            ArrayList<Integer> ith = numMultiples.get(i);
            if (i % 2 == 0) {
                for (Integer num : ith) {
                    lResult += num;
                }
            } else {
                for (Integer num : ith) {
                    lResult -= num;
                }
            }
        }

        int b = (r - l + 1) - rResult + lResult;


        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}