package com.example.manager.controller;

import com.example.manager.dto.UserDto;
import com.example.manager.entity.User;
import com.example.manager.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserRepository userRepository;

    @GetMapping("main")
    public String index(Model model){
        List<User> userList = userRepository.findAllByIsActive("0");
        model.addAttribute("userList", userList);
        return "main";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable String id, Model model){
       User user = userRepository.findById(id).orElse(null);

        model.addAttribute("user", user);
        return "detail";
    }

    @GetMapping("modify/{id}")
    public String modify(@PathVariable String id, Model model){
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "modify";
    }

    @PostMapping("modify")
    public String modifyProc(UserDto userDto){

        User user = User.builder()
                .id(userDto.getId())
                .password(userDto.getPassword1())
                .name(userDto.getName())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .birth(userDto.getBirth())
                .createDay(userDto.getCreateDay())
                .startDay(userDto.getStartDay())
                .isActive(userDto.getIsActive())
                .build();
        userRepository.save(user);
        return "redirect:/detail/" + userDto.getId();
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setIsActive("1");
            userRepository.save(user);
        }
        return "redirect:/main";
    }
}
