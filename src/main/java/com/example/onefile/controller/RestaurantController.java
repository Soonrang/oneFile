package com.example.onefile.controller;

import com.example.onefile.dto.PageRequestDTO;
import com.example.onefile.dto.PageResponseDTO;
import com.example.onefile.dto.RestaurantDTO;
import com.example.onefile.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/restaurant")
@Log4j2
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<RestaurantDTO> responseDTO = restaurantService.list(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET(Model model) {
        model.addAttribute("restaurantDTO", new RestaurantDTO());
    }

    @PostMapping("/register")
    public String registerPost(@Valid RestaurantDTO restaurantDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("menuImage1") MultipartFile menuImage1,
                               @RequestParam("menuImage2") MultipartFile menuImage2,
                               @RequestParam("menuImage3") MultipartFile menuImage3) {

        log.info("식당을 등록합니다.");

        if (bindingResult.hasErrors()) {
            log.info("에러가 있습니다.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/restaurant/register";
        }

        // 이미지 파일 저장 경로
        String savePath = "C:\\Temp"; // 슬래시로 경로 지정

        // 각 이미지 파일의 고유한 파일 이름 생성
        String menuImageFileName1 = UUID.randomUUID().toString() + getFileExtension(menuImage1.getOriginalFilename());
        String menuImageFileName2 = UUID.randomUUID().toString() + getFileExtension(menuImage2.getOriginalFilename());
        String menuImageFileName3 = UUID.randomUUID().toString() + getFileExtension(menuImage3.getOriginalFilename());

        // 이미지 파일 저장 경로에 파일 저장
        try {
            saveImage(menuImage1, savePath, menuImageFileName1);
            saveImage(menuImage2, savePath, menuImageFileName2);
            saveImage(menuImage3, savePath, menuImageFileName3);
        } catch (IOException e) {
            log.error("이미지 파일 저장 중 오류 발생: " + e.getMessage());
            // 예외 처리 로직 추가
        }

        RestaurantDTO uploadFile = RestaurantDTO.builder()
                .name(restaurantDTO.getName())
                .categories(restaurantDTO.getCategories())
                .callNumber(restaurantDTO.getCallNumber())
                .location(restaurantDTO.getLocation())
                .openingTime(restaurantDTO.getOpeningTime())
                .closingTime(restaurantDTO.getClosingTime())
                .description(restaurantDTO.getDescription())
                .menuName1(restaurantDTO.getMenuName1())
                .menuName2(restaurantDTO.getMenuName2())
                .menuName3(restaurantDTO.getMenuName3())
                .menuDesc1(restaurantDTO.getMenuDesc1())
                .menuDesc2(restaurantDTO.getMenuDesc2())
                .menuDesc3(restaurantDTO.getMenuDesc3())
                .menuUrl1(savePath + "\\" + menuImageFileName1) // 슬래시로 경로 지정
                .menuUrl2(savePath + "\\" + menuImageFileName2) // 슬래시로 경로 지정
                .menuUrl3(savePath + "\\" + menuImageFileName3) // 슬래시로 경로 지정
                .build();

        Long id = restaurantService.register(uploadFile);

        redirectAttributes.addFlashAttribute("result", id);

        return "redirect:/restaurant/list";
    }

    private void saveImage(MultipartFile file, String savePath, String fileName) throws IOException {
        Path path = Paths.get(savePath, fileName); // 슬래시로 경로 지정
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }
    }


//    @PostMapping("/register")
//    public String registerPost(@Valid RestaurantDTO restaurantDTO,
//                               BindingResult bindingResult,
//                               RedirectAttributes redirectAttributes,
//                               @RequestParam("menuImage1") MultipartFile menuImage1,
//                               @RequestParam("menuImage2") MultipartFile menuImage2,
//                               @RequestParam("menuImage3") MultipartFile menuImage3) {
//
//        log.info("식당을 등록합니다.");
//
//        if (bindingResult.hasErrors()) {
//            log.info("에러가 있습니다.");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/restaurant/register";
//        }
//
//        // 이미지 파일 저장 경로
//        String savePath = "C:\\Temp";
//
//        // 각 이미지 파일의 고유한 파일 이름 생성
//        String menuImageFileName1 = UUID.randomUUID().toString() + getFileExtension(menuImage1.getOriginalFilename());
//        String menuImageFileName2 = UUID.randomUUID().toString() + getFileExtension(menuImage2.getOriginalFilename());
//        String menuImageFileName3 = UUID.randomUUID().toString() + getFileExtension(menuImage3.getOriginalFilename());
//
//        // 이미지 파일 저장 경로에 파일 저장
//        try {
//            saveImage(menuImage1, savePath, menuImageFileName1);
//            saveImage(menuImage2, savePath, menuImageFileName2);
//            saveImage(menuImage3, savePath, menuImageFileName3);
//        } catch (IOException e) {
//            log.error("이미지 파일 저장 중 오류 발생: " + e.getMessage());
//            // 예외 처리 로직 추가
//        }
//
//        RestaurantDTO uploadFile = RestaurantDTO.builder()
//                .name(restaurantDTO.getName())
//                .categories(restaurantDTO.getCategories())
//                .callNumber(restaurantDTO.getCallNumber())
//                .location(restaurantDTO.getLocation())
//                .openingTime(restaurantDTO.getOpeningTime())
//                .closingTime(restaurantDTO.getClosingTime())
//                .description(restaurantDTO.getDescription())
//                .menuName1(restaurantDTO.getMenuName1())
//                .menuName2(restaurantDTO.getMenuName2())
//                .menuName3(restaurantDTO.getMenuName3())
//                .menuDesc1(restaurantDTO.getMenuDesc1())
//                .menuDesc2(restaurantDTO.getMenuDesc2())
//                .menuDesc3(restaurantDTO.getMenuDesc3())
//                .menuUrl1(savePath + menuImageFileName1)
//                .menuUrl2(savePath + menuImageFileName2)
//                .menuUrl3(savePath + menuImageFileName3)
//                .build();
//
//        Long id = restaurantService.register(uploadFile);
//
//        redirectAttributes.addFlashAttribute("result", id);
//
//        return "redirect:/restaurant/list";
//    }
//
//    private void saveImage(MultipartFile file, String savePath, String fileName) throws IOException {
//        Path path = Paths.get(savePath + fileName);
//        try (InputStream inputStream = file.getInputStream()) {
//            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
//        }
//    }


    @GetMapping("/read")
    public String read(@RequestParam Long id, Model model) {
        RestaurantDTO restaurantDTO = restaurantService.readOne(id);
        model.addAttribute("restaurantDTO", restaurantDTO);
        return "restaurant/read";
    }

    @GetMapping("/image/{id}/{menuNumber}")
    public ResponseEntity<byte[]> imageFile(@PathVariable Long id, @PathVariable int menuNumber) {
        RestaurantDTO restaurantDTO = restaurantService.readOne(id);

        String requestedMenuUrl = null;

        if (menuNumber == 1) {
            requestedMenuUrl = restaurantDTO.getMenuUrl1();
        } else if (menuNumber == 2) {
            requestedMenuUrl = restaurantDTO.getMenuUrl2();
        } else if (menuNumber == 3) {
            requestedMenuUrl = restaurantDTO.getMenuUrl3();
        }

        if (requestedMenuUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Path path = Paths.get(requestedMenuUrl);

            byte[] imageBytes = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }
}
