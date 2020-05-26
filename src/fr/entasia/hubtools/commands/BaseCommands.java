package fr.entasia.hubtools.commands;

import fr.entasia.apis.socket.SocketClient;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommands {
	public static class Creatif implements CommandExecutor {
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
			SocketClient.sendData("BungeeCord send "+sender.getName()+" Creatif");
			return true;
		}
	}

	public static class Skyblock implements CommandExecutor {
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
			SocketClient.sendData("BungeeCord send "+sender.getName()+" Skyblock");
			return true;
		}
	}
	public static class EntaGames implements CommandExecutor {
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
			SocketClient.sendData("BungeeCord send "+sender.getName()+" EntaGames");
			return true;
		}
	}
}