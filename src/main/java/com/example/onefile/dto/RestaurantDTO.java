package com.example.onefile.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String categories;

    @NotNull
    private String openingTime;

    @NotNull
    private String closingTime;

    @NotEmpty
    private String location;

    @NotEmpty
    private String callNumber;

    @NotEmpty
    private String description;

    private String menuName1;
    private String menuName2;
    private String menuName3;

    private String menuDesc1;
    private String menuDesc2;
    private String menuDesc3;

    private String menuUrl1;
    private String menuUrl2;
    private String menuUrl3;


}
