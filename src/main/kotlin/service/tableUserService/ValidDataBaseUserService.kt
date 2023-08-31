package service.tableUserService

import connection.Connect
import java.sql.SQLException

class ValidDataBaseUserService {
    private val connection = Connect().creatConnect()
    fun isValidUserId(id: Int): Boolean {
        val sql = "SELECT COUNT(*) FROM users WHERE id=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            val resultSet = preparedStatement.executeQuery()
            resultSet.next()
            val count = resultSet.getInt(1)

            resultSet.close()
            preparedStatement.close()

            return count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return false
    }
    fun isValidUserInfo(cpf: String, alias: String, email: String, senha: String): Boolean {
        return cpf.isNotBlank() && alias.isNotBlank() && email.isNotBlank() && senha.isNotBlank()
    }
    fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.endsWith("@gmail.com")
    }
    fun isValidUserCredentials(alias: String, senha: String): Boolean {
        if (alias.isBlank() || senha.isBlank()) {
            println("O nome de usuário e a senha não podem estar vazios.")
            return false
        }

        val sql = "SELECT COUNT(*) FROM users WHERE alias=? AND senha=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, alias)
            preparedStatement.setString(2, senha)
            val resultSet = preparedStatement.executeQuery()
            resultSet.next()
            val count = resultSet.getInt(1)

            resultSet.close()
            preparedStatement.close()

            return count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return false
    }
    fun userInfoByAlias(alias: String) {
        val sql = "SELECT id, cpf, email FROM users WHERE alias=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, alias)
            val resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                val id = resultSet.getInt("id")
                val cpf = resultSet.getString("cpf")
                val email = resultSet.getString("email")
                println("Informações da Conta:\n ID: $id | CPF: $cpf | Nome: $alias | Email: $email")
            } else {
                println("Usuário com o nome $alias não encontrado.")
            }

            resultSet.close()
            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}