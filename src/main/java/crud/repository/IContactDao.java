/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository;

import crud.repository.model.Contact;
import java.util.List;

/**
 *
 * @author losmelli
 */
public interface IContactDao {
    List<Contact> getContacts();
    void saveContact(Contact contact);
    Contact getContact(String username);
}
