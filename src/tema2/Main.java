// Gherman Maria Irina @ 324 CB

package tema2;

import java.io.IOException;

/**
 * Clasa principala pentru main
 */

public class Main {
	public static void main(String[] args) {
		int rooms;
		long ref_time;
		double ref_temp;
		double ref_humid = -1;
		String command;
		String id;
		String name;
		double area;
		long time;
		double value;
		double hour = -1;
		double hour_start;
		double hour_end;
		String buffer;
		String[] tokens = new String[10];
		Room room;
		
		ReadFromFile in = new ReadFromFile();
		
		try {
			WriteToFile.init();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		buffer = in.readNextLine();
		tokens = buffer.split(" ");
		rooms = Integer.parseInt(tokens[0]);
		ref_temp = Double.parseDouble(tokens[1]);
		
		if (tokens.length == 4) {			
			ref_humid = Double.parseDouble(tokens[2]);
			ref_time = Long.parseLong(tokens[3]);
		} else {
			ref_time = Long.parseLong(tokens[2]);
		}
		// Citeste numarul de camere date
		for (int i = 0; i < rooms; i++) {
			name = in.readString();
			id = in.readString();
			area = in.readDouble();
			
			if (ref_humid == -1) {
				room = new Room(name, id, area);
			} else {
				room = new Room(name, id, id, area);
			}

			Database.add(room);
		}
		// Cat timp exista comenzi, le citim si le interpretam
		while (in.hasNext()) {
			command = in.readString();

			if (command.contains("OBSERVE")) {
				id = in.readString();
				time = in.readLong();
				value = in.readDouble();
				hour = (double)(ref_time - time) / 3600;
				// Daca avem ora valida
				if (hour > 0 && hour < 24) {
					// Daca trebuie sa inregistram temperatura
					if (command.equals("OBSERVE")) {
						Database.insertTemp(id, value, (int)hour);
					}
					// Daca trebuie sa inregistram umiditate
					if (command.equals("OBSERVEH")) {
						Database.insertHumid(id, value, (int)hour);
					}
							
				}
			}
			
			if (command.equals("TRIGGER")) {
				in.readString();
				// Verificam conditia umiditatii
				if (Database.triggerH(ref_humid)) {
					if (Database.trigger(ref_temp)) {
						WriteToFile.buildBuffer("YES\n");
					} else {
						WriteToFile.buildBuffer("NO\n");
					}
				} else {
					WriteToFile.buildBuffer("NO\n");
				}
			}
			
			if (command.equals("LIST")) {
				name = in.readString();
				time = in.readLong();
				hour_end = (double)(ref_time - time) / 3600;
				time = in.readLong();
				hour_start = (double)(ref_time - time) / 3600;

				WriteToFile.buildBuffer(name);
				Database.listRoom(name, (int)hour_start, (int)hour_end);
				WriteToFile.buildBuffer("\n");
			}
			
			if (command.equals("TEMPERATURE")) {
				ref_temp = in.readDouble();
			}
		}
				
		try {
			WriteToFile.write();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
