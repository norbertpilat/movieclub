package lnp.movieclub.movie;

import lnp.movieclub.genre.Genre;
import lnp.movieclub.genre.GenreRepository;
import lnp.movieclub.movie.dto.MovieDto;
import lnp.movieclub.movie.dto.MovieSaveDto;
import lnp.movieclub.rating.Rating;
import lnp.movieclub.rating.RatingRepository;
import lnp.movieclub.storage.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;
    private final FileStorageService fileStorageService;

    public List<MovieDto> findAllPromotedMovies(){
        return movieRepository.findAllByPromotedIsTrue()
                .stream()
                .map(MovieDtoMapper::mapper)
                .toList();
    }

    public Optional<MovieDto> findMovieById(long id){
        return movieRepository.findById(id).map(MovieDtoMapper::mapper);
    }

    public List<MovieDto> findMoviesByGenreName(String genre){
        return movieRepository.findAllByGenre_NameIgnoreCase(genre)
                .stream()
                .map(MovieDtoMapper::mapper)
                .toList();
    }

    public void addMovie(MovieSaveDto movieToSave){
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setOriginalTitle(movieToSave.getOriginalTitle());
        movie.setPromoted(movieToSave.isPromoted());
        movie.setReleaseYear(movieToSave.getReleaseYear());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setDescription(movieToSave.getDescription());
        movie.setYoutubeTrailerId(movieToSave.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if (movieToSave.getPoster() != null && movieToSave.getPoster().getSize() > 0) {
            String savedFileName = fileStorageService.saveImage(movieToSave.getPoster());
            movie.setPoster(savedFileName);
        }
        movieRepository.save(movie);
    }

    public List<MovieDto> findTopMovies(int size){
        Pageable page = Pageable.ofSize(size);
        return movieRepository.findTopByRating(page).stream()
                .map(MovieDtoMapper::mapper)
                .toList();
    }

    //edytuj film
    @Transactional
    public void editMovie(MovieSaveDto movie) {
        Movie movieFromDb = movieRepository.findByTitleIgnoreCase(movie.getTitle()).orElseThrow();
        movieFromDb.setTitle(movie.getTitle());
        movieFromDb.setOriginalTitle(movie.getOriginalTitle());
        movieFromDb.setReleaseYear(movie.getReleaseYear());
        movieFromDb.setShortDescription(movie.getShortDescription());
        movieFromDb.setDescription(movie.getDescription());
        movieFromDb.setYoutubeTrailerId(movie.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movie.getGenre()).orElseThrow();
        movieFromDb.setGenre(genre);
        movieFromDb.setPromoted(movie.isPromoted());
        movieRepository.save(movieFromDb);
    }
    //usun film
}
