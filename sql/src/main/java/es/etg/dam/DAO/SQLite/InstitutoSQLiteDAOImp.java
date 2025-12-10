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
import es.etg.dam.DAO.Falta;
import es.etg.dam.DAO.InstitutoDAO;

public class InstitutoSQLiteDAOImp implements InstitutoDAO {

    private static final String DATABASE_NAME = "/es/etg/dam/Instituto.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private final Connection conn;

    public InstitutoSQLiteDAOImp() throws Exception {
        URL resource = InstitutoSQLiteDAOImp.class.getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        conn = DriverManager.getConnection(url);
    }

    // ALUMNO
    @Override
    public void crearTablaAlumno() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS alumno (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "edad INTEGER)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.execute();
        }
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
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
                Alumno a = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"));
                alumnos.add(a);
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
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Alumno a : listaAlumnos) {
                ps.setString(1, a.getNombre());
                ps.setString(2, a.getApellido());
                ps.setInt(3, a.getEdad());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.setAutoCommit(true);
        }
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

    // FALTA
    @Override
    public void crearTablaFalta() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS falta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "alumnoId INTEGER NOT NULL," +
                "dia TEXT NOT NULL," +
                "justificada INTEGER DEFAULT 0," +
                "FOREIGN KEY(alumnoId) REFERENCES alumno(id))";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.execute();
        }
    }

    @Override
    public void eliminarTablaFalta() throws Exception {
        try (PreparedStatement st = conn.prepareStatement("DROP TABLE IF EXISTS falta")) {
            st.execute();
        }
    }

    @Override
    public List<Falta> listarFaltas() throws SQLException {
        List<Falta> faltas = new ArrayList<>();
        String sql = "SELECT * FROM falta";
        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Falta f = new Falta(
                        rs.getInt("id"),
                        rs.getInt("alumnoId"),
                        rs.getString("dia"),
                        rs.getInt("justificada") != 0);
                faltas.add(f);
            }
        }
        return faltas;
    }

    @Override
    public List<Falta> listarFaltasPorAlumno(int alumnoId) throws SQLException {
        List<Falta> faltas = new ArrayList<>();
        String sql = "SELECT * FROM falta WHERE alumnoId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alumnoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Falta f = new Falta(
                            rs.getInt("id"),
                            rs.getInt("alumnoId"),
                            rs.getString("dia"),
                            rs.getInt("justificada") != 0);
                    faltas.add(f);
                }
            }
        }
        return faltas;
    }

    @Override
    public int insertarFalta(Falta f) throws SQLException {
        String sql = "INSERT INTO falta(alumnoId, dia, justificada) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, f.getAlumnoId());
            ps.setString(2, f.getDia());
            ps.setInt(3, f.isJustificada() ? 1 : 0);
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarFaltas(List<Falta> listaFaltas) throws SQLException {
        String sql = "INSERT INTO falta(alumnoId, dia, justificada) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Falta f : listaFaltas) {
                ps.setInt(1, f.getAlumnoId());
                ps.setString(2, f.getDia());
                ps.setInt(3, f.isJustificada() ? 1 : 0);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.setAutoCommit(true);
        }
        return listaFaltas.size();
    }

    @Override
    public int actualizarFalta(Falta f) throws SQLException {
        String sql = "UPDATE falta SET alumnoId = ?, dia = ?, justificada = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, f.getAlumnoId());
            ps.setString(2, f.getDia());
            ps.setInt(3, f.isJustificada() ? 1 : 0);
            ps.setInt(4, f.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int borrarFalta(Falta f) throws SQLException {
        String sql = "DELETE FROM falta WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, f.getId());
            return ps.executeUpdate();
        }
    }
}
