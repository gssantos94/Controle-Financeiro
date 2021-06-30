package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

  //Parâmetros de conexão
  private final String driver = "com.mysql.jdbc.Driver";
  private final String url = "jdbc:mysql://localhost:3306/financeiro?useTimezone=true&serverTimezone=UTC";
  private final String user = "root";
  private final String password = "";

  //Métodos de conexão
  public Connection conectar() {
    Connection conexao = null;
    try {
      Class.forName(driver);
      conexao = DriverManager.getConnection(url, user, password);
      return conexao;
    } catch (Exception e) {
      return null;
    }
  }
}
