document.querySelector(".hamburger").addEventListener("click", () =>
  document.querySelector(".aparecer-menu").classList.toggle("show-menu")
);

// Selecione todos os links com hashes
$('a[href*="#"]')
        // Remova os links que na verdade não direcionam a nada
        .not('[href="#"]')
        .not('[href="#0"]')
        .click(function (event) {
          // Links na página
          if (
                  location.pathname.replace(/^\//, '') === this.pathname.replace(/^\//, '') &&
                  location.hostname === this.hostname
                  ) {
            // Descobrir o elemento para rolar para
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            // Existe um alvo de rolagem?
            if (target.length) {
              // Apenas previna o padrão se a animação realmente acontecer
              event.preventDefault();
              $('html, body').animate({
                scrollTop: target.offset().top
              }, 500, function () {
                // Retorno de chamada após animação
                // Deve mudar o foco!
                var $target = $(target);
                $target.focus();
                if ($target.is(":focus")) { // Verificar se o alvo estava focado
                  return false;
                } else {
                  $target.attr('tabindex', '-1'); // Adicionando tabindex para elementos não focalizáveis
                  $target.focus(); // Defina o foco novamente
                }
                ;
              });
            }
          }
        });

//<![CDATA[
$(window).on('load', function () {
  if (document.querySelector("#preloader")) {
    $('#preloader .inner').fadeOut();
    $('#preloader').delay(550).fadeOut('slow');
    $('body').delay(550).css({
      'overflow': 'visible'
    });
  }
});
//]]>