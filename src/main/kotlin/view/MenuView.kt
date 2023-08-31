package view

import model.InputUserModel
import service.tableUserService.TableUserService

class MenuView {
    private val loginUserView = LoginUserView()
    private val loginAdminView = LoginAdminView()
    private val inputUserModel = InputUserModel()
    private val tableUserService = TableUserService()

    fun start() {
        try {
            println("======================== Cinema SimCity ==========================")
            var option: Int
            do {
                printMenu()
                option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

                when (option) {
                    0 -> println("Encerrando o programa...")
                    1 -> loginUserView.fazerLogin()
                    2 -> registerUser()
                    3 -> loginAdminView.fazerLogin()
                    else -> println("Opção inválida, tente novamente!")
                }
            } while (option != 0)
        }catch (e: IllegalArgumentException){
            println("Erro: ${e.message}")
        }
    }

    private fun registerUser(){
        val cpf = inputUserModel.readStringFromUser("Digite o seu CPF sem caracter especial: ")
        val name = inputUserModel.readStringFromUser("Digite seu nome: ")
        val email = inputUserModel.readStringFromUser("Digite seu email: ")
        val senha = inputUserModel.readStringFromUser("Digite a senha: ")

        tableUserService.addUser(cpf,name,email,senha)
    }

    private fun printMenu() {
        println("\n. Sair | 1. Login Usuario | 2. Cadastrar Usuario | 3. Login Admin")
    }
}