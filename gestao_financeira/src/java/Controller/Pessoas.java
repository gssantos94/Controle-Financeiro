package Controller;

import JavaBeans.Admin;
import JavaBeans.Usuario;
import Model.PessoasDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Pessoas", urlPatterns = {
  "/autenticar",
  "/usuario", "/cria_usuario", "/edita_usuario", "/get_usuario", "/apaga_usuario",
  "/admin", "/cria_admin", "/edita_admin", "/get_admin", "/apaga_admin"})

public class Pessoas extends HttpServlet {

  PessoasDAO pessoadao = new PessoasDAO();
  Usuario usuario = new Usuario();
  Admin admin = new Admin();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
      case "/usuario":
        usuario(request, response);
        break;
      case "/get_usuario":
        get_usuario(request, response);
        break;
      case "/apaga_usuario":
        apaga_usuario(request, response);
        break;
      case "/admin":
        admin(request, response);
        break;
      case "/get_admin":
        get_admin(request, response);
        break;
      case "/apaga_admin":
        apaga_admin(request, response);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
      case "/edita_usuario":
        edita_usuario(request, response);
        break;
      case "/cria_usuario":
        cria_usuario(request, response);
        break;
      case "/edita_admin":
        edita_admin(request, response);
        break;
      case "/cria_admin":
        cria_admin(request, response);
        break;
      case "/autenticar":
        response.sendRedirect("index.jsp");
        break;
    }
  }

  //CRUD - CREATE
  private void cria_usuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    usuario.setNome(request.getParameter("nomeC"));
    usuario.setCpf(request.getParameter("cpfC"));
    usuario.setSenha(request.getParameter("senhaC"));
    usuario.setSuspenso(request.getParameter("suspensoC"));
    try {
      pessoadao.cria_usuario(usuario);
      if (pessoadao.existe) {
        request.setAttribute("mensagem", "CPF já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("usuario");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Usuário Servlet - CREATE - Usuário: " + e.getMessage());
    }
  }

  //CRUD - READ
  private void usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Criando um objeto que irá receber tudo da tabela categoria
    ArrayList<Usuario> lista = pessoadao.lista_usuario();
    //Passa atributo que será recebio na .jsp
    request.setAttribute("usuario", lista);
    //redireciona para a pagina
    request.getRequestDispatcher("temp-usuario.jsp").forward(request, response);

  }

  //CRUD - READ
  private void get_usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    usuario.setId(id);

    pessoadao.get_usuario(usuario);

    request.setAttribute("id", usuario.getId());
    request.setAttribute("nome", usuario.getNome());
    request.setAttribute("cpf", usuario.getCpf());
    request.setAttribute("senha", usuario.getSenha());
    request.setAttribute("suspenso", usuario.getSuspenso());
    request.getRequestDispatcher("usuario").forward(request, response);
  }

  //CRUD - UPDATE
  private void edita_usuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");
    usuario.setId(Integer.parseInt(request.getParameter("id")));
    usuario.setNome(request.getParameter("nomeE"));
    usuario.setCpf(request.getParameter("cpfE"));
    usuario.setSenha(request.getParameter("senhaE"));
    usuario.setSuspenso(request.getParameter("suspensoE"));

    pessoadao.altera_usuario(usuario);

    response.sendRedirect("usuario");
  }

  //CRUD - DELETE
  private void apaga_usuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String id = request.getParameter("id");
    usuario.setId(Integer.parseInt(id));

    pessoadao.apaga_usuario(usuario);

    response.sendRedirect("usuario");
  }

  //CRUD - CREATE
  private void cria_admin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    admin.setNome(request.getParameter("nomeC"));
    admin.setCpf(request.getParameter("cpfC"));
    admin.setSenha(request.getParameter("senhaC"));
    try {
      pessoadao.cria_admin(admin);
      if (pessoadao.existe) {
        request.setAttribute("mensagem", "CPF já existe!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
        rd.forward(request, response);
      } else {
        response.sendRedirect("admin");
      }
    } catch (IOException | SQLException e) {
      System.out.println("\n\nErro - Admin Servlet - CREATE - Admin: " + e.getMessage());
    }
  }

  //CRUD - READ
  private void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Criando um objeto que irá receber tudo da tabela categoria
    ArrayList<Admin> lista = pessoadao.lista_admin();
    //Passa atributo que será recebio na .jsp
    request.setAttribute("admin", lista);
    //redireciona para a pagina
    request.getRequestDispatcher("temp-admin.jsp").forward(request, response);

  }

  //CRUD - READ
  private void get_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    admin.setId(id);

    pessoadao.get_admin(admin);

    request.setAttribute("id", admin.getId());
    request.setAttribute("nome", admin.getNome());
    request.setAttribute("cpf", admin.getCpf());
    request.setAttribute("senha", admin.getSenha());
    request.getRequestDispatcher("admin").forward(request, response);
  }

  //CRUD - UPDATE
  private void edita_admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");
    admin.setId(Integer.parseInt(request.getParameter("id")));
    admin.setNome(request.getParameter("nomeE"));
    admin.setCpf(request.getParameter("cpfE"));
    admin.setSenha(request.getParameter("senhaE"));

    pessoadao.altera_admin(admin);

    response.sendRedirect("admin");
  }

  //CRUD - DELETE
  private void apaga_admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String id = request.getParameter("id");
    admin.setId(Integer.parseInt(id));

    pessoadao.apaga_admin(admin);

    response.sendRedirect("admin");
  }

}//----------END----------
