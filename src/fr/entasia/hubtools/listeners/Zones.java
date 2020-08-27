package fr.entasia.hubtools.listeners;

import fr.entasia.apis.regionManager.events.RegionEnterEvent;
import fr.entasia.hubtools.Main;
import fr.entasia.hubtools.utils.Coords;
import fr.entasia.hubtools.utils.Regions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Zones implements Listener {

	@EventHandler
	public void a(RegionEnterEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld() != Main.world) return;
		if (e.getRegion() == Regions.PLANET.region) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 7));
		}else if(e.getRegion() == Regions.TPPLANET.region) {
			Coords.TPPLANET.teleport(p);
		}else if(e.getRegion() == Regions.TPWATER.region) {
			Coords.WATER4.teleport(p);
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 7));
		}else if(e.getRegion() == Regions.WATER.region) {
			Coords.WATER3.teleport(p);
		}else if(e.getRegion() == Regions.TPDRAGON.region) {
			Coords.DRAGON.teleport(p);
		}else if(e.getRegion() == Regions.DRAGON.region) {
			Coords.DRAGONBALCON.teleport(p);
		}else if(e.getRegion() == Regions.WATERGATE.region) {
			Coords.BLOCK.teleport(p);
		}else if(e.getRegion() == Regions.GATEWATER.region) {
			Coords.BLOCK1.teleport(p);
		}else if(e.getRegion() == Regions.CHESTOUT.region) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 35, 7));
		}else if(e.getRegion() == Regions.CHESTOUT2.region) {
			Coords.CHEST2.teleport(p);
		}else if(e.getRegion() == Regions.GROTTE_OUT.region){
			Coords.TPTROU.teleport(p);
			p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
			p.setGliding(true);
		}else if(e.getRegion() == Regions.GROTTE_IN.region) {
			Coords.TROUTROU.teleport(p);
			Vector v = new Vector(0, 2.4, 0);
			p.setVelocity(v);
			new BukkitRunnable() {
				@Override
				public void run() {
					Vector v = p.getVelocity();
					v.setZ(v.getZ()+1.6);
					p.setVelocity(v);
				}
			}.runTaskLater(Main.main, 22);
		}
	}

	@EventHandler
	public void a(EntityPortalEnterEvent e){
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getWorld() == Main.world) {
				Coords.PORTAL.teleport(p);
			}
		}
	}

	private static boolean isDoor(Location loc, Location door){
		return loc.equals(door)||loc.equals(door.clone().add(0, 1, 0));
	}

	@EventHandler
	public void a(PlayerInteractEvent e){
		if(e.hasBlock()){
			Player p = e.getPlayer();
			if(isDoor(e.getClickedBlock().getLocation(), Coords.DOOR1.loc)){
				Coords.DOOR2.teleport(p);
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.DOOR2.loc)){
				Coords.DOOR1.teleport(p);
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.DOORS2.loc)){
				Coords.DOORS1.teleport(p, -90);
			}else if(isDoor(e.getClickedBlock().getLocation(), Coords.DOORS1.loc)){
				Coords.DOORS2.teleport(p, 90);
			}else if(e.getClickedBlock().getLocation().equals(Coords.CHEST2.loc)) {
				Coords.CHEST1.teleport(p, 180);
			}else return;
			e.setCancelled(true);
		}
	}

}
