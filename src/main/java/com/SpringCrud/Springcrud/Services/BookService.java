package com.SpringCrud.Springcrud.Services;

import com.SpringCrud.Springcrud.DTO.BookDTO;

import java.io.IOException;

public interface BookService {
    void saveBook(BookDTO bookDTO) throws IOException;
}
