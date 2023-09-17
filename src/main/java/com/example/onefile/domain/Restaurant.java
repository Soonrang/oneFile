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
}
