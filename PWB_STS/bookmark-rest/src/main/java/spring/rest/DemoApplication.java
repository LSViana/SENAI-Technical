package spring.rest;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.rest.bookmark.data.AccountRepository;
import spring.rest.bookmark.data.BookmarkRepository;
import spring.rest.bookmark.models.Account;
import spring.rest.bookmark.models.Bookmark;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Arrays.asList(
						"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
						.forEach(
								a -> {
									Account account = accountRepository.save(new Account(a, "password"));
									bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + a, "A description"));
									bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + a, "A description"));
								});
			}
		};
	}
}
