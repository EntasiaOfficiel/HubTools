package fr.entasia.hubtools.commands;

import fr.entasia.hubtools.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubTools implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		Player p = (Player) sender;
		if(p.hasPermission("plugin.hubtools")){
			if(arg.length >= 1){
				if(arg[0].equalsIgnoreCase("setspawn")){
					Location a = p.getLocation().getBlock().getLocation();
					Main.main.getConfig().set("spawn.x", a.getX());
					Main.main.getConfig().set("spawn.y", a.getY());
					Main.main.getConfig().set("spawn.z", a.getZ());
					Main.spawn.setX(a.getX());
					Main.spawn.setY(a.getY());
					Main.spawn.setZ(a.getZ());
					Main.main.saveConfig();
					p.sendMessage("§bSpawn défini !");
				}
			}else p.sendMessage("§cArgument incorrect");
		}else p.sendMessage("&bEnta&7sia &8» &cTu n'as pas accès à cette commande !");
		return true;
	}
}