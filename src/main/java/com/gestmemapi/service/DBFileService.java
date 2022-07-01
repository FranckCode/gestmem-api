package com.gestmemapi.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gestmemapi.dao.DBFileRepository;
import com.gestmemapi.dao.RoleRepository;
import com.gestmemapi.exception.BusinessResourceException;
import com.gestmemapi.model.DBFile;
import com.gestmemapi.model.Role;

@Service
public class DBFileService implements IDBFileService {
	
    @Autowired
	private DBFileRepository dbFileRepository;

    @Override
    public Iterable<DBFile> getAllFiles() {
        
        return dbFileRepository.findAll();

    }

    @Override
    public Optional<DBFile> getFile(Long id) {
        
        return dbFileRepository.findById(id);

    }

    @Override
    public DBFile store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DBFile dbFile = new DBFile(fileName, file.getBytes(), file.getContentType());
        return dbFileRepository.save(dbFile);

    }
	
	
		
		
}