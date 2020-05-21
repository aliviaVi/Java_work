package com.telran.contacts_js.repository;

import com.telran.contacts_js.dto.Contact;

import java.util.List;

public interface IContactRepo {

    void add(Contact contact);

    Contact get(int id);

    void edit(Contact contact);

    Contact remove(int id);

    boolean remove(Contact contact);

    List<Contact> getAll();
}
