/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.model.dao;

import controlando.model.base.BaseDAO;
import controlando.model.criteria.PlanoCriteria;
import controlando.model.entity.Plano;
import controlando.model.entity.Restricao;
import controlando.model.entity.Usuario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class PlanoDAO implements BaseDAO<Plano> {

    @Override
    public void create(Connection conn, Plano entity) throws Exception {
        String sql = "INSERT INTO plano( nome) VALUES (?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        createPlanoRestricao(conn, entity);
        rs.close();
        ps.close();
    }

    @Override
    public Plano readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT plano.nome nome_plano ,plano_restricao.data_criacao data_criacao,restricao.id id_restricao,restricao.nome nome_restricao, restricao.tempo_acesso tempo_acesso FROM plano left join plano_restricao on plano.id= plano_restricao.plano_fk left join restricao  on restricao.id = plano_restricao.restricao_fk where plano.id = ? and ativo =1;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Plano plano = null;
        if (rs.next()) {
            plano = new Plano();
            plano.setId(id);
            plano.setNome(rs.getString("nome_plano"));
            plano.setDataCriacao(rs.getTimestamp("data_criacao"));
            Restricao restricao = new Restricao();
            restricao.setId(rs.getLong("id_restricao"));
            restricao.setNome(rs.getString("nome_restricao"));
            restricao.setTempoAcesso(rs.getInt("tempo_acesso"));
            plano.setRestricao(restricao);
        }
        return plano;
    }

    @Override
    public List<Plano> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT plano.id id_plano, plano.nome nome_plano,plano_restricao.data_criacao data_criacao , restricao.id id_restricao ,restricao.nome nome_restricao, restricao.tempo_acesso tempo_acesso FROM plano left join plano_restricao on plano_restricao.plano_fk = plano.id left join  restricao on restricao.id = plano_restricao.restricao_fk where 1=1 ";
        if (criteria.size() > 0) {
            sql += applyCriteria(criteria);
        }

        List<Plano> planoList = new ArrayList<>();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Plano plano = null;
        while (rs.next()) {
            plano = new Plano();
            plano.setId(rs.getLong("id_plano"));
            plano.setNome(rs.getString("nome_plano"));
            plano.setDataCriacao(rs.getTimestamp("data_criacao"));
            Restricao restricao = new Restricao();
            restricao.setId(rs.getLong("id_restricao"));
            restricao.setNome(rs.getString("nome_restricao"));
            restricao.setTempoAcesso(rs.getInt("tempo_acesso"));
            plano.setRestricao(restricao);
            planoList.add(plano);
        }
        return planoList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "select count(*) total  from plano inner join usuario_plano on usuario_plano.plano_fk = plano.id where ativo =1 and plano.id = ?";
        Long count = null;
        return count;
    }

    @Override
    public void update(Connection conn, Plano entity) throws Exception {
        String sql = "UPDATE plano SET nome=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, entity.getNome());
        ps.setLong(2, entity.getId());
        createPlanoRestricao(conn, entity);
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        Plano p = readById(conn, id);
        p.setAtivo(0);
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        p.setDataCriacao(dataDeHoje);
        updatePlanoRestricao(conn, p);
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = "";

        String planoIlike = (String) criteria.get(PlanoCriteria.NOME_ILIKE);
        if (planoIlike != null && !planoIlike.isEmpty()) {
            sql += " AND plano.nome ILIKE '%" + planoIlike + "%'";
        }
        Long plano = (Long) criteria.get(PlanoCriteria.PLANO_FK);
        if (plano != null && plano > 0) {
            sql += "AND plano_fk = " + plano;
        }
        Long restricao = (Long) criteria.get(PlanoCriteria.RESTRICAO_FK);
        if (restricao != null && restricao > 0) {
            sql += "and restricao_fk =  " + restricao;
        }

        Integer ativo = (Integer) criteria.get(PlanoCriteria.ATIVO);
        if (ativo != null && ativo == 1) {
            sql += "AND ativo=" + ativo;
        }
        return sql;
    }

    public void createPlanoRestricao(Connection conn, Plano entity) throws SQLException, Exception {
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(PlanoCriteria.PLANO_FK, entity.getId());
        criteria.put(PlanoCriteria.RESTRICAO_FK, entity.getRestricao().getId());
        List<Plano> p = readByCriteria(conn, criteria, null, null);
        if (p.isEmpty()) {
            String sql = "INSERT INTO plano_restricao(plano_fk, restricao_fk, data_criacao,ativo,modificado) VALUES (?, ?, ?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            int i = 0;
            ps.setLong(++i, entity.getId());
            ps.setLong(++i, entity.getRestricao().getId());
            ps.setTimestamp(++i, entity.getDataCriacao());
            ps.setInt(++i, entity.getAtivo());
            ps.setLong(++i, entity.getUsuario().getId());
            updatePlanoRestricao(conn, entity);
            ps.execute();
            ps.close();
        } else {
            updatePlanoRestricaoExistente(conn, entity);
        }

    }

    public void updatePlanoRestricao(Connection conn, Plano entity) throws SQLException, Exception {
        Plano p = readPlano(conn, entity);
        if (p == null) {

        } else {
            String sql = "UPDATE plano_restricao SET  ativo=?,data_criacao=? where plano_fk =? and ativo = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setTimestamp(2, entity.getDataCriacao());
            ps.setLong(3, entity.getId());
            ps.execute();
            ps.close();
        }

    }

    private Plano readPlano(Connection conn, Plano entity) throws SQLException {
        Plano p = null;
        String sql = "SELECT * FROM plano_restricao where plano_fk =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, entity.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new Plano();
        }
        ps.close();
        return p;
    }

    public Long countByUser(Connection conn, Long id) throws Exception {
        String sql = "select count(*) total  from plano inner join usuario_plano on usuario_plano.plano_fk = plano.id where ativo =1 and plano.id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Long count = null;
        if (rs.next()) {
            count = rs.getLong("total");
        }

        return count;
    }

    public void updatePlanoRestricaoExistente(Connection conn, Plano entity) throws SQLException, Exception {
        String sql = "UPDATE plano_restricao SET  ativo=?, data_criacao=? where plano_fk =? and restricao_fk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setTimestamp(2, entity.getDataCriacao());
        ps.setLong(3, entity.getId());
        ps.setLong(4, entity.getRestricao().getId());
        updatePlanoRestricao(conn, entity);
        ps.execute();
        ps.close();

    }

    public void createTxtSquid(Connection conn, Plano entity) {
        FileWriter arq;
        try {
            arq = new FileWriter(new File("/etc/squid3/" + entity.getNome() + ".txt"));
            for (int i = 0; i < entity.getRestricao().getSiteList().size(); i++) {
                Integer index = null;
                Integer fim =  null;
                String url = entity.getRestricao().getSiteList().get(i).getUrl();
                index = url.indexOf("w");                
                String urlQuebrada = null;
                if (index == -1) {
                    index = url.indexOf("/");    
                    fim = url.indexOf(".");
                    urlQuebrada = url.substring(index+2,fim);
                    arq.write(urlQuebrada + "\n");
                } else {
                    urlQuebrada = url.substring(index+4);   
                    fim = urlQuebrada.indexOf(".");
                    urlQuebrada = urlQuebrada.substring(0, fim);
                    arq.write(urlQuebrada + "\n");
                }           

            }
            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Plano> countTopPlanos(Connection conn) throws SQLException {
        List<Plano> planoList = new ArrayList<>();
        String sql = "SELECT  count(nome) qtd, nome FROM plano inner join usuario_plano on plano.id = usuario_plano.plano_fk where ativo =1 group by plano.nome ";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Plano p = null;
        while (rs.next()) {
            p = new Plano();
            p.setNome(rs.getString("nome"));
            p.setQtd(rs.getLong("qtd"));
            planoList.add(p);
        }

        return planoList;

    }
}
