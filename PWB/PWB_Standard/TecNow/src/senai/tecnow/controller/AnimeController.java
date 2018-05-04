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

import senai.tecnow.data.mysql.AnimeDAO;
import senai.tecnow.model.Anime;
import senai.tecnow.model.User;
import senai.tecnow.utils.SessionManager;

@Controller
@RequestMapping("/app/animes")
public class AnimeController {

	@Autowired
	AnimeDAO animeDAO;
	@Autowired
	SessionManager sessionManager;
	
	@GetMapping({ "/", "" })
	public ModelAndView animes(HttpSession session) {
		ModelAndView mav = new ModelAndView("anime/animes");
		//
		User u = sessionManager.getLoggedIn(session);
		List<Anime> animes = animeDAO.get(u);
		mav.addObject("animes", animes);
		//
		return mav;
	}
	
	@GetMapping("/insert")
	public String insert() {
		return "anime/insert";
	}
	
	@GetMapping("/edit")
	public ModelAndView edit(
			@RequestParam(name="id") Long id
			) {
		Anime a = animeDAO.get(id);
		ModelAndView mav = new ModelAndView("anime/insert");
		mav.addObject("id", a.getId());
		mav.addObject("name", a.getName());
		return mav;
	}
	
	@PostMapping("/persist")
	public String persist(
			HttpSession session,
			@RequestParam(name="id") Long id,
			@RequestParam(name="name") String name
			) {
		if(name == null || name.length() < 1 || name.length() > 40) {
			return "redirect:/app/animes/insert?message=name";
		}
		//
		User u = sessionManager.getLoggedIn(session);
		Anime anime = new Anime(id, name, new Date(), u);
		animeDAO.persist(anime);
		//
		return "redirect:/app/animes/";
	}
	
	@GetMapping("/delete")
	public String delete(
			@RequestParam(name="id") Long id
			) {
		animeDAO.delete(id);
		//
		return "redirect:/app/animes/";
	}
	
}
