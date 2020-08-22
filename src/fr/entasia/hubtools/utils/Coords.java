package fr.entasia.hubtools.utils;

import fr.entasia.apis.regionManager.api.Region;
import fr.entasia.apis.regionManager.api.RegionManager;
import fr.entasia.hubtools.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum Coords {
	CAVE_GO(5, 5, 5,  "cave_go"),
	DOOR1(60, 132, 22),
	DOOR2(-159, 138, 3),
	GOLD1(-147, 113, 17),
	GOLD2(395, 48, 1136),
	JAIL_GO(397, 48, 1137),
	DOORS1(-402, 74, 1167),
	DOORS2(4, 99, -1),
	BLOCK1(29, 127,37),
	BLOCK2(977, 20, 1076),
	CHEST1(-185, 53, 1143),
	CHEST2(40, 124, -51),
	CHEST3(-245, 85, 1204),
	;


	public Location loc;
	public Region trigger;
	
	Coords(int x, int y, int z){
		this(x, y, z, null);
	}
	
	Coords(int x, int y, int z, String reg) {
		loc = new Location(Main.world, x, y, z);
		trigger = RegionManager.getRegionByName(reg);
	}

	public void teleport(Player p){
		Location t = loc.clone().add(0.5, 0.5, 0.5);
		t.setYaw(p.getLocation().getYaw());
		t.setPitch(p.getLocation().getPitch());
		p.teleport(t);
	}
}
