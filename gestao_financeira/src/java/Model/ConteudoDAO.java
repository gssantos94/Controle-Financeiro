package Model;

import JavaBeans.Categoria;
import JavaBeans.Conta;
import JavaBeans.Lancamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConteudoDAO {

  private final Connection conexao;
  public boolean existe;

  public ConteudoDAO() {
    this.conexao = new Conexao().conectar();
  }

  //CRUD - CREATE
  public void cria_categoria(Categoria categoria) throws SQLException {
    //Define as query para criar e verificar se existe
    String create = "INSERT INTO categorias (descricao) VALUES (?);";
    String select = "SELECT * FROM categorias WHERE descricao = ?;";
    //Try-with-resource para abrir a conexão e fechar logo depois do uso
    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      //Prepara a variavel que vai no banco veificar se já existe uma categoria com a mesma desc.
      pst_verifica.setString(1, categoria.getDescricao());
      //Executa a query no banco
      ResultSet rs = pst_verifica.executeQuery();
      //Variavel true/false que indica se já existe ou não a categoria
      existe = false;
      //Se o rs.next() retornar algum dado, indica que existe uma categoria com a inserida
      if (rs.next()) {
        //Define a variavel de controle para true (que existe)
        existe = true;
        System.out.println("\n\nErro - ConteudoDAO - CREATE - Categoria: Categoria ja existente!");
      } else {
        //Caso não exista, prepara a query de inserir no banco
        try (PreparedStatement pst = conexao.prepareStatement(create);) {
          //Prepara a variavel que vai enviar ao banco o dado
          pst.setString(1, categoria.getDescricao());
          //Executa a query de inserir
          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - ConteudoDAO - CREATE - Categoria: " + e.getMessage());
        }

      }
    }

  }

  //CRUD - READ
  public ArrayList<Categoria> lista_categoria() {
    ArrayList<Categoria> lista_categoria = new ArrayList<>();

    String read = "SELECT * FROM categorias";

    try (PreparedStatement pst = conexao.prepareStatement(read);) {
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int id = rs.getInt(1);
        String descricao = rs.getString(2);

        lista_categoria.add(new Categoria(id, descricao));
      }
      return lista_categoria;
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - READ - Categoria: " + e.getMessage());
      return null;
    }
  }

  //CRUD - READ
  public void get_categoria(Categoria categoria) {
    String read = "SELECT * FROM categorias WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(read)) {
      pst.setInt(1, categoria.getId());
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        categoria.setId(rs.getInt(1));
        categoria.setDescricao(rs.getString(2));
      }
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - READ GET - Categoria: " + e.getMessage());
    }
  }

  //CRUD - UPDATE
  public void altera_categoria(Categoria categoria) throws SQLException {
    String update = "UPDATE categorias SET descricao = ? WHERE id = ?;";
    String select = "SELECT * FROM categorias WHERE descricao = ?;";
    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      pst_verifica.setString(1, categoria.getDescricao());
      ResultSet rs = pst_verifica.executeQuery();
      existe = false;
      if (rs.next()) {
        existe = true;
        System.out.println("\n\nErro - ConteudoDAO - CREATE - Categoria: Categoria ja existente!");
      } else {
        try (PreparedStatement pst = conexao.prepareStatement(update)) {

          pst.setString(1, categoria.getDescricao());
          pst.setInt(2, categoria.getId());

          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - ConteudoDAO - UPDATE - Categoria: " + e.getMessage());
        }
      }
    }
  }
  //CRUD - DELETE

  public void apaga_categoria(Categoria categoria) {
    String delete = "DELETE FROM categorias WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(delete)) {
      pst.setInt(1, categoria.getId());
      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - DELETE - Categoria: " + e.getMessage());
    }

  }

  //CRUD - CREATE
  public void cria_conta(Conta conta) throws SQLException {
    //Define as query para criar e verificar se existe
    String create = "INSERT INTO contas (id_usuario,nome_conta,banco,agencia,conta_corrente) VALUES (?,?,?,?,?);";
    String select = "SELECT * FROM contas WHERE banco=? && agencia=? && conta_corrente = ?;";
    //Try-with-resource para abrir a conexão e fechar logo depois do uso
    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      //Prepara a variavel que vai no banco veificar se já existe uma categoria com a mesma desc.
      pst_verifica.setString(1, conta.getBanco());
      pst_verifica.setString(2, conta.getAgencia());
      pst_verifica.setString(3, conta.getConta_corrente());
      //Executa a query no banco
      ResultSet rs = pst_verifica.executeQuery();
      //Variavel true/false que indica se já existe ou não a categoria
      existe = false;
      //Se o rs.next() retornar algum dado, indica que existe uma categoria com a inserida
      if (rs.next()) {
        //Define a variavel de controle para true (que existe)
        existe = true;
        System.out.println("\n\nErro - ConteudoDAO - CREATE - Conta: Conta ja existente!");
      } else {
        //Caso não exista, prepara a query de inserir no banco
        try (PreparedStatement pst = conexao.prepareStatement(create);) {
          //Prepara a variavel que vai enviar ao banco o dado
          pst.setInt(1, conta.getId_usuario());
          pst.setString(2, conta.getNome_conta());
          pst.setString(3, conta.getBanco());
          pst.setString(4, conta.getAgencia());
          pst.setString(5, conta.getConta_corrente());
          //Executa a query de inserir
          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - ConteudoDAO - CREATE - Conta: " + e.getMessage());
        }

      }
    }

  }

  //CRUD - READ
  public ArrayList<Conta> lista_conta() {
    ArrayList<Conta> lista_conta = new ArrayList<>();

    String read = "SELECT * FROM contas";

    try (PreparedStatement pst = conexao.prepareStatement(read);) {
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int id = rs.getInt(1);
        int id_usuario = rs.getInt(2);
        String nome_conta = rs.getString(3);
        String banco = rs.getString(4);
        String agencia = rs.getString(5);
        String conta_corrente = rs.getString(6);

        lista_conta.add(new Conta(id, id_usuario, nome_conta, banco, agencia, conta_corrente));
      }
      return lista_conta;
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - READ - Conta: " + e.getMessage());
      return null;
    }
  }

  //CRUD - READ
  public void get_conta(Conta conta) {
    String read = "SELECT * FROM contas WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(read)) {
      pst.setInt(1, conta.getId());
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        conta.setId(rs.getInt(1));
        conta.setId_usuario(rs.getInt(2));
        conta.setNome_conta(rs.getString(3));
        conta.setBanco(rs.getString(4));
        conta.setAgencia(rs.getString(5));
        conta.setConta_corrente(rs.getString(6));
      }
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - READ GET - Conta: " + e.getMessage());
    }
  }

  //CRUD - UPDATE
  public void altera_conta(Conta conta) throws SQLException {
    String update = "UPDATE contas SET id_usuario=?,nome_conta=?,banco=?,agencia=?,conta_corrente=? WHERE id = ?;";
    String select = "SELECT * FROM contas WHERE banco=? && agencia=? && conta_corrente = ?;";

    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      pst_verifica.setString(1, conta.getBanco());
      pst_verifica.setString(2, conta.getAgencia());
      pst_verifica.setString(3, conta.getConta_corrente());
      ResultSet rs = pst_verifica.executeQuery();
      existe = false;
      if (rs.next()) {
        existe = true;
        System.out.println("\n\nErro - ConteudoDAO - CREATE - Conta: Conta ja existente!");
      } else {
        try (PreparedStatement pst = conexao.prepareStatement(update)) {

          pst.setInt(1, conta.getId_usuario());
          pst.setString(2, conta.getNome_conta());
          pst.setString(3, conta.getBanco());
          pst.setString(4, conta.getAgencia());
          pst.setString(5, conta.getConta_corrente());
          pst.setInt(6, conta.getId());

          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - ConteudoDAO - CREATE - Conta: " + e.getMessage());
        }
      }
    }
  }

  //CRUD - DELETE
  public void apaga_conta(Conta conta) {
    String delete = "DELETE FROM contas WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(delete)) {
      pst.setInt(1, conta.getId());
      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - DELETE - Conta: " + e.getMessage());
    }

  }

  //CRUD - CREATE
  public void cria_lancamento(Lancamento lancamento) throws SQLException {
    //Define as query para criar e verificar se existe
    String create = "INSERT INTO lancamentos (id_conta,id_categoria,valor,operacao,data,descricao) VALUES (?,?,?,?,?,?);";
    String select = "SELECT * FROM lancamentos WHERE id_conta=? && id_categoria=? && valor=? && operacao = ?;";
    //Try-with-resource para abrir a conexão e fechar logo depois do uso
    try (PreparedStatement pst_verifica = conexao.prepareStatement(select);) {
      //Prepara a variavel que vai no banco veificar se já existe uma categoria com a mesma desc.
      pst_verifica.setInt(1, lancamento.getId_conta());
      pst_verifica.setInt(2, lancamento.getId_categoria());
      pst_verifica.setFloat(3, lancamento.getValor());
      pst_verifica.setString(4, lancamento.getOperacao());
      //Executa a query no banco
      ResultSet rs = pst_verifica.executeQuery();
      //Variavel true/false que indica se já existe ou não a categoria
      existe = false;
      //Se o rs.next() retornar algum dado, indica que existe uma categoria com a inserida
      if (rs.next()) {
        //Define a variavel de controle para true (que existe)
        existe = true;
        System.out.println("\n\nErro - ConteudoDAO - CREATE - Lançamento: Lançamento ja existente!");
      } else {
        //Caso não exista, prepara a query de inserir no banco
        try (PreparedStatement pst = conexao.prepareStatement(create);) {
          //Prepara a variavel que vai enviar ao banco o dado
          pst.setInt(1, lancamento.getId_conta());
          pst.setInt(2, lancamento.getId_categoria());
          pst.setFloat(3, lancamento.getValor());
          pst.setString(4, lancamento.getOperacao());
          pst.setDate(5, lancamento.getData());
          pst.setString(6, lancamento.getDescricao());
          //Executa a query de inserir
          pst.executeUpdate();
        } catch (Exception e) {
          System.out.println("\n\nErro - ConteudoDAO - CREATE - Lancamento: " + e.getMessage());
        }

      }
    }

  }

  //CRUD - READ
  public ArrayList<Lancamento> lista_lancamento() {
    ArrayList<Lancamento> lista_lancamento = new ArrayList<>();

    String read = "SELECT * FROM lancamentos";

    try (PreparedStatement pst = conexao.prepareStatement(read);) {
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int id = rs.getInt(1);
        int id_conta = rs.getInt(2);
        int id_categoria = rs.getInt(3);
        float valor = rs.getFloat(4);
        String operacao = rs.getString(5);
        Date data = rs.getDate(6);
        String descricao = rs.getString(7);

        lista_lancamento.add(new Lancamento(id, id_conta, id_categoria, valor, operacao, data, descricao));
      }
      return lista_lancamento;
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - READ - Lancamento: " + e.getMessage());
      return null;
    }
  }

  //CRUD - READ
  public void get_lancamento(Lancamento lancamento) {
    String read = "SELECT * FROM lancamentos WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(read)) {
      pst.setInt(1, lancamento.getId());
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        lancamento.setId(rs.getInt(1));
        lancamento.setId_conta(rs.getInt(2));
        lancamento.setId_categoria(rs.getInt(3));
        lancamento.setValor(rs.getFloat(4));
        lancamento.setOperacao(rs.getString(5));
        lancamento.setData(rs.getDate(6));
        lancamento.setDescricao(rs.getString(7));
      }
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - READ GET - Lancamento: " + e.getMessage());
    }
  }

  //CRUD - UPDATE
  public void altera_lancamento(Lancamento lancamento) {
    String update = "UPDATE lancamentos SET id_conta=?,id_categoria=?,valor=?,operacao=?,data=?,descricao=? WHERE id = ?;";

    try (PreparedStatement pst = conexao.prepareStatement(update)) {

      pst.setInt(1, lancamento.getId_conta());
      pst.setInt(2, lancamento.getId_categoria());
      pst.setFloat(3, lancamento.getValor());
      pst.setString(4, lancamento.getOperacao());
      pst.setDate(5, lancamento.getData());
      pst.setString(6, lancamento.getDescricao());
      pst.setInt(7, lancamento.getId());

      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - UPDATE - Lancamento: " + e.getMessage());
    }
  }

  //CRUD - DELETE
  public void apaga_lancamento(Lancamento lancamento) {
    String delete = "DELETE FROM lancamentos WHERE id = ?";

    try (PreparedStatement pst = conexao.prepareStatement(delete)) {
      pst.setInt(1, lancamento.getId());
      pst.executeUpdate();
    } catch (Exception e) {
      System.out.println("\n\nErro - ConteudoDAO - DELETE - Lancamento: " + e.getMessage());
    }

  }
}//----------END----------
