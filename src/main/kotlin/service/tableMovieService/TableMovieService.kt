package service.tableMovieService

import connection.Connect
import java.sql.SQLException

class TableMovieService {

    private val connection = Connect().creatConnect()
    private val validDataBaseMovieService = ValidDataBaseMovieService()

    fun addMovie(codeMovie: String, title: String, image: String, description: String, gender: String) {
        try {
            if (!validDataBaseMovieService.isValidMovieInfo(codeMovie, title, image, description, gender)) {
                println("As informações do filme não podem estar vazias ou nulas.")
                return
            }
            val sql =
                "INSERT INTO movies (code, title, image, description, gender) VALUES ('$codeMovie', '$title', '$image', '$description', '$gender')"

            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Filme $codeMovie adicionado com sucesso!")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun deleteMovie(codeMovie: String) {
        if (!validDataBaseMovieService.isValidMovieCode(codeMovie)) {
            println("codigo de filme inválido!")
            return
        }
        val sql =
            "DELETE FROM movies WHERE code=$codeMovie"

        try {
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Filme deletado com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun updateMovie(codeMovie: String, title: String, image: String, description: String, gender: String) {
        try {
            if (!validDataBaseMovieService.isValidMovieCode(codeMovie)) {
                println("codigo de filme inválido!")
                return
            } else if (!validDataBaseMovieService.isValidMovieInfo(codeMovie, title, image, description, gender)) {
                println("As informações do filme não podem estar vazias ou nulas.")
                return
            }
            val sql =
                "UPDATE movies SET title='$title', image='$image', description='$description', gender='$gender' WHERE code=$codeMovie"
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Filme $title atualizado com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
    fun listMovies() {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT code, title, image, description, gender FROM movies")

        try {
            while (resultSet.next()) {
                val codeMovie = resultSet.getString("code")
                val title = resultSet.getString("title")
                val image = resultSet.getString("image")
                val description = resultSet.getString("description")
                val gender = resultSet.getString("gender")
                println("Codigo do Filme: $codeMovie | Nome do filme: $title | Imagem: $image | Descrição: $description | Gênero: $gender")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println()
    }

}