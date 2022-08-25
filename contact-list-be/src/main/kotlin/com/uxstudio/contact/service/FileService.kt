package com.uxstudio.contact.service

import com.uxstudio.contact.exception.FileNotFoundException
import org.aspectj.util.FileUtil
import org.springframework.core.io.FileSystemResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileService {
    val uploadLocation = FileSystemResource("").file.absolutePath + "/uploads/"

    fun getFile(fileName : String): ResponseEntity<ByteArray>? {
        var fileBytes : ByteArray = byteArrayOf()
        try {
            val uploadLocation : String = FileSystemResource("").file.absolutePath + "/uploads/"
            val file : File = File(uploadLocation + fileName)
            val contentType : String = Files.probeContentType(file.toPath())
            fileBytes = FileUtil.readAsByteArray(file)
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(fileBytes)

        } catch (ex : IOException) {
            throw FileNotFoundException("File not found: $fileName")
        }
    }

    fun uploadFile(fileName : String, multipartFile: MultipartFile) {
        val uploadPath : Path = Paths.get(uploadLocation)

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            val inputStream : InputStream = multipartFile.inputStream
            val filePath : Path = uploadPath.resolve(fileName)

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)
        } catch (ex: IOException) {
            throw IOException("Could not save file: $fileName, $ex")

        }
    }

    fun deleteFile(fileName: String) {
        val uploadPath : Path = Paths.get(uploadLocation)

        try {
            val filePath : Path = uploadPath.resolve(fileName)
            Files.delete(filePath)
        } catch (ex: IOException) {
            throw IOException("Could not delete file: $fileName, $ex")
        }

    }
}
