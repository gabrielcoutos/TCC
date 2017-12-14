
package controlando.model.service;

import controlando.model.base.service.BaseCategoriaService;
import controlando.model.dao.CategoriaDAO;
import controlando.model.entity.Categoria;
import controlandomodel.ConnectionManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CategoriaService implements BaseCategoriaService{

    @Override
    public void create(Categoria entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        CategoriaDAO dao= new CategoriaDAO();
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
    public Categoria readById(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        CategoriaDAO dao= new CategoriaDAO();
        Categoria categoria = null;
        try{
            categoria= dao.readById(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return categoria;
    }

    @Override
    public List<Categoria> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        List<Categoria> categoriaList= new ArrayList<>();
        CategoriaDAO dao= new CategoriaDAO();
        try{
            categoriaList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return categoriaList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        Long count=null;
        try{
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return count;
    }

    @Override
    public void update(Categoria entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        CategoriaDAO dao= new CategoriaDAO();
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
        CategoriaDAO dao= new CategoriaDAO();
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

    public void update(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
