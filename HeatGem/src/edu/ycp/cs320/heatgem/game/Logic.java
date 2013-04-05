package edu.ycp.cs320.heatgem.game;

public class Logic {
	 public void doBattle(Player Player1, Player Player2)
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
             Player1.setHealth(skirmish.getHealth1());
             Monster.setHealth(skirmish.getHealth2());
             if (skirmish.battleState() != 0)
                 break;
         }

     }
}
