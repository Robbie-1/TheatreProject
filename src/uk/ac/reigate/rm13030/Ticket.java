package uk.ac.reigate.rm13030;

import uk.ac.reigate.rm13030.Theatre.SeatingType;

public class Ticket {
	
	private int ID;
	private String name;
	private SeatingType seatingType;
	private int[][] seatLocation;
	
	
	public Ticket(int id, String name, SeatingType seatingType, int[][] seatLocation) {
		this.ID = id;
		this.name = name;
		this.seatingType = seatingType;
		this.seatLocation = seatLocation;
	}
	

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public SeatingType getSeatingType() {
		return seatingType;
	}


	public void setSeatingType(SeatingType seatingType) {
		this.seatingType = seatingType;
	}


	public int[][] getSeatLocation() {
		return seatLocation;
	}


	public void setSeatLocation(int[][] seatLocation) {
		this.seatLocation = seatLocation;
	}
	
}
