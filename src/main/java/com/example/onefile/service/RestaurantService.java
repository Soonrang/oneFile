package com.example.onefile.service;

import com.example.onefile.domain.Restaurant;
import com.example.onefile.dto.PageRequestDTO;
import com.example.onefile.dto.PageResponseDTO;
import com.example.onefile.dto.RestaurantDTO;

public interface RestaurantService {

    Long register(RestaurantDTO restaurantDTO);

    RestaurantDTO readOne(Long id);

    void modify(RestaurantDTO restaurantDTO);

    void remove(Long rno);

    PageResponseDTO<RestaurantDTO> list(PageRequestDTO pageRequestDTO);

//    default Restaurant dtoToEntity(RestaurantDTO restaurantDTO) {
//
//        Restaurant restaurant = Restaurant.builder()
//                .id(restaurantDTO.getId())
//                .categories(restaurantDTO.getCategories())
//                .callNumber(restaurantDTO.getCallNumber())
//                .location(restaurantDTO.getLocation())
//                .openingTime(restaurantDTO.getOpeningTime())
//                .closingTime(restaurantDTO.getClosingTime())
//                .menuName1(restaurantDTO.getMenuName1())
//                .menuName2(restaurantDTO.getMenuName2())
//                .menuName3(restaurantDTO.getMenuName3())
//                .menuDesc1(restaurantDTO.getMenuDesc1())
//                .menuDesc2(restaurantDTO.getMenuDesc2())
//                .menuDesc3(restaurantDTO.getMenuDesc3())
//                .menuUrl1(restaurantDTO.getMenuUrl1())
//                .menuUrl2(restaurantDTO.getMenuUrl2())
//                .menuUrl3(restaurantDTO.getMenuUrl3())
//                .build();
//
//        return restaurantDTO;
//    }
//}
}
