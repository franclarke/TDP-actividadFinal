
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Logica {

	public Logica() {

	}

	public HashMap<String, Integer> cuentaPalabras(String texto, HashMap<String, Integer> diccionario) throws FileNotFoundException {

		String saltador = " .,;\n\r\t";
		Scanner teclado = new Scanner(new File(texto));
		String p;

		for (; teclado.hasNextLine();) {

			StringTokenizer linea = new StringTokenizer(teclado.nextLine(), saltador);

			for (; linea.hasMoreTokens();) {

				p = linea.nextToken().toLowerCase();

				if (!diccionario.containsKey(p)) {
					diccionario.put(p, 1);

				} else {
					diccionario.put(p, diccionario.get(p) + 1);
				}
			}

		}
		return diccionario.entrySet()
				.stream()
				.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public int totalPalabras(String text) {
		int tot=0;
		try {
			String saltador = " .,;\n\r\t";
			Scanner teclado;

			teclado = new Scanner(new File(text));
			for (; teclado.hasNextLine();) {
				StringTokenizer linea = new StringTokenizer(teclado.nextLine(), saltador);
				for (; linea.hasMoreTokens();) {
					tot+=1;
					linea.nextToken();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return tot;
	}
}