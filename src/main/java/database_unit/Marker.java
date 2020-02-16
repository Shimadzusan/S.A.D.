package database_unit;

import java.util.HashMap;

/**
 * МАРКЕР ЭТО КОНФИГУРАЦИОННЫЙ КЛАСС!
 * ..частично решает проблему "как связать поля таблицы со строками текста пользователя??"
 * 1. определяем какой элемент строки пользователя является маркером
 * 2. определяем стратегию по извлечению числовых интервалов для остальных элементов строки пользователя
 * 3. создаем необходимые методы для каждого поля в мап
 */
public class Marker {
	String[] userLine;
	int markerPosition = -1;	//.. -1 это условное значение указывающее, что индексов не существует
	HashMap<String, int[]> marker = new HashMap<String, int[]>();
	
	Marker(String[] userLine) {
		this.userLine = userLine;
		marker.put("volume", getMarkerNumber());
		marker.put("deal", getDeal());
		marker.put("payment", getPayment());
		marker.put("comment", getComment());
	//..для каждого ключа пишем свой специфический метод, это является частью конфигурации
	}
	
	private int[] getComment() {
		int[] payment = {-1};
		int commentPosition = -1;
		if(markerPosition < userLine.length - 1) {
			for(int i = markerPosition; i < userLine.length; i++) {
				if(userLine[i].contains("*")) {
					commentPosition = i;
					int[] operationalArray = new int[userLine.length - commentPosition];
					
					for(int k = 0; k < operationalArray.length; k++) {
						operationalArray[k] = commentPosition + k;
					}
					payment = operationalArray;
				}
			}
		}
		return payment;
	}

	private int[] getDeal() {
		int[] deal = new int[markerPosition];
		for(int i = 0; i < deal.length; i++) {
			deal[i] = i;
		}
		return deal;
	}
	
	private int[] getPayment() {
		int[] payment = {-1};
		if(markerPosition < userLine.length - 1 && !userLine[markerPosition + 1].contains("*"))payment[0] = markerPosition + 1;
		return payment;
	}

	private int[] getMarkerNumber() {
		int[] marker = {-1};
		for(int i = 0; i < userLine.length; i++) {
			if(containsOnlyNumber(userLine[i])) {marker[0] = i; markerPosition = i;}
		}
		return marker;
	}
	
	/**
	 *	method containsOnlyNumber returns "true" if variable of the input(String text) has all symbols
	 *	only as digit,
	 *	in another case(otherwise) method returns "false"
	 */
	private boolean containsOnlyNumber(String text) {
		char[] ch = text.toCharArray();
		boolean logicTest = false;
		int logicCounter = 0;
			for(int i = 0; i < ch.length; i++) {
				if(Character.isDigit(ch[i]) || ch[i] == '-') {logicTest = true;logicCounter++;}
			}
		if(logicCounter == ch.length && logicTest == true)return logicTest;
		else return false;
	}
	
	public HashMap<String, int[]> getConfig() {		
		return marker;
	}
	
}
