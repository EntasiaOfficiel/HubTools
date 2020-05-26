package fr.entasia.hubtools.listeners;

import fr.entasia.hubtools.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import static fr.entasia.hubtools.Main.buildToggle;

public class ProtectionListeners implements Listener {

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
		if (!(e.getEntity() instanceof Player)) e.getEntity().remove();
		else if (e.getCause() == EntityDamageEvent.DamageCause.VOID) Main.tpLobby((Player) e.getEntity());
	}

	@EventHandler
	public static void hunger(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}

	@EventHandler
	public static void hunger(ExplosionPrimeEvent e){
		e.setCancelled(true);
	}
}