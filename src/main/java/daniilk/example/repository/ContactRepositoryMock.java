package daniilk.example.repository;



import daniilk.example.persistance.Contact;
import daniilk.example.utils.CSVReader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepositoryMock {

    private static List<Contact> initContacts;

    public List<Contact> listAllContacts() {
        if (initContacts == null) {
            initContacts();
        }
        return initContacts;
    }

    public List<Contact> searchContactsByName(String name) {
        List<Contact> contactsFound = new ArrayList<>();
        if (initContacts == null) {
            initContacts();
        }
        for (Contact contact : initContacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())){
                contactsFound.add(contact);
            }
        }
        return contactsFound;
    }

    private void initContacts() {
        initContacts = CSVReader.readContactsFromCSV();
    }
}
