/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.controller;

import controlando.model.entity.Organizacao;
import controlando.model.service.OrganizacaoService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author root
 */
@Controller
public class OrganizacaoController {
    
    @RequestMapping(value="/organizacao",method = RequestMethod.GET)
     public ModelAndView getOrganizacao() throws Exception{
         ModelAndView mv = new ModelAndView("organizacao/organizacao");
         OrganizacaoService os = new OrganizacaoService();
         List<Organizacao> orgList = os.readByCriteria(null, 0L, 0L);
         mv.addObject("organizacaoList", orgList);
         return mv;
     }
     
     @RequestMapping(value="/organizacao/novo",method = RequestMethod.GET)
     public ModelAndView getOrganizacaoNovo() throws Exception{
         ModelAndView mv = new ModelAndView("organizacao/form");        
         return mv;
     }
     @RequestMapping(value="/organizacao/novo",method = RequestMethod.POST)
     public ModelAndView postOrganizacaoNovo(String nomeFantasia,String razaoSocial,String email,String endereco,String bairro,String numero,String cnpj,String telefone) throws Exception{
         ModelAndView mv = new ModelAndView("redirect:/organizacao");   
         Organizacao org = new Organizacao();
         OrganizacaoService os = new OrganizacaoService();
         org.setBairro(bairro);
         org.setCnpj(cnpj);
         org.setEmail(email);
         org.setNome_fantasia(nomeFantasia);
         org.setNumero(numero);
         org.setRazaoSocial(razaoSocial);
         org.setRua(endereco);
         org.setTelefone(telefone);
         os.create(org);
         return mv;
     }
     
      @RequestMapping(value="/organizacao/{id}",method = RequestMethod.GET)
     public ModelAndView getOrganizacaoEditar(@PathVariable("id")Long id) throws Exception{
         ModelAndView mv = new ModelAndView("organizacao/form");
         OrganizacaoService os = new OrganizacaoService();
         Organizacao org = os.readById(id);
         mv.addObject("organizacao", org);
         return mv;
     }
     
      @RequestMapping(value="/organizacao/{id}",method = RequestMethod.POST)
     public ModelAndView postOrganizacaoEditar(@PathVariable("id")Long id,String nomeFantasia,String razaoSocial,String email,String endereco,String bairro,String numero,String cnpj,String telefone) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/organizacao");   
         Organizacao org = new Organizacao();
         OrganizacaoService os = new OrganizacaoService();
         org.setId(id);
         org.setBairro(bairro);
         org.setCnpj(cnpj);
         org.setEmail(email);
         org.setNome_fantasia(nomeFantasia);
         org.setNumero(numero);
         org.setRazaoSocial(razaoSocial);
         org.setRua(endereco);
         org.setTelefone(telefone);
         os.update(org);
         return mv;
     }
     
      @RequestMapping(value="/organizacao/{id}/deletar",method = RequestMethod.GET)
     public ModelAndView getOrganizacaoDeletar(@PathVariable("id")Long id) throws Exception{
         ModelAndView mv = new ModelAndView("redirect:/organizacao");
         OrganizacaoService os = new OrganizacaoService();
         os.delete(id);
         return mv;
     }
}
