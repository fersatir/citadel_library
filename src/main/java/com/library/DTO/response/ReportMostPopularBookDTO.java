package com.library.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportMostPopularBookDTO {

    private Long id;

    private String name;

    private String isbn;

    private Integer pageCount;

    private File image;

    private Integer amount;

}
