package methods;


public class LottoTickets {
	int numberOfTickets;
	String selectedGame;
	String[] tickets;
	int number;
	
	public LottoTickets(int numberOfTickets, String selectedGame,String[]tickets){
		this.numberOfTickets = numberOfTickets;
		this.selectedGame = selectedGame;
		this.tickets =tickets;
	}
	
	//Game selection
	private int[] getGame(String game) {
		//Game selection array
		int[] gameArray = new int[3];
		
		//Game decision
		switch(game) {		
		case "PowerBall" :
			gameArray[0] = 69;
			gameArray[1] = 26;
			gameArray[2] =  6;
			break;
		default:
			gameArray[0] = 70;
			gameArray[1] = 25;
			gameArray[2] =  6;
		}
		return gameArray ;
		
	}
	
	//Generating the tickets
	public void getTickets(String selection, int numberOfTickets) {
		// Selecting the game
		int[] game = getGame(selection);
		
		//Generating the numbers
		for (int i = 0; i < numberOfTickets; i ++) {
			int [] ticket = new int[game[2]];
			for (int k = 0; k < ticket.length - 1; k++) {
				generateNumber(game,ticket);
				ticket[k] = number;
			}
			//Generating the last number
			ticket[5] = 1 + (int) (Math.random()*(game[1]));
			
			//Building the number string
			StringBuilder uniquticket = new StringBuilder();
			for (int num: ticket) {
				// padding the single digit numbers
				String numb = String.valueOf(num);
				if (numb.length() == 1) numb = String.valueOf(0) + numb;
				
				//Bulding the strings
				uniquticket.append(numb + "   ");
			}
			tickets[i] = uniquticket.toString();
		}
		//print(tickets);
	}
	
	//Number generator
	private void generateNumber (int game[], int[] ticket) {
		number = 1 + (int) (Math.random()*(game[0]));
		isValid(game, ticket);
	}
	
	//Number validation (Checking for duplicate numbers)
	private void isValid(int[] game, int[] ticket){
		for (int i = 0; i < ticket.length - 1; i++) {
			if(number == ticket[i]) {
				number += (1 + (int) (Math.random()*10));
				if(number > game[0]) {
					number = (number - game[0]);
					isValid(game, ticket);
				}
			}
		}
	}
	
	//Print to the console
	public void print(String[] ticket) {
		for (String uniquticket: ticket) 
			System.out.println(uniquticket);	
	}
}
