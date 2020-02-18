package tema2;
import java.io.*;
import java.util.Scanner;

/**
 * Clasa ajutatoare pentru a facilita citirea din fisier
 */

public class ReadFromFile {
	Scanner in;
	
	/**
	 * Initializeaza stream-ul
	 */
	
	public ReadFromFile() {
		try {
			this.in = new Scanner(new File("therm.in"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!!!!!");
		}
	}
	
	/**
	 * Citeste un int
	 * @return int-ul citit sau o valoare aleasa pentru debugging
	 */
	
	public int readInt() {
		if (in.hasNext()) {
			int temp = in.nextInt();
			//in.nextLine();
			return temp;
		}
		return -69;
	}
	
	/**
	 * Citeste un string
	 * @return String-ul citit sau null
	 */
	
	public String readString() {
		if (in.hasNext()) {
			return in.next();
		}
		return null;
	}
	
	/**
	 * Citeste un double
	 * @return Double-ul citit sau o valoare aleasa pentru debugging
	 */
	
	public double readDouble() {
		if (in.hasNext()) {
			return in.nextDouble();
		}
		return -69;
	}
	
	/**
	 * Citeste un long
	 * @return Long-ul citit sau o valoare aleasa pentru debugging
	 */
	
	public long readLong() {
		if (in.hasNext()) {
			return in.nextLong();
		}
		return -69;
	}
	
	/**
	 * Verifica daca exista double
	 * @return Daca da sau nu
	 */
	
	public boolean hasNextDouble() {
		return in.hasNextDouble();
	}
	
	/**
	 * Citeste o linie
	 * @return Linia citita
	 */
	
	public String readNextLine() {
		return in.nextLine();
	}
	
	/**
	 * Verifica daca mai exista de citit ceva
	 * @return Daca da sau nu
	 */
	
	public boolean hasNext() {
		return in.hasNext();
	}
}

/**
 * Clasa ajutatoare pentru a facilita scrierea in fisier
 */

final class WriteToFile {
	static FileWriter out;
	static File file = new File("therm.out");
	static String buffer = "";
	
	/**
	 * Initializeaza file writer-ul
	 */
	
	public static void init() throws IOException {
		out = new FileWriter(file);
	}
	
	/**
	 * Adauga la buffer-ul de scriere
	 */
	
	public static void buildBuffer(String data) {
		buffer = buffer.concat(data);
	}
	
	/**
	 * Scrie buffer-ul
	 */
	
	public static void write() throws IOException {
		out.write(buffer);
		out.close();
	}
}