package br.com.richard.converter.mocks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.richard.data.model.Book;
import br.com.richard.data.vo.v1.BookVO;

public class MockBook {
    public Book mockEntity(){
        return mockEntity(0);
    }

    public BookVO mockVO(){
        return mockVO(0);
    }

    public List<Book> mockEntityList(){
        List<Book> books = new ArrayList<Book>();
        for (int i=0; i<14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    private Book mockEntity(Integer number) {
        Book book = new Book();
        Date today = new Date();

        book.setAuthor("Richard Brehmer" + number);
        book.setId(number.longValue());
        book.setLaunchDate(today);
        book.setPrice(299.00 + number);
        book.setTitle("How to move to Miami" + number);

        return book;

    }

    private BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        // Date today = new Date(2018, 11, 31);
        Calendar day = Calendar.getInstance();
        day.set(2018, 11, 31);

    	book.setAuthor("Richard Brehmer" + number);
        book.setKey(number.longValue());
        book.setLaunchDate(day);
        book.setPrice(299.00 + number);
        book.setTitle("Europe" + number);

        return book;
    }
    
}
