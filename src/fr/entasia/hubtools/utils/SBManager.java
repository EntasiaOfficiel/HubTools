package fr.entasia.hubtools.utils;

import fr.entasia.hubtools.Main;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SBManager {

	public HubPlayer hp;
	public Scoreboard scoreboard;
	public Objective objective;
	public String onlines = "";

	public SBManager(HubPlayer hp){
		this.hp = hp;
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("hub", "dummy");
	}

	public void clear(){
		scoreboard.getEntries().forEach(a -> scoreboard.resetScores(a));
	}

	public void refresh(){
		hp.p.setScoreboard(scoreboard);
		clear();
		objective.setDisplayName("§bEnta§7sia");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.getScore("§b§m-----------").setScore(50);
		objective.getScore("§7Pseudo : §b"+hp.p.getDisplayName()).setScore(49);
		objective.getScore("§7Grade : §b"+Main.getPrefix(hp.p)).setScore(48);
		objective.getScore(" ").setScore(46);
		refreshOnlines();
		objective.getScore("§b§m----------- ").setScore(40);
		objective.getScore("§bplay.enta§7sia.fr").setScore(10);
	}

	public void refreshOnlines(){
		scoreboard.resetScores(onlines);
		if(Main.onlines==1) onlines = "§7Connecté : §b1";
		else onlines = "§7Connectés : §b"+ Main.onlines;
		objective.getScore(onlines).setScore(45);
	}
}
