package edu.ycp.cs320.heatgem.shared;

import java.util.Random;

public class Logic {
	static int gameState = 0;
	 public static void doBattle(Player Player1, Player Player2)  //Battle
     {

		 Battle skirmish = new Battle(Player1, Player2);

        	 Random TRandom = new Random();
     		 int MTurn = (int)TRandom.nextInt(200);

             skirmish.Attack(); //Player attacks monster
             if(MTurn < 150){  //Monster attacks player OR
     			 skirmish.MonsterAttack();
     		 }
     		 else{
     			skirmish.MonsterHeal(); //Monster Heals
     		 }
             gameState = skirmish.battleState();
             Player1.setHealth(skirmish.getHealth1()); //Set new health values
             Player2.setHealth(skirmish.getHealth2());
             

     }
	 
	 // maybe eliminate second parameter
	 public static void doHeal(Player Player1, Player Player2)  //Heal
     {
         Battle Heal = new Battle(Player1, Player2);

        	 Random TRandom = new Random();
     		 int MTurn = (int)TRandom.nextInt(200);


             Heal.heal(); //Player Heals
     		 if(MTurn >= 150){ //Monster Heals
     			 Heal.MonsterHeal(); 
     		 }
     		 else{
     			 Heal.MonsterAttack(); //Monster Attacks
     		 }
     		 gameState = Heal.battleState();
             Player1.setHealth(Heal.getHealth1()); //Set new health
             Player2.setHealth(Heal.getHealth2());
             
     }
}