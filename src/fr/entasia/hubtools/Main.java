package fr.entasia.hubtools;

import fr.entasia.apis.menus.MenuAPI;
import fr.entasia.apis.socket.SocketClient;
import fr.entasia.apis.socket.SocketEvent;
import fr.entasia.hubtools.commands.*;
import fr.entasia.hubtools.listeners.Other;
import fr.entasia.hubtools.listeners.Protection;
import fr.entasia.hubtools.utils.EntasiaServer;
import fr.entasia.hubtools.utils.HubPlayer;
import fr.entasia.hubtools.utils.InvsManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

	public static Main main;
	public static HashMap<String, HubPlayer> playerCache = new HashMap<>();
	public static Location spawn;
	public static ArrayList<String> buildToggle = new ArrayList<>();
	public static Chat vaultChat;
	public static World world;
	public static int onlines;


	@Override
	public void onEnable() {
		try{
			main = this;
			world = Bukkit.getWorlds().get(0);
			try{
				vaultChat = getServer().getServicesManager().getRegistration(Chat.class).getProvider();
			}catch(NullPointerException | NoClassDefFoundError ignore){}

			File[] files  = new File(world.getName()+"/playerdata").listFiles();
			if(files!=null){
				for(File lf : files){
					lf.delete();
				}
			}

			saveDefaultConfig();
			loadConfig();

			new BukkitRunnable() {
				@Override
				public void run() {
					for(HubPlayer hp : playerCache.values()){
						hp.sb.refresh();
					}
				}
			}.runTaskTimer(this, 0, 26*60*3);

			getServer().getPluginManager().registerEvents(new Other(), this);
			getServer().getPluginManager().registerEvents(new Protection(), this);

			getCommand("spawn").setExecutor(new Spawn());
			getCommand("menu").setExecutor(new Menu());
			getCommand("hubtools").setExecutor(new HubTools());
			getCommand("buildtoggle").setExecutor(new BuildToggle());

			getCommand("skyblock").setExecutor(new BaseCommands.Skyblock());
			getCommand("entagames").setExecutor(new BaseCommands.EntaGames());
			getCommand("creatif").setExecutor(new BaseCommands.Creatif());

			SocketClient.addListener(new SocketEvent("onlines") {
				@Override
				public void onEvent(String[] data) {
					try{
						EntasiaServer s = EntasiaServer.valueOf(data[0]);
						s.onlines = Integer.parseInt(data[1]);
						for(MenuAPI.InvInst i : InvsManager.gMenu.instances){
							i.inv.setItem(s.iconloc, s.getIcon());
						}
					} catch(IllegalArgumentException e){
						getLogger().warning("Update de joueurs pour un serveur non existant : "+data[0]);
					}
				}
			});

			SocketClient.addListener(new SocketEvent("players") {
				@Override
				public void onEvent(String[] data) {
					onlines = data.length;
					for(HubPlayer hp : playerCache.values()){
						hp.sb.refreshOnlines();
					}
				}
			});

			Bukkit.getLogger().info("Plugin HubTools activé !");
		} catch (Throwable e) {
			e.printStackTrace();
			getLogger().severe("Erreur lors du chargement du plugin ! ARRET DU SERVEUR");
			getServer().shutdown();
		}
	}


	private static void loadConfig() {
		main.reloadConfig();
		ConfigurationSection b = main.getConfig().getConfigurationSection("spawn");
		spawn = new Location(Bukkit.getServer().getWorlds().get(0), b.getInt("x")+0.5, b.getInt("y")+0.5, b.getInt("z")+0.5);
	}


	public static void tpLobby(Player p) {
		tpLobby(playerCache.get(p.getName()));
	}
	public static void tpLobby(HubPlayer hp) {
		hp.p.teleport(spawn);
		hp.p.getInventory().clear();
		hp.p.getActivePotionEffects().clear();
		hp.p.setGameMode(GameMode.SURVIVAL);

		ItemStack item = new ItemStack(Material.ENDER_CHEST);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§7Menu d'§bEnta§7sia");
		item.setItemMeta(meta);
		hp.p.getInventory().setItem(4, item);

		item = new ItemStack(Material.COMMAND);
		meta = item.getItemMeta();
		meta.setDisplayName("§7Paramètres");
		item.setItemMeta(meta);
		hp.p.getInventory().setItem(7, item);
		hp.sb.refresh();
	}

	public static String getPrefix(Player p){
		if(vaultChat==null)return "";
		else return Main.vaultChat.getPlayerPrefix(p).replace("&", "§");
	}
}