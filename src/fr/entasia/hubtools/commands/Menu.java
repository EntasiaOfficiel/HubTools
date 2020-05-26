package fr.entasia.hubtools.commands;

import fr.entasia.hubtools.utils.InvsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Menu implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		InvsManager.gMenuOpen((Player) sender);
		return true;
	}
}