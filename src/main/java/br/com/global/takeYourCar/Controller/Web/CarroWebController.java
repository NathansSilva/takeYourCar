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

import br.com.global.takeYourCar.Model.Carro;
import br.com.global.takeYourCar.Service.CarroService;

@Controller
@RequestMapping("/carro")
public class CarroWebController {

  @Autowired
  CarroService service;

  @GetMapping
  public ModelAndView disponivel() {
    ModelAndView mav = new ModelAndView("/carros/index");
    // mav.setViewName("/carro/disponivel");

    List<Carro> list = service.listAll();
    mav.addObject("carros", list);
    return mav;
  }

  @GetMapping("/newCarro")
  public ModelAndView createForm() {
    return new ModelAndView("/carros/form").addObject("carro", new Carro());
  }

  @PostMapping
  public String create(@Valid Carro carro, BindingResult binding) {
    if (binding.hasErrors())
      return "/carro/form";
    service.save(carro);
    return "redirect:/carro";
  }

  @GetMapping("delete/{id}")
  public String delete(@PathVariable Long id) {
    service.remove(id);

    return "redirect:/carro";
  }

  @GetMapping("update/{id}")
  public ModelAndView update(@PathVariable Long id) {
    Optional<Carro> optional = service.get(id);
    if (optional.isPresent()) {
      return new ModelAndView("carros/form")
          .addObject("carro", optional.get());
    }
    return new ModelAndView("/carro/index");
  }

}
