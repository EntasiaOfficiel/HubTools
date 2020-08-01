package fr.entasia.hubtools.listeners;

import fr.entasia.apis.utils.ItemUtils;
import fr.entasia.apis.utils.OtherUtils;
import fr.entasia.apis.utils.VectorUtils;
import fr.entasia.cosmetiques.utils.CosmAPI;
import fr.entasia.hubtools.Main;
import fr.entasia.hubtools.utils.HubPlayer;
import fr.entasia.hubtools.utils.InvsManager;
import fr.entasia.hubtools.utils.SBManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.List;

import static fr.entasia.hubtools.Main.buildToggle;


public class Other implements Listener {


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
		if(e.getAction()==Action.PHYSICAL){
			if(e.getClickedBlock().getType()==Material.GOLD_PLATE){

				e.getPlayer().getInventory().setChestplate(new ItemStack(Material.ELYTRA));
				e.getPlayer().getInventory().setItem(2, new ItemStack(Material.FIREWORK, 8));
				e.getPlayer().setMetadata("gliding", new FixedMetadataValue(Main.main, true));
				new BukkitRunnable() {
					public void run() {
						e.getPlayer().setVelocity(new Vector(0, 0.9, 0));
					}
				}.runTask(Main.main);
				new BukkitRunnable() {
					public void run() {
						if(e.getPlayer().isFlying())return;
						e.getPlayer().setGliding(true);
						Vector v = e.getPlayer().getLocation().getDirection().setY(0);
						v.normalize();
						e.getPlayer().setVelocity(v);
					}
				}.runTaskLater(Main.main, 14);
			}
		}else{
			if(e.getHand()==EquipmentSlot.HAND&&e.hasItem()){
				if(e.getItem().getType()==Material.ENDER_CHEST) {
					if (ItemUtils.hasName(e.getItem(), "§7Menu d'§bEnta§7sia")) {
						e.setCancelled(true);
						InvsManager.gMenuOpen(e.getPlayer());
					}
				}else if(e.getItem().getType()==Material.COMMAND){
					if(e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName()!=null & e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Cosmétiques")){
						CosmAPI.openCosmMenu(e.getPlayer());
					}

				}else if(e.getPlayer().getInventory().getItemInMainHand().getType()==Material.FIREWORK){
					e.setCancelled(true);
					if(e.getAction()==Action.RIGHT_CLICK_AIR){
						List<MetadataValue> list = e.getPlayer().getMetadata("gliding");
						if(list.size()==0)e.getPlayer().getInventory().setItemInMainHand(null);
						else if(e.getPlayer().isGliding()){
							int a = e.getItem().getAmount();
							if(a==1)e.getPlayer().getInventory().setItemInMainHand(null);
							else e.getItem().setAmount(a-1);
							Vector v = e.getPlayer().getVelocity();
							v.add(e.getPlayer().getLocation().getDirection().multiply(2));
							VectorUtils.limitVector(v, 1.6);
							e.getPlayer().setVelocity(v);
							new BukkitRunnable() {
								Iterator<Integer> ite = OtherUtils.notes.iterator();
								int i=0;
								@Override
								public void run() {
									if(e.getPlayer().isGliding()){
										if(ite.hasNext()){
											Location loc = e.getPlayer().getLocation();
											loc.getWorld().spawnParticle(Particle.NOTE, loc, 0, ite.next()/24f, 0, 0);
											i++;
											loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_HARP, 1, i/(float)OtherUtils.notes.size()*2);
										}
									}else cancel();
								}
							}.runTaskTimer(Main.main, 0, 2);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public static void a(EntityToggleGlideEvent e) {
		if(e.getEntity() instanceof Player&&!e.isGliding()){
			Player p = (Player)e.getEntity();
			p.getInventory().setChestplate(null);
			p.getInventory().setItem(2, null);
			e.getEntity().removeMetadata("gliding", Main.main);
		}
	}

	@EventHandler
	public void a(WeatherChangeEvent e) {
		e.setCancelled(e.toWeatherState()); // en gros si c'est true c'est la pluie
	}

}
