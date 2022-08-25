package com.uxstudio.contact.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ContactDTO (
    val id: Int?,
    @get:NotBlank(message = "contactDTO.email must not be blank")
    val email: String,
    @get:NotBlank(message = "contactDTO.name must not be blank")
    val name: String,
    @get:NotBlank(message = "contactDTO.phoneNumber must not be blank")
    val phoneNumber: String,
    val profilePicture: String?,
)

