package fr.entasia.hubtools.utils;

import fr.entasia.apis.regionManager.api.Region;
import fr.entasia.apis.regionManager.api.RegionManager;
import fr.entasia.apis.utils.BasicLocation;
import fr.entasia.hubtools.Main;

public enum Regions {
	TPPLANET("tpplanet", new BasicLocation(-976, 70, 1199), new BasicLocation(-974, 68, 1201)),
	PLANET("planet", new BasicLocation(-976, 59, 1199), new BasicLocation(-974, 58, 1201)),
	TPWATER("tpwater", new BasicLocation(396, 47, 1135), new BasicLocation(396, 47, 1135)),
	WATER("water", new BasicLocation(130, 142, -32), new BasicLocation(132, 142, -34)),
	TPDRAGON("tpdragon", new BasicLocation(-48, 143, 44), new BasicLocation(-50, 140, 46)),
	DRAGON("dragon", new BasicLocation(466, 33, 1163), new BasicLocation(464, 31, 1161)),
	WATERGATE("watergate", new BasicLocation(967, 17, 1086), new BasicLocation(968, 16, 1086)),
	GATEWATER("gatewater", new BasicLocation(52, 111, -73), new BasicLocation(52, 113, -73)),
	CHESTOUT("chestout", new BasicLocation(-245, 84, 1204), new BasicLocation(-245, 87, 1204)),
	CHESTOUT2("chestout2", new BasicLocation(-249, 96, 1207), new BasicLocation(-233, 96, 1194)),
	GROTTE_OUT("grotte_out", new BasicLocation(-663, 19, 1164), new BasicLocation(-654, 20, 1155)),
	GROTTE_IN("grotte_in", new BasicLocation(24, 119, 117), new BasicLocation(22, 120, 115)),
	;

	public Region region;

	Regions(String name, BasicLocation b1, BasicLocation b2){
		this.region = RegionManager.registerRegion(name, Main.world, b1, b2);
	}


	public static void init(){ // to init enum

	}



}
