package controlando.controller;

import controlando.model.criteria.PlanoCriteria;
import controlando.model.criteria.UsuarioCriteria;
import controlando.model.entity.Plano;
import controlando.model.entity.Usuario;
import controlando.model.service.PlanoService;
import controlando.model.service.SquidService;
import controlando.model.service.UsuarioService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdministradorController {

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public ModelAndView listaUsuarios(String searchNome, String searchNomeC) throws Exception {
        ModelAndView mv = new ModelAndView("administrador/loginAdm");
        UsuarioService us = new UsuarioService();
        List<Usuario> usuarioListNCadastrado = new ArrayList<>();
        List<Usuario> usuarioList = new ArrayList<>();
        Map<Long, Object> criteriaNcadastrado = new HashMap<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteriaNcadastrado.put(UsuarioCriteria.NOMENCADASTRADO_ILIKE, searchNome);
        criteria.put(UsuarioCriteria.NOME_ILIKE, searchNomeC);
        usuarioList = us.readByCriteria(criteria, 0L, 0L);
        usuarioListNCadastrado = us.usuarioNaoCadastrado(criteriaNcadastrado);
        mv.addObject("usuarioList", usuarioList);
        mv.addObject("usuarioListNCadastrado", usuarioListNCadastrado);

        return mv;

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("Usuario/form");
        Usuario usuario = new Usuario();
        UsuarioService us = new UsuarioService();
        PlanoService ps = new PlanoService();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(PlanoCriteria.ATIVO, 1);
        List<Plano> planoList = ps.readByCriteria(criteria, 0L, 0L);
        usuario = us.readById(id);
        mv.addObject("planoList", planoList);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public ModelAndView updateUser(String nomeUsuario, String emailUsuario, String cpfUsuario, String dataUsuario, @PathVariable Long id, Integer tipoUsuario, Long planoUsuario, HttpSession session,String senhaUsuario) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        UsuarioService us = new UsuarioService();
        Usuario usuario = new Usuario();
        usuario = us.readById(id);
        usuario.setCpf(cpfUsuario);
        usuario.setData_nascimento(dataUsuario);
        usuario.setTipo(tipoUsuario);
        usuario.setEmail(emailUsuario);
        usuario.setId(id);
        usuario.setNome(nomeUsuario);
        usuario.setStatus(1);
        Usuario usuarioAdm = (Usuario) session.getAttribute("usuarioLogado");
        usuario.setUser(usuarioAdm);
        Plano plano = new Plano();
        plano.setId(planoUsuario);
        plano.setAtivo(1);
        usuario.setPlano(plano);
        if(!senhaUsuario.equals("PEGADINHA")){
           usuario.setSenhaAsPlanText(senhaUsuario);
        }
        
        us.update(usuario);
        return mv;
    }

    @RequestMapping(value = "/user/{id}/reprovar", method = RequestMethod.GET)
    public ModelAndView updateRemoveUser(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        Usuario usuario = new Usuario();
        usuario.setId(id);
        UsuarioService us = new UsuarioService();
        us.delete(id);
        return mv;

    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public ModelAndView usuarios() throws Exception {
        ModelAndView mv = new ModelAndView("Usuario/usuarios");
        UsuarioService us = new UsuarioService();
        List<Usuario> usuarioList = new ArrayList<>();
        Map<Long, Object> criteriaNcadastrado = new HashMap<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.USUARIOS_ATIVOS, "ativos");
        usuarioList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("usuarioList", usuarioList);

        return mv;

    }

    @RequestMapping(value = "/procurarUsuario/{tecla}", method = RequestMethod.GET)
    public ModelAndView procurarUsuarios(@PathVariable("tecla") String searchNomeC) throws Exception {
        ModelAndView mv = new ModelAndView("Usuario/tabela");
        UsuarioService us = new UsuarioService();
        List<Usuario> usuarioList = new ArrayList<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_ILIKE, searchNomeC);
        criteria.put(UsuarioCriteria.USUARIOS_ATIVOS, "ativos");
        usuarioList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("usuarioList", usuarioList);
        return mv;

    }

    @RequestMapping(value = "/procurarUsuario", method = RequestMethod.GET)
    public ModelAndView procurarUsuariosTodos(String searchNomeC) throws Exception {
        ModelAndView mv = new ModelAndView("Usuario/tabela");
        UsuarioService us = new UsuarioService();
        List<Usuario> usuarioList = new ArrayList<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_ILIKE, searchNomeC);
        criteria.put(UsuarioCriteria.USUARIOS_ATIVOS, "ativos");
        usuarioList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("usuarioList", usuarioList);
        return mv;

    }

    @RequestMapping(value = "/usuario/novo", method = RequestMethod.GET)
    public ModelAndView getUsuarioNovo() throws Exception {
        ModelAndView mv = new ModelAndView("Usuario/form");
        PlanoService ps = new PlanoService();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(PlanoCriteria.ATIVO, 1);
        List<Plano> planoList = ps.readByCriteria(criteria, 0L, 0L);
        mv.addObject("planoList", planoList);
        return mv;
    }

    @RequestMapping(value = "/usuario/novo", method = RequestMethod.POST)
    public ModelAndView postUsuarioNovo(String nomeUsuario, String emailUsuario, String cpfUsuario, String dataUsuario, Integer tipoUsuario, Long planoUsuario, HttpSession session, String senhaUsuario) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        Usuario usuario = new Usuario();
        usuario.setCpf(cpfUsuario);
        usuario.setData_nascimento(dataUsuario);
        usuario.setTipo(tipoUsuario);
        usuario.setEmail(emailUsuario);
        usuario.setNome(nomeUsuario);
        usuario.setSenhaAsPlanText(senhaUsuario);
        usuario.setStatus(1);
        Usuario usuarioAdm = (Usuario) session.getAttribute("usuarioLogado");
        usuario.setUser(usuarioAdm);
        Plano plano = new Plano();
        plano.setId(planoUsuario);
        plano.setAtivo(1);
        usuario.setPlano(plano);
        UsuarioService us = new UsuarioService();

        us.create(usuario);
        return mv;
    }

    @RequestMapping(value = "/gerarIp", method = RequestMethod.GET)
    public void gerarIps(String ip) throws IOException {
        SquidService ss = new SquidService();
        String ipSemEspaço = ip.replaceAll(" ", "");
        ss.gerarIp(ipSemEspaço);

    }

    @RequestMapping(value = "/iniciarSquid", method = RequestMethod.GET)
    public void iniciarSquid() throws IOException {
        SquidService ss = new SquidService();
        ss.startSquid();
    }

    @RequestMapping(value = "/recarregarSquid", method = RequestMethod.GET)
    public void recarregarSquid() throws IOException {
        SquidService ss = new SquidService();
        ss.reloadSquid();
    }
    
     @RequestMapping(value = "/reiniciarSquid", method = RequestMethod.GET)
    public void reiniciarSquid() throws IOException {
        SquidService ss = new SquidService();
        ss.restartSquid();
    }
    
    @RequestMapping(value = "/pararSquid", method = RequestMethod.GET)
    public void pararSquid() throws IOException {
        SquidService ss = new SquidService();
        ss.stopSquid();
    }

}
