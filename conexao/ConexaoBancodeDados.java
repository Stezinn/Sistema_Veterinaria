package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancodeDados {
    static String host = "localhost";
    static String database = "aula";
    static String user = "root";
    static String password = "123456";

    static int port = 3306;
    private static ConexaoBancodeDados instance;
    private static Connection connection;
    // Conecta ao banco de dados MySQL

    private void conecta() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if(this.connection == null ){
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useTimezone=true&serverTimezone=UTC", this.user, this.password);
        }else if(this.connection.isClosed()){
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useTimezone=true&serverTimezone=UTC", this.user, this.password);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public static ConexaoBancodeDados getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexaoBancodeDados();
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useTimezone=true&serverTimezone=UTC", user, password);

        }
        return instance;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
