package servlets;

public class Musica {
    String Caminho,Artista,Estilo,Nome;

    public Musica(String Caminho, String Artista, String Estilo, String Nome) {
        this.Caminho = Caminho;
        this.Artista = Artista;
        this.Estilo = Estilo;
        this.Nome = Nome;
    }

    public Musica() {
    }
    
    public String getCaminho() {
        return Caminho;
    }

    public void setCaminho(String Caminho) {
        this.Caminho = Caminho;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String Artista) {
        this.Artista = Artista;
    }

    public String getEstilo() {
        return Estilo;
    }

    public void setEstilo(String Estilo) {
        this.Estilo = Estilo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    
}
