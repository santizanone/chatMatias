/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository;

import crud.repository.model.Contact;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author losmelli
 */
public class ContactDao implements IContactDao {

    private String username;

    public ContactDao(String username) {
        this.username = username;
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> contactsList = new ArrayList<>();
        Path file = Paths.get(System.getProperty("user.home"), "contactsList-" + username, "contacts.txt");
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String nameDivided[] = line.split("-");
                
                contactsList.add(new Contact(nameDivided[0],Integer.parseInt(nameDivided[1])));
            }
        } catch (IOException x) {
            System.err.format("1 - IOException: %s%n", x);
        }
        return contactsList;
    }

    @Override
    public void saveContact(Contact contact) {
        Charset charset = Charset.forName("UTF-8");
        String username = contact.getUsername();
        Path dir = Paths.get(System.getProperty("user.home"), "contactsList-" + this.username);
        Path path = Paths.get(System.getProperty("user.home"), "contactsList-" + this.username, "contacts.txt");

        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException ex) {
                Logger.getLogger(ContactDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path, charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(username + "-"  + contact.getId());
            writer.newLine();
        } catch (IOException x) {
            System.err.format("2 -IOException: %s%n", x);
        }

    }

    @Override
    public Contact getContact(String username) {
        Path file = Paths.get(System.getProperty("user.home"), "contactsList-" + this.username, "contacts.txt");
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.equals(username)) {
                    String nameDivided[] = line.split("-");
                    return new Contact(nameDivided[0],Integer.parseInt(nameDivided[1]));
                }
            }
        } catch (IOException x) {
            System.err.format(" 3- IOException: %s%n", x);
            return null;
        }
        return null;
    }

}
