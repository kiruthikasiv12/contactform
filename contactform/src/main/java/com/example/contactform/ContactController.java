package com.example.contactform;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/submit")
    public String submitForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message) {

        System.out.println("========== FORM DATA ==========");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);
        System.out.println("================================");

        Contact contact = new Contact(name, email, message);

        contactRepository.save(contact);

        return """
                <html>
                <body style='font-family:Arial;text-align:center;padding-top:100px'>
                    <h1>✅ Message Sent Successfully!</h1>
                    <p>Thank you for contacting us.</p>
                    <a href='/'>Go Back</a>
                </body>
                </html>
                """;
    }
    @GetMapping("/contacts")
public List<Contact> getAllContacts() {
    return contactRepository.findAll();
}

}