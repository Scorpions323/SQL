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

    // ==================== ALUMNO ====================

    @Override
    public void crearTablaAlumno() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS alumno (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    edad INTEGER
                )
                """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.execute();
        }
    }

    @Override
    public void eliminarTablaAlumno() throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("DROP TABLE IF EXISTS alumno")) {
            st.execute();
        }
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alumnos.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad")
                ));
            }
        }
        return alumnos;
    }

    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        String sql = "INSERT INTO alumno(nombre, apellido, edad) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarAlumnos(List<Alumno> listaAlumnos) throws SQLException {
        String sql = "INSERT INTO alumno(nombre, apellido, edad) VALUES (?, ?, ?)";
        boolean autoCommitPrev = conn.getAutoCommit();
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Alumno a : listaAlumnos) {
                ps.setString(1, a.getNombre());
                ps.setString(2, a.getApellido());
                ps.setInt(3, a.getEdad());
                ps.addBatch();
            }
            ps.executeBatch();
        }

        conn.setAutoCommit(autoCommitPrev);
        return listaAlumnos.size();
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        String sql = "UPDATE alumno SET nombre = ?, apellido = ?, edad = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            ps.setInt(4, a.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int borrarAlumno(Alumno a) throws SQLException {
        String sql = "DELETE FROM alumno WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            return ps.executeUpdate();
        }
    }

    // ==================== PROFESOR ====================

    @Override
    public void crearTablaProfesor() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS profesor (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    departamento TEXT NOT NULL
                )
                """;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.execute();
        }
    }

    @Override
    public void eliminarTablaProfesor() throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("DROP TABLE IF EXISTS profesor")) {
            st.execute();
        }
    }

    @Override
    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesor";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                profesores.add(new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento")
                ));
            }
        }
        return profesores;
    }

    @Override
    public int insertarProfesor(Profesor p) throws SQLException {
        String sql = "INSERT INTO profesor(nombre, apellido, departamento) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getDepartamento());
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarProfesores(List<Profesor> lista) throws SQLException {
        String sql = "INSERT INTO profesor(nombre, apellido, departamento) VALUES (?, ?, ?)";
        boolean autoCommitPrev = conn.getAutoCommit();
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Profesor p : lista) {
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getApellido());
                ps.setString(3, p.getDepartamento());
                ps.addBatch();
            }
            ps.executeBatch();
        }

        conn.setAutoCommit(autoCommitPrev);
        return lista.size();
    }

    @Override
    public int actualizarProfesor(Profesor p) throws SQLException {
        String sql = "UPDATE profesor SET nombre = ?, apellido = ?, departamento = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getDepartamento());
            ps.setInt(4, p.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int borrarProfesor(Profesor p) throws SQLException {
        String sql = "DELETE FROM profesor WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            return ps.executeUpdate();
        }
    }
}
