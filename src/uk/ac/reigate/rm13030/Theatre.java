package uk.ac.reigate.rm13030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author rm13030
 */
public class Theatre {
    
    static String UI;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final String ADMIN_PASSWORD = "pass";
    static boolean isAdmin;
    static ArrayList<Ticket> ticketMap = new ArrayList<Ticket>();
    static ArrayList<String> vehicleMap = new ArrayList<String>();
    
    public static void main(String[] args) {
    	mainMenu(getMenuType());
    }
    
    public static boolean getMenuType() {
    	System.out.println("Welcome to my Theatre program!\n"+
    					   "1) Admin Menu\n"+
    					   "2) Regular Menu\n");
    	try {
			UI = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (UI.equals("1")) {
    		return login();
    	} else {
    		return false;
    	}
    }
    
    public static boolean login() {
    	boolean run = true;
    	while (run) {
        	System.out.println("\nPlease enter the admin password");
        	try {
				UI = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	if (UI.equals(ADMIN_PASSWORD)) {
        		run = false;
        		System.out.println("Access granted!\n");
        		isAdmin = true;
        		return true;
        	} else {
        		System.out.println("INCORRECT! Please try again...");
        	}
    	}
    	return false;
    }
    
    public static void mainMenu(boolean adminMenu) {
        System.out.println("Would you like to buy a new ticket (B) or edit an existing ticket (E)" + (adminMenu ? " or view all tickets sold (V)?" : "?"));
        try {
			UI = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (UI.equalsIgnoreCase("B")) {
            buyTicket();
        } else if (UI.equalsIgnoreCase("E")) {
            editTicket();
        } else if (UI.equalsIgnoreCase("V")) {
        	if (adminMenu) {
        		isAdmin = true;
        		viewTicketsSold();
        	}
        } else {
            System.out.println("\nInvalid input! Restarting process...\n");
            mainMenu(adminMenu);
        }
    }
    
    public static void buyTicket() {
    	System.out.println("Would you like to buy a cark park ticket as well (Y/N)?");
    	try {
			UI = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (UI.equalsIgnoreCase("N")) {
    		//Theatre ticket booking
            if (Grid.getInput()) {
                System.out.println("\nYour ticket has been booked successfully!\n"
                					+ "Ticket cost: £"+ticketMap.get(ticketMap.size()-1).getSeatingType().getPrice()+"\n");
                //Grid.printDebug();
                printTicket();
                mainMenu(isAdmin);
            } else {
                System.out.println("\nSorry but the seat you've chosen is not available.\n");
            }
    	} else {
    		//Car park ticket booking...
    		carParkTickets();
    	}
    }
    
    public static void carParkTickets() {
    	System.out.println("Car Parking tickets cost £15 and you will be charged the following day.");
    	System.out.println("Please enter the registration of your vehicle:");
    	try {
			UI = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	vehicleMap.add(UI);
    	System.out.println("\nYou've successfully purchased a car park ticket for registration "+UI+"!\n");
        if (Grid.getInput()) {
            System.out.println("\nYour ticket has been booked successfully!\n"
            					+ "Ticket cost: £"+ticketMap.get(ticketMap.size()-1).getSeatingType().getPrice());
            //Grid.printDebug();
            mainMenu(isAdmin);
        } else {
            System.out.println("\nSorry but the seat you've chosen is not available.\n");
        }
    }
    
    public static Ticket getTicketByID(int ID) {
    	for (int i=0; i < ticketMap.size(); i++) {
    		if (ticketMap.get(i).getID() == i) {
    			return ticketMap.get(i);
    		}
    	}
    	return null;
    }
    
    public static void editTicket() {
    	int ticketID = -1;
        System.out.println("Please enter the ticket ID you wish to edit:");
        try {
			UI = in.readLine();
			ticketID = Integer.valueOf(UI);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        printTicketInfo(getTicketByID(Integer.valueOf(UI)));
        System.out.println("Would you like to edit details (E) or delete the ticket (D)?");
        try {
			UI = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (UI.equalsIgnoreCase("E")) {
        	//edit details
        	editDetails(ticketID);
        } else {
        	//delete ticket
        	deleteTicket(ticketID);
        }
    }
    
    public static void editDetails(int id) {
        Ticket t = getTicketByID(id);
        System.out.println("What would you like to edit?\n" +
            "Name (N), Seating Type (ST), Seat Location (SL)");
        try {
            UI = in.readLine();
            if (UI.equalsIgnoreCase("N")) {
                System.out.println("Please enter the new name");
                UI = in.readLine();
                t.setName(UI);
            } else if (UI.equalsIgnoreCase("ST")) {
            	System.out.println("Please enter the new seating type");
            	UI = in.readLine();
            	t.setSeatingType(SeatingType.valueOf(UI));
            } else if (UI.equalsIgnoreCase("SL")) {
            	System.out.println("Please enter the new seat coordinate in the form A-#:");
            	UI = in.readLine();
                String[] xy = UI.split("-");
                t.setSeatLocation(new int[Grid.getLoc(xy[0])][Integer.valueOf(xy[1])]);
            } else {
                System.out.println("Unrecognised input, returning...");
                editDetails(id);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\nSuccessfully edited your ticket details!\n");
    }
    
    public static void deleteTicket(int id) {
        ticketMap.remove(id);
        System.out.println("\nSuccessfully removed your ticket from the system.\n");
    }
    
    public static void printTicketInfo(Ticket t) {
    	System.out.println("\nTicket info:");
    	System.out.println("\nID: "+t.getID()+ "\n" +
    						"Name: "+t.getName()+ "\n" +
    						"Seating Type: "+t.getSeatingType() + "\n");
    }
    
    public static void viewTicketsSold() {
    	if (isAdmin) {
    		for (int i=0; i < Theatre.ticketMap.size(); i++) {
    			System.out.println("Ticket ID: "+ticketMap.get(i).getID()+"\n"+
    								"Name: "+ticketMap.get(i).getName()+"\n"+
    								"Seating Type: "+ticketMap.get(i).getSeatingType().toString()+"\n");
    		}
    	} else {
    		System.out.println("You do not have the priviliges required to access this.");
    	}
    }
    
    public static void printTicket() {
        JFrame f = new JFrame(ticketMap.get(ticketMap.size()-1).getName()+"'s ticket");
        JLabel ticket = new JLabel();
        ticket.add(f);
        f.setSize(250, 350);
        f.setVisible(true);
    }
        
    enum SeatingType {
        
        GRAND_CIRCLE_FIFTH_PRICE(15),
        GRAND_CIRCLE_FOURTH_PRICE(29.50),
        GRAND_CIRCLE_THIRD_PRICE(39.50),
        STALLS_TOP_PRICE(57.95),
        STALLS_SECOND_PRICE(49.50),
        STALLS_PREMIUM(75),
        ROYAL_CIRCLE_TOP_PRICE(57.95),
        ROYAL_CIRCLE_PREMIUM(75);
        
        double price;
        
        SeatingType(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }
    
}
