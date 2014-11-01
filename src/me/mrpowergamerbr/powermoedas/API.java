package me.mrpowergamerbr.powermoedas;

import me.mrpowergamerbr.powermoedas.Utils.DataUtils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class API {
	static DataUtils userdata = DataUtils.getInstance();
	  // APIs Baianas
	  public static FileConfiguration getConfig()
	  {
	    getPlugin().reloadConfig();
	    return getPlugin().getConfig();
	  }
	  
	  public static FileConfiguration saveConfig()
	  {
	    getPlugin().saveConfig();
	    return getPlugin().getConfig();
	  }
	  
	  public static Plugin getPlugin()
	  {
	    return Bukkit.getPluginManager().getPlugin("PowerMoedas");
	  }
	  
	  public static int pegarMoedas(Player player)
	  {
		return userdata.getUserData().getInt("dindin." + player.getName());
	  }
	  
	  public static void setarMoedas(Player player, int quantia)
	  {
		  userdata.getUserData().set("dindin." + player.getName(), quantia);
		  userdata.saveUserData();
		  return;
	  }
	  
	  public static void darMoedas(Player player, int quantia)
	  {
		  int calcs = (userdata.getUserData().getInt("dindin." + player.getName())) + quantia;
		  userdata.getUserData().set("dindin." + player.getName(), calcs);
		  userdata.saveUserData();
		  return;
	  }
	  
	  public static boolean tirarMoedas(Player player, int quantia)
	  {
		  int calcs = ( userdata.getUserData().getInt("dindin." + player.getName())) + -quantia;
		  if ( userdata.getUserData().getInt("dindin." + player.getName()) > quantia) {
			  userdata.getUserData().set("dindin." + player.getName(), calcs);
		  		userdata.saveUserData();
		  		return true;
		  }
		  else
		  {
			  userdata.getUserData().set("dindin." + player.getName(), 0);
		      userdata.saveUserData();
			  return true;
		  }
		  }

	  // Códigos Alternativos
	  public static String pegarMoedas(String player)
	  {
		return userdata.getUserData().getString("dindin." + player);
	  }
	  
	  public static void setarMoedas(String player, int quantia)
	  {
		  userdata.getUserData().set("dindin." + player, quantia);
		  userdata.saveUserData();
		  return;
	  }
	  
}
