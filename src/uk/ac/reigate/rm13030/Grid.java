package uk.ac.reigate.rm13030;

import java.util.Scanner;

import uk.ac.reigate.rm13030.Theatre.SeatingType;

/**
 * @author rm13030 <rm13030@reigate.ac.uk>
 */
public class Grid {
    
    private static Scanner sc;
    private static Object[][] map = new Object[10][10];
    private static final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    public static void main(String[] args) {
        addIndexes();
        getInput();
        //printDebug();
        //printMap();
    }
    
    /*public static void printDebug() {
    	for (int i=0; i < Theatre.ticketMap.size(); i++) {
    		System.out.println("Ticket ID: "+Theatre.ticketMap.get(i).getID()+"\n"+
    							"Name: "+Theatre.ticketMap.get(i).getName()+"\n"+
    							"Seating Type: "+Theatre.ticketMap.get(i).getSeatingType().toString()+"\n");
    	}
    }*/
    
    /* TODO: this */
    public static void printMap() {
        for (int i=0; i < map.length; i++) {
            System.out.println("["+map[i][0]+"]");
            for (int j=0; j < map.length; j++) {
                System.out.print("["+map[0][i]+"]");
            }
        }
    }
    
    public static boolean getInput() {
    	String data[] = new String[5];
    	
        sc = new Scanner(System.in);
        System.out.println("Please enter the seat coordinate in the form A-#:");
        String in = sc.next();
        String[] xy = in.split("-");
        System.out.println("Please enter your name");
        data[0] = sc.next();
        System.out.println("Please enter your seating type");
        data[1] = sc.next();
        //where x=A,B,C...
        //where y=1,2,3...
        //x = xy[0]; y = xy[1];
        if (!check(getLoc(xy[0]), Integer.valueOf(xy[1]))) {
            //System.out.println("Invalid");
            return false;
        } else {
            Theatre.ticketMap.add(new Ticket(Theatre.ticketMap.size(), data[0], SeatingType.valueOf(data[1].toUpperCase()), new int[getLoc(xy[0])][Integer.valueOf(xy[1])]));
            //System.out.println("Valid!");
            return true;
        }
    }
    
    public static boolean check(int x, int y) {
        boolean result = false;
        if (x <= 10 && y <= 10) {
            result = true;
        } if (x == -1) {
            result = false;
        }
        return result;
    }
    
    /* Get's index of String in letters Array */
    public static int getLoc(String str) {
        int index = -1;
        for (int i=0; i < letters.length; i++) {
            if (letters[i].equalsIgnoreCase(str)) {
                index=i;
            }
        }
        //System.out.println("[DEBUG]: getLoc: "+index);
        return index;
    }
    
    public static void addIndexes() {
        for (int i=0; i<10; i++) {
            //first dimension
            map[i][0] = letters[i];
        }
        
        for (int i=0; i<10; i++) {
            //second dimension
            map[0][i] = i;
        }
    }
    
}
