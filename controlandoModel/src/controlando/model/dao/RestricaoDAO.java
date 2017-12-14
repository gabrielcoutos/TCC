/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.model.dao;

import controlando.model.base.BaseDAO;
import controlando.model.criteria.RestricaoCriteria;
import controlando.model.entity.Categoria;
import controlando.model.entity.Restricao;
import controlando.model.entity.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class RestricaoDAO implements BaseDAO<Restricao> {

    @Override
    public void create(Connection conn, Restricao entity) throws Exception {
        String sql = "INSERT INTO restricao(tempo_acesso, nome) VALUES (?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, entity.getTempoAcesso());
        ps.setString(2, entity.getNome());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        siteRestricao(conn, entity);
        rs.close();
        ps.close();
    }

    @Override
    public Restricao readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT restricao.tempo_acesso,restricao.nome nome_restricao,site.id id_site,site.nome nome_site,site.descricao descricao_site,site.url,categoria.id id_categoria,categoria.nome nome_categoria,categoria.descricao descricao_categoria FROM restricao left join site_restricao on site_restricao.restricao_fk = restricao.id left join site on site.id= site_restricao.site_fk left join categoria on categoria.id = site.categoria_fk where restricao.id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Restricao restricao = null;
        while (rs.next()) {
            if (restricao == null) {
                restricao = new Restricao();
            }
            restricao.setId(id);
            restricao.setNome(rs.getString("nome_restricao"));
            restricao.setTempoAcesso(rs.getInt("tempo_acesso"));
            if (rs.getLong("id_site") > 0) {
                Site site = new Site();
                site.setId(rs.getLong("id_site"));
                site.setNome(rs.getString("nome_site"));
                site.setDescricao(rs.getString("descricao_site"));
                site.setUrl(rs.getString("url"));
                Categoria cat = new Categoria();
                cat.setId(rs.getLong("id_categoria"));
                cat.setNome(rs.getString("nome_categoria"));
                cat.setDescricao(rs.getString("descricao_categoria"));
                site.setCategoria(cat);
                restricao.getSiteList().add(site);
            }

        }
        return restricao;

    }

    @Override
    public List<Restricao> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from restricao where 1=1 ";
        sql += applyCriteria(criteria);
        List<Restricao> restricaoList = new ArrayList<>();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Restricao restricao = null;
        while (rs.next()) {
            restricao = new Restricao();
            restricao.setId(rs.getLong("id"));
            restricao.setNome(rs.getString("nome"));
            restricao.setTempoAcesso(rs.getInt("tempo_acesso"));
            restricaoList.add(restricao);
        }
        return restricaoList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Restricao entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {

        String sql = "DELETE FROM restricao WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();

    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = "";
        String nomeILike = (String) criteria.get(RestricaoCriteria.NOME_ILIKE);
        if (nomeILike != null && !nomeILike.isEmpty()) {
            sql += " AND nome ILIKE '%" + nomeILike + "%'";
        }
        return sql;
    }

    private void siteRestricao(Connection conn, Restricao entity) throws Exception {
        String sql = "INSERT INTO site_restricao(site_fk, restricao_fk) VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < entity.getSiteList().size(); i++) {
            ps.setLong(1, entity.getSiteList().get(i).getId());
            ps.setLong(2, entity.getId());
            ps.execute();
        }
        ps.close();
    }

    public Long verificarPlanoRestricao(Connection conn, Long id) throws SQLException {
        Long planoRestricao = 0L;
        String sql = "SELECT count(*) FROM restricao inner join plano_restricao on restricao.id = plano_restricao.restricao_fk where restricao.id =? and ativo =1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            planoRestricao = rs.getLong("count");
        }
        return planoRestricao;
    }
}
