package spring.rest.bookmark.controllers;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.rest.bookmark.data.AccountRepository;
import spring.rest.bookmark.data.BookmarkRepository;
import spring.rest.bookmark.exceptions.UserNotFoundException;
import spring.rest.bookmark.models.Bookmark;

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {
	
	private final BookmarkRepository bookmarkRepository;
	
	private final AccountRepository accountRepository;
	
	@Autowired
	public BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}
	
	@GetMapping
	Collection<Bookmark> read(@PathVariable String userId) throws UserNotFoundException {
		validateUser(userId);
		return bookmarkRepository.findByAccountUsername(userId);
	}
	
	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) throws UserNotFoundException {
		validateUser(userId);
		//
		return accountRepository
				.findByUsername(userId)
				.map(account -> {
					Bookmark result = bookmarkRepository.save(new Bookmark(account, input.getUri(), input.getDescription()));
					URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(result.getId())
							.toUri();
					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());
	}
	
	private Optional<Bookmark> read(@PathVariable String userId, @PathVariable Long bookmarkId) throws UserNotFoundException {
		validateUser(userId);
		return bookmarkRepository.findById(bookmarkId);
	}
	
	private void validateUser(String userId) throws UserNotFoundException {
		accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

}
