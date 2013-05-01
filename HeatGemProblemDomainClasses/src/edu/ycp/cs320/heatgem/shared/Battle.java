package edu.ycp.cs320.heatgem.shared;

import java.util.Random;


public class Battle {
	 private Player One;
     private Player Two;


     public Battle(Player playerOne, Player Monster)
     {
         One = playerOne;
         Two = Monster;
     }

     public void Attack() //Initiate attack phase, and determine what the enemy will do
     {
         Random random = new Random();
         Random TRandom = new Random();
         int playerDamage = (int)random.nextInt(10);
         int monsterDamage = random.nextInt(10);
         
         int MTurn = (int)TRandom.nextInt(2);
         Two.setHealth(One.getHealth() - playerDamage); //Attack enemy
         
         if (MTurn == 1){ //Monster attacks
        	MonsterAttack();
         }
         else
         {
        	 MonsterHeal(); //Monster heals
         }
     }

 	public void MonsterAttack() {  //Monster attacks
 		Random random = new Random();
 		int monsterDamage = random.nextInt(10);
		One.setHealth(One.getHealth() - monsterDamage);
	}

	public void MonsterHeal() { //Monster heals
 		Random random = new Random();
 		int monsterHeal = random.nextInt(10);
		Two.setHealth(Two.getHealth() + monsterHeal);
	}

	public void heal() { //Initiates instance for player to heal and determines if the enemy will heal as well
		Random random = new Random();
		Random TRandom = new Random();
		int playerHeal = (int)random.nextInt(10);
		One.setHealth(One.getHealth() + playerHeal);  //Player is healed
		
		int MTurn = (int)TRandom.nextInt(2);
		  if (MTurn == 1){ //Monster heals
	        	MonsterHeal();
	         }
	         else
	         {
	        	 MonsterAttack(); //Monster attacks
	         }
		
	}
     public int battleState()
     {
         if (One.getHealth() <= 0)
             return 1;   // player is dead

         else if (Two.getHealth() <= 0)
             return 2;   // monster is dead
         else
             return 0; // no one is dead
     }

     public int getHealth1()
     {
         return One.getHealth();
     }

     public int getHealth2()
     {
         return Two.getHealth();
     }


 }