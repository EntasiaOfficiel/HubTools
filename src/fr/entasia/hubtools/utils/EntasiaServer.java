package fr.entasia.hubtools.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EntasiaServer {
	EntaGames("§c", 32, new ItemStack(Material.TNT), "§cServeur regroupant tout les", "§cmini-jeux d'Entasia !"),
	Skyblock("§a", 12, new ItemStack(Material.SAPLING), "§aLe serveur Skyblock !" , "§aQuoi de plus normal qu'un serveur Skyblock ?", "§2*rire*"),
	Creatif("§3", 30, new ItemStack(Material.BRICK),"§3Amusez-vous, construisez !");

	private ItemStack icon;
	public List<String> desc;
	public int iconloc;
	public int onlines;

	EntasiaServer(String color, int iconloc, ItemStack icon, String... desc){
		this.iconloc = iconloc;
		this.icon = icon;
		ItemMeta meta = icon.getItemMeta();
		meta.setDisplayName(color+name());
		this.icon.setItemMeta(meta);
		this.desc = new ArrayList<>(Arrays.asList(desc));
		this.desc.add(" ");
	}

	public ItemStack getIcon(){
		ArrayList<String> l = new ArrayList<>(desc);
		if(onlines==1) l.add("§6Connecté : 1 joueur");
		else l.add("§6Connecté : "+onlines+" joueurs");

		ItemStack item = icon.clone();
		ItemMeta meta = item.getItemMeta();
		meta.setLore(l);
		item.setItemMeta(meta);
		return item;
	}
}
