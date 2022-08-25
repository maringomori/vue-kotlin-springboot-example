package com.uxstudio.contact.repository

import com.uxstudio.contact.entity.Contact
import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, Int> {

}