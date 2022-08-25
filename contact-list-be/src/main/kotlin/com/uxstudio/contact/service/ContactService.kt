package com.uxstudio.contact.service

import com.google.common.io.Files
import com.uxstudio.contact.dto.ContactDTO
import com.uxstudio.contact.entity.Contact
import com.uxstudio.contact.exception.ContactNotFoundException
import com.uxstudio.contact.repository.ContactRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class ContactService(val contactRepository: ContactRepository, val fileService: FileService) {

    fun getContact(contactId: Int): ContactDTO {
        val existingContact = contactRepository.findById(contactId)

        return if(existingContact.isPresent) {
            existingContact.get().let {
                ContactDTO(it.id, it.email, it.name, it.phoneNumber,  it.profilePicture)
            }
        } else {
            throw ContactNotFoundException("No contact found for the contactId: $contactId")
        }
    }

    fun retrieveAllContacts(): List<ContactDTO> {
        return contactRepository.findAll().map {
            ContactDTO(it.id, it.email, it.name, it.phoneNumber, it.profilePicture)
        }
    }

    fun addContact(contactDTO: ContactDTO, profilePicture: MultipartFile?): ContactDTO {
        var contactEntity = contactDTO.let {
            Contact(null, it.email, it.name, it.phoneNumber,null)
        }

        contactRepository.save(contactEntity)

        if (profilePicture != null) {
            val fileName : String = "" + contactEntity.id + "." + Files.getFileExtension(profilePicture.originalFilename.toString())

            fileService.uploadFile(fileName, profilePicture)

            contactEntity.profilePicture = fileName

            contactRepository.save(contactEntity)
        }
        return contactEntity.let {
            ContactDTO(it.id, it.email, it.name, it.phoneNumber, it.profilePicture)
        }
    }

    fun updateContactById(contactId: Int, contactDTO: ContactDTO, profilePicture: MultipartFile?, delete: Boolean?): ContactDTO {
        val existingContact = contactRepository.findById(contactId)

        return if(existingContact.isPresent) {
            existingContact.get().let {
                it.email = contactDTO.email
                it.name = contactDTO.name
                it.phoneNumber = contactDTO.phoneNumber

                contactRepository.save(it)
                if (profilePicture != null) {
                    val fileName: String =
                        "" + it.id + "." + Files.getFileExtension(profilePicture.originalFilename.toString())

                    fileService.uploadFile(fileName, profilePicture)

                    it.profilePicture = fileName
                } else {
                    if(delete == true) {
                        it.profilePicture?.let { it1 -> fileService.deleteFile(it1) }
                        it.profilePicture = null
                    }
                }
                contactRepository.save(it)
                ContactDTO(it.id, it.email, it.name, it.phoneNumber, it.profilePicture)
            }
        } else {
            throw ContactNotFoundException("No contact found for the contactId: $contactId")
        }
    }

    fun deleteContactById(contactId: Int) {
        val existingContact = contactRepository.findById(contactId)

        if(existingContact.isPresent) {
            existingContact.get()
                .let {
                    if(it.profilePicture != null) {
                        fileService.deleteFile(it.profilePicture!!)
                    }
                    contactRepository.deleteById(contactId)

                }

        } else {
            throw ContactNotFoundException("No contact found for the contactId: $contactId")
        }
    }




}
