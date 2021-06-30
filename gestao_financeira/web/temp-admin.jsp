<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="JavaBeans.Admin"%>
<%@ page import="java.util.ArrayList"%>
<%
  ArrayList<Admin> lista_admin = (ArrayList<Admin>) request.getAttribute("admin");
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <jsp:include page="header.html"/>
    <title>Form: Administradores</title>
  </head>
  <body>
    <jsp:include page="menu.jsp"/>
  <body>
    <main class="pag-temporarias">
      <section class="container">
        <h1 class="text-center p-4" id="topo">
          Formulário de Administradores
        </h1>
        <div class="row">
          <div class="col-md-2 text-center">
            <form method="post" action ="cria_admin" class="form-temporario p-md-0">
              <i class="fab fa-2x fa-creative-commons-share"></i>
              <h1 class="h3 mb-1">Criar</h1>
              <div class="form-temporario mb-2">
                <input type="text" class="form-control mb-2" maxlength="20" placeholder="Nome" required name="nomeC">
                <input type="text" class="form-control mb-2" id="cpfC" placeholder="123.456.789-00" required name="cpfC">
                <input  type="password" class="form-control mb-2" placeholder="Senha" required name="senhaC">
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Criar</button>
            </form>
          </div>
          <div class="col-md-2 text-center">
            <form method="post" action="edita_admin" class=" form-temporario p-4 p-md-0">
              <i class="far fa-2x fa-edit"></i>
              <h1 class="h3 mb-1">Editar</h1>
              <div class="form-temporario mb-2">
                <input name="id" type="text" class="form-control mb-2" placeholder="Id" value="${id}" readonly>
                <input type="text" class="form-control mb-2" maxlength="20" placeholder="Nome" value="${nome}" required name="nomeE">
                <input type="text" class="form-control mb-2" id="cpfE" placeholder="123.456.789-00" value="${cpf}" required name="cpfE">
                <input  type="password" class="form-control mb-2" placeholder="Senha" value="${senha}" required name="senhaE">
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Editar</button>
            </form>
          </div>
          <div class="col-md-8">
            <table class="table table-sm" id="tabela-usuarios">
              <thead>
                <tr>
                  <th scope="col">Id</th>
                  <th scope="col">Nome</th>
                  <th scope="col">CPF</th>
                  <th scope="col">Senha</th>
                  <th scope="col">Ações</th>
                </tr>
              </thead>
              <tbody>
                <%
                  for (int i = 0; i < lista_admin.size(); i++) {
                    Admin aux = lista_admin.get(i);
                %>
                <tr>
                  <td><%=aux.getId()%></td>
                  <td><%=aux.getNome()%></td>
                  <td><%=aux.getCpf()%></td>
                  <td><%=aux.getSenha()%></td>
                  <td>
                    <a class="btn" href="get_admin?id=<%= aux.getId()%>"><i class=" far fa-edit"></i></a>
                    <a class="btn" href="javascript: apaga_admin(<%= aux.getId()%>)"><i class="fas fa-trash-alt"></i></a>
                  </td>
                </tr>
                <% }%>
              </tbody>
            </table>
          </div>
        </div>
      </section>
    </main>

    <footer class="text-center fixed-bottom">
      <jsp:include page="footer.jsp"/>
    </footer>
    <jsp:include page="scripts.html"/>
    <script type="text/javascript" src="assets/js/jquery.maskedinput.js"></script>
    <script type="text/javascript">
      $(document).ready(function () {
        $("#cpfC, #cpfE").mask("999.999.999-99");
      });
      function apaga_admin(id) {
        let resposta = confirm('Deseja apagar este administrador?');
        if (resposta === true) {
          window.location.href = "apaga_admin?id=" + id;
        }
      }
    </script>
  </body>
</html>
