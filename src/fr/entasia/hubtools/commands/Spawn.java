package fr.entasia.hubtools.commands;

import fr.entasia.hubtools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Main.tpLobby((Player) sender);
		return true;
	}
}