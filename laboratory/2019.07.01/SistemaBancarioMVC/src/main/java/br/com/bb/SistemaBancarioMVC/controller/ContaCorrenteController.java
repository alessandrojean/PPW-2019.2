package br.com.bb.SistemaBancarioMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bb.SistemaBancarioMVC.model.dao.ContaCorrenteRepository;
import br.com.bb.SistemaBancarioMVC.model.entity.ContaCorrente;

@Controller
public class ContaCorrenteController {
  @Autowired
  private ContaCorrenteRepository ccRepository;

  @RequestMapping("/contas")
  public ModelAndView listAll() {
    ModelAndView mv = new ModelAndView("contas");
    mv.addObject("ccs", ccRepository.findAll());

    return mv;
  }

  @PostMapping("/save")
  public ModelAndView save(ContaCorrente contaCorrente) {
    ccRepository.save(contaCorrente);

    return new ModelAndView("redirect:/contas");
  }

}