package controlando.controller;

import controlando.model.criteria.CategoriaCriteria;
import controlando.model.entity.Categoria;
import controlando.model.service.CategoriaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoriaController {

    @RequestMapping(value = "/categorias", method = RequestMethod.GET)
    public ModelAndView getCategorias() throws Exception {
        ModelAndView mv = new ModelAndView("categoria/categorias");
        CategoriaService cs = new CategoriaService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Categoria> categoriaList = cs.readByCriteria(criteria, null, null);
        mv.addObject("categoriaList", categoriaList);
        return mv;
    }

    @RequestMapping(value = "procurarCategoria/{tecla}", method = RequestMethod.GET)
    public ModelAndView pesquisaCategoria(@PathVariable("tecla") String tecla) throws Exception {
        ModelAndView mv = new ModelAndView("categoria/tabela");
        CategoriaService cs = new CategoriaService();
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(CategoriaCriteria.CATEGORIA_ILIKE, tecla);
        List<Categoria> categoriaList = cs.readByCriteria(criteria, null, null);
        mv.addObject("categoriaList", categoriaList);
        return mv;

    }

    @RequestMapping(value = "procurarCategoria/", method = RequestMethod.GET)
    public ModelAndView pesquisaCategoriaVoid() throws Exception {
        ModelAndView mv = new ModelAndView("categoria/tabela");
        CategoriaService cs = new CategoriaService();
        Map<Long, Object> criteria = new HashMap<>();
        List<Categoria> categoriaList = cs.readByCriteria(criteria, null, null);
        mv.addObject("categoriaList", categoriaList);
        return mv;
    }

    @RequestMapping(value = "/categoria/novo", method = RequestMethod.GET)
    public ModelAndView getCategoriaNovo() {
        ModelAndView mv = new ModelAndView("categoria/form");
        return mv;
    }

    @RequestMapping(value = "/categoria/novo", method = RequestMethod.POST)
    public ModelAndView postCategoriaNovo(String nomeCategoria, String descricao) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/categorias");
        Categoria categoria = new Categoria();
        categoria.setNome(nomeCategoria);
        categoria.setDescricao(descricao);
        CategoriaService cs = new CategoriaService();
        cs.create(categoria);
        return mv;
    }

    @RequestMapping(value = "/categoria/{id}/deletar", method = RequestMethod.GET)
    public ModelAndView deletarCategoria(@PathVariable("id") Long id) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/categorias");
        CategoriaService cs = new CategoriaService();
        cs.delete(id);
        return mv;
    }

    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.GET)
    public ModelAndView getCategoriaEditar(@PathVariable("id") Long id) throws Exception {
        ModelAndView mv = new ModelAndView("categoria/form");
        CategoriaService cs = new CategoriaService();
        Categoria categoria = cs.readById(id);
        mv.addObject("categoria", categoria);
        return mv;
    }

    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.POST)
    public ModelAndView postCategoriaEditar(@PathVariable("id") Long id,String nomeCategoria, String descricao) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/categorias");
        Categoria categoria = new Categoria();
        categoria.setNome(nomeCategoria);
        categoria.setDescricao(descricao);
        categoria.setId(id);
        CategoriaService cs = new CategoriaService();
        cs.update(categoria);
        return mv;
    }
}
