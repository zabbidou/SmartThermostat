package tema2;

import java.util.ArrayList;

/**
 * Clasa ajutatoare pentru a stoca toate camerele
 */

public class Database {
	static ArrayList<Room> data = new ArrayList<Room>();
	
	/**
	 * Adauga o camera in database
	 * @param Camera de adaugat
	 */
	
	public static void add(Room room) {
		data.add(room);		
	}
	
	/**
	 * Adauga o temperatura
	 * @param id Id-ul senzorului care a inregistrat valoarea
	 * @param temp Temperatura de adaugat
	 * @param hour Ora la care s-a inregistrat
	 */
	
	public static void insertTemp(String id, double temp, int hour) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getTempId().equals(id)) {
				data.get(i).addTemp(temp, hour);
			}
		}
	}
	
	/**
	 * Acelasi lucru ca la temperatura, dar cu umiditate
	 * @see #insertTemp(String, double, int)
	 */
	
	public static void insertHumid(String id, double humid, int hour) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getHumidId().equals(id)) {
				data.get(i).addHumid(humid, hour);
			}
		}
	}
	
	/**
	 * Cauta o camera
	 * @param name Numele camerei de cautat
	 */
	
	public static Room getRoomByName(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getName().equals(name)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Afiseaza valorile senzorului de temperatura
	 * @param id Id-ul senzorului de afisat
	 * @param start Start
	 * @param end End
	 */
	
	public static void listRoom(String id, int start, int end) {
		Room room = getRoomByName(id);
		
		while (start < end) {
			room.listHour(start);
			start++;
		}
	}
	
	/**
	 * Comanda trigger
	 * @param ref_temp Temperatura de referinta
	 * @return true - trebuie activat, false - nu trebuie
	 */
	
	public static boolean trigger(double ref_temp) {
		int hour;
		double area;
		double sum = 0;
		double total_area = 0;
		double value;
		
		for (int i = 0; i < data.size(); i++) {
			hour = data.get(i).getLastHour();
			value = data.get(i).getMinTemp(hour);
			area = data.get(i).getArea();
			value = value * area;
			sum = sum + value;
			total_area = total_area + area;
		}
				
		if ((sum / total_area < ref_temp)) {
			return true;
		}		

		return false;
	}
	
	/**
	 * trigger pentru umiditate
	 * @see #trigger(double)
	 */
	
	public static boolean triggerH(double ref_humid) {
		int hour;
		double area;
		double sum = 0;
		double total_area = 0;
		double value;
		
		if (ref_humid == -1) {
			return true;
		}
		
		for (int i = 0; i < data.size(); i++) {
			hour = data.get(i).getLastHourH();
			value = data.get(i).getMaxHumid(hour);
			area = data.get(i).getArea();
			value = value * area;
			sum = sum + value;
			total_area = total_area + area;
		}
				
		if ((sum / total_area < ref_humid)) {
			return true;
		}		

		return false;
	}
}
