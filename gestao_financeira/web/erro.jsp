<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <jsp:include page="header.html"/>
    <title>Gestão Financeira</title>
  </head>
  <body>
    <jsp:include page="menu.jsp"/>
    <section class="container pt-5">
      <article class="text-center">
        <p class="display-4 alert-warning">${mensagem}</p>
        <a type="button" href="index.jsp" name="voltar" class="btn btn-outline-dark btn-lg text-nowrap">Ínicio</a>
      </article>
    </section>
    <footer class="text-center fixed-bottom">
      <jsp:include page="footer.jsp"/>
    </footer>
    <jsp:include page="scripts.html"/>
  </body>
</html>