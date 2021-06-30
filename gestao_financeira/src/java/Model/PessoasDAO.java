package Model;

import JavaBeans.Admin;
import JavaBeans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoasDAO {

  private final Connection conexao;
  public boolean existe;

  public PessoasDAO() {
    this.conexao = new Conexao().conectar();
  }

  //CRUD - CREATE
  public void cria_usuario(Usuario usuario) throws SQLException {
    //Define as query para criar e verificar se existe
    String create = "INSERT INTO usuarios (nome, cpf, senha, suspenso) VALUES (?,?,?,?);";
    String select = "SELECT * FROM usuarios WHERE cpf = ?;";
    //Try-with-resource para abrir a conexão e fechar logo depois do uso
    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      //Prepara a variavel que vai no banco verificar se já existe uma categoria com a mesma desc.
      pst_verifica.setString(1, usuario.getCpf());
      //Executa a query no banco
      ResultSet rs = pst_verifica.executeQuery();
      //Variavel true/false que indica se já existe ou não a categoria
      existe = false;
      //Se o rs.next() retornar algum dado, indica que existe uma categoria com a inserida
      if (rs.next()) {
        //Define a variavel de controle para true (que existe)
        existe = true;
        System.out.println("\n\nErro - UsuarioDAO - CREATE - Usuário: CPF ja existente!");
      } else {
        //Caso não exista, prepara a query de inserir no banco
        try (PreparedStatement pst = conexao.prepareStatement(create);) {
          //Prepara a variavel que vai enviar ao banco o dado
          pst.setString(1, usuario.getNome());
          pst.setString(2, usuario.getCpf());
          pst.setString(3, usuario.getSenha());
          pst.setString(4, usuario.getSuspenso());
          //Executa a query de inserir
          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - UsuarioDAO - CREATE - Usuário: " + e.getMessage());
        }

      }
    }

  }

  //CRUD - READ
  public ArrayList<Usuario> lista_usuario() {
    ArrayList<Usuario> lista_usuario = new ArrayList<>();

    String read = "SELECT * FROM usuarios";

    try (PreparedStatement pst = conexao.prepareStatement(read);) {
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int id = rs.getInt(1);
        String nome = rs.getString(2);
        String cpf = rs.getString(3);
        String senha = rs.getString(4);
        String suspenso = rs.getString(5);

        lista_usuario.add(new Usuario(id, nome, cpf, senha, suspenso));
      }
      return lista_usuario;
    } catch (Exception e) {
      System.out.println("\n\nErro - UsuarioDAO - READ - Usuário: " + e.getMessage());
      return null;
    }
  }

  //CRUD - READ
  public void get_usuario(Usuario usuario) {
    String read = "SELECT * FROM usuarios WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(read)) {
      pst.setInt(1, usuario.getId());
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        usuario.setId(rs.getInt(1));
        usuario.setNome(rs.getString(2));
        usuario.setCpf(rs.getString(3));
        usuario.setSenha(rs.getString(4));
        usuario.setSuspenso(rs.getString(5));
      }
    } catch (Exception e) {
      System.out.println("\n\nErro - UsuarioDAO - READ GET - Usuário: " + e.getMessage());
    }
  }

  //CRUD - UPDATE
  public void altera_usuario(Usuario usuario) {
    String update = "UPDATE usuarios SET nome = ?, cpf = ?, senha = ?, suspenso = ? WHERE id = ?;";

    try (PreparedStatement pst = conexao.prepareStatement(update)) {

      pst.setString(1, usuario.getNome());
      pst.setString(2, usuario.getCpf());
      pst.setString(3, usuario.getSenha());
      pst.setString(4, usuario.getSuspenso());
      pst.setInt(5, usuario.getId());

      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - UsuarioDAO - UPDATE - Usuário: " + e.getMessage());
    }
  }

  //CRUD - DELETE
  public void apaga_usuario(Usuario usuario) {
    String delete = "DELETE FROM usuarios WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(delete)) {
      pst.setInt(1, usuario.getId());
      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - UsuarioDAO - DELETE - Usuário: " + e.getMessage());
    }

  }

  //CRUD - CREATE
  public void cria_admin(Admin admin) throws SQLException {
    //Define as query para criar e verificar se existe
    String create = "INSERT INTO administradores (nome, cpf, senha) VALUES (?,?,?);";
    String select = "SELECT * FROM administradores WHERE cpf = ?;";
    //Try-with-resource para abrir a conexão e fechar logo depois do uso
    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      //Prepara a variavel que vai no banco verificar se já existe uma categoria com a mesma desc.
      pst_verifica.setString(1, admin.getCpf());
      //Executa a query no banco
      ResultSet rs = pst_verifica.executeQuery();
      //Variavel true/false que indica se já existe ou não a categoria
      existe = false;
      //Se o rs.next() retornar algum dado, indica que existe uma categoria com a inserida
      if (rs.next()) {
        //Define a variavel de controle para true (que existe)
        existe = true;
        System.out.println("\n\nErro - AdminDAO - CREATE - Admin: CPF ja existente!");
      } else {
        //Caso não exista, prepara a query de inserir no banco
        try (PreparedStatement pst = conexao.prepareStatement(create);) {
          //Prepara a variavel que vai enviar ao banco o dado
          pst.setString(1, admin.getNome());
          pst.setString(2, admin.getCpf());
          pst.setString(3, admin.getSenha());
          //Executa a query de inserir
          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - AdminDAO - CREATE - Admin: " + e.getMessage());
        }

      }
    }

  }

  //CRUD - READ
  public ArrayList<Admin> lista_admin() {
    ArrayList<Admin> lista_admin = new ArrayList<>();

    String read = "SELECT * FROM administradores";

    try (PreparedStatement pst = conexao.prepareStatement(read);) {
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int id = rs.getInt(1);
        String nome = rs.getString(2);
        String cpf = rs.getString(3);
        String senha = rs.getString(4);

        lista_admin.add(new Admin(id, nome, cpf, senha));
      }
      return lista_admin;
    } catch (Exception e) {
      System.out.println("\n\nErro - AdminDAO - READ - Admin: " + e.getMessage());
      return null;
    }
  }

  //CRUD - READ
  public void get_admin(Admin admin) {
    String read = "SELECT * FROM administradores WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(read)) {
      pst.setInt(1, admin.getId());
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        admin.setId(rs.getInt(1));
        admin.setNome(rs.getString(2));
        admin.setCpf(rs.getString(3));
        admin.setSenha(rs.getString(4));
      }
    } catch (Exception e) {
      System.out.println("\n\nErro - AdminDAO - READ GET - Admin: " + e.getMessage());
    }
  }

  //CRUD - UPDATE
  public void altera_admin(Admin admin) {
    String update = "UPDATE administradores SET nome = ?, cpf = ?, senha = ? WHERE id = ?;";

    try (PreparedStatement pst = conexao.prepareStatement(update)) {

      pst.setString(1, admin.getNome());
      pst.setString(2, admin.getCpf());
      pst.setString(3, admin.getSenha());
      pst.setInt(4, admin.getId());

      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - AdminDAO - UPDATE - Admin: " + e.getMessage());
    }
  }

  //CRUD - DELETE
  public void apaga_admin(Admin admin) {
    String delete = "DELETE FROM administradores WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(delete)) {
      pst.setInt(1, admin.getId());
      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - AdminDAO - DELETE - Admin: " + e.getMessage());
    }

  }

}//----------END----------
