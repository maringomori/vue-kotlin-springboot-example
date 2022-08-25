package com.uxstudio.contact.entity

import javax.persistence.*

@Entity
@Table(name = "Contacts")
data class Contact (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,

    var email: String,

    var name: String,

    var phoneNumber: String,

    var profilePicture: String?
)