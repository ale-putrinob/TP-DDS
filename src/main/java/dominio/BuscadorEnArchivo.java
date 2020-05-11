package dominio;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

public class BuscadorEnArchivo {
    
    public static void encontrarIgualdad(String palabraBuscada, String direccionDelArchivo) throws IOException {
    	Condicion condicion = (value, value2) -> value.equals(value2);
    	
    	encontrar(palabraBuscada, direccionDelArchivo, condicion);
    }

	public static void encontrarInclusion(String palabraBuscada, String direccionDelArchivo) throws IOException {
		Condicion condicion = (value, value2) -> value.contains(value2);
		
		encontrar(palabraBuscada, direccionDelArchivo, condicion);
	}
	
	private static void encontrar(String palabraBuscada, String direccionDelArchivo, Condicion condicion) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(direccionDelArchivo))) {
			if(stream.anyMatch(line -> condicion.comparacion(palabraBuscada,line))) {
				throw new PasswordInseguraException("La contaseña ingresada es insegura");
			}
			
		} catch(SecurityException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

