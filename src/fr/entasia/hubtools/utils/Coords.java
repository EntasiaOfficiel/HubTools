package fr.entasia.hubtools.utils;

import fr.entasia.hubtools.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum Coords {
	TPPLANET(2, 115, -0),
	TPTROU(2, 200, -0),
	TROUTROU(-657, 25, 1160),
	PORTAL(-956, 57, 1202),
	WATER3(396, 48, 1136),
	WATER4(131, 145, -32),
	DRAGON(468, 31, 1152),
	DRAGONBALCON(38, 155, 40),
	DOOR1(60, 132, 22),
	DOOR2(-159, 138, 3),
	DOORS1(-402, 74, 1167),
	DOORS2(4, 99, -1),
	BLOCK(41, 120, -40),
	BLOCK1(977, 20, 1076),
	CHEST1(-185, 53, 1143),
	CHEST2(40, 124, -51),
	;



	public Location loc;

	Coords(int x, int y, int z){
		loc = new Location(Main.world, x, y, z);
	}

	public void teleport(Player p){
		teleport(p, 0);
	}

	public void teleport(Player p, float yaw){
		Location t = loc.clone().add(0.5, 0.5, 0.5);
		t.setYaw(p.getLocation().getYaw()+yaw);
		t.setPitch(p.getLocation().getPitch());
		p.teleport(t);
	}
}
