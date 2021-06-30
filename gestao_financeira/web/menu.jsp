<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <div class="aparecer-menu">
    <div class="hamburger">
      <div class="line" id="line1"></div>
      <div class="line" id="line2"></div>
      <div class="line" id="line3"></div>
    </div>
    <aside class="sidebar">
      <nav>
        <ul class="menu">
          <li class="menu-item">
            <a href="login.jsp" class="menu-link login"><i class="fas fa-sign-in-alt"></i> Login</i></a>
          </li>
          <li class="menu-item">
            <a href="index.jsp" class="menu-link">Home</a>
          </li>
          <li class="menu-item">
            <a href="/admin" class="menu-link">Administrador</a>
          </li>
          <li class="menu-item">
            <a href="/categoria" class="menu-link">Categoria</a>
          </li>
          <li class="menu-item">
            <a href="/conta" class="menu-link">Conta</a>
          </li>
          <li class="menu-item">
            <a href="/lancamento" class="menu-link">Lançamento</a>
          </li>
          <li class="menu-item">
            <a href="/usuario" class="menu-link">Usuário</a>
          </li>
        </ul>
      </nav>
      <div class="social-media">
        <a href="https://github.com/gssantos94/Controle-Financeiro-HTML.git" target="_blank"><i
            class="fab fa-github"></i></a>
      </div>
    </aside>
  </div>
</html>
