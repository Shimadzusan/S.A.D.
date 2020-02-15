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
	
	/** version two
	 * ..в сущности "Deal" центральным ориентиром для распознавания является сумма сделки,
	 * т.е. это всегда число. Таким образом имеем <число(сумма)>, данные слева от числа и данные спарава
	 * от числа.
	 * Данные слева от Integer - это наименование товара, или сделки
	 * Данные справа от Integer - это способ оплаты, и возможно комментарий
	 * 
	 * При таком подходе появляется возможность иметь наименования товаров(или услуг) состоящих из нескольких
	 * слов написанных через пробел! В version one такой возможности нет и поэтому нулевая позиция не должна
	 * содержать пробелов иначе ошибка!
	 * ..осталось придумать для этого подхода конфигурацию..
	 * ..universal configuration for any entity of the database??
	 * key = field of the database
	 * volume = position from facture array
	 *
	 */
	public final HashMap<String, String> factureDatabaseConfigTwo = new HashMap<String, String>();
	{
		factureDatabaseConfigTwo.put("date", null);
		factureDatabaseConfigTwo.put("deal", "leftPart=all");	//.. сделка извлекается при помощи маркера 0
		factureDatabaseConfigTwo.put("volume", "centralMarker=Integer, т.е. здесь должна быть инструкция?");	//.. объем извлекается при помощи маркера 1
		factureDatabaseConfigTwo.put("payment", "rightPart=0");
		factureDatabaseConfigTwo.put("comment", "как здесь передать интервал чисел?");

	}
	
	public final HashMap<String, String> infoDatabaseConfig = new HashMap<String, String>();
	{
		infoDatabaseConfig.put("date", null);
		infoDatabaseConfig.put("begin_cash", "касса утро");
		infoDatabaseConfig.put("end_cash", "касса вечер");
		infoDatabaseConfig.put("salary", "зп");
	}

	public HashMap<String, String> getFactureDatabaseConfig() {
		return factureDatabaseConfig;
	}
	
	public HashMap<String, String> getInfoDatabaseConfig() {
		return infoDatabaseConfig;
	}
}

class Marker {
	/*как связать поля таблицы со строками текста пользователя*/
	/*	МАРКЕР ЭТО КОНФИГУРАЦИОННЫЙ КЛАСС!
	 *	имена полей таблицы известны
	 *	в строке пользователя ориентируемся по номеру индекса
	 * 
	 */
	String Marker = "маркер это метод!";
	
	HashMap<String, int[]> marker = new HashMap<String, int[]>();
	{
		int[] deal = {1,2};
		int[] payment = {5};
		marker.put("deal", deal);
		marker.put("volume", getMarkerNumber());
		marker.put("payment", payment);
	}
	private int[] getMarkerNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}