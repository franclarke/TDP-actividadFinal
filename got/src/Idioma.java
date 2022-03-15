
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Idioma{

	public Idioma() {

		try (OutputStream output = new FileOutputStream("src/es_ES.properties")) {

			Properties prop = new Properties();

			prop.setProperty("cargarDir", "Cargar Directorio");
			prop.setProperty("comenzar", "Comenzar");
			prop.setProperty("titulo", "Viene el invierno");

			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}

		try (OutputStream output = new FileOutputStream("src/en_US.properties")){
			Properties prop = new Properties();

			prop.setProperty("cargarDir", "Upload Directory");
			prop.setProperty("comenzar", "Start");
			prop.setProperty("titulo", "Winter is Coming");

			prop.store(output, null);


		} catch (IOException io) {
			io.printStackTrace();
		}

	}
}

