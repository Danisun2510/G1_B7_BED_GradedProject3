package com.ticket.tracker.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.tracker.entity.Tickets;
import com.ticket.tracker.repository.TicketRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class homeController {
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@GetMapping("/")
	public String home(Model m)
	{
		List<Tickets> list = ticketRepo.findAll();
		
		System.out.println(m);
		
		if(!m.containsAttribute("tickets"))
		{
			m.addAttribute("tickets", list);
		}
		
		return "home";
	}
	
	@GetMapping("/create")
	public String create_ticket()
	{
		return "createTicket";
	}
	
	@GetMapping("/delete")
	public void delete_ticket()
	{
		return ;
	}
	
	@PostMapping("/make_ticket")
	public String add_ticket(@ModelAttribute Tickets tkt_details, HttpSession session) {
		
		tkt_details.setDate(LocalDate.now());
		ticketRepo.save(tkt_details);
		session.setAttribute("msg", "Added successfully");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit_ticket(@PathVariable(value= "id") long id, Model m)
	{
	
		Optional<Tickets> tkt = ticketRepo.findById(id);
		Tickets ticket = tkt.get();
		m.addAttribute("ticket", ticket);
		
		return "editTicket";
	}
	
	@PostMapping("/update_ticket")
	public String update_ticket(@ModelAttribute Tickets tkt_details, HttpSession session) {
		
		tkt_details.setDate(LocalDate.now());
		System.out.println(tkt_details);
		ticketRepo.save(tkt_details);
		session.setAttribute("msg", "Added successfully");
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String delete_ticket(@PathVariable(value="id") long id, HttpSession session)
	{
		
		ticketRepo.deleteById(id);
		session.setAttribute("msg", "Ticket deleted");
		
		return "redirect:/";
	}
	
	@PostMapping("/search_ticket")
	public String search_ticket(@RequestParam("ticket") String ticket, Model m )
	{
		
		Optional<Tickets> tkt = ticketRepo.getByTitle(ticket); 
	
		if( tkt.isPresent())
		{
			m.addAttribute("tickets", tkt.get());
		}
		
		
		return "home";
	}
}
