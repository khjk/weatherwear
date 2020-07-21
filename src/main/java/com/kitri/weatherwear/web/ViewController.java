package com.kitri.weatherwear.web;

import com.kitri.weatherwear.user.UserDaoService;
import com.kitri.weatherwear.wear.WearDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final UserDaoService userDaoService;
    private final WearDaoService wearDaoService;

    //메인 페이지
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        System.out.println(">>>>>>>>세션 아이디 : " + user_id);
        model.addAttribute("user_id",user_id);
        return "index";
    }

    //로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model) {
        return "user-login";
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
    public String evalWear(Model model, HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        System.out.println(">>>>>eval-wear세션아이디: "+ user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("wears", wearDaoService.findNotEvaluated(user_id));
        return "eval-wear";
   }
}
