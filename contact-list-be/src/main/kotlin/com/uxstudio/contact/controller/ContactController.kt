package com.uxstudio.contact.controller

import com.uxstudio.contact.dto.ContactDTO
import com.uxstudio.contact.service.ContactService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/contacts")
@Validated
class ContactController(val contactService: ContactService) {

    @GetMapping("/{contact_id}")
    @ResponseStatus(HttpStatus.OK)
    fun getContactById(@PathVariable contact_id: Int) : ContactDTO = contactService.getContact(contact_id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun retrieveAllContacts(): List<ContactDTO> = contactService.retrieveAllContacts()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun updateContactById(@ModelAttribute @Valid contactDTO: ContactDTO, @RequestParam picture : MultipartFile?) : ContactDTO =
        contactService.addContact(contactDTO, picture)

    @PutMapping("/{contact_id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateContactById( @PathVariable contact_id: Int, @ModelAttribute @Valid contactDTO: ContactDTO, @RequestParam picture : MultipartFile?, @RequestParam delete : Boolean?) : ContactDTO =
        contactService.updateContactById(contact_id, contactDTO, picture, delete)


    @DeleteMapping("/{contact_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteContactById(@PathVariable contact_id: Int) = contactService.deleteContactById(contact_id)
}