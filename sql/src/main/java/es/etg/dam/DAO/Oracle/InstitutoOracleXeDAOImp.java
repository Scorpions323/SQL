package es.etg.dam.DAO.Oracle;

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

public class InstitutoOracleXeDAOImp implements InstitutoDAO {

    private final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private final String DATABASE_USER = "usuario";
    private final String DATABASE_PASS = "usuario";
    private final Connection conn;

    public InstitutoOracleXeDAOImp() throws SQLException {
        conn = DriverManager.getConnection(URL, DATABASE_USER, DATABASE_PASS);
    }

    // TABLAS
    @Override
    public void crearTablaAlumno() throws SQLException {
        String sql = """
                CREATE TABLE alumnos (
                    id NUMBER GENERATED ALWAYS AS IDENTITY,
                    nombre VARCHAR2(50) NOT NULL,
                    apellido VARCHAR2(50) NOT NULL,
                    edad NUMBER,
                    CONSTRAINT pk_alumno PRIMARY KEY (id)
                )
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void crearTablaProfesor() throws SQLException {
        String sql = """
                CREATE TABLE profesores (
                    id NUMBER GENERATED ALWAYS AS IDENTITY,
                    idAlumno NUMBER,
                    nombre VARCHAR2(50) NOT NULL,
                    apellido VARCHAR2(50) NOT NULL,
                    departamento VARCHAR2(50),
                    CONSTRAINT pk_profesor PRIMARY KEY (id),
                    CONSTRAINT fk_profesor_alumno FOREIGN KEY (idAlumno)
                    REFERENCES alumnos(id) ON DELETE CASCADE
                )
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void eliminarTablaAlumno() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DROP TABLE alumnos CASCADE CONSTRAINTS")) {
            ps.execute();
        } catch (SQLException e) {
        }
    }

    @Override
    public void eliminarTablaProfesor() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DROP TABLE profesores CASCADE CONSTRAINTS")) {
            ps.execute();
        } catch (SQLException e) {
        }
    }

    // ALUMNOS
    @Override
    public int insertarAlumno(Alumno a) throws SQLException {
        String sql = "INSERT INTO alumnos(nombre, apellido, edad) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarAlumnos(List<Alumno> alumnos) throws SQLException {
        String sql = "INSERT INTO alumnos(nombre, apellido, edad) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Alumno a : alumnos) {
                ps.setString(1, a.getNombre());
                ps.setString(2, a.getApellido());
                ps.setInt(3, a.getEdad());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return alumnos.size();
    }

    @Override
    public int actualizarAlumno(Alumno a) throws SQLException {
        String sql = "UPDATE alumnos SET nombre = ?, apellido = ?, edad = ? WHERE id = ?";
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
        String sql = "DELETE FROM alumnos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad")));
            }
        }
        return lista;
    }

    // PROFESORES
    @Override
    public int insertarProfesor(Profesor p) throws SQLException {
        String sql = "INSERT INTO profesores(idAlumno, nombre, apellido, departamento) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdAlumno());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getDepartamento());
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertarProfesores(List<Profesor> profesores) throws SQLException {
        String sql = "INSERT INTO profesores(idAlumno, nombre, apellido, departamento) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Profesor p : profesores) {
                ps.setInt(1, p.getIdAlumno());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getApellido());
                ps.setString(4, p.getDepartamento());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return profesores.size();
    }

    @Override
    public int actualizarProfesor(Profesor p) throws SQLException {
        String sql = "UPDATE profesores SET idAlumno = ?, nombre = ?, apellido = ?, departamento = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdAlumno());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getDepartamento());
            ps.setInt(5, p.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int borrarProfesor(Profesor p) throws SQLException {
        String sql = "DELETE FROM profesores WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Profesor(
                        rs.getInt("id"),
                        rs.getInt("idAlumno"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento")));
            }
        }
        return lista;
    }

    @Override
    public List<Profesor> listarProfesoresPorAlumno(int alumnoId) throws SQLException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores WHERE idAlumno = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alumnoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Profesor(
                            rs.getInt("id"),
                            rs.getInt("idAlumno"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("departamento")));
                }
            }
        }
        return lista;
    }

    @Override
    public Profesor buscarProfesorPorId(int id) throws SQLException {
        String sql = "SELECT * FROM profesores WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Profesor(
                            rs.getInt("id"),
                            rs.getInt("idAlumno"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("departamento"));
                }
            }
        }
        return null;
    }
}
