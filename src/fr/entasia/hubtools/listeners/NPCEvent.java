package fr.entasia.hubtools.listeners;

import fr.entasia.apis.socket.SocketClient;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_15_R1.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCEvent implements Listener {


    @EventHandler
    public void npcRightClick(NPCRightClickEvent e){
        Player p = e.getClicker();
        NPC npc = e.getNPC();
        if(npc.getName().contains("Skyblock")){
            SocketClient.sendData("BungeeCord send "+p.getName()+" Skyblock");
        }else if(npc.getName().contains("Entagames")){
            SocketClient.sendData("BungeeCord send "+p.getName()+" Entagames");
        }else if(npc.getName().contains("Creatif") || npc.getName().contains("Créatif")){
            SocketClient.sendData("BungeeCord send "+p.getName()+" Creatif");
        }
    }

    @EventHandler
    public void npcLeftClick(NPCLeftClickEvent e){
        e.setCancelled(true);
    }
}
