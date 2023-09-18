package com.example.onefile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable = false)
    private String name;

    @Column(length=500, nullable = false)
    private String categories;

    @Column(nullable = false)
    private String openingTime;

    @Column(nullable = false)
    private String closingTime;

    @Column(length=500, nullable = false)
    private String location;

    @Column(length=500, nullable = false)
    private String callNumber;

    @Column(length = 2000)
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

    @CreatedDate
    private LocalDateTime createAt;

    public void change(String name, String categories, String openingTime, String closingTime,
                       String callNumber, String location, String description,
                       String menuName1, String menuName2, String menuName3,
                       String menuDesc1, String menuDesc2, String menuDesc3,
                       String menuUrl1, String menuUrl2, String menuUrl3) {
        this.name = name;
        this.categories = categories;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.callNumber = callNumber;
        this.location = location;
        this.description = description;
        this.menuName1 = menuName1;
        this.menuName2 = menuName2;
        this.menuName3 = menuName3;
        this.menuDesc1 = menuDesc1;
        this.menuDesc2 = menuDesc2;
        this.menuDesc3 = menuDesc3;
        this.menuUrl1 = menuUrl1;
        this.menuUrl2 = menuUrl2;
        this.menuUrl3 = menuUrl3;
    }


}
