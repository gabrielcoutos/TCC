package controlando.controller;

import controlando.model.criteria.SiteCriteria;
import controlando.model.entity.Categoria;
import controlando.model.entity.Site;
import controlando.model.service.CategoriaService;
import controlando.model.service.SiteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public ModelAndView getSites() throws Exception {
        ModelAndView mv = new ModelAndView("sites/sites");
        SiteService ss = new SiteService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Site> siteList = ss.readByCriteria(criteria, null, null);
        mv.addObject("siteList", siteList);
        return mv;
    }

    @RequestMapping(value = "/procurarSite/{tecla}", method = RequestMethod.GET)
    public ModelAndView pesquisaSite(@PathVariable("tecla") String tecla) throws Exception {
        ModelAndView mv = new ModelAndView("sites/tabela");
        SiteService ss = new SiteService();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(SiteCriteria.SITE_ILIKE, tecla);
        List<Site> siteList = ss.readByCriteria(criteria, null, null);
        mv.addObject("siteList", siteList);
        return mv;

    }
    @RequestMapping(value = "/procurarSite/", method = RequestMethod.GET)
    public ModelAndView pesquisaSite() throws Exception {
        ModelAndView mv = new ModelAndView("sites/tabela");
        SiteService ss = new SiteService();
        Map<Long, Object> criteria = new HashMap<>();       
        List<Site> siteList = ss.readByCriteria(criteria, null, null);
        mv.addObject("siteList", siteList);
        return mv;
    }
    
    @RequestMapping(value = "/site/novo",method = RequestMethod.GET)
    public ModelAndView getSiteNovo() throws Exception{
        ModelAndView mv = new ModelAndView("sites/form");
        CategoriaService cs= new CategoriaService();
        Map<Long, Object> criteria = new HashMap<>(); 
        List<Categoria> categoriaList = cs.readByCriteria(criteria,null, null);
        mv.addObject("categoriaList", categoriaList);
        return mv;
    }
    
    @RequestMapping(value = "/site/novo",method = RequestMethod.POST)
    public ModelAndView postSiteNovo(String nomeSite,String descricao,String urlSite,Long selectCategoria) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/sites");
        Site site  = new Site();
        Categoria categoria = new Categoria();
        categoria.setId(selectCategoria);
        site.setNome(nomeSite);
        site.setDescricao(descricao);
        site.setUrl(urlSite);
        site.setCategoria(categoria);
        SiteService ss = new SiteService();
        ss.create(site);
        return mv;
    }
    
    @RequestMapping(value = "site/{id}/deletar",method = RequestMethod.GET)
    public ModelAndView deletarSite(@PathVariable("id") Long id) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/sites");
        SiteService ss = new SiteService();
        ss.delete(id);
        return mv;
    }
    
    @RequestMapping(value = "/site/{id}",method = RequestMethod.GET)
    public ModelAndView getSiteEditar(@PathVariable("id") Long id) throws Exception{
        ModelAndView mv = new ModelAndView("sites/form");
        CategoriaService cs= new CategoriaService();
        Map<Long, Object> criteria = new HashMap<>(); 
        List<Categoria> categoriaList = cs.readByCriteria(criteria,null, null);
        SiteService ss = new SiteService();
        Site site  = ss.readById(id);
        mv.addObject("site", site);
        mv.addObject("categoriaList", categoriaList);
        return mv;
    }
    
    @RequestMapping(value = "/site/{id}",method = RequestMethod.POST)
    public ModelAndView postSiteEditar(@PathVariable("id") Long id ,String nomeSite,String descricao,String urlSite,Long selectCategoria) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/sites");
        Site site  = new Site();
        Categoria categoria = new Categoria();
        categoria.setId(selectCategoria);
        site.setId(id);
        site.setNome(nomeSite);
        site.setDescricao(descricao);
        site.setUrl(urlSite);
        site.setCategoria(categoria);
        SiteService ss = new SiteService();
        ss.update(site);
        return mv;
    }
}
