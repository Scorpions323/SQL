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
            // MENÚ 1 - Tipo de Conexión
            System.out.println("===== SELECCIONA EL TIPO DE CONEXIÓN =====");
            System.out.println("1. Mock");
            System.out.println("2. SQLite");
            System.out.println("3. Oracle");
            System.out.print("Elige una opción: ");
            int tipo = Integer.parseInt(sc.nextLine());

            switch (tipo) {
                case 1 -> dao = new InstitutoMockDAOImp();
                case 2 -> dao = new InstitutoSQLiteDAOImp();
                case 3 -> dao = new InstitutoOracleXeDAOImp();
                default -> {
                    System.out.println("Opción no válida.");
                    return;
                }
            }

            int opcion = -1;

            // MENÚ 2 - Operaciones
            while (opcion != 0) {
                System.out.println("\n===== MENÚ OPERACIONES =====");
                System.out.println("1. Crear tablas");
                System.out.println("2. Insertar Alumno");
                System.out.println("3. Insertar Profesor");
                System.out.println("4. Actualizar Alumno");
                System.out.println("5. Actualizar Profesor");
                System.out.println("6. Listar Alumnos");
                System.out.println("7. Listar Profesores");
                System.out.println("8. Listar Alumnos con sus Profesores");
                System.out.println("9. Listar Profesores por alumnoId");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> {
                        dao.crearTablaAlumno();
                        dao.crearTablaProfesor();
                        System.out.println("Tablas creadas.");
                    }
                    case 2 -> {
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
                    case 3 -> {
                        System.out.print("AlumnoId (FK): ");
                        int alumnoId = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre profesor: ");
                        String pnom = sc.nextLine();
                        System.out.print("Apellido profesor: ");
                        String pap = sc.nextLine();
                        System.out.print("Departamento: ");
                        String dep = sc.nextLine();
                        Profesor p = new Profesor(0, alumnoId, pnom, pap, dep);
                        dao.insertarProfesor(p);
                        System.out.println("Profesor insertado.");
                    }
                    case 4 -> {
                        System.out.print("ID alumno a actualizar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Apellido: ");
                        String ap = sc.nextLine();
                        System.out.print("Edad: ");
                        int e = Integer.parseInt(sc.nextLine());
                        Alumno aa = new Alumno(id, n, ap, e);
                        dao.actualizarAlumno(aa);
                        System.out.println("Alumno actualizado.");
                    }
                    case 5 -> {
                        System.out.print("ID profesor a actualizar: ");
                        int idp = Integer.parseInt(sc.nextLine());
                        System.out.print("AlumnoId (FK): ");
                        int aid = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre: ");
                        String np = sc.nextLine();
                        System.out.print("Apellido: ");
                        String app = sc.nextLine();
                        System.out.print("Departamento: ");
                        String dp = sc.nextLine();
                        Profesor pp = new Profesor(idp, aid, np, app, dp);
                        dao.actualizarProfesor(pp);
                        System.out.println("Profesor actualizado.");
                    }
                    case 6 -> {
                        List<Alumno> alumnos = dao.listarAlumnos();
                        System.out.println("--- ALUMNOS ---");
                        for (Alumno al : alumnos)
                            System.out.println(al);
                    }
                    case 7 -> {
                        List<Profesor> profes = dao.listarProfesores();
                        System.out.println("--- PROFESORES ---");
                        for (Profesor pr : profes)
                            System.out.println(pr);
                    }
                    case 8 -> {
                        List<Alumno> alumnos = dao.listarAlumnos();
                        System.out.println("--- ALUMNOS CON SUS PROFESORES ---");
                        for (Alumno al : alumnos) {
                            System.out.println("\nAlumno: " + al.getNombre() + " " + al.getApellido() + " (id="
                                    + al.getId() + ")");
                            List<Profesor> profs = dao.listarProfesoresPorAlumno(al.getId());
                            if (profs.isEmpty())
                                System.out.println("  (sin profesores)");
                            else
                                for (Profesor pr : profs)
                                    System.out.println("  - " + pr);
                        }
                    }
                    case 9 -> {
                        System.out.print("Introduce alumnoId: ");
                        int aid = Integer.parseInt(sc.nextLine());
                        List<Profesor> profs = dao.listarProfesoresPorAlumno(aid);
                        System.out.println("--- PROFESORES del alumnoId=" + aid + " ---");
                        for (Profesor pr : profs)
                            System.out.println(pr);
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
