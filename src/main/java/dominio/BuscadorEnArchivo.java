package dominio;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

public class BuscadorEnArchivo {
    
    public static void encontrarIgualdad(String palabraBuscada, String direccionDelArchivo) throws IOException {
    	try (Stream<String> stream = Files.lines(Paths.get(direccionDelArchivo))) {
			if(stream.anyMatch(line -> palabraBuscada.equals(line))) {
				throw new PasswordInseguraException("La contaseña ingresada es insegura");
			}
				
		} catch(SecurityException e) {
			System.out.println(e.getMessage());
		}
    }

	public static void encontrarInclusion(String palabraBuscada, String direccionDelArchivo) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(direccionDelArchivo))) {
			if(stream.anyMatch(line -> palabraBuscada.contains(line))) {
				throw new PasswordInseguraException("La contaseña ingresada es insegura");
			}
			
		} catch(SecurityException e) {
			System.out.println(e.getMessage());
		}
	}
	
}