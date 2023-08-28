package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Huesped;

import factory.ConnectionFactory;

public class HuespedDao {
    ConnectionFactory factory;
    private Connection con;

    public HuespedDao() {
        factory = new ConnectionFactory();
        con = factory.recuperaConexion();
    }

    public List<Huesped> buscar(String text) {
        List<Huesped> resultado = new ArrayList<>();
        String consulta = "SELECT * FROM huespedes WHERE 1=1 ";
        if (text != null && !"".equals(text)) {
            consulta += "AND apellido=?";
        }

        try (PreparedStatement statement = con
                .prepareStatement(consulta)) {
            if (text != null && !"".equals(text)) {
                statement.setString(1, text);
            }
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Huesped huesped = new Huesped(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getDate("fecha_nacimiento").toLocalDate(),
                        resultSet.getString("nacionalidad"),
                        resultSet.getString("telefono"),
                        resultSet.getInt("id_reserva"));
                resultado.add(huesped);
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Huesped huesped) {
        try (PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
                + "nombre = ? , apellido = ? , fecha_nacimiento = ?, "
                + "nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id= ?;");) {
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());
            statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
            statement.setString(4, huesped.getNacionalidad());
            statement.setString(5, huesped.getTelefono());
            statement.setInt(6, huesped.getIdReserva());
            statement.setInt(7, huesped.getId());
            statement.execute();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try (PreparedStatement statement = con.prepareStatement("DELETE"
                + " FROM huespedes WHERE ID = ?")) {
            statement.setInt(1, id);
            statement.execute();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean guardar(Huesped huesped) {
        try (PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes"
                + "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) "
                + "VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());
            statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
            statement.setString(4, huesped.getNacionalidad());
            statement.setString(5, huesped.getTelefono());
            statement.setInt(6, huesped.getIdReserva());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                huesped.setId(resultSet.getInt(1));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            // throw new RuntimeException(e);
        }
    }

}
