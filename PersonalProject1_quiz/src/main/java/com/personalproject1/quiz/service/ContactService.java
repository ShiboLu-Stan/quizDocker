package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.ContactDao;
import com.personalproject1.quiz.dao.FeedBackDao;
import com.personalproject1.quiz.domain.Contact;
import com.personalproject1.quiz.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContactService {
    private final ContactDao contactDao;

    @Autowired
    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public List<Contact> getAllContact() {
        return contactDao.getAllContacts();
    }

    public void newContact(int userID, String contactText){
        contactDao.createNewContact(new Contact(0, new Date(), userID, contactText));
    }

}
