package edu.ycp.cs320.heatgem.shared;

import java.util.Random;


public class Battle {
	 Player One;
     Player Two;


     public Battle(Player playerOne, Player Monster)
     {
         One = playerOne;
         Two = Monster;
     }

     public void turn()
     {
         Random random = new Random();
         int playerDamage = (int)random.nextInt(10);
         int monsterDamage = random.nextInt(10);
         One.setHealth(One.getHealth() - playerDamage);
         Two.setHealth(Two.getHealth() - monsterDamage);
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
