package com.library.dto.mapper;

import com.library.domain.Book;
import com.library.dto.BookDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface BookMapper {

    public BookDTO bookToBookDTO(Book book);

    public Book bookDTOToBook(BookDTO bookDto);


}
