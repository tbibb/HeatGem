package edu.ycp.cs320.heatgem.shared;

public class Logic {
	 public static void doBattle(Player Player1, Player Player2)
     {
         Player Monster = new Player("Monster");
         Battle skirmish = new Battle(Player1, Monster);
         while (true)
         {

             skirmish.Attack();
             Player1.setHealth(skirmish.getHealth1());
             Player2.setHealth(skirmish.getHealth2());
             if (skirmish.battleState() != 0)
                 break;
         }

     }


	
             

	 
	 public static void doHeal(Player Player1, Player Player2)
     {
         Player Monster = new Player("Monster");
         Battle Heal = new Battle(Player1, Monster);
         while (true)
         {
     
             Heal.heal();
             Player1.setHealth(Heal.getHealth1());
             Player2.setHealth(Heal.getHealth2());
             if (Heal.battleState() != 0)
                 break;
         }

     }
}
