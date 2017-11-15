$(document).ready(function () {
    var cont_btn = $(this).find("#cont");
    cont_btn.hide();
    var h1 = $(this).find(".main").find("h1");
    h1.hide();
    var h2 = $(this).find(".main").find("h2");
    h2.hide();
    var h3 = $(this).find(".main").find("h3");
    h3.hide();
    
    $(".head-content").removeClass("hidden");
    
    
    $(h1).show('fade', 700, function() {
        $(h2).show('slide', {direction: 'down'}, 400, function() {
            $(h3).show('slide', {direction: 'down'}, 400, function() {
                $(cont_btn).fadeIn(400, function() {
                    $(this).css({transition:'0.5s'});
                    });    
                });
            });
    });
    
    var icons = document.getElementsByClassName('icon-floating');
    for (var i = 0; i < icons.length; i++) {
        $(icons[i]).css({'opacity':0}).removeClass('hidden');
    }

});

