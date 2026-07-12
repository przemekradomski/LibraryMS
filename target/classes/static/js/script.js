document.addEventListener("DOMContentLoaded", function(){
    
    var searchInput = document.querySelector(".search");

    if(searchInput){
        searchInput.addEventListener("keyup", function(event){
            if(event.key === "Enter"){
                var query = searchInput.value.trim();
                if(query.lenght > 0){
                    if (query.length > 0) {
                        window.location.href = "/books?search=" + encodeURIComponent(query);
                    }
                }
            }
        })
    }

});


document.addEventListener("DOMContentLoaded", function(){
    var addButton = document.getElementById("addBook");
    var closeButton = document.getElementById("closeForm");
    var form = document.getElementById("addBooksForm");
    var cancelButton = document.getElementById("cancelForm"); 

    if(addButton){ addButton.addEventListener("click", function(){form.classList.remove("hidden");}); }
    if(closeButton){ closeButton.addEventListener("click", function(){form.classList.add("hidden");}); }
    if(cancelButton){ cancelButton.addEventListener("click", function(){form.classList.add("hidden");}); }
    form.addEventListener("submit", function(event){
        if(!confirm("Czy na pewno chcesz dodać książkę?")){
            event.preventDefault();
        }
    })

});

document.addEventListener("DOMContentLoaded", function(){
    document.querySelectorAll(".btn-danger").forEach(function(button){
        button.addEventListener("click", function(event){
            if(!confirm("Czy na pewno chcesz usunąć książkę?")){
                event.preventDefault();
            }
        })
    })
});

function searchBooks(){
    var searchInput = document.querySelector(".search");
    if(searchInput){
        var query = searchInput.value.trim();
        if(query.length > 0){
            window.location.href = "/books?search" + encodeURIComponent(query);
            let result = document.createElement("p");
            result.innerHTML = "Nie znaleziono książek spełniających kryteria wyszukiwania.";
            result.setStyle.color = "red";
            document.body.appendChild(result);
        }

    }
}

function clearSearch(){
    var searchInput = document.querySelector(".search");
    if(searchInput){
        searchInput.value = "";
    }
}