package view

import model.InputUserModel
import service.TableFavoriteMovieService
import service.TableMovieService
import service.TableUserService

class MenuView {
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
                1 -> fazerLogin()
                2 -> cadastrarUser()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    private fun cadastrarUser(){
        val cpf = inputUserModel.readStringFromUser("Digite o seu CPF sem caracter especial: ")
        val name = inputUserModel.readStringFromUser("Digite seu nome: ")
        val email = inputUserModel.readStringFromUser("Digite seu email: ")
        val senha = inputUserModel.readStringFromUser("Digite a senha: ")

        tableUserService.addUser(cpf,name,email,senha)

    }
    private fun fazerLogin(){
        val name = inputUserModel.readStringFromUser("Digite seu nome:")
        val senha = inputUserModel.readStringFromUser("Digite a senha:")


        if(tableUserService.isValidUserCredentials(name,senha)){
            println("Bem-Vindo $name")
        }else{
            println("Senha ou nome invalidos!")
        }
    }
    private fun printMenu() {
        println("\n0. Sair | 1. Usuario | 2. Cadastrar")
    }
}