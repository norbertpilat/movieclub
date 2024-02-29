package lnp.movieclub.rating;

import lnp.movieclub.movie.Movie;
import lnp.movieclub.movie.MovieRepository;
import lnp.movieclub.user.User;
import lnp.movieclub.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;


    public void addOrUpdateRating(String userEmail, long movieId, int rating){
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setMovie(movie);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
    }

    public Optional<Integer> getUserRatingForMovie(String userEmail, long movieId){
        return ratingRepository.findByUser_EmailAndMovie_Id(userEmail,movieId)
                .map(Rating::getRating);
    }

}
