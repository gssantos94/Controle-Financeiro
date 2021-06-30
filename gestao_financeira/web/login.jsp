<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="header.html"/>
    <title>Login</title>
  </head>
  <jsp:include page="menu.jsp"/>

  <body>
    <main class="pag-login text-center">
      <form class="needs-validation" method="post" action="autenticar">
        <i class="fas mb-4 fa-4x fa-user-circle"></i>
        <h1 class="h3 mb-3 fw-normal">Login</h1>
        <div class="form-floating">
          <input name="cpf" type="text" class="form-control" id="cpf" placeholder="123.456.789-00" required>
          <label for="floatingInput">CPF</label>
        </div>

        <div class="form-floating mb-4 mt-4">
          <input name="senha" type="password" class="form-control" placeholder="Senha" required>
          <label for="floatingSenha">Senha</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
      </form>
    </main>
    <jsp:include page="scripts.html"/>
    <script type="text/javascript" src="assets/js/jquery.maskedinput.js"></script>
    <script type="text/javascript">
      $(document).ready(function () {
        $("#cpf").mask("999.999.999-99");
      });
    </script>
  </body>
</html>
