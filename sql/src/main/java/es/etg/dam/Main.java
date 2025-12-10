package es.etg.dam;

import java.util.List;
import java.util.Scanner;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.Falta;
import es.etg.dam.DAO.InstitutoDAO;
import es.etg.dam.DAO.Mock.InstitutoMockDAOImp;
import es.etg.dam.DAO.Oracle.InstitutoOracleXeDAOImp;
import es.etg.dam.DAO.SQLite.InstitutoSQLiteDAOImp;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InstitutoDAO dao = null;

        try {
            // MENÚ 1: Tipo de conexión
            System.out.println("===== SELECCIONA EL TIPO DE CONEXIÓN =====");
            System.out.println("1. Mock");
            System.out.println("2. SQLite");
            System.out.println("3. Oracle");
            System.out.print("Elige una opción: ");
            int opcionConexion = Integer.parseInt(sc.nextLine());

            switch (opcionConexion) {
                case 1:
                    dao = new InstitutoMockDAOImp();
                    break;
                case 2:
                    dao = new InstitutoSQLiteDAOImp();
                    break;
                case 3:
                    dao = new InstitutoOracleXeDAOImp();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            int opcion = -1;

            while (opcion != 0) {

                // MENÚ 2: Operaciones
                System.out.println("\n===== MENÚ OPERACIONES =====");
                System.out.println("1. Crear tablas");
                System.out.println("2. Insertar en tabla Alumno");
                System.out.println("3. Insertar en tabla Falta");
                System.out.println("4. Actualizar Alumno");
                System.out.println("5. Actualizar Falta");
                System.out.println("6. Listar todos los alumnos");
                System.out.println("7. Listar alumnos con faltas");
                System.out.println("8. Listar faltas por alumno (consulta con parámetro)");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {

                    case 1: // Crear tablas
                        dao.crearTablaAlumno();
                        dao.crearTablaFalta();
                        System.out.println("Tablas creadas correctamente.");
                        break;

                    case 2: // Insertar Alumno
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());
                        Alumno a = new Alumno(0, nombre, apellido, edad);
                        dao.insertarAlumno(a);
                        System.out.println("Alumno insertado.");
                        break;

                    case 3: // Insertar Falta
                        System.out.print("ID del alumno: ");
                        int alumnoId = Integer.parseInt(sc.nextLine());
                        System.out.print("Día de la falta (ej: 2025-12-11): ");
                        String dia = sc.nextLine();
                        System.out.print("¿Justificada? (true/false): ");
                        boolean justificada = Boolean.parseBoolean(sc.nextLine());
                        Falta f = new Falta(0, alumnoId, dia, justificada);
                        dao.insertarFalta(f);
                        System.out.println("Falta insertada.");
                        break;

                    case 4: // Actualizar Alumno
                        System.out.print("ID del alumno a actualizar: ");
                        int idActualizar = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String ap = sc.nextLine();
                        System.out.print("Nueva edad: ");
                        int e = Integer.parseInt(sc.nextLine());
                        Alumno actualizado = new Alumno(idActualizar, n, ap, e);
                        dao.actualizarAlumno(actualizado);
                        System.out.println("Alumno actualizado.");
                        break;

                    case 5: // Actualizar Falta
                        System.out.print("ID de la falta a actualizar: ");
                        int idFalta = Integer.parseInt(sc.nextLine());
                        System.out.print("ID del alumno: ");
                        int idAl = Integer.parseInt(sc.nextLine());
                        System.out.print("Día: ");
                        String diaF = sc.nextLine();
                        System.out.print("Justificada (true/false): ");
                        boolean just = Boolean.parseBoolean(sc.nextLine());
                        Falta actualizarFalta = new Falta(idFalta, idAl, diaF, just);
                        dao.actualizarFalta(actualizarFalta);
                        System.out.println("Falta actualizada.");
                        break;

                    case 6: // Listar todos los alumnos
                        List<Alumno> listaAl = dao.listarAlumnos();
                        System.out.println("--- ALUMNOS ---");
                        for (Alumno alum : listaAl) {
                            System.out.println(alum);
                        }
                        break;

                    case 7: // Listar alumnos con faltas
                        List<Falta> listaFaltas = dao.listarFaltas();
                        System.out.println("--- ALUMNOS CON FALTAS ---");
                        for (Falta falt : listaFaltas) {
                            System.out.println(falt);
                        }
                        break;

                    case 8: // Consulta con parámetro
                        System.out.print("ID del alumno: ");
                        int idConsulta = Integer.parseInt(sc.nextLine());
                        List<Falta> faltasAlumno = dao.listarFaltasPorAlumno(idConsulta);
                        System.out.println("--- FALTAS DEL ALUMNO " + idConsulta + " ---");
                        for (Falta fa : faltasAlumno) {
                            System.out.println(fa);
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
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
