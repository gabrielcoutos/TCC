package controlando.model.service;

import controlando.model.base.service.BaseSiteService;
import controlando.model.dao.SiteDAO;
import controlando.model.entity.Site;
import controlandomodel.ConnectionManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SiteService implements BaseSiteService {

    @Override
    public void create(Site entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        SiteDAO dao= new SiteDAO();
        try {
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
    }

    @Override
    public Site readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Site site = null;
        SiteDAO dao = new SiteDAO();
        try {
            site = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
        return site;
    }

    @Override
    public List<Site> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Site> siteList = new ArrayList<>();
        SiteDAO dao = new SiteDAO();
        try {
            siteList= dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
        return siteList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long count = null;
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
        return count;
    }

    @Override
    public void update(Site entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        SiteDAO dao = new SiteDAO();
        try {
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        SiteDAO dao = new SiteDAO();
        try {
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
