package daniilk.example.controller;

import daniilk.example.persistance.Contact;
import daniilk.example.service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ContactServiceController {

	private final ContactService contactService;

	public ContactServiceController(ContactService contactService) {
		this.contactService = contactService;
	}

	@RequestMapping({"/","/contacts"})
	public String listAllContacts(Model model,  @RequestParam("page") Optional<Integer> page,
								  @RequestParam("size") Optional<Integer> size) {
		List<Contact> allContacts = contactService.listAllContacts();
		if (allContacts != null && allContacts.size() > 0) {
			preparePagination(allContacts, model, page.orElse(1), size.orElse(5));
		} else {
			model.addAttribute("error", "No data found!");
		}
		return "contacts";
	}

	@RequestMapping({"/search"})
	public String searchContactsByName(Model model, @RequestParam("name") String name) {
		List<Contact> foundContacts = contactService.searchContactsByName(name);
		if (foundContacts != null && foundContacts.size() > 0) {
			preparePagination(foundContacts, model, 1, foundContacts.size());
		} else {
			model.addAttribute("error", "No data found!");
		}
		return "contacts";
	}

	private void preparePagination(List<Contact> contactList, Model model, int currentPage, int pageSize) {
		Page<Contact> contactPage = contactService.paginateContacts(contactList, PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("contactPage", contactPage);
		int totalPages = contactPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
	}
}
