package es.etg.dam;

import java.util.List;
import java.util.Scanner;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.InstitutoDAO;
import es.etg.dam.DAO.Mock.InstitutoMockDAOImp;
import es.etg.dam.DAO.Oracle.InstitutoOracleXeDAOImp;
import es.etg.dam.DAO.Profesor;
import es.etg.dam.DAO.SQLite.InstitutoSQLiteDAOImp;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InstitutoDAO dao = null;

        try {
            // MENÚ 1: TIPO DE CONEXIÓN
            System.out.println("===== SELECCIONA EL TIPO DE CONEXIÓN =====");
            System.out.println("1. Mock");
            System.out.println("2. SQLite");
            System.out.println("3. Oracle");
            System.out.print("Elige una opción: ");
            int opcionConexion = Integer.parseInt(sc.nextLine());

            switch (opcionConexion) {
                case 1 ->
                    dao = new InstitutoMockDAOImp();
                case 2 ->
                    dao = new InstitutoSQLiteDAOImp();
                case 3 ->
                    dao = new InstitutoOracleXeDAOImp();
                default -> {
                    System.out.println("Opción no válida.");
                    return;
                }
            }

            int opcion = -1;
            while (opcion != 0) {

                // MENÚ 2: OPERACIONES
                System.out.println("\n===== MENÚ OPERACIONES =====");
                System.out.println("1. Crear tablas");
                System.out.println("2. Insertar alumno");
                System.out.println("3. Insertar profesor");
                System.out.println("4. Actualizar alumno");
                System.out.println("5. Actualizar profesor");
                System.out.println("6. Listar todos los alumnos");
                System.out.println("7. Listar todos los profesores");
                System.out.println("8. Buscar profesor por ID");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {

                    case 1 -> { // CREAR TABLAS
                        dao.crearTablaAlumno();
                        dao.crearTablaProfesor();
                        System.out.println("Tablas creadas correctamente.");
                    }

                    case 2 -> { // INSERTAR ALUMNO
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());
                        Alumno a = new Alumno(0, nombre, apellido, edad);
                        dao.insertarAlumno(a);
                        System.out.println("Alumno insertado.");
                    }

                    case 3 -> { // INSERTAR PROFESOR
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String ap = sc.nextLine();
                        System.out.print("Departamento: ");
                        String dep = sc.nextLine();
                        Profesor p = new Profesor(0, n, ap, dep);
                        dao.insertarProfesor(p);
                        System.out.println("Profesor insertado.");
                    }

                    case 4 -> { // ACTUALIZAR ALUMNO
                        System.out.print("ID del alumno a actualizar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String ap = sc.nextLine();
                        System.out.print("Nueva edad: ");
                        int e = Integer.parseInt(sc.nextLine());
                        Alumno actualizado = new Alumno(id, n, ap, e);
                        dao.actualizarAlumno(actualizado);
                        System.out.println("Alumno actualizado.");
                    }

                    case 5 -> { // ACTUALIZAR PROFESOR
                        System.out.print("ID del profesor a actualizar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String ap = sc.nextLine();
                        System.out.print("Nuevo departamento: ");
                        String dep = sc.nextLine();
                        Profesor actualizado = new Profesor(id, n, ap, dep);
                        dao.actualizarProfesor(actualizado);
                        System.out.println("Profesor actualizado.");
                    }

                    case 6 -> { // LISTAR ALUMNOS
                        List<Alumno> alumnos = dao.listarAlumnos();
                        System.out.println("--- ALUMNOS ---");
                        alumnos.forEach(System.out::println);
                    }

                    case 7 -> { // LISTAR PROFESORES
                        List<Profesor> profesores = dao.listarProfesores();
                        System.out.println("--- PROFESORES ---");
                        profesores.forEach(System.out::println);
                    }

                    case 0 ->
                        System.out.println("Saliendo...");

                    default ->
                        System.out.println("Opción no válida.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
