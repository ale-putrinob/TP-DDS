package dominio.buscador;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import dominio.validacionPassword.*;

public class BuscadorEnArchivo {
    
    public static boolean encontrarIgualdad(String palabraBuscada, String direccionDelArchivo) {
    	Condicion condicion = (value, value2) -> value.equals(value2);
    	
    	return encontrar(palabraBuscada, direccionDelArchivo, condicion);
    }

	public static boolean encontrarInclusion(String palabraBuscada, String direccionDelArchivo) {
		Condicion condicion = (value, value2) -> value.contains(value2);
		
		return encontrar(palabraBuscada, direccionDelArchivo, condicion);
	}
	
	private static boolean encontrar(String palabraBuscada, String direccionDelArchivo, Condicion condicion) {
		try (Stream<String> stream = Files.lines(Paths.get(direccionDelArchivo))) {
			return stream.anyMatch(line -> condicion.comparacion(palabraBuscada,line));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}

