package com.jk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookBean implements Serializable {

    private Integer id;

    private String name;

    private Integer price;

    private Integer typeId;

    private String createTime;

    private String author;

    private String typeName;
}
