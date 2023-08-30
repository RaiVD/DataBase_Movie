package service

import connection.Connect
import java.sql.SQLException

class TableUserService {

    private val connection = Connect().creatConnect()

    fun addUser(cpf: String, nameUser: String, email: String, senha: String) {
        try {
            if (!isValidUserInfo(cpf, nameUser, email, senha)) {
                println("As informações do usuário não podem estar vazias ou nulas.")
                return
            }else if(!isValidEmail(email)){
                println("Email invalido!")
                return
            }
            val sql =
                "INSERT INTO users (cpf, nameUser, email, senha) VALUES ('$cpf', '$nameUser', '$email', '$senha')"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Usuário $nameUser adicionado com sucesso!")
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

    fun updateUser(id: Int, cpf: String, nameUser: String, email: String, senha: String) {
        try {
            if (!isValidUserId(id)) {
                println("ID de usuário inválido!")
                return
            } else if (!isValidUserInfo(cpf, nameUser, email, senha)) {
                println("As informações do usuário não podem estar vazias ou nulas.")
                return
            } else if(!isValidEmail(email)){
                println("Email invalido!")
                return
            }
            val sql =
                "UPDATE users SET cpf='$cpf', nameUser='$nameUser', email='$email', senha='$senha' WHERE id=$id"
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Usuário $nameUser atualizado com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listUsers() {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT id, cpf, nameUser, email FROM users")

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val cpf = resultSet.getString("cpf")
                val nameUser = resultSet.getString("nameUser")
                val email = resultSet.getString("email")
                println("ID do Usuário: $id | CPF: $cpf | Nome: $nameUser | Email: $email")
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
    private fun isValidUserInfo(cpf: String, nameUser: String, email: String, senha: String): Boolean {
        return cpf.isNotBlank() && nameUser.isNotBlank() && email.isNotBlank() && senha.isNotBlank()
    }
    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.endsWith("@gmail.com")
    }
    fun isValidUserCredentials(nameUser: String, senha: String): Boolean {
        if (nameUser.isBlank() || senha.isBlank()) {
            println("O nome de usuário e a senha não podem estar vazios.")
            return false
        }

        val sql = "SELECT COUNT(*) FROM users WHERE nameUser=? AND senha=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(3, nameUser)
            preparedStatement.setString(5, senha)
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
}