package view

import model.InputUserModel
import service.TableFavoriteMovieService
import service.TableMovieService
import service.TableUserService

class MenuView {
    private val loginView = LoginUserView()
    private val inputUserModel = InputUserModel()
    private val tableMovieService = TableMovieService()
    private val tableUserService = TableUserService()
    private val tableFavoriteMovieService = TableFavoriteMovieService()

    fun start() {
        println("======================= Cinema SimCity =========================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> println("Encerrando o programa...")
                1 -> loginView.fazerLogin()
                2 -> registerUser()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    private fun registerUser(){
        val cpf = inputUserModel.readStringFromUser("Digite o seu CPF sem caracter especial: ")
        val name = inputUserModel.readStringFromUser("Digite seu nome: ")
        val email = inputUserModel.readStringFromUser("Digite seu email: ")
        val senha = inputUserModel.readStringFromUser("Digite a senha: ")

        tableUserService.addUser(cpf,name,email,senha)
    }

    private fun printMenu() {
        println("0. Sair | 1. Login Usuario | 2. Cadastrar Usuario | 3. Login Admin")
    }
}