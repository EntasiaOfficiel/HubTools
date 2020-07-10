package fr.entasia.hubtools.utils;

import fr.entasia.apis.other.ChatComponent;
import fr.entasia.apis.menus.MenuClickEvent;
import fr.entasia.apis.menus.MenuCreator;
import fr.entasia.apis.socket.SocketClient;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;

public class InvsManager {



	public static MenuCreator gMenu = new MenuCreator(null, null) {

		@Override
		public void onMenuClick(MenuClickEvent e) {
			switch(e.item.getType()){
				case TNT:
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" EntaGames");
					break;
				case SAPLING:
					if(e.player.hasPermission("staff.sbaccess"))
						SocketClient.sendData("BungeeCord send "+e.player.getName()+" skyblock");
					else
						e.player.sendMessage("§cLa béta est actuellement réservée aux staffs !");
					break;
				case BRICK:
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" Creatif");
					break;
				case COMMAND:
					int a = e.slot-46;
					SocketClient.sendData("BungeeCord send "+e.player.getName()+" dev"+a);
					e.player.sendMessage("§7Tu as téléporté au serveur de dev "+a+" !");
					break;
				case PAPER:
					ChatComponent m = new ChatComponent("§6Notre site Web : ");
					ChatComponent site = new ChatComponent("§bhttps://enta§7sia.fr");
					site.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Clique pour aller sur le site !").create()));
					site.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://entasia.fr"));
					e.player.sendMessage(ChatComponent.create(m, site, new ChatComponent(" §6 !")));
					break;
				default:
					e.player.sendMessage("§cCette option n'est pas disponible pour le moment !");
			}
			e.player.closeInventory();
		}
	};

	public static void gMenuOpen(Player p){
		Inventory inv = gMenu.createInv(6, "§7Menu d'§bEnta§7sia");

		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
		for(int i : new int[]{2,10,19,28,36,45,8,16,25,34,42,51})inv.setItem(i, item);
//
		String[] b = new String[]{"EntaGames", "Skyblock", "Creatif"};
		ArrayList<String> l;
		ItemMeta meta;
		for(EntasiaServer s : EntasiaServer.values()){
			inv.setItem(s.iconloc, s.getIcon());
		}

		item = new ItemStack(Material.PAPER);
		meta = item.getItemMeta();
		meta.setDisplayName("§7Site Web !");
		meta.setLore(Collections.singletonList("§bhttps://enta§7sia.fr"));
		item.setItemMeta(meta);
		inv.setItem(18, item);

		item = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta sm = (SkullMeta) item.getItemMeta();
		sm.setDisplayName("§dStatistiques !");
		sm.setOwner(p.getName());
		sm.setLore(Collections.singletonList("§9Non disponible pour le moment !"));
		item.setItemMeta(sm);
		inv.setItem(27, item);

		item = new ItemStack(Material.REDSTONE_COMPARATOR);
		meta = item.getItemMeta();
		meta.setDisplayName("§8Paramètrages");
		meta.setLore(Collections.singletonList("§9Non disponible pour le moment !"));
		item.setItemMeta(meta);
		inv.setItem(53, item);


		item = new ItemStack(Material.GOLD_INGOT);
		meta = item.getItemMeta();
		meta.setDisplayName("§eBoutique");
		sm.setLore(Collections.singletonList("§9Non disponible pour le moment !"));
		item.setItemMeta(meta);
		inv.setItem(44, item);

		if(p.hasPermission( "entasia.dev")){
			item = new ItemStack(Material.COMMAND);
			meta = item.getItemMeta();
			meta.setDisplayName("§8Serveur de développement §11");
			item.setItemMeta(meta);
			inv.setItem(47, item);
			meta.setDisplayName("§8Serveur de développement §12");
			item.setItemMeta(meta);
			inv.setItem(48, item);
			meta.setDisplayName("§8Serveur de développement §13");
			item.setItemMeta(meta);
			inv.setItem(49, item);
		}

		p.openInventory(inv);
	}
}
