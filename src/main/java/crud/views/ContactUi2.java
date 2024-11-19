package crud.views;

import crud.repository.UserDao;
import crud.repository.model.Contact;
import crud.repository.model.UserDto;
import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import org.w3c.dom.UserDataHandler;

public class ContactUi2 extends JPanel {

    private JLabel profileImageLabel;
    private JLabel nameLabel;
    private Contact contact;

    public ContactUi2(Contact contact) {
        this.contact = contact;
        // Set the layout for horizontal stacking (profile image and name side by side)
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(200, 70)); // Adjust height as needed
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); // Allow it to expand in width

        // Profile Image (use an icon here or a placeholder)
        
        UserDao dao = new UserDao();
        UserDto dto = dao.retrieveUser(contact.getUsername());
        System.out.println(dto.getPhotoPath());
        
        profileImageLabel = new JLabel();
        String path = dto.getPhotoPath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
        profileImageLabel.setIcon(imageIcon); // Replace with actual image path
        profileImageLabel.setPreferredSize(new Dimension(75, 55)); // Square image
        profileImageLabel.setMaximumSize(new Dimension(75, 55));  // Ensure it stays the same size

        // Name Label
        nameLabel = new JLabel(contact.getUsername());
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align text to the left
        nameLabel.setFont(new Font("Segoe ui", Font.PLAIN, 20) {
        });
        add(Box.createRigidArea(new Dimension(20, 0))); // This adds space to the left of the profile image

        // Add components to the panel
        add(profileImageLabel);
        add(Box.createRigidArea(new Dimension(15, 0))); // Space between image and text
        add(nameLabel);
        add(Box.createHorizontalGlue());  // Allow name to expand to fill remaining space

        // Set background color for better visualization (optional)
        setBackground(new Color(244, 232, 232));
    }

    public void setImage(String path) {
        profileImageLabel = new JLabel();

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH));
        profileImageLabel.setIcon(imageIcon); // Replace with actual image path
        profileImageLabel.setPreferredSize(new Dimension(75, 55)); // Square image
        profileImageLabel.setMaximumSize(new Dimension(75, 55));  // Ensure it stays the same size
    }

    public JLabel getProfileImageLabel() {
        return profileImageLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public String getContactName() {
        return contact.getUsername();
    }

    public Contact getContact() {
        return contact;
    }

}
