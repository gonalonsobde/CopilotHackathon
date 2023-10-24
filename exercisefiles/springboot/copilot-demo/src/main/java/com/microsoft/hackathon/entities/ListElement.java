package com.microsoft.hackathon.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListElement {
    private String color;
    private String category;
    private String type;
    private Code code;
}