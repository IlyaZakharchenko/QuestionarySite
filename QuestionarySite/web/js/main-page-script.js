var isChanged = true;

function marginImagesByParent(parent) {
    var images = $(parent).find(".under-imgs");
    $(images).each(function(i, el) {
        $(el).width($(parent).width() / images.length - 7)
        console.log($(el).width());
    });
}

function getListHeight(parent) {
    var height = parseInt($(parent).find(".feats").attr("h"));
    if (height == 0) {
        height = parseInt($(parent).find(".feats").innerHeight());
        $(parent).find(".feats").attr("h", height);
    }
    return height;
}

function priceToDown(parent) {
    var height = $(parent).height() + getListHeight(parent);
    if (window.innerHeight >= 768) {
        $(parent).find(".inf").stop(true).animate({
            height: height
        }, 300);
    }
    $(parent).find(".feats").stop(true).slideDown(function () {
        isChanged = true;
    });
}

function priceToUp(parent) {
    var height = $(parent).height() - getListHeight(parent);
    if (window.innerHeight >= 768) {
        $(parent).find(".inf").stop(true).animate({
            height: height
        }, 300)
    }
    $(parent).find(".feats").stop(true).slideUp(function () {
        isChanged = true;
    });
}

$(document).ready(function() {
    var fromDate = $("#form-from-date");
    var toDate = $("#form-to-date");
    
    $(fromDate).datepicker();
    $(toDate).datepicker();
    
    var isSRCHLaunched = false;
    
    
    $(".btn-toggle").on("click", function() {
        var isToggled = $(this).attr("toggled") == "true";
        if (!isChanged) {
            return;
        }
        isChanged = false;
        if (!isToggled) {
            priceToDown($(this).parent().parent());
            $(this).attr("toggled", "true");
        } else {
            priceToUp($(this).parent().parent())
            $(this).attr("toggled", "false");
        }
        return false;
    })
    
    marginImagesByParent($("#main-img-container"));
    marginImagesByParent($("#gallery-img-container"));
    
    var inputs = document.getElementsByClassName("form-control");
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].onfocus = function () {
                $(this).attr("placeholder", "");
            }
            inputs[i].addEventListener("focusout", function() {
                var hint = this.getAttribute("hint");
                this.setAttribute("placeholder", hint);
            });
        }
        $(function() {
            $("#slider").slider({
                range: true,
                min: 0,
                max: 1000,
                values:[0, 300],
                slide: function(event, el) {
                    $( "#amount" ).html( "$" + el.values[ 0 ] + " - $" + el.values[ 1 ] );
                }
        });
        $( "#amount" ).html( "$" + $( "#slider").slider("values",0) + " - $" + $( "#slider" ).slider( "values", 1 ) );
        });
        
        $("[data-toggle='tooltip']").tooltip();
        
        var par = document.getElementsByClassName("out-li");
        if (window.innerHeight >= 768) {
            $(par).each(function(n, el){
                $(el).find(".inf").height($(el).height());
            })
        } else {
            $(par).each(function(n, el) {
                $(el).find(".price").css({
                    position:'relative'
                });
            })
        }
});