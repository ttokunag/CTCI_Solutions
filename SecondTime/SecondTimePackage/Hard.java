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
                System.out.println("Name: " + name.name + " Freq: " + name.frequency);
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
            System.out.println("Name: " + name.name + " Freq: " + name.frequency);
        }
    }


}