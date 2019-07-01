package br.com.bb.SistemaBancarioMVC.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bb.SistemaBancarioMVC.model.dao.ContaCorrenteRepository;
import br.com.bb.SistemaBancarioMVC.model.entity.ContaCorrente;

@Service
public class ContaCorrenteService {
  @Autowired
  private ContaCorrenteRepository ccDao;

  public List<ContaCorrente> listAll() {
    return ccDao.findAll();
  }
}
