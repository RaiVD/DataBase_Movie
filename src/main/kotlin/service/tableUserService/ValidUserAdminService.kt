package service.tableUserService

import connection.Connect
import java.sql.SQLException

class ValidUserAdminService {
    private val connection = Connect().creatConnect()

    private fun isAdmin(alias: String): Boolean {
        return alias == "José"
    }

    fun isValidUserCredentials(alias: String, senha: String): Boolean {
        if (alias.isBlank() || senha.isBlank()) {
            throw IllegalArgumentException("O nome de usuário e a senha não podem estar vazios.")
        }

        if (isAdmin(alias)) {
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

                if (count > 0) {
                    return true
                } else {
                    throw IllegalArgumentException("Credenciais de administrador inválidas.")
                }
            } catch (e: SQLException) {
                // Handle the exception appropriately, e.g., log it
                e.printStackTrace()
                throw RuntimeException("Erro ao verificar as credenciais do usuário.")
            }
        } else {
            throw IllegalArgumentException("Acesso não autorizado. Você não é um administrador.")
        }
    }
}
