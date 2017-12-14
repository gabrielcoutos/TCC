/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.interceptor;

import controlando.model.entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AAIntercerptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean shallPass = false;

        String uri = request.getRequestURI();
        String context = "/controlando";
        //urls livres
        if (uri.startsWith(context + "/resources")) {
            shallPass = true;
        }
        if (uri.startsWith(context + "/cadastrar")) {
            shallPass = true;
        }
        if (uri.equals(context + "/")) {
            shallPass = true;
        }
        if (uri.startsWith(context + "/entrar")) {
            shallPass = true;
        }
        if (uri.equals(context + "/ajuda")) {
            shallPass = true;
        }
         if (uri.equals(context + "/autenticado")) {
            shallPass = true;
        }
          if (uri.equals(context + "/sair")) {
            shallPass = true;
        }


        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuario != null) {
            if(usuario.getTipo()==1){
                shallPass = true;
            }
            
            
        }
        if (!shallPass) {
            response.sendRedirect(context + "/");
        }

        return shallPass;
    }

}
