import service.TableMovieService
import service.TableUserService
import view.MenuView

fun main() {
    val usersService = TableUserService()
    val moviesService = TableMovieService()

    // Insert users
    usersService.addUser("43755230400", "Raissa Vicente", "Raissa@gmail.com", "a111")
    usersService.addUser("23456789010", "Ana", "Ana@gmail.com", "b111")
    usersService.addUser("12332111198", "Paula", "Paula@gmail.com", "c111")
    usersService.addUser("12234456678", "José", "José@gmail.com", "d111")
    usersService.addUser("98765432132", "Mariana", "Mariana@gmail.com", "e111")

    // Insert movies
    moviesService.addMovie(
        "a123",
        "Doutor Estranho no Multiverso da Loucura",
        "hq2igFqb31fDqGotz8ZuUfwKgn8.jpg",
        "“Doutor Estranho no Multiverso da Loucura” apresenta conexões com as aclamadas séries “WandaVision” e “Loki”. Após derrotar Dormammu e enfrentar Thanos, o protagonista continua fazendo investigações sobre a joia do tempo. No entanto, antigos amigos poderão colocar tudo a perder para ele.",
        "Ação"
    )
    moviesService.addMovie(
        "a321",
        "Arremessando Alto",
        "arremessando-alto-divulgacao.jpg",
        "“Arremessando Alto” conta a história do treinador e olheiro Stanley Beren, um homem que já está cansado do trabalho e acaba sendo demitido. Uma nova esperança vai surgir em sua vida quando ele encontra um jogador amador com grande potencial para a NBA, o espanhol Bo Cruz.",
        "Drama"
    )
    moviesService.addMovie(
        "a111",
        "Órfã 2: A Origem",
        "a-orfa-cke.jpg",
        "Disfarçada de filha que desapareceu há 4 anos de uma família, Esther retorna para a casa. Inicialmente tudo começa de modo afetivo, mas aos poucos ela passa a mostrar a personalidade. Enquanto isso, detetives e psicólogos que estão acompanhando a reintegração dela na família notam algumas inconsistências na história dela que indicam ser uma impostora. Este é um filme de terror pesado!",
        "Terror"
    )
    moviesService.addMovie(
        "a222",
        "Homem-Aranha: Sem Volta para Casa",
        "KEY ART_SPIDER NO WAY HOME.JPG",
        "Homem-Aranha: Sem Volta para Casa teve uma das maiores estreias do ano e agradou os fãs da franquia. Na história, após o super-herói ter sua identidade revelada, ele pede ajuda ao Doutor Estranho. No entanto, um feitiço mal sucedido faz com que vilões poderosos de outras versões de Homem-Aranha acabem surgindo em seu universo.",
        "Ação"
    )
    moviesService.addMovie(
        "a333",
        "The Adam Project",
        "the-adam-project-poster.jpg",
        "Após um acidente durante uma viagem no tempo, Adam vai parar na sua antiga casa, quando era apenas uma criança lidando com o recente falecimento do pai. Diante disto, a versão jovem e a versão do futuro juntam-se, embarcando na missão de lutar contra forças do futuro e consertar a linha do tempo.",
        "Ação"
    )
    moviesService.addMovie(
        "a444",
        "Não Olhe para Cima",
        "01el.jpg",
        "Leonardo DiCaprio, Jennifer Lawrence, Meryl Streep e Cate Blanchett são apenas alguns dos nomes de peso que estão no elenco de “Não Olhe para Cima”, uma comédia dramática repleta de críticas às redes sociais, fake news e negacionismo contemporâneo. Na história, dois astrônomos descobrem que um cometa está em uma rota de colisão com a Terra.",
        "Ficção Científica"
    )
    moviesService.addMovie(
        "a555",
        "007 - Sem Tempo para Morrer",
        "no-time-to-die-new-movie-poster-1235577.jpeg",
        "A mais nova atração da interminável franquia 007 chegou aos cinemas em 2021 no Brasil. Neste novo longa, o espião mais famoso do mundo decide abandonar o seu merecido descanso na Jamaica para investigar o desaparecimento de um cientista. Claro que esse é só o começo de uma aventura recheada de cenas de ação e muita adrenalina!",
        "Ação"
    )
    moviesService.addMovie(
        "a666",
        "Soul",
        "OIP.FYaqDqtZAvT2icizr6bESQHaLH.jpg",
        "Esta nova animação da Pixar é sobre um professor de música que sonha em levar a vida tocando jazz. O problema é que, justo no momento em que as coisas começam a dar certo, ele sofre um terrível acidente que o faz voltar ao 'mundo das almas'.",
        "Aventura"
    )
    moviesService.addMovie(
        "a777",
        "Cruella",
        "Cruella-Poster.jpeg",
        "Estrelado por Emma Stone, Cruella conta a história das origens de uma das vilãs mais aclamadas da Disney, Cruella de Vil, antagonista que aparece em 101 Dálmatas. O filme acompanha a rebelde e talentosa Estella, que possui uma visão única de moda. Ao chamar atenção de uma baronesa em Londres, ela vai acabar abraçando cada vez mais o seu lado cruel.",
        "Desconhecido"
    )
    moviesService.addMovie(
        "a888",
        "Viúva Negra",
        "viuva-negra-critica-filme-2021-marvel-poster-scaled.jpg",
        "Produzido pelos estúdios Marvel, Viúva Negra é uma continuação de Capitão América: Guerra Civil, de 2016. A espiã Natasha Romanoff (Scarlett Johansson) deverá prestar contas com seu passado, enfrentando inimigos terríveis como o Treinador e a Dama de Ferro. Para quem curte um filme de ação, é uma excelente pedida.",
        "Ação"
    )

    val menuView = MenuView()
    menuView.start()
}