/*Escribir un programa de consola que permite chatear de la siguiente forma:

1. Se tienen que ejecutar varios procesos del mismo programa

2. Todos al inicializar, tienen que indicar el nombre de la persona

3. Por la consola tiene que poder ingresar 2 opciones, escribir un mensaje o leer todos los

mensajes

4. cuando elige escribir mensaje, se ingresa el mismo y se escribe en el archivo

5. otro proceso debe leer todos los mensajes y ver lo que escribio el anterior

6. El proceso anterior se debe repetir todas las veces que uno quiera

7. Vale aclarar, que no se leen automáticamente los mensajes, uno debe elegir dicha

opción.*/




package inicio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Chat {
	private static  String archivo_chat = "chat.txt";
    private static  String usuario1 = "Mariana";
    private static  String usuario2 = "Valentina";

    public static void main(String[] args) {
        System.out.println("Bienvenido a la aplicación de chat!");

        List<String> mensajes = new ArrayList<>();
        String usuarioActual = usuario1;

        while (true) {
            ejecutarSesion(usuarioActual, mensajes);
            if (usuarioActual.equals(usuario1)) {
                usuarioActual = usuario2;
            } else {
                usuarioActual = usuario1;
            }
        }
    }

    private static void ejecutarSesion(String nombreUsuario, List<String> mensajes) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu(nombreUsuario);
            int opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su mensaje: ");
                    String mensaje = scanner.nextLine();
                    escribirMensaje(nombreUsuario, mensaje, mensajes);                   
                    return;
                case 2:
                    leerMensajes(mensajes);
                    break;
                case 3:
                    System.out.println("Fin del chat. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private static void mostrarMenu(String nombreUsuario) {
        System.out.println("[" + nombreUsuario + "]");
        System.out.println("1. Escribir mensaje");
        System.out.println("2. Leer mensajes");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");             
    }

    private static int leerOpcion(Scanner scanner) {
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        return opcion;
    }

    private static void escribirMensaje(String nombre, String mensaje, List<String> mensajes) {
        try {
            mensajes.add(nombre + ": " + mensaje);
            Files.write(Path.of(archivo_chat), mensajes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void leerMensajes(List<String> mensajes) {
        if (mensajes.isEmpty()) {
            System.out.println("No hay mensajes disponibles.");
        } else {
            System.out.println("----- Mensajes -----");
            mensajes.forEach(System.out::println);
            System.out.println("---------------------");
            System.out.println();
        }
    }
}
