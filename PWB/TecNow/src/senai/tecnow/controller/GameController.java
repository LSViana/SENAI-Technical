package senai.tecnow.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import senai.tecnow.data.mysql.GameDAO;
import senai.tecnow.model.Game;
import senai.tecnow.model.GameCategory;
import senai.tecnow.model.User;
import senai.tecnow.utils.SessionManager;

@Controller
@RequestMapping("/app/games")
public class GameController {
	
	@Autowired
	GameDAO gameDAO;
	@Autowired
	SessionManager sessionManager;
	
	@GetMapping({ "/", "" })
	public ModelAndView games(HttpSession session) {
		ModelAndView mav = new ModelAndView("game/games");
		//
		User u = sessionManager.getLoggedIn(session);
		List<Game> games = gameDAO.get(u);
		mav.addObject("games", games);
		//
		return mav;
	}
	
	@GetMapping("/insert")
	public String insert() {
		return "game/insert";
	}
	
	@GetMapping("/edit")
	public ModelAndView edit(
			@RequestParam(name="id") Long id
			) {
		Game g = gameDAO.get(id);
		ModelAndView mav = new ModelAndView("game/insert");
		mav.addObject("id", g.getId());
		mav.addObject("name", g.getName());
		mav.addObject("category", g.getCategory().toString().toUpperCase());
		return mav;
	}
	
	@PostMapping("/persist")
	public String persist(
			@RequestParam(name="id") Long id,
			@RequestParam(name="name") String name,
			@RequestParam(name="category") String category,
			HttpSession session
			) {
		if(name == null || name.length() < 1 || name.length() > 40) {
			return "redirect:/app/games/insert?message=name";
		}
		//
		User u = sessionManager.getLoggedIn(session);
		Game game = new Game(id, name, GameCategory.valueOf(category.toUpperCase()), new Date(), u);
		gameDAO.persist(game);
		//
		return "redirect:/app/games/";
	}
	
	@GetMapping("/delete")
	public String delete(
			@RequestParam(name="id") Long id
			) {
		gameDAO.delete(id);
		//
		return "redirect:/app/games/";
	}
	
}