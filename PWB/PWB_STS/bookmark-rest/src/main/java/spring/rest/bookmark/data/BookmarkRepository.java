package spring.rest.bookmark.data;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.rest.bookmark.models.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	
	Collection<Bookmark> findByAccountUsername(String username);
	
	Optional<Bookmark> findById(Long id);

}
