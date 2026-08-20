import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Plataforma plataforma = new Plataforma();

        plataforma.cadastrarMusica(new Musica("Caso Indefinido", "Cristiano Araújo", 313));
        plataforma.cadastrarMusica(new Musica("A Morte do Autotune", "Matuê", 216));
        plataforma.cadastrarMusica(new Musica("Taças Pro Ar", "Filipe Ret", 233));

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=== Sonora ===");
            System.out.println("1 - Cadastrar musica manualmente");
            System.out.println("2 - Cadastrar usuario");
            System.out.println("3 - Criar playlist e adicionar musicas");
            System.out.println("4 - Buscar musica por id");
            System.out.println("5 - Buscar musica por titulo");
            System.out.println("6 - Reproduzir uma musica");
            System.out.println("7 - Listar acervo");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarMusica(sc, plataforma);
                    break;
                case 2:
                    cadastrarUsuario(sc, plataforma);
                    break;
                case 3:
                    criarPlaylist(sc, plataforma);
                    break;
                case 4:
                    buscarPorId(sc, plataforma);
                    break;
                case 5:
                    buscarPorTitulo(sc, plataforma);
                    break;
                case 6:
                    reproduzirMusica(sc, plataforma);
                    break;
                case 7:
                    listarAcervo(plataforma);
                    break;
                case 0:
                    System.out.println("Até uma próxima!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }

        sc.close();
    }

    private static void cadastrarMusica(Scanner sc, Plataforma plataforma) {
        sc.nextLine();
        System.out.println("Titulo: ");
        String titulo = sc.nextLine();
        System.out.println("Artista: ");
        String artista = sc.nextLine();
        System.out.println("Duração em segundos:");
        int duracao = sc.nextInt();

        Musica nova = new Musica(titulo, artista, duracao);
        boolean ok = plataforma.cadastrarMusica(nova);
        System.out.println(ok ? "Cadastrada com id " + nova.getId() : "Erro ao cadastrar");
    }

    public static void cadastrarUsuario(Scanner sc, Plataforma plataforma) {
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Usuario novo = new Usuario(nome, email);
        boolean ok = plataforma.cadastrarUsuario(novo);
        System.out.println(ok ? "Cadastrado com id " + novo.getId() : "Erro ao cadastrar");
    }

    private static void criarPlaylist(Scanner sc, Plataforma plataforma) {
        if (plataforma.getTotalUsuarios() == 0) {
            System.out.println("Cadastre um usuário primeiro.");
            return;
        }

        sc.nextLine();
        System.out.println("Nome da playlist: ");
        String nome = sc.nextLine();

        System.out.print("Indice do usuario dono (0 a " + (plataforma.getTotalUsuarios() - 1) + "): ");
        int indiceUsuario = sc.nextInt();
        Usuario dono = plataforma.getUsuario(indiceUsuario);

        if (dono == null) {
            System.out.println("Usuario invalido.");
            return;
        }

        Playlist playlist = new Playlist(nome,dono);

        System.out.println("Quantas músicas você deseja adicionar?");
        int qtd = sc.nextInt();

        for (int i = 0; i < qtd; i++) {
            System.out.print("Id da musica " + (i + 1) + ": ");
            int id = sc.nextInt();
            Musica musica = plataforma.buscarMusicaPorId(id);
            boolean ok = playlist.adicionar(musica);
            System.out.println(ok ? "Adicionada: " + musica : "Nao foi possivel adicionar.");
        }

        System.out.println("Playlist '" + playlist.getNome() + "' criada com "
                + playlist.getQuantidade() + " musicas, duracao total de "
                + playlist.getDuracaoTotalSegundos() + " segundos.");
    }

    private static void buscarPorId(Scanner sc, Plataforma plataforma) {
            System.out.println("Id da música: ");
            int id = sc.nextInt();
            Musica musica = plataforma.buscarMusicaPorId(id);
            System.out.println(musica != null ? musica : "Música não encontrada.");
    }

    private static void buscarPorTitulo(Scanner sc, Plataforma plataforma) {
        sc.nextLine();
        System.out.print("Titulo da musica: ");
        String titulo = sc.nextLine();
        Musica musica = plataforma.buscarMusica(titulo);
        System.out.println(musica != null ? musica : "Musica nao encontrada.");
    }

    private static void reproduzirMusica(Scanner sc, Plataforma plataforma) {
        System.out.print("Id da musica: ");
        int id = sc.nextInt();
        Musica musica = plataforma.buscarMusicaPorId(id);
        if (musica == null) {
            System.out.println("Musica nao encontrada.");
            return;
        }
        musica.reproduzir();
        System.out.println("Reproduzida. Total de reproducoes: " + musica.getReproducoes());
    }

    private static void listarAcervo(Plataforma plataforma) {
        System.out.println("--- Acervo (" + plataforma.getTotalMusicas() + " musicas) ---");
        for (int i = 0; i < plataforma.getTotalMusicas(); i++) {
            System.out.println(plataforma.getMusicaNoAcervo(i));
        }
    }
}
