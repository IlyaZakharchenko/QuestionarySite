function isVisible(elem) {
    var height = document.documentElement.clientHeight;
    var coords = elem.getBoundingClientRect();
    
    var top = coords.top > 0 && coords.top < height;
    var bottom = coords.bottom < height && coords.bottom > 0;
    
    return top || bottom;
}

function animate(images, type) {
    for (var i = 0; i < images.length; i++) {
        if ($(images[i]).isShown) continue;
        if (isVisible(images[i])) {
            if (type == "show") {
                $(images[i]).delay(100).animate({
                    'opacity':1
                }, 700);
            } else if (type == "slide") {
                $(images[i]).delay(100).slideDown('slow');
            }
        }
    }
}

function showImage() {
    var images = document.getElementsByClassName('icon-floating');
    animate(images, "show");
}

window.onscroll = showImage;
showImage();