package tema2;

import java.util.*;

/**
 * Clasa senzor
 */

public class Sensor {
	/**
	 * Id-ul senzorului
	 */
	String id;
	/**
	 * Un arraylist de tree-uri
	 * Arraylist -> cele 24 de intervale de ora
	 * Treeset -> temperaturile din intervalul x
	 */
	ArrayList<TreeSet<Double>> day;

	public Sensor(String id) {
		this.id = id;
		day = new ArrayList<TreeSet<Double>>(24);
		
		for (int i = 0; i < 24; i++) {
			day.add(new TreeSet<Double>());
		}
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + "]";
	}
	
	/**
	 * Adauga o valoare (temperatura sau umiditate)
	 * @param value Valoarea
	 * @param hour Ora la care trebuie adaugata
	 */
	
	public void add(Double value, int hour) {
		day.get(hour).add(value);
	}
	
	/**
	 * Afiseaza tot bucket-ul
	 * @param Ora de afisat
	 */
	
	public void listHour(int hour) {
		TreeSet<Double> temp = new TreeSet<Double>();
		int size = day.get(hour).size();
		
		for (int i = 0; i < size; i++) {
			Double temp_value = day.get(hour).first();
			WriteToFile.buildBuffer(" " + String.format("%.2f", temp_value));
			day.get(hour).remove(temp_value);
			temp.add(temp_value);
		}

		day.set(hour, temp);
	}
	
	public double getMin(int hour) {
		return day.get(hour).first();
	}
	
	public double getMax(int hour) {
		return day.get(hour).last();
	}
	
	/**
	 * @return Numarul de valori din ora data
	 */
	
	public int getSizeInHour(int hour) {
		return day.get(hour).size();
	}
	
}
