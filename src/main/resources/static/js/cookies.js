function setCookie(name, value, days){
    var expires = ""
    if(days){
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expieres=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires
}

function getCookie(name){
    
    var nameEQ = name + "=";
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++){
        var c = cookies[i].trim();
        if(c.indexOf(nameEQ) === 0){
            return c.substring(nameEQ.length);
        }
    }

    return null;

}

document.addEventListener("DOMContentLoaded", function(){
    
    var cookieBanner = document.getElementById("cookie-banner");
    var acceptButton = document.getElementById("accept-cookies");
    var declineButton = document.getElementById("decline-cookies");

    if(!getCookie("cookiesAccepted")){
        cookieBanner.classList.remove("hide-banner");
    }

    acceptButton.addEventListener("click", function(){
        setCookie("cookiesAccepted", "true", 365);
        cookieBanner.classList.add("hide-banner");
    })

    declineButton.addEventListener("click", function(){
        setCookie("cookiesAccepted", "false", 365);
        let result = document.createElement("p");
        result.innerHTML = "Nie zakceptowałeś plików cookie. Nie możesz korzystać z tej aplikacji, dopuki nie zakceptujesz plików cookies";
        cookieBanner.appendChild(result);
    })

});
