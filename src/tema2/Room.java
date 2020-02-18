package tema2;

/**
 * Clasa pentru camera
 */

public class Room {
	/**
	 * Numele
	 */
	private String name;
	/**
	 * Senzorul de temperatura
	 */
	private Sensor temp;
	/**
	 * Senzorul de umiditate
	 */
	private Sensor humid;
	/**
	 * Suprafata
	 */
	private Double area;
	
	/**
	 * Constructor pentru cazul in care nu exista senzor de umiditate
	 */
	
	public Room(String name, String id, Double area) {
		this.temp = new Sensor(id);
		this.area = area;
		this.name = name;
	}
	
	/**
	 * Constructor pentru cazul in care exista senzor de umiditate
	 */
	
	public Room(String name, String idT, String idH, Double area) {
		this.temp = new Sensor(idT);
		this.humid = new Sensor(idH);
		this.area = area;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Room [name=" + name + ", " + temp + ", area=" + area + "]";
	}
	
	public String getTempId() {
		return temp.id;
	}
	
	public String getHumidId() {
		return humid.id;
	}
	
	public void addTemp(Double temp, int hour) {
		this.temp.add(temp, hour);
	}
	
	public void addHumid(Double humid, int hour) {
		this.humid.add(humid, hour);
	}
	
	public void listHour(int hour) {
		temp.listHour(hour);
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getMinTemp(int hour) {
		return temp.getMin(hour);
	}
	
	public double getMaxHumid(int hour) {
		return humid.getMax(hour);
	}
	
	public double getArea() {
		return this.area;
	}
	
	/**
	 * @see Sensor#getSizeInHour(int)
	 */
	
	public int getSizeInHour(int hour) {
		return temp.getSizeInHour(hour);
	}
	
	/**
	 * Calculeaza ultima ora la care sunt inregistrate date
	 * @return Ultima ora sau -1
	 */
	
	public int getLastHour() {
		for (int i = 0; i < 24; i++) {
			if (temp.getSizeInHour(i) > 0) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @see Room#getLastHour()
	 */
	
	public int getLastHourH() {
		for (int i = 0; i < 24; i++) {
			if (humid.getSizeInHour(i) > 0) {
				return i;
			}
		}
		return -1;
	}
	
}
