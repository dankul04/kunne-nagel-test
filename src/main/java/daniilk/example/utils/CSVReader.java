package daniilk.example.utils;


import daniilk.example.persistance.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Contact> readContactsFromCSV() {
        List<Contact> contacts = new ArrayList<>();
        Contact contact;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("people.csv");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                contact = createContact(attributes);
                if (contact != null)
                    contacts.add(contact);
                line = br.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private static Contact createContact(String[] attributes) {
        if (attributes[0].equalsIgnoreCase("name") && attributes[1].equalsIgnoreCase("url"))
            return null;
        else if (attributes.length > 2)
            return new Contact(attributes[0] + attributes[1], attributes[2]);
        else return new Contact(attributes[0], attributes[1]);
    }
}



