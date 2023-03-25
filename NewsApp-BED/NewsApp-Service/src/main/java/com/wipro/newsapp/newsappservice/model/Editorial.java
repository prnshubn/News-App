package com.wipro.newsapp.newsappservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {

    private int editorialId;
    private String title;
    private String body;

    private boolean news;

}
