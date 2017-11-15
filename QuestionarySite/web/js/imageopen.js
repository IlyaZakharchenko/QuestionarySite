$(document).ready(function() {
    
    var modal = $(".image-modal");
    var img = $(modal).find(".image-modal-content");

    $(".image").on("click", function(){
        $(modal).css({
            display:"block"
        }); 
        $(img).attr("src", $(this).attr("src"));
    });

    $(".close").on("click", function() {
        $(modal).css({
            display:"none"
        });
    });
    
    $(document).keyup(function(event) {
        if (event.keyCode == 27) {
            $(modal).css({
                display:"none"
            });
        }
    })
})