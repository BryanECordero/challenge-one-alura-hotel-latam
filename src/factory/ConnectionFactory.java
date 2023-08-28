package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    private final DataSource dataSource;

    public ConnectionFactory() {
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("contrasena2023");
        pooledDataSource.setMaxPoolSize(10);
        this.dataSource = pooledDataSource;
    }

    public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}