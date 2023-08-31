package view

import model.InputUserModel
import service.TableFavoriteMovieService
import service.TableMovieService
import service.TableUserService

class LoginUserView {
    private val inputUserModel = InputUserModel()
    private val tableMovieService = TableMovieService()
    private val tableUserService = TableUserService()
    private val tableFavoriteMovieService = TableFavoriteMovieService()
    fun fazerLogin() {
        val name = inputUserModel.readStringFromUser("Digite seu nome:")
        val password = inputUserModel.readStringFromUser("Digite a senha:")

        if (tableUserService.isValidUserCredentials(name, password)) {
            println("========== Bem-Vindo $name ==========")
            var option: Int
            do {
                menu()
                option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

                when (option) {
                    0 -> MenuView()
                    1 -> availableMovies()
                    2 -> availableFavoriteMovies()
                    3 -> addFavoriteMovies()
                    4 -> deleteFavoriteMovies()
                    else -> println("Opção inválida, tente novamente!")
                }
            } while (option != 0)
        } else {
            println("Senha ou nome invalidos!")
        }
    }

    private fun availableMovies() {
        tableMovieService.listMovies()
    }

    private fun availableFavoriteMovies() {
        tableFavoriteMovieService.listFavoriteMovies()
    }

    private fun addFavoriteMovies() {
        val title = inputUserModel.readStringFromUser("Qual o nome do filme: ")
        val image = inputUserModel.readStringFromUser("Qual a url da imagem do filme: ")

        if (!tableMovieService.isValidMovieToAddToFavorite(title)) {
            println("Filme não encontardo, tente adicinar outro!")
            return
        } else {
            tableFavoriteMovieService.addFavoriteMovie(title, image)
        }
    }

    private fun deleteFavoriteMovies(){
        val id = inputUserModel.readIntFromUser("Qual o ID do filme que deseja deletar: ")
        tableFavoriteMovieService.deleteFavoriteMovie(id)
    }
    private fun menu() {
        println(" 0. Menu Principal |" +
                " 1. Filmes disponiveis |" +
                " 2. Filmes Favoritos |" +
                " 3. Adicinar ao favoritos |" +
                " 4. Deletar do favorito")
    }
}