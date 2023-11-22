package lnp.movieclub.genre;

import lnp.movieclub.genre.dto.GenreDto;
import lnp.movieclub.genre.dto.GenreSaveDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    //wyszukanie gatunków po nazwie
    public Optional<GenreDto> findGenreByName(String name){
        return genreRepository.findByNameIgnoreCase(name)
                .map(GenreDtoMapper::map);
    }
    //wyszukanie wyszystkich gatunków
    public List<GenreDto> findAllGenres(){
        return StreamSupport.stream(genreRepository.findAll().spliterator(),false)
                .map(GenreDtoMapper::map)
                .toList();
    }

    //wyszukanie po id
    public Optional<GenreDto> findGenreById(long id){
        return genreRepository.findById(id).map(GenreDtoMapper::map);
    }
    //dodanie gatunku
    @Transactional
    public void addGenre(GenreDto genre){
        Genre genreToSave = new Genre();
        genreToSave.setName(genre.getName());
        genreToSave.setDescription(genre.getDescription());
        genreRepository.save(genreToSave);
    }

    //zapisanie edytowanego gatunku
    @Transactional
    public void editGenre(GenreSaveDto genre){
        Genre genreFromDb = genreRepository.findByNameIgnoreCase(genre.getName()).orElseThrow();
        genreFromDb.setName(genre.getName());
        genreFromDb.setDescription(genre.getDescription());
        genreRepository.save(genreFromDb);
    }
}
