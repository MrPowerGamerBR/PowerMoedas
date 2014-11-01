package me.mrpowergamerbr.powermoedas.SkMoedas;

import javax.annotation.Nullable;

import me.mrpowergamerbr.powermoedas.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.classes.Changer;
import ch.njol.util.coll.CollectionUtils;

public class ExprMoedas extends SimplePropertyExpression<Player, Number>{

	@Override
	public Class<? extends Number> getReturnType() {
		// TODO Auto-generated method stub
		return Number.class;
	}

	@Override
	@Nullable
	public Number convert(Player arg0) {
		// TODO Auto-generated method stub
		return API.pegarMoedas(arg0);
	}

	@Override
	protected String getPropertyName() {
		// TODO Auto-generated method stub
		return "coins";
	}

	public void change(Event evento, Object[] delta, Changer.ChangeMode mode)
	  {
	    Player p = (Player)getExpr().getSingle(evento);
	    if (p == null) {
	      return;
	    }
	    if (mode == Changer.ChangeMode.ADD)
	    {
	    	Long quantia = (Long)delta[0];
	    	if ((quantia + "").contains("-")) {
	    		return;
	    	}
	    	API.darMoedas(p, Integer.parseUnsignedInt(quantia + ""));
	    }
	    if (mode == Changer.ChangeMode.REMOVE)
	    {
	    	Long quantia = (Long)delta[0];
	    	if ((quantia + "").contains("-")) {
	    		return;
	    	}
	    	API.tirarMoedas(p, Integer.parseUnsignedInt(quantia + ""));
	    }
	    if (mode == Changer.ChangeMode.SET)
	    {
	    	Long quantia = (Long)delta[0];
	    	if ((quantia + "").contains("-")) {
	    		return;
	    	}
	    	API.setarMoedas(p, Integer.parseUnsignedInt(quantia + ""));
	    }
	  }
	
	public Class<?>[] acceptChange(Changer.ChangeMode mode)
	  {
	    if (mode == Changer.ChangeMode.ADD) {
	      return (Class[])CollectionUtils.array(new Class[] { Number.class });
	    }
	    if (mode == Changer.ChangeMode.REMOVE) {
	      return (Class[])CollectionUtils.array(new Class[] { Number.class });
	    }
	    if (mode == Changer.ChangeMode.SET) {
		      return (Class[])CollectionUtils.array(new Class[] { Number.class });
		}
	    return null;
	  }
	}
