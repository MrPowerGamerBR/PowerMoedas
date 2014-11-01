package me.mrpowergamerbr.powermoedas.Utils;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIUtils extends PowerUtils{
	static DataUtils userdata = DataUtils.getInstance();
	@SuppressWarnings("deprecation")
	public static void abrirGUI(Player player) {
		int a = 9;
    	a = PowerUtils.getConfig().getInt("Loja.Geral.Slots");
    	if (!(a == (9) || (a == (18) || (a == (27) || (a == (36) || (a == (45) || (a == (54)))))))) {
    		getLogger().log(Level.WARNING, "'Slots' precisam ser 9, 18, 27, 36, 45 ou 54!");
    		if (player.hasPermission("Reload")) {
    			player.sendMessage("§2Veja a sua Config§c! As 'Slots' precisam ser 9, 18, 27, 36, 45 ou 54! Atualmente está §4" + a);
    		}
    		a = 54;
    	}
    	Inventory inv = Bukkit.createInventory(null, a, getGUIName(PowerUtils.getConfig().getString("Loja.Geral.NomeDaGUI"), PowerUtils.getConfig().getString("Loja.Geral.PlayerMoedas"), PowerUtils.getConfig().getString("Loja.Geral.PlayerMoedasNaGUI"), player).replace("&", "§"));
		//ItemStack normal1 = new ItemStack(Material.REDSTONE_ORE);
		//ItemMeta normal1Meta = normal1.getItemMeta();
		//normal1Meta.setDisplayName("&4Teste Teste Teste".replace("&", "§"));
		//normal1.setItemMeta(normal1Meta);
		int i = 0;
		Material item = Material.getMaterial(PowerUtils.getConfig().getString("Loja.Geral.EncherGUI"));
		while (i <= a - 1) {
			int metadata = 0;
			int quantidade = 1;
	    	item = Material.getMaterial(PowerUtils.getConfig().getString("Loja.Slot" + i + ".Item"));
			if (PowerUtils.getConfig().getInt("Loja.Slot" + i + ".Metadata") != 0) {
				metadata = getConfig().getInt("Loja.Slot" + i + ".Metadata");
			}
			if (PowerUtils.getConfig().getString("Loja.Slot" + i + ".Item") == null || item == null) {
				item = Material.getMaterial(PowerUtils.getConfig().getString("Loja.Geral.EncherGUI"));
			}
			if (getConfig().isSet("Loja.Slot" + i + ".Quantidade")) {
				quantidade = getConfig().getInt("Loja.Slot" + i + ".Quantidade");
			}
			
			// Cria uma ItemStack com Ar, só para evitar NullPointerExceptions
			ItemStack menuItem = new ItemStack(Material.AIR);
			// Se o Item setado anteriormente for null...
			if (item == null) {
				// Vamos ver se é um ID de um Item em vez de um Material.
		    	if (getConfig().getInt("Loja.Geral.EncherGUI") != 0) {
		    		// Se for, adicione o ID ao ItemStack
		    		menuItem = new ItemStack(getConfig().getInt("Loja.Geral.EncherGUI"));
		    	}
		    	else {
		    		// Se não, crie um novo ItemStack com Ar
		    		menuItem = new ItemStack(Material.AIR);
		    	}
			}
			else {
			// Se o Item existir, então o Item é um Material, então crie um ItemStack com o Material
			menuItem = new ItemStack(item, quantidade, (byte)metadata);
			}
			if (getConfig().isSet("Loja.Slot" + i + ".Item")) {
				if (getConfig().getInt("Loja.Slot" + i + ".Item") != 0) {
					menuItem = new ItemStack(getConfig().getInt("Loja.Slot" + i + ".Item"), PowerUtils.getConfig().getInt("Loja.Slot" + i + ".Quantidade"));
				}
			}
			if (menuItem.getType() != Material.AIR) {
				if (PowerUtils.getConfig().getString("Loja.Slot" + i + ".Nome") != null) {
					ItemMeta itemMeta = menuItem.getItemMeta();
					String itemNome = PowerUtils.getConfig().getString("Loja.Slot" + i + ".Nome").replace("&", "§");
					itemMeta.setDisplayName(fixPTBR(itemNome));
					menuItem.setItemMeta(itemMeta);
				}
				if (PowerUtils.getConfig().getStringList("Loja.Slot" + i + ".Lore") != null) {
					List<String> LoreColorido = PowerUtils.getConfig().getStringList("Loja.Slot" + i + ".Lore");
					List<String> LoreColoridoAlt = PowerUtils.getConfig().getStringList("Loja.Slot" + i + ".Lore");
					LoreColoridoAlt.removeAll(LoreColorido);
			        for(String s : LoreColorido) {
			        	LoreColoridoAlt.add(fixPTBR((s.replace("&", "§"))));
			        }
					ItemMeta itemMeta = menuItem.getItemMeta();
					itemMeta.setLore(LoreColoridoAlt);
					menuItem.setItemMeta(itemMeta);
				}
				if (PowerUtils.getConfig().getBoolean("Loja.Slot" + i + ".MostrarMoedas") == true) {
					ItemMeta itemMeta = menuItem.getItemMeta();
					//itemMeta.setLore(LoreColoridoAlt);
					if (userdata.getUserData().getString("dindin." + player.getName()) == null)
					{
						itemMeta.setDisplayName(PowerUtils.getConfig().getString("Mensagens.SuasMoedas").replace("&", "§").replace("@moedas", "0").replace("@prefixo ", "").replace("@prefixo", ""));
					}
					else
					{
					itemMeta.setDisplayName(PowerUtils.getConfig().getString("Mensagens.SuasMoedas").replace("&", "§").replace("@moedas", userdata.getUserData().getString("dindin." + player.getName())).replace("@prefixo ", "").replace("@prefixo", ""));
					}
					menuItem.setItemMeta(itemMeta);
				}
				
				// Aplicar os Enchants no Item
				if (PowerUtils.getConfig().getString("Loja.Slot" + i + ".Enchants") != null) {
					ItemMeta itemMeta = menuItem.getItemMeta();
					List<String> itemEnchants = PowerUtils.getConfig().getStringList("Loja.Slot" + i + ".Enchants");
			        for(String s : itemEnchants) {
			        	String [] thisEnchant = s.split(":");
			        	if (thisEnchant[0].equalsIgnoreCase("Sharpness") || thisEnchant[0].equalsIgnoreCase("Afiada")) {
			        		itemMeta.addEnchant(Enchantment.DAMAGE_ALL, Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Smite") || thisEnchant[0].equalsIgnoreCase("Castigo")) {
			        		itemMeta.addEnchant(Enchantment.DAMAGE_UNDEAD , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Fire Aspect") || thisEnchant[0].equalsIgnoreCase("Aspecto Flamejante")) {
			        		itemMeta.addEnchant(Enchantment.FIRE_ASPECT , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Looting") || thisEnchant[0].equalsIgnoreCase("Pilhagem")) {
			        		itemMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Knockback") || thisEnchant[0].equalsIgnoreCase("Repulsão")) {
			        		itemMeta.addEnchant(Enchantment.KNOCKBACK , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Bane of Arthropods") || thisEnchant[0].equalsIgnoreCase("Ruína dos Artrópodes")) {
			        		itemMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Efficiency") || thisEnchant[0].equalsIgnoreCase("Eficiência")) {
			        		itemMeta.addEnchant(Enchantment.DIG_SPEED , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Silk Touch") || thisEnchant[0].equalsIgnoreCase("Toque Suave")) {
			        		itemMeta.addEnchant(Enchantment.SILK_TOUCH , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Unbreaking") || thisEnchant[0].equalsIgnoreCase("Inquebrável")) {
			        		itemMeta.addEnchant(Enchantment.DURABILITY , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Fortune") || thisEnchant[0].equalsIgnoreCase("Fortuna")) {
			        		itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Power") || thisEnchant[0].equalsIgnoreCase("Força")) {
			        		itemMeta.addEnchant(Enchantment.ARROW_DAMAGE , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Flame") || thisEnchant[0].equalsIgnoreCase("Chama")) {
			        		itemMeta.addEnchant(Enchantment.ARROW_FIRE , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Punch") || thisEnchant[0].equalsIgnoreCase("Impact")) {
			        		itemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Infinity") || thisEnchant[0].equalsIgnoreCase("Infinidade")) {
			        		itemMeta.addEnchant(Enchantment.ARROW_INFINITE , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Protection") || thisEnchant[0].equalsIgnoreCase("Proteção")) {
			        		itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Fire Protection") || thisEnchant[0].equalsIgnoreCase("Proteção à Chamas")) {
			        		itemMeta.addEnchant(Enchantment.PROTECTION_FIRE , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Feather Falling") || thisEnchant[0].equalsIgnoreCase("Peso Pena")) {
			        		itemMeta.addEnchant(Enchantment.PROTECTION_FALL , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Blast Protection") || thisEnchant[0].equalsIgnoreCase("Proteção de Explosões")) {
			        		itemMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Respiration") || thisEnchant[0].equalsIgnoreCase("Respiração")) {
			        		itemMeta.addEnchant(Enchantment.OXYGEN , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Aqua Affinity") || thisEnchant[0].equalsIgnoreCase("Afinidade Aquática")) {
			        		itemMeta.addEnchant(Enchantment.WATER_WORKER , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	if (thisEnchant[0].equalsIgnoreCase("Thorns") || thisEnchant[0].equalsIgnoreCase("Espinhos")) {
			        		itemMeta.addEnchant(Enchantment.THORNS , Integer.parseInt(thisEnchant[1]), true);
			        	}
			        	// TODO: Acabar de colocar os Encantamentos
			        }
			        menuItem.setItemMeta(itemMeta);
				}
				if (PowerUtils.getConfig().getBoolean("AutoTagPreco") == true) {
					
					// Somente Marcar a Tag de Preço em Itens, e não no Item de EncherGUI!
					if (getConfig().isSet("Loja.Slot" + i + ".Item")) {
						ItemMeta itemMeta = menuItem.getItemMeta();
						String itemSecretTag = itemMeta.getDisplayName();
						if (itemMeta.getDisplayName() == null) {
							itemSecretTag = "§4Ative o Nome Customizado deste Item!";
						}
						if (PowerUtils.getConfig().getBoolean("ReverseAutoTag") != true) {
							String price = "0";
							if (getConfig().isSet("Loja.Slot" + i + ".Custo")) {
								price = getConfig().getString("Loja.Slot" + i + ".Custo");
							}
							itemMeta.setDisplayName((PowerUtils.getConfig().getString("Mensagens.TagPreco").replace("@preco", price).replace("&", "§")) + " " + ((itemSecretTag)));
						}
						else {
							itemMeta.setDisplayName(((itemSecretTag)) + " " + (PowerUtils.getConfig().getString("Mensagens.TagPreco").replace("@preco", PowerUtils.getConfig().getString("Loja.Slot" + i + ".Custo")).replace("&", "§")));
						}
						menuItem.setItemMeta(itemMeta);
					}
				}
				
			}
			//ItemMeta normal1Meta = normal1.getItemMeta();
			//normal1Meta.setDisplayName("&4Teste Teste Teste".replace("&", "§"));
			//normal1.setItemMeta(normal1Meta);
			inv.setItem(i, menuItem);
			i++;
		}
		player.playSound(player.getLocation(), Sound.valueOf(PowerUtils.getConfig().getString("Sons.AbrirGUI")), 1.0F, 1.0F);
		player.openInventory(inv);
		}
	
	public static String getGUIName(String nomegui, String moedasadd, String aplicar, Player dindin) {
		if (aplicar == "true") {
			if (userdata.getUserData().getString("dindin." + dindin.getName()) == null) {
				return nomegui;
			}
			else {
				String dindinstr = userdata.getUserData().getString("dindin." + dindin.getName()) + "";
				int convertme = (nomegui + moedasadd.replace("@moedas", dindinstr)).length();
				if (convertme > 32) {
					getLogger().log(Level.WARNING, "'PlayerMoedas' é maior que 32 letras! Recomendo você diminuir ela ou desativar!");
					return nomegui;
				}
				else
				{
				return nomegui + moedasadd.replace("@moedas", dindinstr);
				}
			}
		}
		else {
			return nomegui;
		}
	}
}
