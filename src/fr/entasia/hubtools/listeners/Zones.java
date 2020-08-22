package fr.entasia.hubtools.listeners;

import fr.entasia.apis.regionManager.events.RegionEnterEvent;
import fr.entasia.hubtools.Main;
import fr.entasia.hubtools.utils.Coords;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Zones implements Listener {

	@EventHandler
	public void a(RegionEnterEvent e) {
		if (e.getPlayer().getWorld() != Main.world) return;
		for (Coords c : Coords.values()) {
			if (c.trigger == e.getRegion()) {
				c.teleport(e.getPlayer());
				return;
			}
		}
	}

	private static boolean isDoor(Location loc, Location door){
		return loc.equals(door)||loc.equals(door.clone().add(0, 1, 0));
	}

	@EventHandler
	public void a(PlayerInteractEvent e){
		if(e.hasBlock()){
			if(isDoor(e.getClickedBlock().getLocation(), Coords.DOOR1.loc)){
				Coords.DOOR2.teleport(e.getPlayer());
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.DOOR2.loc)){
				Coords.DOOR1.teleport(e.getPlayer());
			}else if(e.getClickedBlock().getLocation().equals(Coords.GOLD1.loc)){
				Coords.JAIL_GO.teleport(e.getPlayer());
			}else if(e.getClickedBlock().getLocation().equals(Coords.GOLD2.loc)){
				Coords.GOLD1.teleport(e.getPlayer());
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.DOORS2.loc)){
				Coords.DOORS1.teleport(e.getPlayer());
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.DOORS1.loc)){
				Coords.DOORS2.teleport(e.getPlayer());
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.BLOCK1.loc)){
				Coords.BLOCK2.teleport(e.getPlayer());
			}else if(e.getClickedBlock().getLocation().equals(Coords.BLOCK2.loc)){
				Coords.BLOCK1.teleport(e.getPlayer());
			}else if(e.getClickedBlock().getLocation().equals(Coords.CHEST2.loc)){
				Coords.CHEST1.teleport(e.getPlayer());
			}else if(e.getClickedBlock().getLocation().equals(Coords.CHEST3.loc)){
				Coords.CHEST2.teleport(e.getPlayer());
			}
		}
	}

}
