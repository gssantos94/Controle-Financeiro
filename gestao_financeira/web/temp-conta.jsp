<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="JavaBeans.Conta"%>
<%@ page import="java.util.ArrayList"%>
<%
  ArrayList<Conta> lista_conta = (ArrayList<Conta>) request.getAttribute("conta");
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <jsp:include page="header.html"/>
    <title>Form: Contas</title>
    <script>

    </script>
  </head>
  <body>
    <jsp:include page="menu.jsp"/>
  <body>
    <main class="pag-temporarias">
      <section class="container">
        <h1 class="text-center p-4" id="topo">
          Formulário de Contas
        </h1>
        <div class="row">
          <div class="col-md-2 text-center">
            <form method="post" action ="cria_conta" class="form-temporario p-md-0">
              <i class="fab fa-2x fa-creative-commons-share"></i>
              <h1 class="h3 mb-1">Criar</h1>
              <div class="form-temporario mb-2">
                <input name="id_usuarioC" type="text" class="form-control mb-2" placeholder="Id Usuário" required>
                <input name="nome_contaC" type="text" class="form-control mb-2" placeholder="Conta" maxlength="20"
                       required>
                <input name="bancoC" type="text" class="form-control mb-2" placeholder="Código Banco" maxlength="3"
                       required>
                <input name="agenciaC" type="text" class="form-control mb-2" placeholder="Agência" maxlength="6"
                       required>
                <input name="conta_correnteC" type="text" class="form-control mb-2" placeholder="Conta Corrente"
                       maxlength="6" required>
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Criar</button>
            </form>
          </div>
          <div class="col-md-2 text-center">
            <form method="post" action="edita_conta" class=" form-temporario p-4 p-md-0">
              <i class="far fa-2x fa-edit"></i>
              <h1 class="h3 mb-1">Editar</h1>
              <div class="form-temporario mb-2">
                <input name="id" value="${id}" type="text" class="form-control mb-2" placeholder="Id" readonly>
                <input name="id_usuarioE" value="${id_usuario}" type="text" class="form-control mb-2" placeholder="Id Usuário" required>
                <input name="nome_contaE" value="${nome_conta}" type="text" class="form-control mb-2" placeholder="Conta" maxlength="20"
                       required>
                <input name="bancoE" value="${banco}" type="text" class="form-control mb-2" placeholder="Código Banco" maxlength="3"
                       required>
                <input name="agenciaE" value="${agencia}" type="text" class="form-control mb-2" placeholder="Agência" maxlength="6"
                       required>
                <input name="conta_correnteE" value="${conta_corrente}" type="text" class="form-control mb-2" placeholder="Conta Corrente"
                       maxlength="6" required>
              </div>
              <button class="btn mb-2 btn-lg btn-primary" type="submit">Editar</button>
            </form>
          </div>
          <div class="col-md-8">
            <table class="table table-sm" id="tabela-contas">
              <thead>
                <tr>
                  <th scope="col" style="width: 1% !important;">Id</th>
                  <th scope="col" style="width: 1% !important;">Usuário</th>
                  <th scope="col" style="width: 30% !important;">Nome da Conta</th>
                  <th scope="col" style="width: 2% !important;">Banco</th>
                  <th scope="col" style="width: 5% !important;">Ag</th>
                  <th scope="col" style="width: 5% !important;">CC</th>
                  <th scope="col" style="width: 20% !important;">Ações</th>
                </tr>
              </thead>
              <tbody>
                <%
                  for (int i = 0; i < lista_conta.size(); i++) {
                    Conta aux = lista_conta.get(i);
                %>
                <tr>
                  <td><%=aux.getId()%></td>
                  <td><%=aux.getId_usuario()%></td>
                  <td><%=aux.getNome_conta()%></td>
                  <td><%=aux.getBanco()%></td>
                  <td><%=aux.getAgencia()%></td>
                  <td><%=aux.getConta_corrente()%></td>
                  <td>
                    <a class="btn" href="get_conta?id=<%= aux.getId()%>"><i class=" far fa-edit"></i></a>
                    <a class="btn" href="javascript: apaga_conta(<%= aux.getId()%>)"><i class="fas fa-trash-alt"></i></a>
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
      function apaga_conta(id) {
        let resposta = confirm('Deseja apagar esta conta?');
        if (resposta === true) {
          window.location.href = "apaga_conta?id=" + id;
        }
      }
    </script>
  </body>
</html>
