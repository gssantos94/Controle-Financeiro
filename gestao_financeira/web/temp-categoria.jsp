<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="JavaBeans.Categoria"%>
<%@ page import="java.util.ArrayList"%>
<%
  ArrayList<Categoria> lista_categoria = (ArrayList<Categoria>) request.getAttribute("categoria");
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <jsp:include page="header.html"/>
    <title>Form: Categorias</title>
    <script>

    </script>
  </head>
  <body>
    <jsp:include page="menu.jsp"/>
    <main class="pag-temporarias">
      <section class="container">
        <h1 class="text-center p-4" id="topo">
          Formulário de Categorias
        </h1>
        <div class="row">
          <div class="col-md-2 text-center">
            <form method="post" action ="cria_categoria" class="form-temporario p-md-0">
              <i class="fab fa-2x fa-creative-commons-share"></i>
              <h1 class="h3 mb-1">Criar</h1>
              <div class="form-temporario mb-2">
                <input type="text" class="form-control" id="criar" maxlength="20" placeholder="Descrição" required name="descricaoC">
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Criar</button>
            </form>
          </div>
          <div class="col-md-2 text-center">
            <form method="post" action="edita_categoria" class=" form-temporario p-4 p-md-0">
              <i class="far fa-2x fa-edit"></i>
              <h1 class="h3 mb-1">Editar</h1>
              <div class="form-temporario mb-2">
                <input name="id" type="text" class="form-control mb-2" placeholder="Id" value="${id}" readonly>
                <input name="descricaoE" type="text" class="form-control" placeholder="Descrição" maxlength="20" value="${descricao}" id="editar" required>
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Editar</button>
            </form>
          </div>
          <div class="col-md-8">
            <table class="table table-sm" id="tabela-categorias">
              <thead>
                <tr>
                  <th scope="col">Id</th>
                  <th scope="col">Descrição</th>
                  <th scope="col">Ações</th>
                </tr>
              </thead>
              <tbody>
                <%
                  for (int i = 0; i < lista_categoria.size(); i++) {
                    Categoria aux = lista_categoria.get(i);
                %>
                <tr>
                  <td><%=aux.getId()%></td>
                  <td><%=aux.getDescricao()%></td>
                  <td>
                    <a class="btn" href="get_categoria?id=<%= aux.getId()%>"><i class=" far fa-edit"></i></a>
                    <a class="btn" href="javascript: apaga_categoria(<%= aux.getId()%>)"><i class="fas fa-trash-alt"></i></a>
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
    <script>
      function apaga_categoria(id) {
        let resposta = confirm('Deseja apagar esta categoria?');
        if (resposta === true) {
          window.location.href = "apaga_categoria?id=" + id;
        }
      }
    </script>
    <jsp:include page="scripts.html"/>
  </body>
</html>
