package me.mrpowergamerbr.powermoedas.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class PowerUtils {
	public static Server getServer()
	  {
	    return Bukkit.getServer();
	  }
	  
	  public static FileConfiguration getConfig()
	  {
	    return getPlugin().getConfig();
	  }
	  
	  public static Plugin getPlugin()
	  {
	    return Bukkit.getPluginManager().getPlugin("PowerMoedas");
	  }
	  
	  public static Logger getLogger()
	  {
		  return Bukkit.getLogger();
	  }
	  
	  public static void saveConfig()
	  {
		  getPlugin().saveConfig();
		  return;
	  }
	  
	  public static void reloadConfig()
	  {
		  getPlugin().reloadConfig();
		  return;
	  }
	  
	  public static File getDataFolder()
	  {
		  return getPlugin().getDataFolder();
	  }
	  
	  public static String fixPTBR(String mensagem) {
		  mensagem = mensagem.replace("Ã­", "í");
		  mensagem = mensagem.replace("Ã¡", "á");
		  mensagem = mensagem.replace("Ã©", "é");
		  mensagem = mensagem.replace("Ã³", "ó");
		  mensagem = mensagem.replace("Ãº", "ú");
		  //mensagem = mensagem.replace("Ã?", "Á");
		  mensagem = mensagem.replace("Ã‰", "É");
		  //mensagem = mensagem.replace("Ã?", "Í");
		  mensagem = mensagem.replace("Ã“", "Ó");
		  mensagem = mensagem.replace("Ãš", "Ú");
		  return mensagem;
	  }
	  	
	  public void logToFile(String message)
	    
	    {
	     
	    try
	    {
	    File dataFolder = getDataFolder();
	    if(!dataFolder.exists())
	    {
	    dataFolder.mkdir();
	    }
	     
	    File saveTo = new File(getDataFolder(), "compras.log");
	    if (!saveTo.exists())
	    {
	    saveTo.createNewFile();
	    }
	     
	     
	    FileWriter fw = new FileWriter(saveTo, true);
	     
	    PrintWriter pw = new PrintWriter(fw);
	     
	    pw.println(message);
	     
	    pw.flush();
	     
	    pw.close();
	     
	    } catch (IOException e)
	    {
	     
	    e.printStackTrace();
	     
	    }
	     
	    }
}