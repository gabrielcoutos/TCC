package controlando.controller;

import controlando.model.criteria.RestricaoCriteria;
import controlando.model.criteria.SiteCriteria;
import controlando.model.entity.Restricao;
import controlando.model.entity.Site;
import controlando.model.service.RestricaoService;
import controlando.model.service.SiteService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestricaoController {

    @RequestMapping(value = "/restricao/novo", method = RequestMethod.GET)
    public ModelAndView getRestricaoCreate() throws Exception {
        ModelAndView mv = new ModelAndView("restricao/form");
        SiteService ss = new SiteService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Site> siteList = ss.readByCriteria(criteria, 0L, 0L);
        mv.addObject("siteList", siteList);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/restricao/novo", method = RequestMethod.POST)
    public void postRestricaoCreate(@RequestBody List<Site> siteList) throws Exception {
        RestricaoService ss = new RestricaoService();
        Restricao r = new Restricao();
        r.setNome(siteList.get(0).getRestricao().getNome());
        r.setTempoAcesso(siteList.get(0).getRestricao().getTempoAcesso());
        r.setSiteList(siteList);
        ss.create(r);
    }

    @RequestMapping(value = "/restricoes", method = RequestMethod.GET)
    public ModelAndView restricoes() throws Exception {
        ModelAndView mv = new ModelAndView("restricao/restricoes");
        RestricaoService rs = new RestricaoService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Restricao> restricaoList = rs.readByCriteria(criteria, 0L, 0L);
        mv.addObject("restricaoList", restricaoList);
        return mv;
    }

    @RequestMapping(value = "/listaSite/{idRestricao}", method = RequestMethod.GET)
    public ModelAndView listaSite(@PathVariable("idRestricao") Long idRestricao) throws Exception {
        ModelAndView mv = new ModelAndView("restricao/tabela");
        RestricaoService rs = new RestricaoService();
        Map<Long, Object> criteria = new HashMap<>();
        Restricao restricao = rs.readById(idRestricao);
        mv.addObject("restricao", restricao);
        return mv;
    }

    @RequestMapping(value = "/procurarRestricao/{tecla}", method = RequestMethod.GET)
    public ModelAndView procurarRestricao(@PathVariable("tecla") String searchRestricaoC) throws Exception {
        ModelAndView mv = new ModelAndView("restricao/tabelaRestricao");
        RestricaoService us = new RestricaoService();
        List<Restricao> restricaoList = new ArrayList<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(RestricaoCriteria.NOME_ILIKE, searchRestricaoC);
        restricaoList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("restricaoList", restricaoList);
        return mv;

    }

    @RequestMapping(value = "/procurarRestricao", method = RequestMethod.GET)
    public ModelAndView procurarRestricaoTodas(String searchRestricaoC) throws Exception {
        ModelAndView mv = new ModelAndView("restricao/tabelaRestricao");
        RestricaoService us = new RestricaoService();
        List<Restricao> restricaoList = new ArrayList<>();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(RestricaoCriteria.NOME_ILIKE, searchRestricaoC);
        restricaoList = us.readByCriteria(criteria, 0L, 0L);
        mv.addObject("restricaoList", restricaoList);
        return mv;

    }
    @RequestMapping(value = "/restricao/{id}")
    public ModelAndView getRestricaoEdita(@PathVariable ("id")Long id) throws Exception{
        ModelAndView mv = new ModelAndView("restricao/form");
        RestricaoService rs = new RestricaoService();
        Restricao restricao =rs.readById(id);
        mv.addObject("restricao", restricao);
        return mv;
    }
    @RequestMapping(value = "restricao/novo/site/{tecla}", method = RequestMethod.GET)
    public ModelAndView procurarSiteRestricaoNovo(@PathVariable("tecla") String tecla) throws Exception {
        ModelAndView mv = new ModelAndView("restricao/tabelaSites");
        SiteService ss = new SiteService();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(SiteCriteria.SITE_ILIKE, tecla);
        List<Site> siteList = ss.readByCriteria(criteria, null, null);
        mv.addObject("siteList", siteList);
        return mv;

    }
     @RequestMapping(value = "restricao/novo/site/", method = RequestMethod.GET)
    public ModelAndView procurarSiteRestricao() throws Exception {
        ModelAndView mv = new ModelAndView("restricao/tabelaSites");
        SiteService ss = new SiteService();
        Map<Long, Object> criteria = new HashMap<>();        
        List<Site> siteList = ss.readByCriteria(criteria, null, null);
        mv.addObject("siteList", siteList);
        return mv;

    }
    
    @RequestMapping(value = "/restricao/{id}/deletar",method = RequestMethod.GET)
    public ModelAndView deletarRestricaoGet(@PathVariable ("id")Long id) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/restricoes");
        RestricaoService rs = new RestricaoService();
        Long countRestricaoPlano = rs.verificaPlanoRestricao(id);
        if(countRestricaoPlano == null || countRestricaoPlano == 0){
            rs.delete(id);
        }else{
            mv.addObject("negado", "negado");
        }
        
        return mv;
    }

}
