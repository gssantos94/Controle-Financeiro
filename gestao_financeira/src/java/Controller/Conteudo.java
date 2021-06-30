package Controller;

import JavaBeans.Categoria;
import JavaBeans.Conta;
import JavaBeans.Lancamento;
import Model.ConteudoDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Conteudo", urlPatterns = {
  "/categoria", "/cria_categoria", "/edita_categoria", "/get_categoria", "/apaga_categoria",
  "/conta", "/cria_conta", "/edita_conta", "/get_conta", "/apaga_conta",
  "/lancamento", "/cria_lancamento", "/edita_lancamento", "/get_lancamento", "/apaga_lancamento"})
public class Conteudo extends HttpServlet {

  ConteudoDAO conteudodao = new ConteudoDAO();
  Categoria categoria = new Categoria();
  Conta conta = new Conta();
  Lancamento lancamento = new Lancamento();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
      case "/categoria":
        categoria(request, response);
        break;
      case "/get_categoria":
        get_categoria(request, response);
        break;
      case "/apaga_categoria":
        apaga_categoria(request, response);
        break;
      case "/conta":
        conta(request, response);
        break;
      case "/get_conta":
        get_conta(request, response);
        break;
      case "/apaga_conta":
        apaga_conta(request, response);
        break;
      case "/lancamento":
        lancamento(request, response);
        break;
      case "/get_lancamento":
        get_lancamento(request, response);
        break;
      case "/apaga_lancamento":
        apaga_lancamento(request, response);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
      case "/cria_categoria":
        cria_categoria(request, response);
        break;
      case "/edita_categoria":
        edita_categoria(request, response);
        break;
      case "/cria_conta":
        cria_conta(request, response);
        break;
      case "/edita_conta":
        edita_conta(request, response);
        break;
      case "/cria_lancamento":
        cria_lancamento(request, response);
        break;
      case "/edita_lancamento":
        edita_lancamento(request, response);
        break;
    }
  }

  //CRUD - CREATE
  private void cria_categoria(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    categoria.setDescricao(request.getParameter("descricaoC"));
    try {
      conteudodao.cria_categoria(categoria);
      if (conteudodao.existe) {
        request.setAttribute("mensagem", "Categoria já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("categoria");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Conteudo Servlet - CREATE - Categoria: " + e.getMessage());
    }
  }

  //CRUD - READ
  private void categoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Criando um objeto que irá receber tudo da tabela categoria
    ArrayList<Categoria> lista = conteudodao.lista_categoria();
    //Passa atributo que será recebio na .jsp
    request.setAttribute("categoria", lista);
    //redireciona para a pagina
    request.getRequestDispatcher("temp-categoria.jsp").forward(request, response);

  }

  //CRUD - READ
  private void get_categoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    categoria.setId(id);

    conteudodao.get_categoria(categoria);

    request.setAttribute("id", categoria.getId());
    request.setAttribute("descricao", categoria.getDescricao());
    request.getRequestDispatcher("categoria").forward(request, response);
  }

  //CRUD - UPDATE
  private void edita_categoria(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    categoria.setDescricao(request.getParameter("descricaoE"));

    try {
      conteudodao.altera_categoria(categoria);
      if (conteudodao.existe) {
        request.setAttribute("mensagem", "Categoria já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("categoria");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Conteudo Servlet - CREATE - Categoria: " + e.getMessage());
    }
  }

  //CRUD - DELETE
  private void apaga_categoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String id = request.getParameter("id");
    categoria.setId(Integer.parseInt(id));

    conteudodao.apaga_categoria(categoria);

    response.sendRedirect("categoria");
  }

  //CRUD - CREATE
  private void cria_conta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");

    conta.setId_usuario(Integer.parseInt(request.getParameter("id_usuarioC")));
    conta.setNome_conta(request.getParameter("nome_contaC"));
    conta.setBanco(request.getParameter("bancoC"));
    conta.setAgencia(request.getParameter("agenciaC"));
    conta.setConta_corrente(request.getParameter("conta_correnteC"));
    try {
      conteudodao.cria_conta(conta);
      if (conteudodao.existe) {
        request.setAttribute("mensagem", "Conta já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("conta");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Conteudo Servlet - CREATE - Conta: " + e.getMessage());
    }
  }

  //CRUD - READ
  private void conta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Criando um objeto que irá receber tudo da tabela categoria
    ArrayList<Conta> lista = conteudodao.lista_conta();
    //Passa atributo que será recebio na .jsp
    request.setAttribute("conta", lista);
    //redireciona para a pagina
    request.getRequestDispatcher("temp-conta.jsp").forward(request, response);

  }

  //CRUD - READ
  private void get_conta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    conta.setId(id);

    conteudodao.get_conta(conta);

    request.setAttribute("id", conta.getId());
    request.setAttribute("id_usuario", conta.getId());
    request.setAttribute("nome_conta", conta.getNome_conta());
    request.setAttribute("banco", conta.getBanco());
    request.setAttribute("agencia", conta.getAgencia());
    request.setAttribute("conta_corrente", conta.getConta_corrente());
    request.getRequestDispatcher("conta").forward(request, response);
  }

  //CRUD - UPDATE
  private void edita_conta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    conta.setId(Integer.parseInt(request.getParameter("id")));
    conta.setId(Integer.parseInt(request.getParameter("id_usuarioE")));
    conta.setNome_conta(request.getParameter("nome_contaE"));
    conta.setBanco(request.getParameter("bancoE"));
    conta.setAgencia(request.getParameter("agenciaE"));
    conta.setConta_corrente(request.getParameter("conta_correnteE"));

    try {
      conteudodao.altera_conta(conta);
      if (conteudodao.existe) {
        request.setAttribute("mensagem", "Conta já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("conta");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Conteudo Servlet - CREATE - Conta: " + e.getMessage());
    }

  }

  //CRUD - DELETE
  private void apaga_conta(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String id = request.getParameter("id");
    conta.setId(Integer.parseInt(id));

    conteudodao.apaga_conta(conta);

    response.sendRedirect("conta");
  }

  //CRUD - CREATE
  private void cria_lancamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    lancamento.setId_conta(Integer.parseInt(request.getParameter("id_contaC")));
    lancamento.setId_categoria(Integer.parseInt(request.getParameter("id_categoriaC")));
    lancamento.setValor(Float.parseFloat(request.getParameter("valorC")));
    lancamento.setData(Date.valueOf(request.getParameter("dataC")));
    lancamento.setOperacao(request.getParameter("operacaoC"));
    lancamento.setDescricao(request.getParameter("descricaoC"));
    try {
      conteudodao.cria_lancamento(lancamento);
      if (conteudodao.existe) {
        request.setAttribute("mensagem", "Lançamento já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("lancamento");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Conteudo Servlet - CREATE - Lancamento: " + e.getMessage());
    }
  }

  //CRUD - READ
  private void lancamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Criando um objeto que irá receber tudo da tabela categoria
    ArrayList<Lancamento> lista = conteudodao.lista_lancamento();
    //Passa atributo que será recebio na .jsp
    request.setAttribute("lancamento", lista);
    //redireciona para a pagina
    request.getRequestDispatcher("temp-lancamento.jsp").forward(request, response);

  }

  //CRUD - READ
  private void get_lancamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    lancamento.setId(id);

    conteudodao.get_lancamento(lancamento);

    request.setAttribute("id", lancamento.getId());
    request.setAttribute("id_conta", lancamento.getId_conta());
    request.setAttribute("id_categoria", lancamento.getId_categoria());
    request.setAttribute("valor", lancamento.getValor());
    request.setAttribute("operacao", lancamento.getOperacao());
    request.setAttribute("data", lancamento.getData());
    request.setAttribute("descricao", lancamento.getDescricao());
    request.getRequestDispatcher("lancamento").forward(request, response);
  }

  //CRUD - UPDATE
  private void edita_lancamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");
    lancamento.setId(Integer.parseInt(request.getParameter("id")));
    lancamento.setId_conta(Integer.parseInt(request.getParameter("id_contaE")));
    lancamento.setId_categoria(Integer.parseInt(request.getParameter("id_categoriaE")));
    lancamento.setValor(Float.parseFloat(request.getParameter("valorE")));
    lancamento.setData(Date.valueOf(request.getParameter("dataE")));
    lancamento.setOperacao(request.getParameter("operacaoE"));
    lancamento.setDescricao(request.getParameter("descricaoE"));

    conteudodao.altera_lancamento(lancamento);

    response.sendRedirect("lancamento");
  }

  //CRUD - DELETE
  private void apaga_lancamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String id = request.getParameter("id");
    lancamento.setId(Integer.parseInt(id));

    conteudodao.apaga_lancamento(lancamento);

    response.sendRedirect("lancamento");
  }

}//----------END----------
