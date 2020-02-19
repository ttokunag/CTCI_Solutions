package SecondTimePackage;

import java.util.*;

public class Hard {
     
    public class Name {
        String name;
        int frequency;
        public Name(String name, int frequency) {
            this.name = name;
            this.frequency = frequency;
        }
    }

    public void revisedList(Name[] names, ArrayList<String[]> synonyms) {
        // KEY: baby name, VALUE: synonym type 
        HashMap<String, Integer> types = new HashMap<>();
        // KEY: synonym type, VALUE: Name object(an element of a list to be returned)
        HashMap<Integer, Name> revisedNames = new HashMap<>();

        int typeNumber = 0; // ~9,999

        // classifies each name
        for (String[] synonym : synonyms) {
            Integer type = types.get(synonym[0]);
            // when a first name is not stored in a hash map
            if (type == null) {
                type = types.get(synonym[1]);
            }

            if (type != null) {
                types.put(synonym[0], type);
                types.put(synonym[1], type);
            } else {
                types.put(synonym[0], typeNumber);
                types.put(synonym[1], typeNumber);
                typeNumber++;
            }
        }

        // sum up a synonym counts by types
        for (Name name : names) {
            Integer type = types.get(name.name);
            // when a name doesn't have a synonym name
            if (type == null) {
                System.out.println("Name: " + name.name + "(" + name.frequency + ")");
                continue;
            }

            Name synonym = revisedNames.get(type);
            // no other synonym names are added yet
            if (synonym == null) {
                synonym = new Name(name.name, 0);
                revisedNames.put(type, synonym);
            }

            synonym.frequency += name.frequency;
        }

        for (int type : revisedNames.keySet()) {
            Name name = revisedNames.get(type);
            System.out.println("Name: " + name.name + "(" + name.frequency + ")");
        }
    }

    public class CircusPerson {
        public int h;
        public int w;
        public CircusPerson(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }

    class SortByHeight implements Comparator<CircusPerson> {
        // sort CircusPerson objects in ascending order by height
        public int compare(CircusPerson p1, CircusPerson p2) {
            return p1.h - p2.h;
        }
    }

    public ArrayList<CircusPerson> circusTower(CircusPerson[] people) {
        // sort people by their height
        CircusPerson[] sorted = people.clone();
        Arrays.sort(sorted, new SortByHeight());    // O(Nlog(N)) time

        ArrayList<CircusPerson> result = new ArrayList<>();
        int maxNumPeople = Integer.MIN_VALUE;

        for (int i = 0; i < sorted.length; i++) {   // O(N^2) time
            CircusPerson top = sorted[i];
            ArrayList<CircusPerson> temp = new ArrayList<>();
            temp.add(top);
            int numPeople = 1;

            for (int j = i + 1; j < sorted.length; j++) {
                CircusPerson curr = sorted[j];
                if (curr.w > top.w) {
                    temp.add(curr);
                    numPeople++;
                }
            }

            // update a max size
            if (numPeople > maxNumPeople) {
                maxNumPeople = numPeople;
                result = temp;
            }
        }

        return result;
        // what if we cannot build a tower?
    }


    


}