package controlandomodel;

import controlando.model.entity.Plano;
import controlando.model.entity.Restricao;
import controlando.model.entity.Usuario;
import controlando.model.service.PlanoService;
import controlando.model.service.RestricaoService;
import controlando.model.service.SquidService;
import controlando.model.service.UsuarioService;

public class main {

    public static void main(String[] args) throws Exception {

//        PlanoService ps = new PlanoService();
//        Plano p = ps.readById(5L);      
//        Restricao r = new Restricao();
//        RestricaoService rs = new RestricaoService();
//        r = rs.readById(p.getRestricao().getId());        
//        p.setRestricao(r);
        SquidService ss = new SquidService();
        ss.diaTotalMb();
//        ss.createACL(p);
//            Usuario u = new Usuario();
//            UsuarioService us = new UsuarioService();
//            u = us.readById(1L);
//            u.setIp("192.168.43.3");
//        UsuarioService us = new UsuarioService();
//        Usuario usuario = new Usuario();
//        usuario.setNome("Administrador");
//        usuario.setEmail("adm@controlando.com");
//        usuario.setSenhaAsPlanText("adm123");
//        usuario.setTipo(1);
//        usuario.setCpf("00000000000");
//        usuario.setData_nascimento("02/09/1996");
//        us.create(usuario);

//        PlanoService ps = new PlanoService();
//        Plano p = ps.readById(13L);
//        RestricaoService rs = new RestricaoService();
//        Restricao r = rs.readById(p.getRestricao().getId());
//        p.setRestricao(r);
//        ps.createAclTxt(p);
//       ss.liberaIP("192.168.50.9");
    }

}
