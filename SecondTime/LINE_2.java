import java.util.*;

class LINE_2 {
    public static void main(String[] args) {
        // String[] args = {};

        String[] recordTypes = {"header", "enter", "leave", "food", "drink", "footer"};
        HashMap<String, Integer> recordMap = new HashMap<>();
        for (int i = 0; i < recordTypes.length; i++) {
            recordMap.put(recordTypes[i], i);
        }

        String[] drinkTypes = {"one_drink", "free_refills", "alcohol_free_refills"};
        int[] drinkPrices = {100, 400, 200, 500, 300, 650};
        HashSet<String> drinkKind = new HashSet<>();
        drinkKind.add("soft_drink"); 
        drinkKind.add("alcohol"); 
        
        HashMap<String, Integer> drinkMap = new HashMap<>();
        for (int i = 0; i < drinkTypes.length; i++) {
            drinkMap.put(drinkTypes[i], i);
        }

        ArrayList<Integer> roomInfo = new ArrayList<>(); // enterTime, drinkType, numPeople
        roomInfo.add(-1);    // enter time
        roomInfo.add(-1);
        roomInfo.add(0); 

        int result = 0;

        for (int i = 0; i < args.length; i++) {
            int price = priceOfLine(args[i], recordMap, drinkMap, roomInfo, drinkPrices, drinkKind);
            if (price == -1) {
                // invalid input
                System.out.print("{\n\"code\": 999\n}\n");
            } else if (price == -2) {
                // inconsistent error
                System.out.print("{\n\"code\": 99\n}\n");
            } else {
                result += price;
            }
        }

        System.out.printf("{\"code\": 0 \"price\": %d}", result);

        // System.out.println(roomFeeCalc(timeToInt("17:23:00"), timeToInt("18:30:20"), 1, drinkPrices));
    }

    static int timeToInt(String time) {
        int[] times = new int[3];
        String[] timeStrings = time.split(":");

        if (timeStrings.length < 3) {
            return -1;
        }

        for (int i = 0; i < timeStrings.length; i++) {
            String curr = timeStrings[i];
            if (curr.length() != 2) {
                return -1;
            }
            // hour
            if (i == 0) {
                if (curr.charAt(0) == '0') {
                    times[i] = curr.charAt(1) - '8';
                } else {
                    times[i] = Integer.valueOf(curr) - 8;
                }
                if (times[i] < 0 || times[i] > 23) {
                    return -1;
                }
            }
            // min
            else if (i == 1) {
                if (curr.charAt(0) == '0') {
                    times[i] = curr.charAt(1) - '0';
                } else {
                    times[i] = Integer.valueOf(curr);
                }
                if (times[i] < 0 || times[i] > 59) {
                    return -1;
                }
            }
            // sec
            else {
                if (curr.charAt(0) == '0') {
                    times[i] = curr.charAt(1) - '0';
                } else {
                    times[i] = Integer.valueOf(curr);
                }
                if (times[i] < 0 || times[i] > 59) {
                    return -1;
                }
            }
        }

        return times[0]*3600 + times[1]*60 + times[2];
    }

    static int priceOfLine(String line, HashMap<String, Integer> recordMap, HashMap<String, Integer> drinkMap, 
            ArrayList<Integer> roomInfo, int[] drinkPrices, HashSet<String> drinkKind) {
        String[] args = line.split(" ");
        
        if (args.length < 2 || args[0] == "" || args[args.length-1] == "") {
            return -1;
        }

        String recordType = args[1];
        if (!recordMap.containsKey(recordType)) {
            return -1;
        }

        if (timeToInt(args[0]) == -1) {
            return -1;
        }

        int recordID = recordMap.get(recordType);
        // header: return enter time
        if (recordID == 0) {
            if (args.length != 3 || !drinkMap.containsKey(args[2])) {
                return -1;
            }
            roomInfo.set(1, drinkMap.get(args[2]));
            return 0;
        }
        // enter
        else if (recordID == 1) {
            if (args.length != 3) {
                return -1;
            }

            int numPeople = Integer.valueOf(args[2]);
            if (numPeople < 1 || numPeople > 999) {
                return -1;
            }
            int currPeople = roomInfo.get(2) + numPeople;
            if (currPeople > 999) {
                return -2;
            }
            roomInfo.set(2, currPeople);

            int intTime = timeToInt(args[0]);
            roomInfo.set(0, intTime);
        }
        // leave
        else if (recordID == 2) {
            if (args.length != 3) {
                return -1;
            }

            int numPeople = Integer.valueOf(args[2]);
            if (numPeople < 1 || numPeople > 999) {
                return -1;
            }
            int remainingPeople = roomInfo.get(2) - numPeople;
            if (remainingPeople < 0) {
                return -2;
            }
            roomInfo.set(2, remainingPeople);

            return 0;
        }
        // food
        else if (recordID == 3) {
            if (args.length != 4) {
                return -1;
            }

            int price = Integer.valueOf(args[2]);
            int amount = Integer.valueOf(args[3]);
            if (price < 1 || price > 9999 || amount < 1 || amount > 99) {
                return -1;
            }

            return price * amount;
        }
        // drink
        else if (recordID == 4) {
            if (args.length != 5) {
                return -1;
            }

            String drink = args[2];
            int price = Integer.valueOf(args[3]);
            int amount = Integer.valueOf(args[4]);
            if (!drinkKind.contains(drink) || price < 1 || price > 9999 || amount < 1 || amount > 99) {
                return -1;
            }

            int drinkID = roomInfo.get(1);
            return (drinkID == 0 || (drinkID == 1 && drink.equals("alcohol"))) ? price * amount : 0;
        }
        // footer
        else {
            int outTime = timeToInt(args[0]);
            int inTime = roomInfo.get(0);
            int drinkID = roomInfo.get(1);
            if (inTime < 0) {
                return -1;
            }
            
            return roomFeeCalc(inTime, outTime, drinkID, drinkPrices);
        }

        return -1;
    }

    static int roomFeeCalc(int inTime, int outTime, int drinkID, int[] drinkPrices) {
        int extraTime = (outTime - inTime) % 1800;
        int duration = outTime - inTime - extraTime + (extraTime < 600 ? 0 : 1800);
        int total = 0;
        // early time
        if (inTime <= 32399) {
            int currTime = inTime;
            while (currTime <= inTime + duration - 1800) {
                total += (currTime <= 38999) ? drinkPrices[drinkID * 2] : drinkPrices[(drinkID * 2) + 1];
                currTime += 1800;
            }
        } 
        // late time
        else {
            int currTime = inTime;
            while (currTime <= inTime + duration - 1800) {
                total += (currTime <= 35399) ? drinkPrices[drinkID * 2] : drinkPrices[(drinkID * 2) + 1];
                currTime += 1800;
            }
        }

        return total;
    }
}