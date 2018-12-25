package methods;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WritePrint {
	private File text = new File("TextAreaTicket.txt");
	private String   selectedGame;
	private String[] tickets;
	private double   totalCost;
	private double   unitCost;
	private int      numberOfTickets;
	
	WritePrint(String selectedGame, String[] tickets, double totalCost, double unitCost, int numberOfTickets){
		this.selectedGame    = selectedGame;
		this.tickets         = tickets;
		this.totalCost       = totalCost;
		this.unitCost        = unitCost;
		this.numberOfTickets = numberOfTickets;
	}
	
	public void writeToText() {
		if (totalCost != 0) {
			try {
				BufferedWriter bf = new BufferedWriter(new FileWriter(text));
				bf.write("Good Luck! Your " + selectedGame + " numbers are:");
				bf.newLine();
				bf.newLine();
				for(int i = 0; i < tickets.length; i++) {
					bf.write( tickets[i]);
					bf.newLine();
				}
				bf.newLine();
				bf.write(numberOfTickets + " Tickets ");
				bf.newLine();
				bf.write("Unit Cost is: $ " + unitCost);
				bf.newLine();
				bf.write("Total Cost is: $ " + totalCost);
				bf.flush();
				bf.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				BufferedWriter bf = new BufferedWriter(new FileWriter(text));
				bf.write("No tickets ");
				bf.flush();
				bf.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}		
	}
	public void openText() {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printText() {
		if(totalCost != 0) {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().print(text);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
