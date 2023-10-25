package com.SpringCrud.Springcrud.Services;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.Title;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.List;

public interface BookService {
    void saveBook(BookDTO bookDTO) throws IOException;

    void addTitle(String title, MultipartFile image);

    List<Title> getTitles(Pageable pageable);

    List<Book> getbooksbytitle(String title,Pageable pageable);

    public void subtractStock(Long bookId, int quantityToSubtract) throws Exception;

    List<Book> getBooksByIds(List<Long> bookIds);
}
