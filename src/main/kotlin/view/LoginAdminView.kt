package view

import model.InputUserModel
import service.TableFavoriteMovieService
import service.TableMovieService
import service.TableUserService

class LoginAdminView {
    private val loginView = LoginUserView()
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
                    1 -> addMovie()
                    2 -> deleteMovies()
                    3 -> updateMovie()
                    4 -> deleteUser()
                    else -> println("Opção inválida, tente novamente!")
                }
            } while (option != 0)
        } else {
            println("Senha ou nome invalidos!")
        }
    }
    private fun addMovie(){
        val code = inputUserModel.readStringFromUser("Codigo do Filme: ")
        val title = inputUserModel.readStringFromUser("Nome do filme: ")
        val image = inputUserModel.readStringFromUser("Url da imagem do filme: ")
        val descriotion = inputUserModel.readStringFromUser("Descrição do filme: ")
        val gender = inputUserModel.readStringFromUser("Qual o gênero do filme: ")
        tableMovieService.addMovie(code,title,image,descriotion,gender)
    }
    private fun deleteMovies(){
        val code = inputUserModel.readStringFromUser("Qual o codigo do Filme: ")
        tableMovieService.deleteMovie(code)
    }
    private fun updateMovie(){
        val code = inputUserModel.readStringFromUser("Novo codigo do Filme: ")
        val title = inputUserModel.readStringFromUser("Novo nome do filme: ")
        val image = inputUserModel.readStringFromUser("Nova Url da imagem do filme: ")
        val descriotion = inputUserModel.readStringFromUser("Nova descrição do filme: ")
        val gender = inputUserModel.readStringFromUser("Qual o gênero do filme: ")

        tableMovieService.updateMovie(code,title,image,descriotion,gender)
    }
    private fun deleteUser(){
        tableUserService.listUsers()
        val id = inputUserModel.readIntFromUser("Qual o id do usuario que deseja deletar: ")
        tableUserService.deleteUser(id)
    }

    private fun menu() {
        println("0. Menu Principal |" +
                " 1. Add Filmes |" +
                " 2. Deletar Filmes |" +
                " 3. Modificar Fimes |" +
                " 4. Deletar Usuario |" +
                " 5. ")
    }
}