package fr.entasia.hubtools.listeners;

import fr.entasia.hubtools.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.util.Vector;

import static fr.entasia.hubtools.Main.buildToggle;

public class Protection implements Listener {

	@EventHandler
	public static void onPlace(BlockPlaceEvent e) {
		if (!buildToggle.contains(e.getPlayer().getName())) e.setCancelled(true);
	}

	@EventHandler
	public static void onBreak(BlockBreakEvent e) {
		if(!buildToggle.contains(e.getPlayer().getName()))e.setCancelled(true);
	}

	@EventHandler
	public static void onDrop(PlayerDropItemEvent e) {
		if(!buildToggle.contains(e.getPlayer().getName()))e.setCancelled(true);
	}

	@EventHandler
	public static void damage(EntityDamageEvent e) {
		e.setCancelled(true);
		if(e.getEntity() instanceof Player){
			if (e.getCause() == EntityDamageEvent.DamageCause.VOID) Main.tpLobby((Player) e.getEntity());
		}else e.getEntity().remove();
	}

	@EventHandler
	public static void damage2(EntityDamageByEntityEvent e){
		e.setCancelled(true);
		if(e.getDamager() instanceof Player){
			Vector v = e.getEntity().getVelocity();
			v.add(e.getDamager().getLocation().getDirection().setY(0).divide(new Vector(5, 5, 5)));

			if(v.getX()>MAX)v.setX(MAX);
			else if(v.getX()<-MAX)v.setX(-MAX);

			if(v.getZ()>MAX)v.setZ(MAX);
			else if(v.getZ()<-MAX)v.setZ(-MAX);
			e.getEntity().setVelocity(v);
		}
	}

	private static final int MAX = 2;

	@EventHandler
	public static void hunger(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}

	@EventHandler
	public static void explosion(ExplosionPrimeEvent e){
		e.setCancelled(true);
	}
}