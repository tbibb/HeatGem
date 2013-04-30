package edu.ycp.cs320.heatgem.shared;

public class Logic {
	 public static void doBattle(Player Player1, Player Player2)
     {
         Player Monster = new Player("Monster");
         Battle skirmish = new Battle(Player1, Monster);
         while (true)
         {
             //Console.WriteLine("You have " + Player1.getHealth() + " health");
            // Console.WriteLine("Monster has " + Monster.getHealth() + " health");
             //cUI.waitForAttack();
            // cUI.parseInput();
             skirmish.turn();
             Player1.setHealth(skirmish.getHealth(Player1));
             Monster.setHealth(skirmish.getHealth(Monster));
             if (skirmish.battleState() != 0)
                 break;
         }

     }
}
