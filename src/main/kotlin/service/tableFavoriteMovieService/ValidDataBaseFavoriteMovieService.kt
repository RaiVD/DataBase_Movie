package service.tableFavoriteMovieService

import connection.Connect
import java.sql.SQLException

class ValidDataBaseFavoriteMovieService {
    private val connection = Connect().creatConnect()

    fun isValidFavoriteMovieId(id: Int): Boolean {
        val sql = "SELECT COUNT(*) FROM favoriteMovies WHERE id=?"

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

    fun isValidFavoriteMovieInfo(title: String, image: String): Boolean {
        return title.isNotBlank() && image.isNotBlank()
    }
}