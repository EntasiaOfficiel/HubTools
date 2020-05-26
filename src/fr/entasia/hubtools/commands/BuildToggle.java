package fr.entasia.hubtools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.entasia.hubtools.Main.buildToggle;

public class BuildToggle implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		if(sender.hasPermission("build.buildtoggle")){
			if(arg.length >= 1){
				if(arg[0].equalsIgnoreCase("list")) {
					sender.sendMessage("§7Liste : ");
					for(String i : buildToggle){
						sender.sendMessage("§7 - §r"+i);
					}
				}else if(arg[0].equalsIgnoreCase("clear")){
					buildToggle.clear();
					sender.sendMessage("§6BuildToggle : §eTu as clear la liste !");
				}else if(sender.hasPermission("build.buildtoggle.others")){
					Player p = Bukkit.getPlayer(arg[0]);
					if(p == null){
						sender.sendMessage("§4Erreur : §cCe joueur n'existe pas !");
					}else {
						if (buildToggle.contains(p.getName())) {
							buildToggle.remove(p.getName());
							sender.sendMessage("§6BuildToggle : §cDésactivé §6pour §e" + p.getName());
							p.sendMessage("§6BuildToggle : §cDésactivé §6par §e" + sender.getName());
						} else {
							buildToggle.add(p.getName());
							sender.sendMessage("§6BuildToggle : §aActivé §6pour §e" + p.getName());
							p.sendMessage("§6BuildToggle : §aActivé §6par §e" + sender.getName());
						}
					}
				}else sender.sendMessage("§4Erreur : §cTu ne peux pas modifier le BuildToggle des autres !");
			}else{
				if(buildToggle.contains(sender.getName())){
					buildToggle.remove(sender.getName());
					sender.sendMessage("§6BuildToggle : §cDésactivé");
				}else{
					buildToggle.add(sender.getName());
					sender.sendMessage("§6BuildToggle : §aActivé");
				}
			}

		}else sender.sendMessage("&cTu n'as pas accès à cette commande !");
		return true;
	}
}