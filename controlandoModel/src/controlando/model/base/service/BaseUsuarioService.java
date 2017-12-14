/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.model.base.service;

import controlando.model.base.BaseCRUDService;
import controlando.model.entity.Usuario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public interface BaseUsuarioService extends BaseCRUDService <Usuario>{
    public Usuario login(String email,String senha) throws Exception;
    public List<Usuario> usuarioNaoCadastrado(Map<Long,Object> criteria) throws Exception;
    
}
