
package controlando.model.service;

import controlando.model.base.service.BasePlanoService;
import controlando.model.dao.PlanoDAO;
import controlando.model.entity.Plano;
import controlandomodel.ConnectionManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PlanoService implements BasePlanoService {
      @Override
    public void create(Plano entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        PlanoDAO dao = new PlanoDAO();
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
    public Plano readById(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        PlanoDAO dao = new PlanoDAO();
        Plano plano = new Plano();
        try{
            plano = dao.readById(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return plano;
    }

    @Override
    public List<Plano> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        List<Plano> planoList= new ArrayList<>();
        PlanoDAO dao = new PlanoDAO();
        try{
           planoList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return planoList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        Long count=null;
        PlanoDAO dao = new PlanoDAO();
        try{
            count= dao.countByUser(conn, count);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return count;
    }

    @Override
    public void update(Plano entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        PlanoDAO dao = new PlanoDAO();
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
        PlanoDAO dao = new PlanoDAO();
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
    
    public Long countByUser(Long id) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();
        Long count=null;
        PlanoDAO dao = new PlanoDAO();
        try{
            count= dao.countByUser(conn, id);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return count;
    }
    //testa se existe usuario associado ao plano
    
    
    public void createAclTxt(Plano entity) throws Exception {
        Connection conn= ConnectionManager.getInstance().getConnection();       
        PlanoDAO dao = new PlanoDAO();
        try{
            dao.createTxtSquid(conn, entity);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        
    }
    public List<Plano> countTopPlanos() throws Exception{
        List<Plano> planoList = null;
        Connection conn= ConnectionManager.getInstance().getConnection();       
        PlanoDAO dao = new PlanoDAO();
        try{
            planoList=dao.countTopPlanos(conn);
            conn.commit();
            conn.close();
        }catch(Exception e){
            conn.rollback();
            conn.close();
            
        }
        return planoList;
    }
}
