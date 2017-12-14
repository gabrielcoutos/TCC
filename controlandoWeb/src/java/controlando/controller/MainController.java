/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.controller;

import controlando.model.entity.Usuario;
import controlando.model.service.PlanoService;
import controlando.model.service.RestricaoService;
import controlando.model.service.SquidService;
import controlando.model.service.UsuarioService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gabri
 */
@Controller
public class MainController {
    

    @RequestMapping(value = "/entrar", method = RequestMethod.POST)
    public ModelAndView login(String nomeUsuarioL, String senhaUsuario1, HttpSession session,HttpServletRequest request) throws Exception {
        ModelAndView mv = mv = new ModelAndView("redirect:/");
        UsuarioService us = new UsuarioService();
        String senhaEncode = Usuario.encodeSenha(senhaUsuario1);
        Usuario usuario = us.login(nomeUsuarioL, senhaEncode);
        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            if (usuario.getTipo() == 0) {
                if (usuario.getStatus() == 1) {
                    mv = new ModelAndView("redirect:/autenticado");
                    usuario = us.readById(usuario.getId());                   
                    String enderecoIP = request.getRemoteAddr();
                    usuario.setIp(enderecoIP);
                    mv.addObject("ip", enderecoIP);
                    SquidService ss = new SquidService();
                    ss.liberaIP(enderecoIP);
                    ss.createACLIPUsuario(usuario);
                    ss.reloadSquid();
                }

            } else if (usuario.getDelete() == 0) {
                mv = new ModelAndView("redirect:/adminLogin");
            } else {
                
            }

        }else{
            mv = new ModelAndView("index");
            mv.addObject("erro", "Email ou senha invalidos");
        }

        return mv;
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastroUsuario(String nomeUsuario, String emailUsuario, String cpfUsuario, String dataUsuario, String senhaUsuario, String senhaUsuario2) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/");
        UsuarioService us = new UsuarioService();
        Usuario usuario = new Usuario();
        usuario.setNome(nomeUsuario);
        usuario.setEmail(emailUsuario);
        usuario.setCpf(cpfUsuario);
        usuario.setData_nascimento(dataUsuario);
        usuario.setSenhaAsPlanText(senhaUsuario);        
        usuario.setTipo(0);
        usuario.setStatus(0);
        us.create(usuario);       
        return mv;
    }

    @RequestMapping(value = "/sair", method = RequestMethod.GET)
    public ModelAndView sair(HttpSession session, HttpServletRequest request) throws Exception {
        ModelAndView mv = null;

        String enderecoIP = request.getRemoteAddr();
        SquidService ss = new SquidService();       
        ss.bloqueiaIP(enderecoIP);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        UsuarioService us = new UsuarioService();
        usuario = us.readById(usuario.getId());
        session.removeAttribute("usuarioLogado");
        usuario.setIp(enderecoIP);
        ss.removerACLIPUsuario(usuario);
        ss.reloadSquid();
        mv = new ModelAndView("redirect:/");

        return mv;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView redirecionar(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario!= null){
            if(usuario.getTipo()==1){
                mv = new ModelAndView("redirect:/adminLogin");
            }else{
                mv = new ModelAndView("redirect:/autenticado");
            }
        }
        return mv;
    }
    @RequestMapping(value = "/ajuda", method = RequestMethod.GET)
    public ModelAndView recuperarSenha() throws Exception {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("help", "Em caso de esquecer sua senha ou problemas para entrar entre em contato com o administrador da rede =D");

        return mv;
    }
    
    @RequestMapping (value = "/autenticado",method = RequestMethod.GET)
    public ModelAndView boasVindas(){
        ModelAndView mv = new ModelAndView("Usuario/bemvindo");
        return mv;
    }
}
