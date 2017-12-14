package controlando.controller;

import controlando.model.criteria.PlanoCriteria;
import controlando.model.entity.Plano;
import controlando.model.entity.Restricao;
import controlando.model.entity.Usuario;
import controlando.model.service.PlanoService;
import controlando.model.service.RestricaoService;
import controlando.model.service.SquidService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlanoController {

    @RequestMapping(value = "/plano/novo", method = RequestMethod.GET)
    public ModelAndView novoPlano() throws Exception {
        ModelAndView mv = new ModelAndView("Plano/form");
        RestricaoService rs = new RestricaoService();
        HashMap<Long, Object> criteria = new HashMap<>();
        List<Restricao> restricaoList = rs.readByCriteria(criteria, 0L, 0L);
        mv.addObject("restricaoList", restricaoList);

        return mv;
    }

    @RequestMapping(value = "/plano/novo", method = RequestMethod.POST)
    public ModelAndView novoPlanoSet(String nomePlano, Long selectRestricao, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/planos");
        Plano p = new Plano();
        p.setNome(nomePlano);
        Restricao r = new Restricao();
        r.setId(selectRestricao);
        p.setRestricao(r);
        p.setAtivo(1);
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        Usuario usuarioAdm = (Usuario) session.getAttribute("usuarioLogado");
        p.setUsuario(usuarioAdm);
        p.setDataCriacao(dataDeHoje);
        PlanoService ps = new PlanoService();
        ps.create(p);
        createAcl(p);
        return mv;
    }

    @RequestMapping(value = "/planos", method = RequestMethod.GET)
    public ModelAndView planos() throws Exception {
        ModelAndView mv = new ModelAndView("Plano/planos");
        PlanoService ps = new PlanoService();
        HashMap<Long, Object> criteria = new HashMap<>();
        criteria.put(PlanoCriteria.ATIVO, 1);
        List<Plano> planoList = ps.readByCriteria(criteria, 0L, 0L);
        mv.addObject("planoList", planoList);
        return mv;
    }

    @RequestMapping(value = "/plano/{id}", method = RequestMethod.GET)
    public ModelAndView editarPlano(@PathVariable("id") Long id) throws Exception {
        ModelAndView mv = new ModelAndView("Plano/form");
        PlanoService ps = new PlanoService();
        Plano p = ps.readById(id);
        RestricaoService rs = new RestricaoService();
        HashMap<Long, Object> criteria = new HashMap<>();
        List<Restricao> restricaoList = rs.readByCriteria(criteria, 0L, 0L);
        mv.addObject("restricaoList", restricaoList);
        mv.addObject("plano", p);

        return mv;
    }

    @RequestMapping(value = "/plano/{id}", method = RequestMethod.POST)
    public ModelAndView editarPlanoSet(@PathVariable("id") Long id, String nomePlano, Integer tempoAcesso, Long selectRestricao, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/planos");
        Plano p = new Plano();
        p.setId(id);
        p.setNome(nomePlano);
        Restricao r = new Restricao();
        r.setId(selectRestricao);
        p.setRestricao(r);
        p.setAtivo(1);
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        Usuario usuarioAdm = (Usuario) session.getAttribute("usuarioLogado");
        p.setUsuario(usuarioAdm);
        p.setDataCriacao(dataDeHoje);
        PlanoService ps = new PlanoService();
        ps.update(p);
        return mv;
    }

    @RequestMapping(value = "/procurarPlano/{tecla}", method = RequestMethod.GET)
    public ModelAndView procurarPlanos(@PathVariable("tecla") String searchPlanoC) throws Exception {
        ModelAndView mv = new ModelAndView("Plano/tabela");
        PlanoService us = new PlanoService();
        List<Plano> planoList = new ArrayList<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(PlanoCriteria.ATIVO, 1);
        criteria.put(PlanoCriteria.NOME_ILIKE, searchPlanoC);
        planoList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("planoList", planoList);
        return mv;

    }

    @RequestMapping(value = "/procurarPlano", method = RequestMethod.GET)
    public ModelAndView procurarPlanosTodos(String searchPlanoC) throws Exception {
        ModelAndView mv = new ModelAndView("Plano/tabela");
        PlanoService us = new PlanoService();
        List<Plano> planoList = new ArrayList<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(PlanoCriteria.NOME_ILIKE, searchPlanoC);
        criteria.put(PlanoCriteria.ATIVO, 1);
        planoList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("planoList", planoList);
        return mv;

    }

    @RequestMapping(value = "/plano/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView deletarPlanoSet(@PathVariable("id") Long id, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/planos");
        Plano p = new Plano();
        PlanoService ps = new PlanoService();
        Long count = ps.countByUser(id);
        if (count == null || count == 0) {
            ps.delete(id);
        } else {
            mv.addObject("excluirNegado", "Negado");
        }

        return mv;
    }

    private void createAcl(Plano entity) throws Exception {
        PlanoService ps = new PlanoService();
        SquidService ss = new SquidService();
        RestricaoService rs = new RestricaoService();
        Restricao restricao = new Restricao();
        entity = ps.readById(entity.getId());
        restricao = rs.readById(entity.getRestricao().getId());
        entity.setRestricao(restricao);
        ps.createAclTxt(entity);
        ss.createACL(entity);

    }

}
