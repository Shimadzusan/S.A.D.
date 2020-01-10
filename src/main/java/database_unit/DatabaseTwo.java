package database_unit;

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

	public void setStructuredData() {
		System.out.println("..writting to database sucssesfull complete");
		//Launch_two.main();
		
	}
	
}