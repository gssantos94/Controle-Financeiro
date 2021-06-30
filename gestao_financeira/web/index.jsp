<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <jsp:include page="header.html" />
    <title>Gestão Financeiro</title>
  </head>
  <body>
    <div id="preloader">
      <div class="inner">
        <div class="bolas">
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
    </div>
    <header id="topo">
      <jsp:include page="menu.jsp" />
      <div class="img-wrapper">
        <img src="assets/img/background.jpg">
      </div>
      <div class="banner">
        <h1>Gestão Financeira</h1>
        <h2>Créd & Déb</h2>
        <p>Gustavo Santos & Nicolas Bicalho</p>
        <a type="button" href="#tecnologias">Saiba mais</a>
      </div>
    </header>


    <section class="tecnologias" id="tecnologias">
      <div class="main-titulo">
        <h1>Tecnologias Utilizadas</h1>
      </div>
      <div class="container text-center">
        <div class="row">
          <div class="offset-md-1 col-md-5 text-md-start">
            <div class="tec-titulo">
              <i class="fab fa-css3-alt" style="color: #0e76a8;"></i>
              <h3>CSS</h3>
            </div>
            <div class="tecnologia-text">
              <p>É a sigla para Cascading Style Sheets, ou seja, Folhas de Estilo em Cascatas. É uma maneira de dar
                estilo ao código criado por linguagens como HTML, XML ou XHTML, entre outras.</p>
            </div>
          </div>
          <div class="col-md-5 text-md-start">
            <div class="tec-titulo">
              <i class="fab fa-bootstrap" style="color: #533b77;"></i>
              <h3>Bootstrap 5</h3>
            </div>
            <div class="tecnologia-text">
              <p>É uma ferramenta gratuita para desenvolvimento Web. Crie sites e aplicações completas com nosso
                sistemas de grid responsivo, componentes pré-construídos, entre outras features.</p>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="offset-md-1 col-md-5 text-md-start">
            <div class="tec-titulo">
              <i class="fab fa-html5" style="color: #e55c25;"></i>
              <h3>HTML 5</h3>
            </div>
            <div class="tecnologia-text">
              <p>O HTML5, sigla para Hypertext Markup Language, é uma linguagem de marcação de hipertexto para
                apresentar e estruturar o conteúdo de sites na web HTML5 é a nova versão do HTML4</p>
            </div>
          </div>
          <div class="col-md-5 text-md-start">
            <div class="tec-titulo">
              <i class="fab fa-java" style="color: #e51e23"></i>
              <h3>Java Web</h3>
            </div>
            <div class="tecnologia-text">
              <p>Java é uma linguagem de programação, uma aplicação Java Web gera páginas Web interativas, que contêm
                vários tipos de linguagem de marcação (HTML, XML, etc) e conteúdo dinâmico.</p>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="offset-md-1 col-md-5 text-md-start">
            <div class="tec-titulo">
              <i class="fab fa-js" style="color: #f0d81e"></i>
              <h3>Javascript</h3>
            </div>
            <div class="tecnologia-text">
              <p>É uma linguagem de programação que permite a você implementar itens complexos em páginas web, É a
                terceira camada do bolo das tecnologias padrões da web seguindo o HTML e oCSS</p>
            </div>
          </div>
          <div class="col-md-5 text-md-start">
            <div class="tec-titulo">
              <i class="fas fa-database" style="color: #1b4d68;"></i>
              <h3>MySQL</h3>
            </div>
            <div class="tecnologia-text">
              <p>O MySQL é um sistema gerenciador de banco de dados relacional de código aberto usado na maioria das
                aplicações gratuitas para gerir suas bases de dados. Utiliza a linguagem SQL</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <footer class="text-center">
      <jsp:include page="footer.jsp"/>
    </footer>
    <a href="#topo" id="link-topo"><i class="fas fa-arrow-up"></i></a>
      <jsp:include page="scripts.html"/>
  </body>
</html>
