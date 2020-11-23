package br.com.richard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.richard.converter.DozerConverter;
import br.com.richard.data.model.Book;
import br.com.richard.data.vo.v1.BookVO;
import br.com.richard.repository.BookRepository;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    public BookVO create(BookVO book){
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
    }

    public BookVO findById(Long id) {
        var entity = repository.findById(id)
            .orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID."));
        return DozerConverter.parseObject(entity, BookVO.class);    
    }

    public BookVO update(BookVO book) {
        Book entity = repository.findById(book.getKey())
            .orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID."));
        
        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());

        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public void delete(Long id){
        Book entity = repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
    
}
