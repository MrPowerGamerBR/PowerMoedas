package me.mrpowergamerbr.powermoedas.Listeners;

import me.mrpowergamerbr.powermoedas.Utils.GUIUtils;
import me.mrpowergamerbr.powermoedas.Utils.PowerUtils;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlacaGUI extends PowerUtils implements Listener {
	@EventHandler
	  public void placa(SignChangeEvent evento)
	  {
	    String linha1 = evento.getLine(0);
	    if (linha1.contains("[PowerMoedas]")) {
	    	Player player = (Player) evento.getPlayer();
	    	if (player.hasPermission("PowerMoedas.CriarPlaca")) {
	    		evento.setLine(0, getConfig().getString("Placa.PlacaTag").replace("&", "§"));
	    	}
	    	else {
	    		evento.getBlock().breakNaturally();
	    		player.sendMessage(PowerUtils.getConfig().getString("Mensagens.SemPerm").replace("&", "§"));
	    		
	    	}
	    }
	 }
	
	// Placa da GUI (Interact)
	@EventHandler
	public void abrirPlacaGUI(PlayerInteractEvent event)
    {
		Action acao = event.getAction();
		if (acao == Action.RIGHT_CLICK_BLOCK)
	    {
			Player player = event.getPlayer();
			Material tipo = event.getClickedBlock().getType();
			if ((tipo == Material.SIGN_POST) || (tipo == Material.WALL_SIGN))
			{
				Sign sign = (Sign)event.getClickedBlock().getState();
				String linha1 = sign.getLine(0);
				if (linha1.equalsIgnoreCase(PowerUtils.getConfig().getString("Placa.PlacaTag").replace("&", "§"))) {
					GUIUtils.abrirGUI(player);
				}
			}
	    }
    }
}
