
package controlando.model.service;

import controlando.model.base.service.BaseRestricaoService;
import controlando.model.dao.RestricaoDAO;
import controlando.model.entity.Restricao;
import controlandomodel.ConnectionManager;
import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class RestricaoService implements BaseRestricaoService {
      @Override
    public void create(Restricao entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        RestricaoDAO dao = new RestricaoDAO();
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
    public Restricao readById(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        RestricaoDAO dao = new RestricaoDAO();
        Restricao restricao = null;
        try{
            restricao = dao.readById(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return restricao;
    }

    @Override
    public List<Restricao> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        RestricaoDAO dao = new RestricaoDAO();
         List<Restricao> restricaoList = null;
        try{
           restricaoList= dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
         
        return restricaoList;
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
    public void update(Restricao entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        try{
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
        RestricaoDAO dao = new RestricaoDAO();
        
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
    
    public Long verificaPlanoRestricao(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        Long count = null;
        RestricaoDAO dao = new RestricaoDAO();
        
        try{
            count = dao.verificarPlanoRestricao(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return count;
    }
    
}
