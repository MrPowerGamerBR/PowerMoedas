package me.mrpowergamerbr.powermoedas.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class DataUtils extends PowerUtils {
	private File userfiled;
	private File tintafiled;
	private File wintherxdfiled;
	private FileConfiguration userfile;
	@SuppressWarnings("rawtypes")
	private HashMap coins;
	private File warcashd;
	private static DataUtils instance = new DataUtils();
	
	public static DataUtils getInstance()
	{
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public void setupUserData(Plugin plugin)
	{
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
	this.userfiled = new File(plugin.getDataFolder(), "userdata.yml");
	if (!this.userfiled.exists()) {
		try {
			this.userfiled.createNewFile();
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cNão foi possível criar o userdata.yml!");
		}
	}
	this.userfile = YamlConfiguration.loadConfiguration(this.userfiled);
	
	// PowerMoedas v1.2.X > PowerMoedas v1.2.2+ UserData Converter
	if (getConfig().isSet("dindin")) {
		Bukkit.getConsoleSender().sendMessage("§aConvertendo UserData do PowerMoedas v1.2.X para o PowerMoedas v1.2.2...");
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if (getConfig().getString("dindin." + player.getName()) != null) {
				userfile.set("dindin." + player.getName(), getConfig().getInt("dindin." + player.getName()));
			}
			Bukkit.getConsoleSender().sendMessage("§aConversão Concluída!");
			getConfig().set("dindin", null);
			// SaveConfig();
			saveUserData();
		}
	}
	
	//
	// Mudanças nas Configs
	//
	
	// Linhas >> Slots
	if (getConfig().isSet("Loja.Geral.Linhas")) {
		getConfig().set("Loja.Geral.Slots", getConfig().getInt("Loja.Geral.Linhas"));
		getConfig().set("Loja.Geral.Linhas", null);
	}
	
	// Adicionar Prefixo
	if (!(getConfig().isSet("Mensagens.Prefixo"))) {
		getConfig().set("Mensagens.Prefixo", "&8[&b&lPower&c&lMoedas&8]&f");
	}
	
	int i = 0;
	while(i < 54) {
		// KeepName > ManterNome
		if (getConfig().getBoolean("Loja.Slot" + i + ".KeepName") == true) {
			getConfig().set("Loja.Slot" + i + ".ManterNome", true);
			getConfig().set("Loja.Slot" + i + ".KeepName", null);
		}
		
		// KeepLore > ManterLore
		if (getConfig().getBoolean("Loja.Slot" + i + ".KeepLore") == true) {
			getConfig().set("Loja.Slot" + i + ".ManterLore", true);
			getConfig().set("Loja.Slot" + i + ".KeepLore", null);
		}
		i++;
		
		// SlotVenda > ItemVenda
		if (getConfig().getBoolean("Loja.Slot" + i + ".SlotVenda") == true) {
			getConfig().set("Loja.Slot" + i + ".ItemVenda", true);
			getConfig().set("Loja.Slot" + i + ".SlotVenda", null);
		}
		
	}
	
	// Adicionar "Ativado: true" na Seção de Lojas
	if (!(getConfig().isSet("Loja.Geral.Ativado"))) {
		getConfig().set("Loja.Geral.Ativado", true);
	}
	
	// Adicionar "ArgumentosInvalidos" na Seção de Mensagens
	if (!(getConfig().isSet("Mensagens.ArgumentosInvalidos"))) {
		getConfig().set("Mensagens.ArgumentosInvalidos", "&cArgumentos &4Inválidos&c! Use &6@comando&c!");
	}
    saveConfig();
	
	//
	// Fim das Mudanças
	//
	
	// TintaCoin > PowerMoedas Converter
	this.tintafiled = new File(plugin.getDataFolder(), "playerdata.yml");
	if (this.tintafiled.exists()) {
		Bukkit.getConsoleSender().sendMessage("§aConvertendo UserData do TintaCoin para o PowerMoedas...");
		YamlConfiguration tintaconverter = YamlConfiguration.loadConfiguration(this.tintafiled);
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if (tintaconverter.getString("Coins." + player.getName()) != null) {
				userfile.set("dindin." + player.getName(), tintaconverter.getInt("Coins." + player.getName()));
			}
		Bukkit.getConsoleSender().sendMessage("§aConversão Concluída!");
		File renamefile = new File(plugin.getDataFolder(), "playerdata.yml");
		File newfile = new File(plugin.getDataFolder(), "playerdata_convertido.yml");
		renamefile.renameTo(newfile);
		saveUserData();
	}
	}
		else {
			
	// WintherXD Coins > PowerMoedas Converter
	
	this.wintherxdfiled = new File(plugin.getDataFolder(), "data.bin");
	if (this.wintherxdfiled.exists()) {
		Bukkit.getConsoleSender().sendMessage("§aConvertendo UserData do WintherXD Coins para o PowerMoedas...");
		try {
			this.coins = ((HashMap)load(getDataFolder() + File.separator + "data.bin"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String convert = this.coins.toString().replace("{", "").replace("}", "").replace(" ", "").replace(".0", "");		
		String[] convertlist = convert.split(",");
		for (String s : convertlist) {
			String[] playerconv = s.split("=");
			userfile.set("dindin." + playerconv[0], Integer.parseInt(playerconv[1]));
		}
		Bukkit.getConsoleSender().sendMessage("§aConversão Concluída!");
		File renamefile = new File(plugin.getDataFolder(), "data.bin");
		File newfile = new File(plugin.getDataFolder(), "data_convertido.bin");
		renamefile.renameTo(newfile);
		saveUserData();
	} else {
		
	// WarCash > PowerMoedas Converter
	this.warcashd = new File(plugin.getDataFolder(), "players.yml");
	if (this.warcashd.exists()) {
		Bukkit.getConsoleSender().sendMessage("§aConvertendo UserData do WarCash para o PowerMoedas...");
		YamlConfiguration warconverter = YamlConfiguration.loadConfiguration(this.warcashd);
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if (warconverter.getString(player.getName()) != null) {
				userfile.set("dindin." + player.getName(), warconverter.getInt(player.getName()));
			}
		Bukkit.getConsoleSender().sendMessage("§aConversão Concluída!");
		File renamefile = new File(plugin.getDataFolder(), "players.yml");
		File newfile = new File(plugin.getDataFolder(), "playerdatas.yml");
		renamefile.renameTo(newfile);
		saveUserData();
		}
		}
	}
		}
	}
	
	public FileConfiguration getUserData()
	{
		return this.userfile;
    }
	
	public void saveUserData()
	{
		try
	    {
	      this.userfile.save(this.userfiled);
	    }
	    catch (Exception e)
	    {
	      Bukkit.getConsoleSender().sendMessage("§cNão foi possível salvar o userdata.yml!");
	    }
	  }
	  
	  public static <T> Object load(String path) throws Exception
	  {
		  ObjectInputStream databin = new ObjectInputStream(new FileInputStream(path));
			    
		  Object getinfo = databin.readObject();
		  databin.close();
		  return getinfo;
	  }
}
