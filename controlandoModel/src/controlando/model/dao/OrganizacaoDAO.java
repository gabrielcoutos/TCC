
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.model.dao;

import controlando.model.base.BaseDAO;
import controlando.model.entity.Organizacao;
import controlando.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class OrganizacaoDAO implements BaseDAO<Organizacao> {

    @Override
    public void create(Connection conn, Organizacao entity) throws Exception {
        String sql = "INSERT INTO organizacao(razao_social, nome_fantasia, cnpj, logotipo, bairro, numero, rua, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getRazaoSocial());
        ps.setString(++i, entity.getNome_fantasia());
        ps.setString(++i, entity.getCnpj());
        ps.setBytes(++i, entity.getLogo());
        ps.setString(++i, entity.getBairro());
        ps.setString(++i, entity.getNumero());
        ps.setString(++i, entity.getRua());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getTelefone());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Organizacao readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT organizacao.id id_organizacao, razao_social, nome_fantasia, cnpj, logotipo, bairro, numero, rua, organizacao.email email_organizacao, telefone FROM organizacao WHERE organizacao.id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Organizacao organizacao = null;
        while (rs.next()) {
            organizacao = new Organizacao();
            organizacao.setId(id);
            organizacao.setRazaoSocial(rs.getString("razao_social"));
            organizacao.setNome_fantasia(rs.getString("nome_fantasia"));
            organizacao.setCnpj(rs.getString("cnpj"));
            organizacao.setLogo(rs.getBytes("logotipo"));
            organizacao.setBairro(rs.getString("bairro"));
            organizacao.setNumero(rs.getString("numero"));
            organizacao.setRua(rs.getString("rua"));
            organizacao.setEmail(rs.getString("email_organizacao"));
            organizacao.setTelefone(rs.getString("telefone"));
        }
        rs.close();
        ps.close();

        return organizacao;
    }

    @Override
    public List<Organizacao> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql ="SELECT organizacao.id id_organizacao, razao_social, nome_fantasia, cnpj, logotipo, bairro, numero, rua, organizacao.email email_organizacao, telefone FROM organizacao";
        PreparedStatement ps = conn.prepareStatement(sql);        
        ResultSet rs = ps.executeQuery();
        Organizacao organizacao = null;
        List<Organizacao> orgList = new ArrayList<>();
        while (rs.next()) {
            organizacao = new Organizacao();
            organizacao.setId(rs.getLong("id_organizacao"));
            organizacao.setRazaoSocial(rs.getString("razao_social"));
            organizacao.setNome_fantasia(rs.getString("nome_fantasia"));
            organizacao.setCnpj(rs.getString("cnpj"));
            organizacao.setLogo(rs.getBytes("logotipo"));
            organizacao.setBairro(rs.getString("bairro"));
            organizacao.setNumero(rs.getString("numero"));
            organizacao.setRua(rs.getString("rua"));
            organizacao.setEmail(rs.getString("email_organizacao"));
            organizacao.setTelefone(rs.getString("telefone"));
            orgList.add(organizacao);
        }
        rs.close();
        ps.close();
        return orgList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Organizacao entity) throws Exception {
        String sql = "UPDATE organizacao SET razao_social=?, nome_fantasia=?, cnpj=?, logotipo=?, bairro=?, numero=?, rua=?, email=?, telefone=? WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getRazaoSocial());
        ps.setString(++i, entity.getNome_fantasia());
        ps.setString(++i, entity.getCnpj());
        ps.setBytes(++i, entity.getLogo());
        ps.setString(++i, entity.getBairro());
        ps.setString(++i, entity.getNumero());
        ps.setString(++i, entity.getRua());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getTelefone());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
