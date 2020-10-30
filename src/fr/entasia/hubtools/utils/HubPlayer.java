package fr.entasia.hubtools.utils;

import org.bukkit.entity.Player;

public class HubPlayer {

	public Player p;
	public SBManager sb;

	public HubPlayer(Player p){
		this.p = p;
		this.sb = new SBManager(this);
	}
}
