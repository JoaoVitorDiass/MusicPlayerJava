function pesquisarMusica() {   
    
    event.preventDefault();
    let html = document.getElementById("musics");
    
    const URL = 'ServletPesquisaMusica'; 

    var formData = new FormData(document.getElementById("pesquisa"));

    fetch(URL,{
        method:'post',
        body: formData
    }).then(function(response) {
        return response.json();
    }).then(retorno => {
        
        let artista
        let estilo
        let url
        let musica
        let conteudoHTML
        
        html.innerHTML = "";

        var temp = []
        var audioTemp = []

        console.log(retorno.length)
        for(let i = 0; i < retorno.length; i++) {
            
            artista = retorno[i]['Artista']
            estilo = retorno[i]['Estilo']
            url = retorno[i]['Caminho']
            musica = retorno[i]['Nome']
            
            
            if(artista.indexOf(".txt") != -1) {
                artista = artista.split(".txt")
                artista = artista[0]
            }
            if(artista.indexOf(".mp3") != -1) {
                artista = artista.split(".mp3")
                artista = artista[0]
            }
            console.log(artista)
            if(artista != 'null') {

                html.innerHTML += `<div class='line-music' >
                <div class='music-play'>
                <button class='music-icon-play' onclick='playMusic("`+musica+`","`+artista+`","`+btoa(url)+`")' >
                    <span class='material-symbols-outlined arrow-icon'>
                        play_arrow
                    </span>
                </button>
                    </div>
            <div id='musica-img' class='musica-img'></div>
                <div class='music-infos'>
                <strong>
                    <div id='music-name' class='music-name'>`+musica+`</div>
                </strong>
                <div id='music-artist' class='music-artist'>`+artista+`</div>
                </div>
            <div id='music-genero' class='music-genero'>`+estilo+`</div>
                <div id='music-tamanho-`+i+`' class='music-tamanho'>`+temp+`</div>
                </div>`

                audioTemp[i] = new Audio("musicas_recebidas/"+url)
                audioTemp[i].addEventListener(
                "loadeddata",
                () => {
                    
                    document.getElementById("music-tamanho-"+i).innerText = getTimeCodeFromNum(
                        audioTemp[i].duration
                    );
                },
                false
                );
        
            }
        } 
        html.style.display = "block"
    }).catch(function(error) {
        html.innerHTML = error;
    });
}
//html.style.display = "block"
function playMusic(nome_msc,artista,url) {

    audio.src = "musicas_recebidas/"+atob(url);

    let a = document.getElementById("buton-play-pause")
    a.innerHTML = "play_arrow"
    a.style.fontVariationSettings = "'FILL' 1"

    document.getElementById("footer-nome-musica").innerText = nome_msc;
    document.getElementById("footer-nome-artista").innerText = artista;
}

document.getElementById("musics").style.display = "block"