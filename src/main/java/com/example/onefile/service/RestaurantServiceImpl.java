package com.example.onefile.service;

import com.example.onefile.domain.Restaurant;
import com.example.onefile.dto.PageRequestDTO;
import com.example.onefile.dto.PageResponseDTO;
import com.example.onefile.dto.RestaurantDTO;
import com.example.onefile.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Long register(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Long id = restaurantRepository.save(restaurant).getId();
        return id;
    }

    @Override
    public RestaurantDTO readOne(Long id) {
        Optional<Restaurant> result = restaurantRepository.findById(id);

        Restaurant restaurant = result.orElseThrow();

        RestaurantDTO restaurantDTO =modelMapper.map(restaurant, RestaurantDTO.class);

        return restaurantDTO;
    }

    @Override
    public void modify(RestaurantDTO restaurantDTO) {
        Optional<Restaurant> result = restaurantRepository.findById(restaurantDTO.getId());
        Restaurant restaurant = result.orElseThrow();

        restaurant.change(
                restaurantDTO.getName(),
                restaurantDTO.getCategories(),
                restaurantDTO.getOpeningTime(),
                restaurantDTO.getClosingTime(),
                restaurantDTO.getCallNumber(),
                restaurantDTO.getLocation(),
                restaurantDTO.getDescription(),
                restaurantDTO.getMenuName1(),
                restaurantDTO.getMenuName2(),
                restaurantDTO.getMenuName3(),
                restaurantDTO.getMenuDesc1(),
                restaurantDTO.getMenuDesc2(),
                restaurantDTO.getMenuDesc3(),
                restaurantDTO.getMenuUrl1(),
                restaurantDTO.getMenuUrl2(),
                restaurantDTO.getMenuUrl3()
        );
        restaurantRepository.save(restaurant);
    };

    @Override
    public void remove(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<RestaurantDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("id");

        Page<Restaurant> result = restaurantRepository.findAll(pageable);

        List<RestaurantDTO> dtoList = result .getContent().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<RestaurantDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

    }
}
