package com.example.demo.service;

import com.example.demo.constants.DomConstants;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(DomConstants.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(DomConstants.CLIENT_ROLE);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(DomConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(DomConstants.CLOSE);
            contact1.setUpdatedAt(LocalDateTime.now());
            contact1.setUpdatedBy(contact1.getName());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null){
            isUpdated = true;
        }
        return isUpdated;
    }

}
