package com.jk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookTypeBean implements Serializable {

    private Integer id;

    private String name;
}
