package com.gestmemapi.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.model.DBFile;
import com.gestmemapi.model.Person;
import com.gestmemapi.model.ResponseMessage;
import com.gestmemapi.model.Role;
import com.gestmemapi.service.DBFileService;
import com.gestmemapi.service.RoleService;
import com.gestmemapi.service.StudentService;

import antlr.collections.List;

//@CrossOrigin(origins = "https://gestmem-api.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/*")
public class DBFileController {

	@Autowired
    private DBFileService dbFileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            dbFileService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
            
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<Iterable<DBFile>> getListFiles() {
        Iterable<DBFile> files = dbFileService.getAllFiles();

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestParam int id) {
        DBFile dbFile = dbFileService.getFile(Long.valueOf(id)).get();
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
            .body(dbFile.getData());
    }
	
}