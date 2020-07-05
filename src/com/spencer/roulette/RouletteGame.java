package com.spencer.roulette;

import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import com.spencer.pojos.*;
import com.spencer.services.RouletteServices;
import com.spencer.threads.PlayerInputThread;
import com.spencer.threads.RouletteTimerThread;

public class RouletteGame {
	PlayerInputThread inputThread;
	public void playGame()  {
			Scanner scan = rouletteService.getScanner();
			RouletteServices rouletteService = new RouletteServices();
			Vector<Player> playerList = rouletteService.createPlayers();
			long startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 30000) {
				for (Player player : playerList) {
					System.out.println(player.getName() + " Would you like to add to your bet? If not type 0.00");
					if (sc.hasNext()) {
						String bet = scan.nextLine();
						while (!isValidBet(bet)) {
							System.out.println(player.getName() + " Would you like to add to your bet? If not type 0.00");
							bet = scan.nextLine();
						}
						player.setBet(player.getBet() + Double.parseDouble(bet));
						scan.close();
					}
				}
				scan.close();
			}
			RouletteResult myresult = rouletteService.getResults(playerList);
			try {
				rouletteService.getPlayerHistoryFromfile(myresult);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n----------------------------------------------------");
			System.out.println("Number: " + myresult.getRouletteNumber());
			System.out.println("Player:       Bet        Outcome      Winnings");
			System.out.println("-------");
			for( Map.Entry<Player, PlayerResult> entry : myresult.getResults().entrySet() ){
			    System.out.println(entry.getKey().getName() + "       " + entry.getKey().getBet() +  "       " +  entry.getValue().getResult() +  "       " + entry.getValue().getWinnings());
			}
			System.out.println("\n----------------------------------------------------");
			System.exit(0);
		}
	
	public static void main(String [] args) {
		RouletteGame game = new RouletteGame();
		game.playGame();
	}
}