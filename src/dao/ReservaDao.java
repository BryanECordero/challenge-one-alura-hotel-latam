package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Reserva;

public class ReservaDao {
    ConnectionFactory factory;
    private Connection con;

    public ReservaDao() {
        factory = new ConnectionFactory();
        con = factory.recuperaConexion();
    }

    public List<Reserva> buscar(int id) {
        List<Reserva> resultado = new ArrayList<>();
        String consulta = "SELECT * FROM reservas WHERE 1=1 ";
        if (id > 0) {
            consulta += "AND id=?";
        }

        try (PreparedStatement statement = con
                .prepareStatement(consulta)) {
            if (id > 0) {
                statement.setInt(1, id);
            }
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Reserva reserva = new Reserva(
                        resultSet.getInt("id"),
                        resultSet.getDate("fecha_entrada").toLocalDate(),
                        resultSet.getDate("fecha_salida").toLocalDate(),
                        resultSet.getFloat("valor"),
                        resultSet.getString("forma_pago"));
                resultado.add(reserva);
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Reserva reserva) {
        try (PreparedStatement statement = con.prepareStatement("UPDATE reservas SET "
                + "fecha_entrada = ? , fecha_salida = ? , valor = ?, forma_pago = ? WHERE id= ?;");) {
            statement.setDate(1, Date.valueOf(reserva.getFechaDeEntrada()));
            statement.setDate(2, Date.valueOf(reserva.getFechaDeSalida()));
            statement.setFloat(3, reserva.getValor());
            statement.setString(4, reserva.getFormaDePago());
            statement.setInt(5, reserva.getId());
            statement.execute();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try (PreparedStatement statement = con.prepareStatement("DELETE"
                + " FROM reservas WHERE ID = ?")) {
            statement.setInt(1, id);
            statement.execute();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean guardar(Reserva reserva) {
        try (PreparedStatement statement = con.prepareStatement("INSERT INTO reservas"
                + "(fecha_entrada, fecha_salida, valor, forma_pago) "
                + "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(reserva.getFechaDeEntrada()));
            statement.setDate(2, Date.valueOf(reserva.getFechaDeSalida()));
            statement.setFloat(3, reserva.getValor());
            statement.setString(4, reserva.getFormaDePago());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                reserva.setId(resultSet.getInt(1));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            // throw new RuntimeException(e);
        }
    }

}
