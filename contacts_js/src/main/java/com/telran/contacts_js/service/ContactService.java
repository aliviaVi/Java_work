package com.telran.contacts_js.service;

import com.telran.contacts_js.dto.Contact;
import com.telran.contacts_js.repository.IContactRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    IContactRepo contactRepo;

    public ContactService(IContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public void add(Contact contact) {
        contactRepo.add(contact);
    }

    public Contact get(int id) {
        return contactRepo.get(id);
    }

    public List<Contact> getAll() {
        return contactRepo.getAll();
    }

    public void edit(Contact contact) {
        contactRepo.edit(contact);
    }

    public void remove(int id) {
        contactRepo.remove(id);
    }
}
