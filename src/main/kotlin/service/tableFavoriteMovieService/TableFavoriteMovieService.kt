package service.tableFavoriteMovieService

import connection.Connect
import java.sql.SQLException

class TableFavoriteMovieService {

    private val connection = Connect().creatConnect()
    private val validDataBaseFavoriteMovieService = ValidDataBaseFavoriteMovieService()

    fun addFavoriteMovie(title: String, image: String) {
        try {
            if (!validDataBaseFavoriteMovieService.isValidFavoriteMovieInfo(title, image)) {
                println("As informações do filme favorito não podem estar vazias ou nulas.")
                return
            }
            val sql =
                "INSERT INTO favoriteMovies (title, image) VALUES ('$title', '$image')"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Filme favorito adicionado com sucesso!")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deleteFavoriteMovie(id: Int) {
        if (!validDataBaseFavoriteMovieService.isValidFavoriteMovieId(id)) {
            println("ID de filme favorito inválido!")
            return
        }
        val sql =
            "DELETE FROM favoriteMovies WHERE id=$id"

        try {
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Filme favorito deletado com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

//    fun updateFavoriteMovie(id: Int, title: String, image: String) {
//        try {
//            if (!isValidFavoriteMovieId(id)) {
//                println("ID de filme favorito inválido!")
//                return
//            } else if (!isValidFavoriteMovieInfo(title, image)) {
//                println("As informações do filme favorito não podem estar vazias ou nulas.")
//                return
//            }
//            val sql =
//                "UPDATE favoriteMovies SET title='$title', image='$image' WHERE id=$id"
//            val statement = connection.createStatement()
//            statement.executeUpdate(sql)
//            println("Filme favorito $title atualizado com sucesso!")
//            statement.close()
//        } catch (e: SQLException) {
//            e.printStackTrace()
//        }
//    }

    fun listFavoriteMovies() {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT id, title, image FROM favoriteMovies")

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val title = resultSet.getString("title")
                val image = resultSet.getString("image")
                println("ID: $id | Nome do filme: $title | Imagem: $image")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println()
    }


}
