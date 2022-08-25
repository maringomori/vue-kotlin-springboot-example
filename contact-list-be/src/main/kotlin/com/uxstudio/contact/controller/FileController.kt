package com.uxstudio.contact.controller

import com.uxstudio.contact.service.FileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uploads")
class FileController(val fileService : FileService) {

    @GetMapping("/{fileName}")
    fun getImage(@PathVariable fileName: String) : ResponseEntity<ByteArray>? {
      return fileService.getFile(fileName)
    }
}