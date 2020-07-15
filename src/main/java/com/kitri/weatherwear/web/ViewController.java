package com.kitri.weatherwear.web;

import com.kitri.weatherwear.user.User;
import com.kitri.weatherwear.user.UserDaoService;
import com.kitri.weatherwear.wear.WearDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final UserDaoService userDaoService;
    private final WearDaoService wearDaoService;

    //메인 페이지
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginOk(Model model) {
        return "redirect:/{id}";
    }

    //회원가입페이지
   @GetMapping("/signup")
    public String signUp(Model model) {
        return "signup";
   }

   //마이페이지
   @GetMapping("/users/{id}")
    public String myPage(Model model) {
        return "mypage";
   }

   //과거에 입은 옷 (지난 평가보기)
   @GetMapping("/users/closet")
    public String myPageCloset(Model model) {
        return "closet";
   }

   //오늘의 추천
   @GetMapping("/users/today-wear")
    public String todayWear(Model model) {
        return "today-wear";
   }

   //룩저장하기
   @GetMapping("users/save-wear")
    public String saveWear(Model model) {
        return "save-wear";
   }

   //평가하지 않은 룩들 평가하기 페이지
   @GetMapping("users/eval-wear")
    public String evalWear(Model model) {
        return "eval-wear";
   }
}
