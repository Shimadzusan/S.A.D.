package Sun.SAD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import database_unit.DatabaseInterface;
import database_unit.Launch_two;
import source_unit.*;

public class Recognize {
	//..databese config!!!
	/*
	 * здесь определяем структуру Б.Д.,
	 * а именно: какие б.д. будет иметь поля
	 * и связываем эти поля с фактурой из текстового файла
	 */
//..parametrs which must be incapsulated(configuration)
//.. for users text*
	
	String split = "\\r\\n";	//.. divider an users text, total notes for day (code 13 10 is new line(\\r\\n))
	String splitFacture = " ";	//.. divider in deals structure
	
//.. date-facture-info(!!!) main kinds!	there exist anyway!
	String formatDate = "00.00.0000, dd.mm.yyyy(..other customer formats)";
	String[] facture = {};
	String[] info = {"касса утро", "касса вечер", "зп"};
	
//.. for users database*
//..	параметры Б.Д., взаимодействие с полями классов?
//.. java-beans и другие необходимые ресурсы для работы д.б. генерируются автоматически после конфигурирования
	
//.. копия 350 сбер
//.. фнд 250
	String[] containsData = {"1", "2", "3"};
	String[] databaseColumn = {"date", "deal", "volume", "payment"}; //НО ЕСТЬ И ДРУГОЙ ТИП, ЭТО INFO!
	
	
//.. связываем неструктурированные данные клиента со структурированными данными в Б.Д.
	HashMap<String, String> linkage = new HashMap<String, String>();
	{
		linkage.put("date", null);
		linkage.put("deal", "0");	//.. сделка извлекается при помощи маркера 0
		linkage.put("volume", "1");	//.. объем извлекается при помощи маркера 1
		linkage.put("payment", "2");
	}
	
	HashMap<String, String> linkageTwo = new HashMap<String, String>();
	{
		linkageTwo.put("date", null);
		linkageTwo.put("begin_cash", "касса утро");	//.. сделка извлекается при помощи маркера 0
		linkageTwo.put("end_cash", "касса вечер");	//.. объем извлекается при помощи маркера 1
		linkageTwo.put("salary", "зп");
	}
//.. кпримеру понадобилась отдельная база только по никому и пультам, берем и создаем методом включения
//..end
	
	Recognize() {
		
	}
	
	Recognize(SourceInterface source, DatabaseInterface parameters) {
		System.out.println("..recognize");
		universalInit(source.getData(), parameters);
		
		/*recognition source and parameters*/
		/*	получаем параметры...
		 *	очень много параметров!
		 *	автоматически генерируем java_bean и другие необходимые ресурсы,
		 *	то есть: SAD003 и сопутствующие xml-конфиги, КАК??
		 *	..используем все это
		 *	запись в базу данных 
		 */
	}
	
	private void universalInit(String text, DatabaseInterface parameters) {
		String[] dayList = text.split(split);
		
//..========================================================OBJECT TEXT
		String date = dayList[0];	//..must be GET_DATE();
		List<String[]> facture = new ArrayList<String[]>();
		List<String> info = new ArrayList<String>();
				
		for(int i = 1; i < dayList.length; i++) {
			if(getInfo(dayList[i])) {
				facture.add(dayList[i].split(splitFacture));
			}
			else {
				info.add(dayList[i]);
			}
		}
//..========================================================END

//..========================================================OBJECT PARAMETRS	
//..writing to db!!!
		List<HashMap> list = new ArrayList<HashMap>();
//..FACTURE! получили методом ИСКЛЮЧЕНИЯ
		for(int i = 0; i < facture.size(); i++) {
			
			HashMap<String, String> to_list = new HashMap<String, String>();
			
			for(Entry<String, String> entry: linkage.entrySet()) {
				 String s = entry.getKey();		//..имя поля в д.б. 
				 if(entry.getValue() != null) {
					 int n = Integer.parseInt(entry.getValue());
					 	if(n < facture.get(i).length)to_list.put(s, facture.get(i)[n]);
				 }
				 //String t = entry.getValue();	//..номер позиции в сделке
				//.. Colision when T > facture.get(i).length
				 //for(int j = facture.get(i).length;
				 //int j = 5;
				 //if(t != null)j = Integer.parseInt(t);
				 //if(j < 2)to_list.put(s, facture.get(i)[j]);
			}			
			list.add(to_list);
		}
//.. Вторая база работать не будет, т.к. 10 строк сверху должны быть в параметрах, НО НЕ в этом методе!!!!			
//.. The code for second database "INFO"	получили методом ВКЛЮЧЕНИЯ
		for(int i = 0; i < info.size(); i++) {
			HashMap<String, String> to_list = new HashMap<String, String>();
			String value_to_database = "" + getNumber(info.get(i));
			//String[] marker = info.get(i).split(splitFacture);
			
			for(Entry<String, String> entry: linkageTwo.entrySet()) {
				 String s = entry.getKey();		//..имя поля в д.б.
				 
				 if(entry.getValue() != null && !getInfo(entry.getValue()) && info.get(i).contains(entry.getValue())) {
					 	to_list.put(s, value_to_database);
					 	System.out.println(s + " = " + value_to_database);					 	
				 }
			}
			list.add(to_list);
		}
		
		parameters.setStructuredData(list);
	}

	private boolean getInfo(String text) {
		boolean answer = true;
		for(int i = 0; i < info.length; i++) {
			if(text.contains(info[i]))answer = false;
		}
		return answer;
	}

	private String getDateFormat(String date) {
		String[] elements = date.split("\\.");
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
