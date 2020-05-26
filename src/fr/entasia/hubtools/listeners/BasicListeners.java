package fr.entasia.hubtools.listeners;

import fr.entasia.hubtools.Main;
import fr.entasia.hubtools.utils.HubPlayer;
import fr.entasia.hubtools.utils.InvsManager;
import fr.entasia.hubtools.utils.SBManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import static fr.entasia.hubtools.Main.buildToggle;


public class BasicListeners implements Listener {


	@EventHandler
	public static void onInvClick(InventoryClickEvent e) {
		if(!buildToggle.contains(e.getWhoClicked().getName()))e.setCancelled(true);
	}

	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		HubPlayer hp = Main.playerCache.get(p.getName());
		if(hp==null){
			hp = new HubPlayer(p);
			Main.playerCache.put(p.getName(), hp);
		}else hp.p = p;
		hp.sb = new SBManager(hp);
		hp.sb.refresh();
		Main.tpLobby(e.getPlayer());
		e.getPlayer().setHealth(20);
		e.getPlayer().setFoodLevel(20);
	}

	@EventHandler
	public static void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		buildToggle.remove(p.getName());
		Main.playerCache.remove(p.getName());
	}

	@EventHandler
	public static void onInteract(PlayerInteractEvent e) {
		if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.LEFT_CLICK_BLOCK||e.getAction()==Action.RIGHT_CLICK_BLOCK||e.getAction()==Action.RIGHT_CLICK_AIR){
			if (e.getItem() != null && e.getItem().getType() == Material.ENDER_CHEST && e.getItem().hasItemMeta() &&
					e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equals("§7Menu d'§bEnta§7sia")) {
				InvsManager.gMenuOpen(e.getPlayer());
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(e.toWeatherState()); // en gros si c'est true c'est la pluie
	}

}
