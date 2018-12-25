package methods;
/*This program helps pickup number for Megamillion and PowerBall lottery game.
 * The number are picked randomly and could be view in text file or printed directly.
 * Picking up your number by pressing the button yourself puts this game of chance in your hands. Good luck
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class LottoPlus extends JPanel implements PropertyChangeListener{

	private JFrame frame;
	private JFormattedTextField textFieldNumbersOfTickets;
	private JFormattedTextField textFieldUnitCost;
	private JFormattedTextField textFieldTotalCost;
	
	// Value of the fields
	private double unitCost = 2;
	private int numberOfTickets = 0;
	private String[] tickets;
	private double totalCost = 0;
	private String selectedGame = "MegaMillion";
	//private File text = new File("TextAreaTicket.txt");
	
	//Format to format and parse numbers
	private NumberFormat unitCostFormat;
	private NumberFormat numberOfTicketsFormat;
	private NumberFormat totalCostFormat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LottoPlus window = new LottoPlus();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LottoPlus() {
		initialize();
		setUpFormats();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Lotto Plus");
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.setBounds(100, 100, 631, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//panel label "Lottery Plus"
		JLabel lblLottoPlus = new JLabel("LOTTERY PLUS");
		lblLottoPlus.setHorizontalAlignment(SwingConstants.CENTER);
		lblLottoPlus.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblLottoPlus.setBounds(191, 6, 239, 31);
		frame.getContentPane().add(lblLottoPlus);
		
		// Select a game label
		JLabel lblSelectAGame = new JLabel("Select a Game");
		lblSelectAGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSelectAGame.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectAGame.setBounds(46, 48, 97, 24);
		frame.getContentPane().add(lblSelectAGame);
		
		// Number of tickets label
		JLabel lblTicketsNum = new JLabel("Tickets num");
		lblTicketsNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblTicketsNum.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTicketsNum.setBounds(191, 48, 97, 24);
		frame.getContentPane().add(lblTicketsNum);
		
		// Ticket unit cost label
		JLabel lblTicketCost = new JLabel("Ticket cost");
		lblTicketCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblTicketCost.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTicketCost.setBounds(341, 48, 97, 24);
		frame.getContentPane().add(lblTicketCost);
		
		// Ticket total cost label
		JLabel lblTotalCost = new JLabel("Total cost");
		lblTotalCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCost.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalCost.setBounds(477, 48, 97, 24);
		frame.getContentPane().add(lblTotalCost);
		
		// Text area scroll bar
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(124, 217, 373, 202);
		frame.getContentPane().add(scrollPane);
		
		// ticket display text area
		JTextArea textAreaTickets = new JTextArea();
		scrollPane.setViewportView(textAreaTickets);
		textAreaTickets.setEditable(false);
		textAreaTickets.setFont(new Font("Times New Roman", Font.BOLD, 25));
		textAreaTickets.setForeground(Color.BLUE);
		
		// Game selection combo box
		JComboBox gameSelection = new JComboBox();
		gameSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedGame = (String) gameSelection.getSelectedItem();
				switch(selectedGame) {				
				case "PowerBall" :
					textAreaTickets.setText("");
					clearAllFields();
					unitCost = 2;
					textFieldUnitCost.setValue(2);
					break;
				default:
					unitCost = 2;
					textFieldUnitCost.setValue(2);
				}
			}
		});
		gameSelection.setModel(new DefaultComboBoxModel(new String[] {"MegaMillion", "PowerBall"}));
		gameSelection.setFont(new Font("Times New Roman", Font.BOLD, 14));
		gameSelection.setBounds(44, 86, 113, 22);
		frame.getContentPane().add(gameSelection);
		
		// Number of ticket text field
		textFieldNumbersOfTickets = new JFormattedTextField(unitCostFormat);
		textFieldNumbersOfTickets.setValue(numberOfTickets);
		textFieldNumbersOfTickets.addPropertyChangeListener("value",this);
		textFieldNumbersOfTickets.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldNumbersOfTickets.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldNumbersOfTickets.setBounds(191, 87, 97, 20);
		frame.getContentPane().add(textFieldNumbersOfTickets);
		textFieldNumbersOfTickets.setColumns(10);
		//textFieldNumbersOfTickets.setText(String.valueOf(0));
		
		// Ticket unit cost text field
		textFieldUnitCost = new JFormattedTextField(numberOfTicketsFormat);
		textFieldUnitCost.setBackground(Color.WHITE);
		textFieldUnitCost.setEditable(false);
		textFieldUnitCost.setValue(unitCost);
		textFieldUnitCost.addPropertyChangeListener("value",this);
		textFieldUnitCost.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldUnitCost.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldUnitCost.setColumns(10);
		textFieldUnitCost.setBounds(341, 86, 97, 20);
		frame.getContentPane().add(textFieldUnitCost);
		textFieldUnitCost.setText(String.valueOf(0));
		
		// Total cost text field
		
		textFieldTotalCost = new JFormattedTextField(totalCostFormat);
		textFieldTotalCost.setBackground(Color.WHITE);
		textFieldTotalCost.setValue(totalCost);
		textFieldTotalCost.addPropertyChangeListener("value",this);
		textFieldTotalCost.setEditable(false);
		textFieldTotalCost.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldTotalCost.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textFieldTotalCost.setColumns(6);
		textFieldTotalCost.setBounds(477, 86, 97, 20);
		frame.getContentPane().add(textFieldTotalCost);
		textFieldTotalCost.setForeground(Color.RED);
		//textFieldTotalCost.setText(String.valueOf(0));
		
		// Quick pick button
		JButton btnQuickPick = new JButton("Quick Pick");
		btnQuickPick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaTickets.setText("");
				totalCost = numberOfTickets* unitCost;
				textFieldUnitCost.setValue(unitCost);
				textFieldTotalCost.setValue(totalCost);
				String[] ticket = new String[numberOfTickets];
				LottoTickets game = new LottoTickets(numberOfTickets, (String) gameSelection.getSelectedItem(),ticket);
				game.getTickets((String) gameSelection.getSelectedItem(), numberOfTickets);
				tickets = ticket;
				
				for(int i = 0; i < numberOfTickets; i++) {
					textAreaTickets.append(tickets[i] + "\n");
					textAreaTickets.selectAll();
					;
				}
			}
		});
		btnQuickPick.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnQuickPick.setBounds(124, 143, 373, 47);
		frame.getContentPane().add(btnQuickPick);		
		
		// Text area label
		JLabel lblYourNumbers = new JLabel("Your numbers");
		lblYourNumbers.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourNumbers.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblYourNumbers.setBounds(124, 187, 373, 31);
		frame.getContentPane().add(lblYourNumbers);
		
		// View Button
		JButton btnview = new JButton("View");
		btnview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WritePrint writePrint = new WritePrint(selectedGame, tickets, totalCost, unitCost, numberOfTickets);
				writePrint.writeToText();
				writePrint.openText();
			}
		});
		btnview.setHorizontalAlignment(SwingConstants.LEFT);
		btnview.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnview.setBounds(25, 220, 89, 23);
		frame.getContentPane().add(btnview);
		
		// Print button
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WritePrint writePrint = new WritePrint(selectedGame, tickets, totalCost, unitCost, numberOfTickets);
				writePrint.writeToText();
				writePrint.printText();
			}
		});
		btnPrint.setHorizontalAlignment(SwingConstants.LEFT);
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnPrint.setBounds(25, 279, 89, 23);
		frame.getContentPane().add(btnPrint);
		
		// Clear button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaTickets.setText("");
				clearAllFields();
			}
			
		});
		btnClear.setHorizontalAlignment(SwingConstants.LEFT);
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnClear.setBounds(25, 334, 89, 23);
		frame.getContentPane().add(btnClear);
		
		//Close button
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numberOfTickets = 0;
				WritePrint writePrint = new WritePrint(selectedGame, tickets, totalCost, unitCost, numberOfTickets);
				writePrint.writeToText();
				System.exit(0);
			}
		});
		btnClose.setHorizontalAlignment(SwingConstants.LEFT);
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnClose.setBounds(25, 396, 89, 23);
		frame.getContentPane().add(btnClose);
		
		// Game panel separation 
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 126, 595, 316);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("$");
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(327, 90, 14, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("$");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_1.setBounds(465, 91, 14, 14);
		frame.getContentPane().add(label_1);
	}
	//** Called when a field's "value" Property changes. */
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		if (source == textFieldNumbersOfTickets) {
			numberOfTickets = ((Number)textFieldNumbersOfTickets.getValue()).intValue();
		}else if (source == textFieldUnitCost) {
			unitCost = ((Number)textFieldUnitCost.getValue()).doubleValue();
		}else if (source == textFieldTotalCost) {
			totalCost = ((Number)textFieldTotalCost.getValue()).doubleValue();
		}
		
	}
	
	// Create and set up number formats. These objects also parse numbers input by user
	private void setUpFormats() {
		numberOfTicketsFormat = NumberFormat.getNumberInstance();
		unitCostFormat = NumberFormat.getCurrencyInstance();
		totalCostFormat = NumberFormat.getCurrencyInstance();
	}
	
	// Clear file method
	private void clearAllFields() {
		tickets = null;
		totalCost = 0;
		WritePrint writePrint = new WritePrint(selectedGame, tickets, totalCost, unitCost, numberOfTickets);
		writePrint.writeToText();
		textFieldNumbersOfTickets.setValue(0);
		textFieldTotalCost.setValue(0);		
	}
}
