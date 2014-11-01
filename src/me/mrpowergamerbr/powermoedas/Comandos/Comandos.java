package me.mrpowergamerbr.powermoedas.Comandos;

import me.mrpowergamerbr.powermoedas.Utils.DataUtils;
import me.mrpowergamerbr.powermoedas.Utils.GUIUtils;
import me.mrpowergamerbr.powermoedas.Utils.PowerUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comandos extends PowerUtils implements CommandExecutor {
	static DataUtils userdata = DataUtils.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if ((cmd.getName().equalsIgnoreCase("coins")))
	    {
	    	if (args.length < 1) {
	    	//sender sender = (sender) sender;
	    	//else
		    {
		    if ((!(sender instanceof Player))) {
		        sender.sendMessage("&4Seu Bobinho! O Console não tem Moedas!".replace("&", "§"));
		        return true;
		    }
	    	if (userdata.getUserData().getString("dindin." + sender.getName()) == null) {
	    		userdata.getUserData().set("dindin." + sender.getName(), getConfig().getString("MoedasDefault"));
	    		userdata.saveUserData();
	    	}
	    	sender.sendMessage(getConfig().getString("Mensagens.SuasMoedas").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@moedas", userdata.getUserData().getString("dindin." + sender.getName())));
	    	return true;
	    	}
	    	}
	    	else
		    //sender sender = (sender) sender;
			if (userdata.getUserData().getString("dindin." + args[0]) == null) {
			  	sender.sendMessage(getConfig().getString("Mensagens.PlayerInexistente").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
			   	return true;
			}
			sender.sendMessage(getConfig().getString("Mensagens.MoedasOutro").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@player", args[0]).replace("@moedas", userdata.getUserData().getString("dindin." + args[0])));
		    return true;
	    	}
	    if (cmd.getName().equalsIgnoreCase("opsetcoins"))
	    {
	    	//sender sender = (sender) sender;
	    	
	    	if (sender.hasPermission("PowerMoedas.OPSetarMoedas")) {
	    	if (args.length < 2)
	    	{
	    		sender.sendMessage(getConfig().getString("Mensagens.ArgumentosInvalidos").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@comando", "/" + cmd.getName() + " player quantidade"));
	    		return true;
	    	}
	    	int a = Integer.parseInt(args[1]);
			userdata.getUserData().set("dindin." + args[0], a);
			userdata.saveUserData();
			sender.sendMessage(getConfig().getString("Mensagens.EnviouMoedas").replace("deu", "setou").replace("enviou", "setou").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@player", args[0]).replace("@moedas", args[1]));
			return true;
	    	}
	    	else {
	    		sender.sendMessage(getConfig().getString("Mensagens.SemPerm").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
	    		return true;
	    	}
	    }
	    
	    if (cmd.getName().equalsIgnoreCase("opdarcoins"))
	    {
	    	// Para suportar o Comando no Console, é necessário trocar todos os "sender" para "sender"
	    	//sender sender = (sender) sender;
	    	
	    	if (sender.hasPermission("PowerMoedas.OPDarMoedas")) {
	    	if (args.length < 2)
	    	{
	    		sender.sendMessage(getConfig().getString("Mensagens.ArgumentosInvalidos").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@comando", "/" + cmd.getName() + " player quantidade"));
	    		return true;
	    	}
	    	int a = Integer.parseInt(args[1]);
	    	int calcs = userdata.getUserData().getInt("dindin." + args[0]) + a;
			userdata.getUserData().set("dindin." + args[0], calcs);
			userdata.saveUserData();
			sender.sendMessage(getConfig().getString("Mensagens.EnviouMoedas").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@player", args[0]).replace("@moedas", args[1]));
			return true;
	    	}
	    	else {
	    		sender.sendMessage(getConfig().getString("Mensagens.SemPerm").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
	    		return true;
	    	}
	    }
	    if (cmd.getName().equalsIgnoreCase("optirarcoins"))
	    {
	    	//sender sender = (sender) sender;
	    	
	    	if (sender.hasPermission("PowerMoedas.OPTirarMoedas")) {
	    	if (args.length < 2)
	    	{
	    		sender.sendMessage(getConfig().getString("Mensagens.ArgumentosInvalidos").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@comando", "/" + cmd.getName() + " player quantidade"));
	    		return true;
	    	}
	    	int a = Integer.parseInt(args[1]);
	    	int calcs = userdata.getUserData().getInt("dindin." + args[0]) + -a;
	    	if (calcs < 0) {
				userdata.getUserData().set("dindin." + args[0], "0");
				userdata.saveUserData();
	    		sender.sendMessage("&cNúmero muito Baixo! Trocando para 0 Moedas!".replace("&", "§"));
	    		return true;
	    	}
			userdata.getUserData().set("dindin." + args[0], calcs);
			userdata.saveUserData();
			sender.sendMessage(getConfig().getString("Mensagens.EnviouMoedas").replace("enviou", "tirou").replace("deu", "tirou").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@player", args[0]).replace("@moedas", args[1]));
			return true;
	    }
	    	else {
	    		sender.sendMessage(getConfig().getString("Mensagens.SemPerm").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
	    		return true;
	    	}
	    }
	    if (cmd.getName().equalsIgnoreCase("darcoins"))
	    {
	    	//sender sender = (sender) sender;
	    	if (sender.hasPermission("PowerMoedas.DarMoedas")) {
			    if ((!(sender instanceof Player))) {
			        sender.sendMessage("&4Seu Bobinho! O Console não tem Moedas!".replace("&", "§"));
			        return true;
			    }
		    	if (args.length < 2)
		    	{
		    		sender.sendMessage(getConfig().getString("Mensagens.ArgumentosInvalidos").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@comando", "/" + cmd.getName() + " player quantidade"));
		    		return true;
		    	}
		    	int a = Integer.parseInt(args[1].replace("-", ""));
		    	if (a >= userdata.getUserData().getInt("dindin." + sender.getName())) {
		    		sender.sendMessage(getConfig().getString("Mensagens.QuantiaAlta").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
		    		return true;
		    	}
		    	String stringa = a + "";
		    	if (stringa.contains("-")) {
		    		sender.sendMessage("&cNão coloque valores negativos!".replace("&", "§"));
		    		return true;
		    	}
		    	int calcs = userdata.getUserData().getInt("dindin." + sender.getName()) + -a;
		    	int calcs2 = userdata.getUserData().getInt("dindin." + args[0]) + a;
				userdata.getUserData().set("dindin." + args[0], calcs2);
				userdata.getUserData().set("dindin." + sender.getName(), calcs);
				userdata.saveUserData();
				sender.sendMessage(getConfig().getString("Mensagens.EnviouMoedas").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@player", args[0]).replace("@moedas", args[1]));
				Player outro = (Player) Bukkit.getServer().getPlayer(args[0]);
				if(outro != null) {
				outro.sendMessage(getConfig().getString("Mensagens.EnviouParaVoceMoedas").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§").replace("@player", args[0]).replace("@moedas", args[1]));
				}
				return true;
	    	}
	    	else {
	    		sender.sendMessage(getConfig().getString("Mensagens.SemPerm").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
	    		return true;
	    	}
	    }
	    if (cmd.getName().equalsIgnoreCase("powermoedas"))
	    {
	    	//sender sender = (sender) sender;
	    	if (sender.hasPermission("PowerMoedas.Reload")) {
	    		PowerUtils.reloadConfig();
	    		DataUtils.getInstance().setupUserData(getPlugin());
	    		sender.sendMessage("&3&lPower&c&lMoedas &arecarregado com sucesso!".replace("&", "§"));
	    		return true;
	    	}
	    	else {
	    		sender.sendMessage("&3&lPower&c&lMoedas &8- &7Criado por &aMrPowerGamerBR &8[&3tails05&8]".replace("&", "§"));
	    		sender.sendMessage("&2Website&8: &3http://mrpowergamerbr.blogspot.com.br/".replace("&", "§"));
	    		return true;
	    	}
	    }
	    if (cmd.getName().equalsIgnoreCase("lojagui"))
	    {
	    	//sender sender = (sender) sender;
		    if ((!(sender instanceof Player))) {
		        sender.sendMessage("&4Seu Bobinho! O Console não pode abrir a GUI!".replace("&", "§"));
		        return true;
		    }
	    	if (sender.hasPermission("PowerMoedas.LojaGUI")) {
	    		Player player = (Player) sender;
	    		GUIUtils.abrirGUI(player);
	    		return true;
	    	}
	    	else {
	    		sender.sendMessage(getConfig().getString("Mensagens.SemPerm").replace("@prefixo", getConfig().getString("Mensagens.Prefixo")).replace("&", "§"));
	    		return true;
	    	}
	    }
		return false;
	}
}
