<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JavaBeans.*"%>
<%@ page import="java.util.ArrayList"%>
<%
  ArrayList<Lancamento> lista_lancamento = (ArrayList<Lancamento>) request.getAttribute("lancamento");
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <jsp:include page="header.html"/>
    <title>Form: Lançamentos</title>
  </head>
  <body>
    <jsp:include page="menu.jsp"/>
  <body>
    <main class="pag-temporarias">
      <section class="container">
        <h1 class="text-center p-4" id="topo">
          Formulário de Lançamentos
        </h1>
        <div class="row">
          <div class="col-md-2 text-center">
            <form method="post" action ="cria_lancamento" class="form-temporario p-md-0">
              <i class="fab fa-2x fa-creative-commons-share"></i>
              <h1 class="h3 mb-1">Criar</h1>
              <div class="form-temporario mb-2">
                <input name="id_contaC" type="text" class="form-control mb-2" placeholder="Id Conta" required>
                <input name="id_categoriaC" type="text" class="form-control mb-2" placeholder="Id Categoria" required>
                <input name="valorC" type="text" class="form-control mb-2" placeholder="Valor" maxlength="13" required>
                <input name="operacaoC" type="text" class="form-control mb-2" placeholder="Operação" maxlength="1" required>
                <input name="dataC" id="dataC" type="date" class="form-control mb-2" placeholder="Data" required>
                <input name="descricaoC" type="text" class="form-control mb-2" placeholder="Descrição" maxlength="100">
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Criar</button>
            </form>
          </div>
          <div class="col-md-2 text-center">
            <form method="post" action="edita_lancamento" class=" form-temporario p-4 p-md-0">
              <i class="far fa-2x fa-edit"></i>
              <h1 class="h3 mb-1">Editar</h1>
              <div class="form-temporario mb-2">
                <input name="id" value="${id}" type="text" class="form-control mb-2" placeholder="Id" readonly>
                <input name="id_contaE" value="${id_conta}" type="text" class="form-control mb-2" placeholder="Id Conta" required>
                <input name="id_categoriaE" value="${id_categoria}" type="text" class="form-control mb-2" placeholder="Id Categoria" required>
                <input name="valorE" value="${valor}" type="text" class="form-control mb-2" placeholder="Valor" maxlength="13" required>
                <input name="operacaoE" value="${operacao}" type="text" class="form-control mb-2" placeholder="Operação" maxlength="1" required>
                <input name="dataE" value="${data}" id="dataE" type="date" class="form-control mb-2" placeholder="Data">
                <input name="descricaoE" value="${descricao}" type="text" class="form-control mb-2" placeholder="Descrição" maxlength="100">
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Editar</button>
            </form>
          </div>
          <div class="col-md-8">
            <table class="table table-sm" id="tabela-contas">
              <thead>
                <tr>
                  <th scope="col" style="width: 1% !important;">Id</th>
                  <th scope="col" style="width: 1% !important;">Conta</th>
                  <th scope="col" style="width: 1% !important;">Categoria</th>
                  <th scope="col" style="width: 15% !important;">Valor</th>
                  <th scope="col" style="width: 1% !important;">Op</th>
                  <th scope="col" style="width: 15% !important;">Data</th>
                  <th scope="col" style="width: 20% !important;">Descrição</th>
                  <th scope="col" style="width: 20% !important;">Ações</th>
                </tr>
              </thead>
              <tbody>
                <%
                  for (int i = 0; i < lista_lancamento.size(); i++) {
                    Lancamento aux = lista_lancamento.get(i);
                %>
                <tr>
                  <td><%=aux.getId()%></td>
                  <td><%=aux.getId_conta()%></td>
                  <td><%=aux.getId_categoria()%></td>
                  <td><%=aux.getValor()%></td>
                  <td><%=aux.getOperacao()%></td>
                  <td><%=aux.getData()%></td>
                  <td><%=aux.getDescricao()%></td>
                  <td>
                    <a class="btn" href="get_lancamento?id=<%= aux.getId()%>"><i class=" far fa-edit"></i></a>
                    <a class="btn" href="javascript: apaga_lancamento(<%= aux.getId()%>)"><i class="fas fa-trash-alt"></i></a>
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
      function apaga_lancamento(id) {
        let resposta = confirm('Deseja apagar este lançamento?');
        if (resposta === true) {
          window.location.href = "apaga_lancamento?id=" + id;
        }
      }
    </script>
  </body>
</html>
