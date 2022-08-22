
$(document).ready(function(){

    //Check to see if the window is top if not then display button
    $(window).scroll(function(){
        if ($(this).scrollTop() > 100) {
            $('.scrollToTop').fadeIn();
        } else {
            $('.scrollToTop').fadeOut();
        }
    });
    
    //Click event to scroll to top
    $('.scrollToTop').click(function(){
        $('html, body').animate({scrollTop : 0},1000);
        return false;
    });

    $(".home-menu").click(function (){
        $('html, body').animate({
            scrollTop: $(".home").offset().top - 100
        }, 1000);
    });

    $(".about-menu").click(function (){
        $('html, body').animate({
            scrollTop: $(".about").offset().top - 100
        }, 1000);
    });

    $(".services-menu").click(function (){
        $('html, body').animate({
            scrollTop: $(".services").offset().top - 100
        }, 1000);
    });

     $(".dishes-menu").click(function (){
        $('html, body').animate({
            scrollTop: $(".dishes").offset().top - 100
        }, 1000);
    });

    $(".contact-menu").click(function (){
        $('html, body').animate({
            scrollTop: $(".contact").offset().top - 100
        }, 1000);
    });



    $(".menu-icon").click(function () {
        $(".overlay").css("width","60%");
    });

    $('main, footer').click(function(){
        $(".overlay").css("width","0%");
    });
    

    const sr = ScrollReveal({
        origin: 'top',
        distance: '30px',
        duration: 2000,
        reset: true
    });

    sr.reveal(`.home,.about,.services,.dishes,.app,.contact`,{
        interval:200
    });
    
});


