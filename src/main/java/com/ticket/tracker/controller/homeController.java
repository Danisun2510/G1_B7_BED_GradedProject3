package com.ticket.tracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		if(list == null)
		{
			System.out.println("asdasdasdasdasdasd");
		}
		System.out.println("Hello");
		System.out.println(list);
		m.addAttribute("tickets", list);
		
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
		return "redirect:/create";
	}
}
