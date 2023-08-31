package service.tableMovieService

import connection.Connect
import java.sql.SQLException

class ValidDataBaseMovieService {
    private val connection = Connect().creatConnect()
    fun isValidMovieCode(codeMovie: String): Boolean {
        val sql = "SELECT COUNT(*) FROM movies WHERE code=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, codeMovie)
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
    fun isValidMovieInfo(codeMovie: String, title: String, image: String, description: String, gender: String): Boolean {
        return codeMovie.isNotBlank() && title.isNotBlank() && image.isNotBlank() && description.isNotBlank() && gender.isNotBlank()
    }
    fun isValidMovieToAddToFavorite(title: String): Boolean {
        val sql = "SELECT COUNT(*) FROM movies WHERE title=?"

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, title)
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