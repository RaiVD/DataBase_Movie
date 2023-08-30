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


    private fun cadastrarUser(){

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