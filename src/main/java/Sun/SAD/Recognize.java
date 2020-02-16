package Sun.SAD;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import database_unit.DatabaseInterface;
import source_unit.*;

/**
 *	Recognize предназначен для:
 *	...
 *	@author user
 */
public class Recognize {
	String customerDate = "";	//"00.00.0000, dd.mm.yyyy(..other customer formats)";
	List<String[]> facture = new ArrayList<String[]>();
	List<String> info = new ArrayList<String>();
	List<HashMap> list = new ArrayList<HashMap>();	//.. для отправки в б.д.
	
	Recognize() {
		
	}
	
	Recognize(SourceInterface source, DatabaseInterface parameters) throws UnsupportedEncodingException {
		/*must be universal analizator*/
		System.out.println("..recognize");
		divideData(source);
		universalInitFactureThree(source, parameters);
		universalInitInfo(source, parameters);
	}
	
	/**
	 *	Метод divideData предназначен для разделения данных пользователя на три части:
	 *	Date - Facture - Info
	 *	на вход принимается оригинальный текст пользователя и параметры из конфигурации,
	 *	после исполнения данного метода одноименные переменные будут проинициализированы
	 */
	private void divideData(SourceInterface source) {
		String[] dayList = source.getData().split(source.getParameters().getSplit());
		customerDate = dayList[0];	//..must be universal GET_DATE();
		for(int i = 1; i < dayList.length; i++) {
			if(getInfo(source.getParameters().getInfoMarker(), dayList[i])) {
				facture.add(dayList[i].split(source.getParameters().getSplitFacture()));
			}
			else {
				info.add(dayList[i]);
			}
		}
	}
	
	/**
	 *	Метод universalInitFacture предназначен для связывания неструктурированных данных клиента,
	 *	которые уже находятся в  списке FACTURE со структурированными данными в Б.Д.
	 *	список FACTURE(!) получили методом "ИСКЛЮЧЕНИЯ" 
	 * @throws UnsupportedEncodingException 
	 */
	private void universalInitFacture(SourceInterface source, DatabaseInterface parameters) {
		for(int i = 0; i < facture.size(); i++) {
			HashMap<String, String> to_list = new HashMap<String, String>();
			
			for(Entry<String, String> entry: parameters.getParametrs().getFactureDatabaseConfigAlpha().entrySet()) {	//..цикл по параметрам б.д.
				 String s = entry.getKey();	//..имя поля в д.б. 
				 if(entry.getValue() != null) {
					 int n = Integer.parseInt(entry.getValue());
					 if(n < facture.get(i).length)to_list.put(s, facture.get(i)[n]);	//..связывание данных из facture с полями б.д.
				 }
			}
			to_list.put("date", getDateFormat(customerDate));	//..добавляем дату после форматирования
			list.add(to_list);
		}
		
		parameters.setStructuredData(list);
	}
	
	/**
	 *	Метод universalInitFactureThree является вторым вариантом universalInitFacture и предназначен
	 *	для связывания неструктурированных данных клиента с использованием class "Marker"
	 *	в будущем должен полностью заменить universalInitFacture
	 */
	private void universalInitFactureThree(SourceInterface source, DatabaseInterface parameters) {
		String[] factureElement;
		for(int i = 0; i < facture.size(); i++) {
			HashMap<String, String> to_list = new HashMap<String, String>();
			factureElement = facture.get(i);

			for(Entry<String, int[]> entry: parameters.getParametrs().getFactureDatabaseConfigBetta(factureElement).entrySet()) {
				String s = entry.getKey();	//..имя поля в д.б.
				String forValue = "";
					for(int k = 0; k < entry.getValue().length; k++) {
						if(entry.getValue()[k] > -1)
						forValue += " " + factureElement[entry.getValue()[k]];
					}
				to_list.put(s, forValue.trim());
			}
			to_list.put("date", getDateFormat(customerDate));	//..добавляем дату после форматирования
			list.add(to_list);
		}
		
		parameters.setStructuredData(list);
	}

	/**
	 *	Метод universalInitInfo предназначен для связывания неструктурированных данных клиента,
	 *	которые уже находятся в  списке INFO со структурированными данными в Б.Д.
	 *	The code for second database "INFO"	получили методом "ВКЛЮЧЕНИЯ"
	 */
	private void universalInitInfo(SourceInterface source, DatabaseInterface parameters) {
		HashMap<String, String> to_list = new HashMap<String, String>();
	
		for(Entry<String, String> entry: parameters.getParametrs().infoDatabaseConfig.entrySet()) {
			 for(int i = 0; i < info.size(); i++) {
				String value_to_database = "" + getNumber(info.get(i));							
				if(entry.getValue() != null && !getInfo(source.getParameters().getInfoMarker(), entry.getValue())
						&& info.get(i).contains(entry.getValue()))to_list.put(entry.getKey(), value_to_database);
			}
		}
					
		to_list.put("date", getDateFormat(customerDate));
		list.add(to_list);	
		parameters.setStructuredData(list);
	}
	
	/**
	 *	Блок вспомогательных методов... 
	 */
	
	private boolean getInfo(String[] infoMarker, String text) {
		boolean answer = true;
		for(int i = 0; i < infoMarker.length; i++) {
			if(text.contains(infoMarker[i]))answer = false;
		}
		return answer;
	}

	private String getDateFormat(String date) {
		String[] elements = date.split("\\.");	//.. to incapsulation!!
		String s = elements[2] + elements[1] + elements[0];
		return s;
	}
	
	private int getNumber(String text) {
		char[] ch = text.toCharArray();
		String number = "";
			for(int i = 0; i < ch.length; i++) {
				if(Character.isDigit(ch[i]))number += "" + ch[i];
			}	
		return Integer.parseInt(number);
	}

}
