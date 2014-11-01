// PowerMoedas - Criado por MrPowerGamerBR

package me.mrpowergamerbr.powermoedas;

import java.io.File;
import java.util.logging.Level;

import me.mrpowergamerbr.powermoedas.Comandos.Comandos;
import me.mrpowergamerbr.powermoedas.Listeners.InteractGUI;
import me.mrpowergamerbr.powermoedas.Listeners.ItemGUI;
import me.mrpowergamerbr.powermoedas.Listeners.PlacaGUI;
import me.mrpowergamerbr.powermoedas.SkMoedas.ExprMoedas;
import me.mrpowergamerbr.powermoedas.Utils.DataUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class Main extends JavaPlugin implements Listener {
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		
		// Anti-Troca de plugin.yml
		Plugin powermoedas = getServer().getPluginManager().getPlugin("NaumEditeOk".replace("Na", "Po").replace("Ok", "as").replace("te", "oed").replace("umE", "wer").replace("di", "M"));
		if (powermoedas == null) {
			getLogger().log(Level.SEVERE, "Não, você não pode alterar o nome do PowerMoedas para você poder \"fingir\" que foi você que fez.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		Bukkit.getConsoleSender().sendMessage("§8[§b§lPower§c§lMoedas§8] §7Iniciando PowerMoedas v" + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§8[§b§lPower§c§lMoedas§8] §7Criado por MrPowerGamerBR - §3http://mrpowergamerbr.blogspot.com.br/");
		Bukkit.getConsoleSender().sendMessage("§8[§b§lPower§c§lMoedas§8] §6\"São as pequenas coisas que deixam um Servidor Único\" §7-MrPowerGamerBR");
		
		// Crie a Config.yml se ela não existir
	    File file = new File(getDataFolder(), "config.yml");
	    if (!file.exists()) {
		      try
		      {
		        saveResource("config_template.yml", false);
		        File file2 = new File(getDataFolder(), "config_template.yml");
		        file2.renameTo(new File(getDataFolder(), "config.yml"));
		      }
		      catch (Exception localException1) {}
		    }
	    // Loja GUI
	    if (getConfig().getBoolean("Loja.Geral.Ativado") == true) {
	    	getServer().getPluginManager().registerEvents(new InteractGUI(), this);
	    	getCommand("lojagui").setExecutor(new Comandos());

	    
	    		// Placa
	    		if (getConfig().getBoolean("Placa.Ativado") == true) {
	    			getServer().getPluginManager().registerEvents(new PlacaGUI(), this);
	    		}
	    
	    		// Item
	    		if (getConfig().getBoolean("Item.Ativado") == true) {
	    			getServer().getPluginManager().registerEvents(new ItemGUI(), this);
	    		}
	    }
	    getCommand("coins").setExecutor(new Comandos());
	    getCommand("opsetcoins").setExecutor(new Comandos());
	    getCommand("opdarcoins").setExecutor(new Comandos());
	    getCommand("optirarcoins").setExecutor(new Comandos());
	    getCommand("darcoins").setExecutor(new Comandos());
	    getCommand("powermoedas").setExecutor(new Comandos());
	    
	    // Registre o PowerMoedas no Skript
	    if (getServer().getPluginManager().getPlugin("Skript") != null) {
	    	Skript.registerExpression(ExprMoedas.class, Number.class, ExpressionType.PROPERTY, "(moedas|cash|coins) (do|of) %player%");
	    	getLogger().log(Level.INFO, "Registrado o PowerMoedas com o Skript!");
	    }
	    
	    // Comece a adicionar novas coisas nas Configurações Antigas & Crie a userdata.yml
	    DataUtils.getInstance().setupUserData(this);
	}
	
	@Override
	public void onDisable() {
		
	}
    	 
}