package com.github.andresvasquez.topmovies.shared.data.source.local

import com.github.andresvasquez.topmovies.shared.data.Result
import java.lang.NullPointerException

class LocalDataSource internal constructor(databaseDriverFactory: DatabaseDriverFactory) :
    LocalDataSourceI {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    override fun getMovies(): Result<List<MovieDTO>> {
        val results = dbQuery.getMovies(::mapSelecting).executeAsList()
        return Result.Success(results);
    }

    private fun mapSelecting(
        id: Long,
        originalTitle: String,
        originalLanguage: String?,
        overview: String?,
        adult: Boolean?,
        popularity: Double?,
        posterPath: String?,
        releaseDate: Long?,
        rating: Double?,
        genreIds: String?
    ): MovieDTO {
        return MovieDTO(
            id = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            overview = overview,
            adult = adult,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            rating = rating,
            genreIds = genreIds
        )
    }

    override fun getMovieById(movieId: Long): Result<MovieDTO> {
        val movie = dbQuery.getMovieById(movieId).executeAsOneOrNull()
        if (movie != null) {
            val movieDTO = MovieDTO(
                id = movie.id,
                originalTitle = movie.originalTitle,
                originalLanguage = movie.originalLanguage,
                overview = movie.overview,
                adult = movie.adult,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                rating = movie.rating,
                genreIds = movie.genreIds
            )
            return Result.Success(movieDTO)
        } else {
            return Result.Error(NullPointerException("Movie not found"));
        }
    }

    override fun insertMovies(movies: List<MovieDTO>) {
        dbQuery.transaction {
            movies.forEach { movie ->
                dbQuery.insertMovie(
                    id = movie.id,
                    originalTitle = movie.originalTitle,
                    originalLanguage = movie.originalLanguage,
                    overview = movie.overview,
                    adult = movie.adult,
                    popularity = movie.popularity,
                    posterPath = movie.posterPath,
                    releaseDate = movie.releaseDate,
                    rating = movie.rating,
                    genreIds = movie.genreIds,
                )
            }
        }
    }

    override fun deleteMovies() {
        dbQuery.transaction {
            dbQuery.deleteMovies()
        }
    }
}