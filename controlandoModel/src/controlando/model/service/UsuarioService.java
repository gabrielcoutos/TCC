/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.model.service;

import controlando.model.base.service.BaseUsuarioService;
import controlando.model.criteria.UsuarioCriteria;
import controlando.model.dao.UsuarioDAO;
import controlando.model.entity.Usuario;
import controlandomodel.ConnectionManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class UsuarioService implements BaseUsuarioService{

      @Override
    public void create(Usuario entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao= new UsuarioDAO();
        try{
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = null;
        try{
            usuario =dao.readById(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        List<Usuario> usuarioList= new ArrayList<>();
        UsuarioDAO dao = new UsuarioDAO();                
        try{
            usuarioList=dao.readByCriteria(conn,criteria,limit,offset);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return usuarioList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        Long count=null;
        UsuarioDAO dao = new UsuarioDAO();
        try{
            count = dao.countByCriteria(conn, criteria);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return count;
    }

    @Override
    public void update(Usuario entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        try{
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        try{
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario login(String email,String senha) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        Map<Long,Object> criteria = new HashMap<>();
        Usuario usuario = null;
        criteria.put(UsuarioCriteria.EMAIL_EQ, email);
        criteria.put(UsuarioCriteria.SENHA_EQ, senha);        
        UsuarioDAO dao= new UsuarioDAO();
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList=dao.readByCriteria(conn, criteria, null, null);
        if(usuarioList.size()==1){
            Usuario aux = usuarioList.get(0);
            if (aux.getEmail().equals(email) && aux.getSenha().equals(senha)) {
                usuario = aux;
            }
        }
        conn.commit();
        conn.close();
        
        return usuario;
    }

   
    public List<Usuario> usuarioNaoCadastrado(Map<Long, Object> criteria) throws Exception  {
        Connection conn= ConnectionManager.getInstance().getConnection();
        List<Usuario> usuarioList= new ArrayList<>();
        UsuarioDAO dao = new UsuarioDAO();                
        try{
            usuarioList=dao.usuarioNaoCadastrado(conn, criteria);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return usuarioList;
    }
    
}
