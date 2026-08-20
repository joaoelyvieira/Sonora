public class Plataforma {

    private static final int capacidade = 500;

    private Musica[] acervo;
    private int totalMusicas;
    private Usuario[] usuarios;
    private int totalUsuarios;

    public Plataforma() {
        this.acervo = new Musica[capacidade];
        this.totalMusicas = 0;
        this.usuarios = new Usuario[capacidade];
        this.totalUsuarios = 0;
    }

    public boolean cadastrarMusica(Musica musica) {
        if(musica == null || totalMusicas >= capacidade) {
            return false;
        }
        acervo[totalMusicas] = musica;
        totalMusicas++;
        return true;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if(usuario == null || totalUsuarios >= capacidade) {
            return false;
        }
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        return true;
    }

    public Musica buscarMusicaPorId(int id) {
        for (int i = 0; i < totalMusicas; i++) {
            if(acervo[i].getId() == id) {
                return acervo[i];
            }
        }
        return null;
    }

    public Musica buscarMusica(String titulo) {
        for (int i = 0; i < totalMusicas; i++) {
            if(acervo[i].getTitulo().equalsIgnoreCase(titulo)) {
                return acervo[i];
            }
        }
        return null;
    }

    public int getTotalMusicas() {
        return totalMusicas;
    }

    public int getTotalUsuarios() {
        return totalUsuarios;
    }

    public Musica getMusicaNoAcervo(int indice) {
        if(indice < 0 || indice >= totalMusicas) {
            return null;
        }
        return acervo[indice];
    }

    public Usuario getUsuario(int indice) {
        if(indice < 0 || indice >= totalUsuarios) {
            return null;
        }
        return usuarios[indice];
    }
}
