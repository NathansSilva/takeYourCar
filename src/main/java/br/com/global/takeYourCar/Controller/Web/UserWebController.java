package br.com.global.takeYourCar.Controller.Web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.global.takeYourCar.Model.User;
import br.com.global.takeYourCar.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserWebController {

  @Autowired
  UserService service;

  @GetMapping
  public ModelAndView disponivel() {
    ModelAndView mav = new ModelAndView("/user/index");

    List<User> list = service.listAll();
    mav.addObject("user", list);
    return mav;
  }

  @GetMapping("/newUser")
  public ModelAndView createForm() {
    return new ModelAndView("/user/form").addObject("user", new User());
  }

  @PostMapping
  public String create(@Valid User user, BindingResult binding) {
    if (binding.hasErrors())
      return "/user/form";
    service.save(user);
    return "redirect:/user";
  }

  @GetMapping("delete/{id}")
  public String delete(@PathVariable Long id) {
    service.remove(id);

    return "redirect:/user";
  }

  @GetMapping("update/{id}")
  public ModelAndView update(@PathVariable Long id) {
    Optional<User> optional = service.get2(id);
    if (optional.isPresent()) {
      return new ModelAndView("user/form")
          .addObject("user", optional.get());
    }
    return new ModelAndView("/user/index");
  }

}
