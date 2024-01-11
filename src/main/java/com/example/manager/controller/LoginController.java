package com.example.manager.controller;

import com.example.manager.dto.UserDto;
import com.example.manager.entity.User;
import com.example.manager.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    @GetMapping("/login")
    private String login() {
        return "index";
    }

    @PostMapping("/login")
    private String login(String id, String password, Model model, HttpServletRequest request) {
        User user = userRepository.findByIdAndPassword(id, password);

        if (user != null) {
            HttpSession session = request.getSession();//세션 사용 방법 (1
            session.setAttribute("sessionUserName", user.getName()); // (2 통째로 넣어두 됨 ("sessionUserName",seresult)
            return "redirect:/main"; //flekdlfprxm tkdyd wnddy
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요.");
            return "index"; //컨트롤러를 재 구축하는 것->"redirect:/main" **
        }
    }

    @GetMapping("/signup")
    private String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    private String signup(UserDto userDto, String email, Model model) {
        if (!userDto.getPassword1().equals(userDto.getPassword2())) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "signup";
        } else {
            if(userDto.getId().trim().isEmpty()||userDto.getPassword1().trim().isEmpty()||userDto.getPassword2().trim().isEmpty()
            ||userDto.getEmail().trim().isEmpty()||userDto.getAddress().trim().isEmpty()){
                model.addAttribute("msg", "입력되지 않은 정보가 있습니다.");
                return "signup";
            } else {
                User check = userRepository.findByEmail(email);
                if (check == null) {
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
                            .build();
                    userRepository.save(user);
                    model.addAttribute("msg", "회원가입이 완료 되었습니다.");
                    return "index";
                } else {
                    model.addAttribute("msg", "이메일이 중복검사를 해주세요");
                    return "signup";
                }
            }
        }
    }
    @GetMapping("emailConfirm")
    private String moveEmailConfirm() {
        return "emailConfirm";
    }

    @PostMapping("emailConfirm")
    private String EmailCheck(String email, Model model){
        User user = userRepository.findByEmail(email);
        if(email.trim().isEmpty()){
            model.addAttribute("msg", "이메일을 입력해주세요");
            return null;
        } else {
            if (user == null) {
                model.addAttribute("msg", "이메일 사용이 가능합니다.");
                return "emailConfirm";
            } else {
                model.addAttribute("msg", "이메일이 중복입니다.");
                return null;
            }
        }

    }

}
