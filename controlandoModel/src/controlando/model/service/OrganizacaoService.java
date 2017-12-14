package controlando.model.service;

import controlando.model.base.service.BaseOrganizacaoService;
import controlando.model.dao.OrganizacaoDAO;
import controlando.model.entity.Organizacao;
import controlandomodel.ConnectionManager;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class OrganizacaoService implements BaseOrganizacaoService {

    @Override
    public void create(Organizacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        OrganizacaoDAO dao = new OrganizacaoDAO();
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
    public Organizacao readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Organizacao organizacao = new Organizacao();
        OrganizacaoDAO dao = new OrganizacaoDAO();
        try {
            organizacao = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
        return organizacao;
    }

    @Override
    public List<Organizacao> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Organizacao> organizacaoList = null;
        OrganizacaoDAO dao = new OrganizacaoDAO();
        try {
            organizacaoList= dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();

        }
        return organizacaoList;
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
    public void update(Organizacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        OrganizacaoDAO dao = new OrganizacaoDAO();
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
        try {
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
