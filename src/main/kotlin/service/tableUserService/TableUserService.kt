package service.tableUserService

import connection.Connect
import java.sql.SQLException

class TableUserService {

    private val connection = Connect().creatConnect()
    private val validDataBase = ValidDataBaseUserService()

    fun addUser(cpf: String, alias: String, email: String, senha: String) {
        try {
            if (!validDataBase.isValidUserInfo(cpf, alias, email, senha)) {
                println("As informações do usuário não podem estar vazias ou nulas.")
                return
            }else if(!validDataBase.isValidEmail(email)){
                println("Email invalido!")
                return
            }
            val sql =
                "INSERT INTO users (cpf, alias, email, senha) VALUES ('$cpf', '$alias', '$email', '$senha')"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Usuário $alias adicionado com sucesso!")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deleteUser(id: Int) {
        if (!validDataBase.isValidUserId(id)) {
            println("ID de usuário inválido!")
            return
        }
        val sql =
            "DELETE FROM users WHERE id=$id"

        try {
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Usuário deletado com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun updateUser(id: Int, email: String, senha: String) {
        try {
            if (!validDataBase.isValidUserId(id)) {
                println("ID de usuário inválido!")
                return
            } else if (!email.isNotBlank() && senha.isNotBlank()) {
                println("As informações do usuário não podem estar vazias ou nulas.")
                return
            } else if(!validDataBase.isValidEmail(email)){
                println("Email invalido!")
                return
            }
            val sql =
                "UPDATE users SET email='$email', senha='$senha' WHERE id=$id"
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Dados atualizados com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listUsers() {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT id, cpf, alias, email FROM users")

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val cpf = resultSet.getString("cpf")
                val alias = resultSet.getString("alias")
                val email = resultSet.getString("email")
                println("ID do Usuário: $id | CPF: $cpf | Nome: $alias | Email: $email")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println()
    }


}