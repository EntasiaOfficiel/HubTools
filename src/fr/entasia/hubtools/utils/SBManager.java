package fr.entasia.hubtools.utils;

import fr.entasia.apis.other.ScoreBoardHelper;
import fr.entasia.apis.utils.LPUtils;
import fr.entasia.hubtools.Main;

public class SBManager extends ScoreBoardHelper {

	public HubPlayer hp;

	public SBManager(HubPlayer hp){
		super(hp.p, "hub", "§bEnta§7sia");
		this.hp = hp;
	}

	@Override
	protected void setSlots() {
		staticLine(8, "§b§m-----------");
		staticLine(6, "§7Pseudo : §b"+hp.p.getDisplayName());
		staticLine(4, "§7Grade : §b"+ LPUtils.getPrefixSafe(hp.p).key);
		staticLine(2, " ");
		refreshOnlines();
		staticLine(0, "§b§m----------- ");

	}

	public void refreshOnlines(){
		String onlines;
		if(Main.onlines==1) onlines = "§7Connecté : §b1";
		else onlines = "§7Connectés : §b"+ Main.onlines;
		dynamicLine(1, onlines);
	}
}
