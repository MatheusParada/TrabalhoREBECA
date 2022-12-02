package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Inserir{
    private final String url = "jdbc:postgresql://localhost/Poo";
    private final String user = "postgres";
    private final String password = "123456";
    private static final String insert_usuario_sql = "insert into usuario" + "  (ID,usuario,senha,nome,email) VALUES " + " (?, ?, ?, ?, ?);";



    public void insertRecord(String ID, String usuario, String senha, String nome, String email ) throws SQLException {
    //    Inserir createTableExample = new Inserir();


        System.out.println(insert_usuario_sql);
// Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);
// Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(insert_usuario_sql)) {
            preparedStatement.setInt(1, Integer.parseInt(ID));
            preparedStatement.setString(2, usuario);
            preparedStatement.setString(3, senha);
            preparedStatement.setString(4, nome);
            preparedStatement.setString(5, email);
            System.out.println(preparedStatement);
// Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
// print SQL exception information
            printSQLException(e);
        }
// Step 4: try-with-resource statement will auto close the connection.
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}