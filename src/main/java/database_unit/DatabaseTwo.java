package database_unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;

public class DatabaseTwo implements DatabaseInterface {
/*
 *	..взаимодействие с центральными модулями только через интерфсы
 *	..кофигурация базы определяется в файле .properties 
 */
	@Value("${Database.config}")
	private String[] databaseArray;
	
	private String[] databaseConfig = {"дата","ремонт мч","ремонт эч","консультация","другое"};

	public String[] getDatabaseArray() {
		return databaseArray;
	}

	public void setDatabaseArray(String[] databaseArray) {
		this.databaseArray = databaseArray;
	}

	public String[] getParametrs() {
		return databaseConfig;
	}

	public void setStructuredData(List list) {
		System.out.println("..writting to database TWO sucssesfull complete");
		
		for(int i = 0; i < list.size(); i++) {
        	HashMap<String, String> hm = new HashMap<String, String>();
        	hm = (HashMap<String, String>) list.get(i);
        	
        	for(Entry<String, String> entry: hm.entrySet()) {
        		System.out.println(entry.getKey() + " = " + entry.getValue());
        	}
       System.out.println(); 	
        }
		//Launch_two.main();
		
	}
	
}