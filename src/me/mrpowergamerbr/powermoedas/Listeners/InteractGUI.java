package me.mrpowergamerbr.powermoedas.Listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.mrpowergamerbr.powermoedas.Utils.DataUtils;
import me.mrpowergamerbr.powermoedas.Utils.PowerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractGUI extends PowerUtils implements Listener {
	static DataUtils userdata = DataUtils.getInstance();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent evento) {
		String guiNome = ChatColor.stripColor(PowerUtils.getConfig().getString("Loja.Geral.NomeDaGUI").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
		if (ChatColor.stripColor(evento.getInventory().getName()).contains(guiNome)) {
		Player player = (Player) evento.getWhoClicked();
		evento.setCancelled(true);
		
		if (evento.getCurrentItem() == null || evento.getCurrentItem().getType() == Material.AIR ) {//|| //!evento.getCurrentItem().hasItemMeta()
			player.closeInventory();
			return;
		}
		
		//switch (evento.getCurrentItem().getType()) {
		//case REDSTONE_ORE:
		// Se ele tem uma "Tag" secreta no nome do item, quer dizer que ele é um item que está a venda
		player.playSound(player.getLocation(), Sound.valueOf(PowerUtils.getConfig().getString("Sons.ClickGUI")), 1.0F, 1.0F);
		//Utils.getConfig().getBoolean("Loja.Slot" + i + ".ItemVenda")
		int invSlot = evento.getSlot();
		if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".FecharGUI") == true) {
			player.closeInventory();
			return;
		}
		
		//ItemStack itemGive = new ItemStack (evento.getCurrentItem());
		ItemStack fillGUI = new ItemStack (Material.getMaterial(PowerUtils.getConfig().getString("Loja.Geral.EncherGUI")));
		fillGUI.setAmount(0);
		String getItemA = evento.getCurrentItem() + "";
		String getItemB = fillGUI + "";
		if ((getItemA.contains(getItemB) //&& (Utils.getConfig().getString("Loja.Slot" + invSlot + ".Custo")) == null
				)) {
			player.closeInventory();
			return;
		}
		//if (evento.getCurrentItem().getItemMeta().getDisplayName().contains("§1§9§9§9") || evento.getCurrentItem().getItemMeta().getDisplayName().contains("§1§7§2")) {
		if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ItemVenda") == true || PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ComandosConsole") != false || PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".ComandosConsole") != null) {
			// Coloca uma Variável para a gente saber os Slots do Inventário Aberto
			//int invSlot = evento.getSlot();
					
	        int playerMoedas = userdata.getUserData().getInt("dindin." + player.getName());
	        
			if ((playerMoedas >= PowerUtils.getConfig().getInt("Loja.Slot" + invSlot + ".Custo")) || (PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".Custo") == null)) {
				// Detecta se o Inventário do Player está cheio ou não (Só funciona se for um Item para Comprar)
				if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ItemVenda") == true) {
		    		if (player.getInventory().firstEmpty() == -1) {
		    			player.sendMessage(PowerUtils.getConfig().getString("Mensagens.InvCheio").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
		    			return;
		    		}
				}
	    		//player.sendMessage(Utils.getConfig().getString("Mensagens.QuantiaAlta").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
		    	int calcs = userdata.getUserData().getInt("dindin." + player.getName()) + -PowerUtils.getConfig().getInt("Loja.Slot" + invSlot + ".Custo");
		    	String calcst = calcs + "";
				userdata.getUserData().set("dindin." + player.getName(), calcs);
				if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ItemVenda") == true) {
					ItemStack itemGive = new ItemStack (evento.getCurrentItem());
					if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ManterNome") == false) {
						ItemMeta itemMeta = itemGive.getItemMeta();
						itemMeta.setDisplayName((""));
						itemGive.setItemMeta(itemMeta);
					}
					if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ManterLore") == false) {
						ItemMeta itemMeta = itemGive.getItemMeta();
						itemMeta.setLore(new ArrayList<String>());
						itemGive.setItemMeta(itemMeta);
					}
					if (PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".VendaNome") != null) {
						ItemMeta itemMeta = itemGive.getItemMeta();
						itemMeta.setDisplayName((PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".VendaNome").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§")));
						itemGive.setItemMeta(itemMeta);
					}
					if (PowerUtils.getConfig().getStringList("Loja.Slot" + invSlot + ".VendaLore") != null) {
						List<String> LoreColorido = PowerUtils.getConfig().getStringList("Loja.Slot" + invSlot + ".VendaLore");
						List<String> LoreColoridoAlt = PowerUtils.getConfig().getStringList("Loja.Slot" + invSlot + ".VendaLore");
						LoreColoridoAlt.removeAll(LoreColorido);
				        for(String s : LoreColorido) {
				        	LoreColoridoAlt.add(s.replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
				        }
						ItemMeta itemMeta = itemGive.getItemMeta();
						itemMeta.setLore(LoreColoridoAlt);
						itemGive.setItemMeta(itemMeta);
					}
					player.getInventory().addItem(itemGive);
				}
				if (PowerUtils.getConfig().getBoolean("Loja.Slot" + invSlot + ".ComandosConsole") != false || PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".ComandosConsole") != null) {
					@SuppressWarnings("unchecked")
					List<String> comandosLista = (List<String>) PowerUtils.getConfig().getList("Loja.Slot" + invSlot + ".ComandosConsole");
			        for(String s : comandosLista) {
			        	Bukkit.dispatchCommand(getServer().getConsoleSender(), s.replace("@player", player.getName()));
			        }
				}
				if (PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".Nome") != null) {
					player.sendMessage(PowerUtils.getConfig().getString("Mensagens.ComprouItem").replace("@item", PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".Nome")).replace("@moedas", calcst).replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
				}
				else
				{
					player.sendMessage(PowerUtils.getConfig().getString("Mensagens.ComprouItem").replace("@item", "Item").replace("@moedas", calcst).replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
				}
				userdata.saveUserData();
				player.closeInventory();
				if (PowerUtils.getConfig().getBoolean("AnuncioCompra") == true) {
					if (PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".Nome") != null) {
						getServer().broadcastMessage(PowerUtils.getConfig().getString("Mensagens.Anuncio").replace("@player", player.getName()).replace("@item", PowerUtils.getConfig().getString("Loja.Slot" + invSlot + ".Nome")).replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
					}
					else
					{
						getServer().broadcastMessage(PowerUtils.getConfig().getString("Mensagens.Anuncio").replace("@player", player.getName()).replace("@item", "Item").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
					}
				}
				if (PowerUtils.getConfig().getBoolean("LogCompras") == true) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					logToFile("(" + dateFormat.format(date) + ")" + " " + player.getName() + " comprou o item no Slot " + invSlot + " que custava " + PowerUtils.getConfig().getInt("Loja.Slot" + invSlot + ".Custo"));
				}
				player.playSound(player.getLocation(), Sound.valueOf(PowerUtils.getConfig().getString("Sons.ComprarGUI")), 1.0F, 1.0F);
				return;
	    	}
	    	else {
	    		player.sendMessage(PowerUtils.getConfig().getString("Mensagens.QuantiaAlta").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
	    		player.closeInventory();
	    		return;
	    	}
		}
	}
}
}
