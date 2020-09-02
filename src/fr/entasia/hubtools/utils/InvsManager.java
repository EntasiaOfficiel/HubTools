package fr.entasia.hubtools.utils;

import fr.entasia.apis.menus.MenuClickEvent;
import fr.entasia.apis.menus.MenuCreator;
import fr.entasia.apis.other.ChatComponent;
import fr.entasia.apis.other.ItemBuilder;
import fr.entasia.apis.socket.SocketClient;
import fr.entasia.apis.utils.ItemUtils;
import fr.entasia.hubtools.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvsManager {



	public static MenuCreator gMenu = new MenuCreator(null, null) {

		@Override
		public void onMenuClick(MenuClickEvent e) {
			switch(e.item.getType()){
				case TNT:
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" EntaGames");
					break;
				case OAK_SAPLING:
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" skyblock");
					break;
				case BRICKS:
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" Creatif");
					break;
				case COMMAND_BLOCK:
					int a = e.slot-46; // gaffe à ca
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" dev"+a);
					e.player.sendMessage("§7Tu as téléporté au serveur de dev "+a+" !");
					break;
				case PAPER:
					ChatComponent m = new ChatComponent("§6Notre site Web : ");
					ChatComponent site = new ChatComponent("§bhttps://enta§7sia.fr");
					site.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Clique pour aller sur le site !").create()));
					site.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://entasia.fr"));
					e.player.spigot().sendMessage(ChatComponent.create(m, site, new ChatComponent(" §6 !")));
					break;
				default:
					e.player.sendMessage("§cCette option n'est pas disponible pour le moment !");
			}
			e.player.closeInventory();
		}
	};

	public static void gMenuOpen(Player p){
		Inventory inv = gMenu.createInv(6, "§7Menu d'§bEnta§7sia");

		ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		for(int i : new int[]{2,10,19,28,36,45,8,16,25,34,42,51})inv.setItem(i, item);

		for(EntasiaServer s : EntasiaServer.values()){
			inv.setItem(s.iconloc, s.getIcon());
		}

		item = new ItemBuilder(Material.PAPER).name("§7Site Web !").lore("§bhttps://enta§7sia.fr").build();
		inv.setItem(18, item);

		item = new ItemBuilder(Material.PLAYER_HEAD).damage(3).name("§dStatistiques !").lore("§9Non disponible pour le moment !").build();
		ItemUtils.placeSkullAsync(inv, 27, item, p, Main.main);

		item = new ItemBuilder(Material.COMPARATOR).name("§8Paramètrages").lore("§9Non disponible pour le moment !").build();
		inv.setItem(53, item);

		if(p.hasPermission( "entasia.dev")){
			ItemBuilder builder = new ItemBuilder(Material.COMMAND_BLOCK).name("§8Serveur de développement §11");
			inv.setItem(47, builder.build());
			builder.name("§8Serveur de développement §12");
			inv.setItem(48, builder.build());
			builder.name("§8Serveur de développement §13");
			inv.setItem(49, builder.build());
		}

		p.openInventory(inv);
	}
}
