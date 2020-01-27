package database_unit;

import java.util.HashMap;

public class Parameters {
	public final HashMap<String, String> factureDatabaseConfig = new HashMap<String, String>();
	{
		factureDatabaseConfig.put("date", null);
		factureDatabaseConfig.put("deal", "0");	//.. сделка извлекается при помощи маркера 0
		factureDatabaseConfig.put("volume", "1");	//.. объем извлекается при помощи маркера 1
		factureDatabaseConfig.put("payment", "2");
	}
	
	public final HashMap<String, String> infodatabaseConfig = new HashMap<String, String>();
	{
		infodatabaseConfig.put("date", null);
		infodatabaseConfig.put("begin_cash", "касса утро");	//.. сделка извлекается при помощи маркера 0
		infodatabaseConfig.put("end_cash", "касса вечер");	//.. объем извлекается при помощи маркера 1
		infodatabaseConfig.put("salary", "зп");
	}

	public HashMap<String, String> getFactureDatabaseConfig() {
		return factureDatabaseConfig;
	}
	
	public HashMap<String, String> getInfodatabaseConfig() {
		return infodatabaseConfig;
	}
}
