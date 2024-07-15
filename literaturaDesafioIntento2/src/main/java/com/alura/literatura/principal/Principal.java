package com.alura.literatura.principal;

import com.alura.literatura.model.Datos;
import com.alura.literatura.model.DatosLibros;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos( );


    public void muestraElMenu(){
//        var opcion = -1;
//        while (opcion != 0) {
//            var menu = """
//                    1 - Buscar libro por titulo
//                    2 - Listar libros regristrados
//                    3 - Listar autores registrados
//                    4 - Listar autores vivos en un determinado ano
//                    5 - Listar libros por idioma
//                    0 - Salir
//                    """;
//            System.out.println(menu);
//            opcion = teclado.nextInt();
//            teclado.nextLine();
//
//            switch (opcion) {
//                case 1:
//                    //buscarPorTitulo();
//                    break;
//
//                case 0:
//                    System.out.println("Cerrando la aplicación...");
//                    break;
//                default:
//                    System.out.println("Opción inválida");
//            }
//        }

        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        System.out.println(datos);

        //Busqueda de libros por nombre
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        }else{
            System.out.println("Libro no encontrado");
        }
    }



//    private Datos getDatosLibros(){
//        var json = consumoAPI.obtenerDatos(URL_BASE);
//        System.out.println(json);
//        Datos datos = conversor.obtenerDatos(json, Datos.class);
//        System.out.println(datos);
//        return datos;
//
//    }


}
