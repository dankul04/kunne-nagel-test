package daniilk.example.service;


import daniilk.example.persistance.Contact;
import daniilk.example.repository.ContactRepositoryMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContactService {
    @Value("${mockUp}")
    private boolean mockUp;

    // ToDo: comment out lines
//    private ContactRepository contactRepository;
    private final ContactRepositoryMock contactRepositoryMock;

    public ContactService(ContactRepositoryMock contactRepositoryMock) {
        this.contactRepositoryMock = contactRepositoryMock;
    }


    public List<Contact> listAllContacts() {
        // ToDo: comment out lines
//        if (mockUp)
        return contactRepositoryMock.listAllContacts();
        // ToDo: comment out lines
//        else
//            return contactRepository.findAll();
    }

    public List<Contact> searchContactsByName(String name) {
        // ToDo: comment out lines
//        if (mockUp)
        return contactRepositoryMock.searchContactsByName(name);
        // ToDo: comment out lines
//        else
//            return contactRepository.search(name);
    }

    public Page<Contact> paginateContacts(List<Contact> contacts, Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Contact> contactList;

        if (contacts.size() < startItem) {
            contactList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, contacts.size());
            contactList = contacts.subList(startItem, toIndex);
        }
        Page<Contact> contactPage
                = new PageImpl<Contact>(contactList, PageRequest.of(currentPage, pageSize), contacts.size());
        return contactPage;

    }

}
