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

     public void Attack() //Initiate attack phase
     {
         Random random = new Random();
         int playerDamage = (int)random.nextInt(10);
         Two.setHealth(Two.getHealth() - playerDamage); //Attack enemy
     }
     
 	public void heal() { //Initiates instance for player to heal
		Random random = new Random();
		int playerHeal = (int)random.nextInt(10);
		One.setHealth(One.getHealth() + playerHeal);  //Player is healed
		if(One.getHealth() >= 100){
			One.setHealth(100);
		}
 	}

 	public void MonsterAttack() {  //Monster attacks
 		Random random = new Random();
 		int monsterDamage = random.nextInt(10);
		One.setHealth(One.getHealth() - monsterDamage); //Attack player
	}

	public void MonsterHeal() { //Monster heals
 		Random random = new Random();
 		int monsterHeal = random.nextInt(10);
		Two.setHealth(Two.getHealth() + monsterHeal);//Attack enemy
		if(Two.getHealth() >= 100){
			Two.setHealth(100);
		}
	}


		
     public int battleState()
     {
         if (One.getHealth() <= 0){
        	 One.setHealth(0);
             return 1;   // player is dead
         }
         else if (Two.getHealth() <= 0){
        	 Two.setHealth(0);
             return 2;   // monster is dead
         }
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