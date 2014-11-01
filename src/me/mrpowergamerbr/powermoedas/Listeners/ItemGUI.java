package me.mrpowergamerbr.powermoedas.Listeners;

import me.mrpowergamerbr.powermoedas.Utils.GUIUtils;
import me.mrpowergamerbr.powermoedas.Utils.PowerUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemGUI extends PowerUtils implements Listener {
	// Negócio de abrir com um Item
	@EventHandler(priority=EventPriority.HIGH)
	public void itemMaoGUI(PlayerInteractEvent evento){
			Player player = evento.getPlayer();
			if(player.getItemInHand().getType() == (Material.getMaterial((getConfig().getString("Item.ItemAbrirGUI"))))) {
				GUIUtils.abrirGUI(player);
		}
	}
}
