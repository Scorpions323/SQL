package es.etg.dam.DAO.Oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.DAO.Alumno;
import es.etg.dam.DAO.InstitutoDAO;
import es.etg.dam.DAO.Profesor;

public class InstitutoOracleXeDAOImp implements InstitutoDAO {

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "usuario";
    private static final String PASS = "usuario";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ----------------- ALUMNOS -----------------
    @Override
    public void crearTablaAlumno() throws SQLException {
        String sql = """
            CREATE TABLE alumno (
                id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                nombre VARCHAR2(50),
                apellido VARCHAR2(50),
                edad NUMBER
            )
        """;
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            // Ignorar error si la tabla ya existe
            if (!e.getMessage().contains("ORA-00955")) {
                throw e;
            }
        }
    }

    @Override
    public void eliminarTablaAlumno() throws SQLException {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute("DROP TABLE alumno PURGE");
        }
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad")
                ));
            }
        }
        return lista;
    }

    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        String sql = "INSERT INTO alumno (nombre, apellido, edad) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarAlumnos(List<Alumno> alumnos) throws SQLException {
        String sql = "INSERT INTO alumno (nombre, apellido, edad) VALUES (?, ?, ?)";
        int[] results;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Alumno a : alumnos) {
                ps.setString(1, a.getNombre());
                ps.setString(2, a.getApellido());
                ps.setInt(3, a.getEdad());
                ps.addBatch();
            }
            results = ps.executeBatch();
            conn.commit();
        }
        int total = 0;
        for (int r : results) total += r;
        return total;
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        String sql = "UPDATE alumno SET nombre = ?, apellido = ?, edad = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            return ps.executeUpdate();
        }
    }

    // ----------------- PROFESORES -----------------
    @Override
    public void crearTablaProfesor() throws SQLException {
        String sql = """
            CREATE TABLE profesor (
                id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                nombre VARCHAR2(50),
                apellido VARCHAR2(50),
                departamento VARCHAR2(50)
            )
        """;
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("ORA-00955")) {
                throw e;
            }
        }
    }

    @Override
    public void eliminarTablaProfesor() throws SQLException {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute("DROP TABLE profesor PURGE");
        }
    }

    @Override
    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesor";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento")
                ));
            }
        }
        return lista;
    }

    @Override
    public int insertarProfesor(Profesor p) throws SQLException {
        String sql = "INSERT INTO profesor (nombre, apellido, departamento) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getDepartamento());
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarProfesores(List<Profesor> profesores) throws SQLException {
        String sql = "INSERT INTO profesor (nombre, apellido, departamento) VALUES (?, ?, ?)";
        int[] results;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Profesor p : profesores) {
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getApellido());
                ps.setString(3, p.getDepartamento());
                ps.addBatch();
            }
            results = ps.executeBatch();
            conn.commit();
        }
        int total = 0;
        for (int r : results) total += r;
        return total;
    }

    @Override
    public int actualizarProfesor(Profesor p) throws SQLException {
        String sql = "UPDATE profesor SET nombre = ?, apellido = ?, departamento = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            return ps.executeUpdate();
        }
    }
}
