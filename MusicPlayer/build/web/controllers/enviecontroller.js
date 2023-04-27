function enviarMusica()
{
    event.preventDefault();

    const URL = 'ServletRecebeMusica' 

    let form = document.getElementById("formMusic")
    
    if(form.querySelector("#estilo").value != "" &&
       form.querySelector("#musica").value != "" &&
       form.querySelector("#artista").value != "" &&
       form.querySelector("#inputTag").value != ""
    ) {
        // form.innerHTML = `<div class="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>`
        var formData = new FormData(form)
        document.getElementById("main-card").style.display = "none"
        document.getElementById("main-loading").style.display = "block"
        fetch(URL, {
          method: 'post',
          body: formData
        }).then( function(response) {
          return response.text()
        }).then(function(retorno) {
            //document.getElementById("main-confirm").innerHTML = retorno;
            document.getElementById("main-loading").style.display = "none"
            document.getElementById("main-confirm").style.display = "flex"
        }).catch (function(error) {
            //document.getElementById("main-confirm").innerHTML = error;
            console.log(error)
        });
    }
    else {
        window.alert("Campos não preenchidos!")
    }
}

//fetch api para executar o servlet de busca de musicas
    // repassando a chave
    //depois dos dados(nome completo de cada mp3) recebidos em uma string
    // divida com split
    // faça um laço
    // gere um html com os audio controls
    /*html+=`<audio controls>
             <source src="${musicas[i]}" type="audio/mpeg">
           </audio>`
    // devolver o html gerado para a interface de busca*/