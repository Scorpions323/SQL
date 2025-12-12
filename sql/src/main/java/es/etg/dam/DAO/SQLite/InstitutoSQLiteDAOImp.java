package es.etg.dam.DAO.SQLite;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.InstitutoDAO;
import es.etg.dam.DAO.Profesor;

public class InstitutoSQLiteDAOImp implements InstitutoDAO {

    private static final String DATABASE_NAME = "/es/etg/dam/mybasedatos.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private final Connection conn;

    public InstitutoSQLiteDAOImp() throws Exception {
        URL resource = InstitutoSQLiteDAOImp.class.getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        conn = DriverManager.getConnection(url);
    }

    // TABLAS

    @Override
    public void crearTablaAlumno() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS alumnos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    edad INTEGER
                )
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void crearTablaProfesor() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS profesores (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    idAlumno INTEGER,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    departamento TEXT,
                    FOREIGN KEY(idAlumno) REFERENCES alumnos(id) ON DELETE CASCADE
                )
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void eliminarTablaAlumno() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DROP TABLE IF EXISTS alumnos");
        ps.execute();
        ps.close();
    }

    @Override
    public void eliminarTablaProfesor() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DROP TABLE IF EXISTS profesores");
        ps.execute();
        ps.close();
    }

    // ALUMNOS

    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        String sql = "INSERT INTO alumnos(nombre, apellido, edad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getApellido());
        ps.setInt(3, a.getEdad());
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    @Override
    public int insertarAlumnos(List<Alumno> alumnos) throws SQLException {
        String sql = "INSERT INTO alumnos(nombre, apellido, edad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        for (Alumno a : alumnos) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            ps.addBatch();
        }
        int[] resultados = ps.executeBatch();
        conn.setAutoCommit(true);
        ps.close();
        return resultados.length;
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        String sql = "UPDATE alumnos SET nombre = ?, apellido = ?, edad = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getApellido());
        ps.setInt(3, a.getEdad());
        ps.setInt(4, a.getId());
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    @Override
    public int borrarAlumno(Alumno a) throws SQLException {
        String sql = "DELETE FROM alumnos WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a.getId());
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Alumno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("edad")));
        }
        rs.close();
        ps.close();
        return lista;
    }

    // PROFESORES

    @Override
    public int insertarProfesor(Profesor p) throws SQLException {
        String sql = "INSERT INTO profesores(idAlumno, nombre, apellido, departamento) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, p.getIdAlumno());
        ps.setString(2, p.getNombre());
        ps.setString(3, p.getApellido());
        ps.setString(4, p.getDepartamento());
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    @Override
    public int insertarProfesores(List<Profesor> profesores) throws SQLException {
        String sql = "INSERT INTO profesores(idAlumno, nombre, apellido, departamento) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        for (Profesor p : profesores) {
            ps.setInt(1, p.getIdAlumno());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getDepartamento());
            ps.addBatch();
        }
        int[] resultados = ps.executeBatch();
        conn.setAutoCommit(true);
        ps.close();
        return resultados.length;
    }

    @Override
    public int actualizarProfesor(Profesor p) throws SQLException {
        String sql = "UPDATE profesores SET idAlumno = ?, nombre = ?, apellido = ?, departamento = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, p.getIdAlumno());
        ps.setString(2, p.getNombre());
        ps.setString(3, p.getApellido());
        ps.setString(4, p.getDepartamento());
        ps.setInt(5, p.getId());
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    @Override
    public int borrarProfesor(Profesor p) throws SQLException {
        String sql = "DELETE FROM profesores WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, p.getId());
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    @Override
    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Profesor(
                    rs.getInt("id"),
                    rs.getInt("idAlumno"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("departamento")));
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public List<Profesor> listarProfesoresPorAlumno(int alumnoId) throws SQLException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores WHERE idAlumno = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, alumnoId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Profesor(
                    rs.getInt("id"),
                    rs.getInt("idAlumno"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("departamento")));
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public Profesor buscarProfesorPorId(int id) throws SQLException {
        Profesor p = null;
        String sql = "SELECT * FROM profesores WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new Profesor(
                    rs.getInt("id"),
                    rs.getInt("idAlumno"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("departamento"));
        }
        rs.close();
        ps.close();
        return p;
    }
}
