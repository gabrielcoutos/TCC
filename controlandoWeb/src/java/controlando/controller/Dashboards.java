package controlando.controller;

import controlando.model.criteria.UsuarioCriteria;
import controlando.model.entity.Plano;
import controlando.model.entity.Site;
import controlando.model.entity.UsuariosDias;
import controlando.model.service.PlanoService;
import controlando.model.service.SquidService;
import controlando.model.service.UsuarioService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Dashboards {

    @RequestMapping(value = "/dashboards", method = RequestMethod.GET)
    public ModelAndView getDashboards() throws Exception {
        ModelAndView mv = new ModelAndView("administrador/dashboards");
        SquidService ss = new SquidService();
        Long aprovados = countUsuarios("usuariosAprovados");
        Long deletados = countUsuarios("usuariosDeletados");
        Long naoAnalisados = countUsuarios("usuariosNaoAnalisados");
        List<Plano> planoList = countTopPlanos();
        for (int i = 0; i < planoList.size(); i++) {
            mv.addObject("plano" + (i + 1), planoList.get(i));
        }
        List<Site> siteTopList = top5SitesDia();
        for(int i =0;i<5;i++){
            mv.addObject("site"+(i+1), siteTopList.get(i));
        }
        List<UsuariosDias> uDias =ss.qtdUsuario5Dias();
        for(int i =0;i<uDias.size();i++){
            mv.addObject("uDias"+(i+1), uDias.get(i));
        }
        uDias = ss.diaTotalMb();
        for(int i =0;i<uDias.size();i++){
            mv.addObject("mega"+(i+1), uDias.get(i));
        }
        
        mv.addObject("aprovados", aprovados);
        mv.addObject("deletados", deletados);
        mv.addObject("naoAnalisados", naoAnalisados);
        return mv;
    }

    private Long countUsuarios(String countUsuario) throws Exception {
        Long count = null;
        Map<Long, Object> criteria = new HashMap<>();
        if (countUsuario.equals("usuariosAprovados")) {
            criteria.put(UsuarioCriteria.USUARIOS_APROVADOS, countUsuario);
        }
        if (countUsuario.equals("usuariosDeletados")) {
            criteria.put(UsuarioCriteria.USUARIOS_DELETADOS, countUsuario);
        }
        if (countUsuario.equals("usuariosNaoAnalisados")) {
            criteria.put(UsuarioCriteria.USUARIOS_NAOANALISADOS, countUsuario);
        }
        UsuarioService us = new UsuarioService();
        count = us.countByCriteria(criteria);
        return count;
    }

    private List<Plano> countTopPlanos() throws Exception {
        List<Plano> planoList = new ArrayList<>();
        PlanoService ps = new PlanoService();
        planoList = ps.countTopPlanos();
        return planoList;
    }

    private List<Site> top5SitesDia() throws IOException {
        List<Site> sites = null;
        SquidService ss = new SquidService();
        sites = ss.topSites();
        Collections.sort(sites);
        
        return sites;
    }
    private List<UsuariosDias> qtdAcessoDiario() throws IOException{
        SquidService ss = new SquidService();
       List<UsuariosDias> uDias = ss.qtdUsuario5Dias();
       return uDias;
    }
}
