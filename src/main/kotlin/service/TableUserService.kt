package service

import connection.Connect
import java.sql.SQLException

class TableUserService {

    private val connection = Connect().creatConnect()

    fun addUser(cpf: String, alias: String, email: String, senha: String) {
        try {
            if (!isValidUserInfo(cpf, alias, email, senha)) {
                println("As informações do usuário não podem estar vazias ou nulas.")
                return
            }else if(!isValidEmail(email)){
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
        if (!isValidUserId(id)) {
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
            if (!isValidUserId(id)) {
                println("ID de usuário inválido!")
                return
            } else if (!email.isNotBlank() && senha.isNotBlank()) {
                println("As informações do usuário não podem estar vazias ou nulas.")
                return
            } else if(!isValidEmail(email)){
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

    private fun isValidUserId(id: Int): Boolean {
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
    private fun isValidUserInfo(cpf: String, alias: String, email: String, senha: String): Boolean {
        return cpf.isNotBlank() && alias.isNotBlank() && email.isNotBlank() && senha.isNotBlank()
    }
    private fun isValidEmail(email: String): Boolean {
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