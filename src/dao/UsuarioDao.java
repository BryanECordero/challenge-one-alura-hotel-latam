package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import factory.ConnectionFactory;

public class UsuarioDao {
    ConnectionFactory factory;
    private Connection con;

    public UsuarioDao() {
        factory = new ConnectionFactory();
        con = factory.recuperaConexion();
    }

    public boolean validarUsuario(String usuario, String contrasena) {
        try (PreparedStatement statement = con
                .prepareStatement("SELECT * FROM LOGIN WHERE usuario = ? AND contrasena = ?")) {
            statement.setString(1, usuario);
            statement.setString(2, contrasena);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
