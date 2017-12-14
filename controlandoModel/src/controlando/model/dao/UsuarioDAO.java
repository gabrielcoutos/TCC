/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlando.model.dao;

import controlando.model.base.BaseDAO;
import controlando.model.criteria.UsuarioCriteria;
import controlando.model.entity.Plano;
import controlando.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class UsuarioDAO implements BaseDAO<Usuario> {

    @Override
    public void create(Connection conn, Usuario entity) throws Exception {
        String sql = "INSERT INTO usuario(cpf, nome, email, senha, deletado,status, data_nascimento) VALUES (?, ?, ?, ?,?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getCpf());
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getSenha());
        ps.setInt(++i, 0);
        if (entity.getStatus() != null) {
            ps.setInt(++i, entity.getStatus());
        } else {
            ps.setInt(++i, 0);
        }
        ps.setString(++i, entity.getData_nascimento());        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        if(entity.getPlano()!= null){
            update(conn, entity);
        }
        rs.close();
        ps.close();
    }

    @Override
    public Usuario readById(Connection conn, Long id) throws Exception {
        String sql = "select usuario.id usuario_id,cpf usuario_cpf,usuario.nome usuario_nome,email usuario_email,usuario.senha senha_usuario,tipo usuario_tipo,deletado usuario_deletado, status usuario_status,data_nascimento usuario_nascimento,usuario.usuario_fk usuario_adm,data_inicio plano_inicio,data_termino plano_termino,usuario_plano.ativo ativo_plano_usuario,plano.nome plano,plano.id plano_id,plano_restricao.ativo ativo_plano from usuario left join usuario_plano on usuario.id= usuario_plano.usuario_fk left join plano on plano.id = usuario_plano.plano_fk  left join plano_restricao on plano.id = plano_restricao.plano_fk where usuario.id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Usuario usuario = null;
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setId(id);
            usuario.setCpf(rs.getString("usuario_cpf"));
            usuario.setData_nascimento(rs.getString("usuario_nascimento"));
            usuario.setDelete(rs.getInt("usuario_deletado"));
            usuario.setEmail(rs.getString("usuario_email"));
            usuario.setSenha(rs.getString("senha_usuario"));
            usuario.setNome(rs.getString("usuario_nome"));
            usuario.setTipo(rs.getInt("usuario_tipo"));
            usuario.setStatus(rs.getInt("usuario_status"));
            Usuario adm = new Usuario();
            if (rs.getLong("ativo_plano_usuario") > 0) {
                Plano plano = new Plano();
                plano.setId(rs.getLong("plano_id"));
                plano.setNome(rs.getString("plano"));
                plano.setDataCriacao(rs.getTimestamp("plano_inicio"));
                usuario.setPlano(plano);
            }
            adm.setId(rs.getLong("usuario_adm"));

            //plano.setDataTermino(rs.getString("plano_termino"));
            usuario.setUser(adm);

        }
        rs.close();
        ps.close();
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "SELECT usuario.id id_usuario, cpf, usuario.nome nome_usuario, email, senha, tipo, deletado,status, data_nascimento,usuario.usuario_fk adm ,plano.nome nome_plano,plano.id id_plano FROM usuario left join usuario_plano on usuario_plano.usuario_fk= usuario.id left join plano on plano.id = usuario_plano.plano_fk WHERE 1=1 ";
        sql += applyCriteria(criteria);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Usuario usuario = null;
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setCpf(rs.getString("cpf"));
            usuario.setData_nascimento(rs.getString("data_nascimento"));
            usuario.setDelete(rs.getInt("deletado"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setId(rs.getLong("id_usuario"));
            usuario.setTipo(rs.getInt("tipo"));
            usuario.setStatus(rs.getInt("status"));
            usuario.setNome(rs.getString("nome_usuario"));
            Usuario userAdm = new Usuario();
            userAdm.setId(rs.getLong("adm"));
            usuario.setUser(userAdm);
            if (rs.getLong("id_plano") > 0) {
                Plano plano = new Plano();
                plano.setId(rs.getLong("id_plano"));
                plano.setNome(rs.getString("nome_plano"));
                usuario.setPlano(plano);
            }
            usuarioList.add(usuario);

        }
        rs.close();
        ps.close();
        return usuarioList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        Long count = null;
        String sql = "select count(*) total  from usuario where 1=1 ";
        sql += applyCriteria(criteria);
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        if (rs.next()) {
            count = rs.getLong("total");
        }
        return count;
    }

    @Override
    public void update(Connection conn, Usuario entity) throws Exception {
        String sql = "UPDATE usuario SET cpf=?, nome=?, email=?,senha=?, tipo=?, deletado=?,status=?, data_nascimento=?,usuario_fk=? WHERE id= ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getCpf());
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getSenha());
        ps.setInt(++i, entity.getTipo());
        if (entity.getDelete() == null) {
            entity.setDelete(0);
        }
        if (entity.getStatus() == null) {
            entity.setStatus(1);
        }
        ps.setInt(++i, entity.getDelete());
        ps.setInt(++i, entity.getStatus());
        ps.setString(++i, entity.getData_nascimento());
        ps.setLong(++i, entity.getUser().getId());
        ps.setLong(++i, entity.getId());
        createUsuarioPlano(conn, entity);
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "UPDATE usuario SET deletado=? WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setLong(2, id);
        deleteUsuarioPlano(conn, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = "";
        String nomeILike = (String) criteria.get(UsuarioCriteria.NOME_ILIKE);
        if (nomeILike != null && !nomeILike.isEmpty()) {
            sql += " AND usuario.nome ILIKE '%" + nomeILike + "%'";
        }
        String emailEQ = (String) criteria.get(UsuarioCriteria.EMAIL_EQ);
        if (emailEQ != null && !emailEQ.isEmpty()) {
            sql += " AND email='" + emailEQ + "'";
        }
        String senhaEQ = (String) criteria.get(UsuarioCriteria.SENHA_EQ);
        if (senhaEQ != null && !senhaEQ.isEmpty()) {
            sql += " AND senha='" + senhaEQ + "'";
        }
        String nomeNCadastradoILike = (String) criteria.get(UsuarioCriteria.NOMENCADASTRADO_ILIKE);
        if (nomeNCadastradoILike != null && !nomeNCadastradoILike.isEmpty()) {
            sql += " AND nome ILIKE '%" + nomeNCadastradoILike + "%'";
        }
        String usuarioAprovados = (String) criteria.get(UsuarioCriteria.USUARIOS_APROVADOS);
        if (usuarioAprovados != null) {
            sql += " and status =1 and deletado = 0";
        }
        String usuarioDeletados = (String) criteria.get(UsuarioCriteria.USUARIOS_DELETADOS);
        if (usuarioDeletados != null) {
            sql += " and deletado = 1";
        }
        String usuarioNaoAnalisados = (String) criteria.get(UsuarioCriteria.USUARIOS_NAOANALISADOS);
        if (usuarioNaoAnalisados != null) {
            sql += " and status =0 and deletado =0";
        }
         String usuarioAtivo = (String) criteria.get(UsuarioCriteria.USUARIOS_ATIVOS);
        if (usuarioAtivo != null ) {
            sql += " and (ativo <>0 or ativo is null)";
        }
        return sql;
    }

    public List<Usuario> usuarioNaoCadastrado(Connection conn, Map<Long, Object> criteria) throws SQLException, Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "SELECT id, cpf, nome, email, senha, tipo, deletado, status,data_nascimento,usuario_fk FROM usuario where 1=1";
        sql += applyCriteria(criteria);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Usuario usuario = null;
        while (rs.next()) {
            if (rs.getInt("status") == 0) {
                if (rs.getInt("deletado") == 0) {
                    usuario = new Usuario();
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setData_nascimento(rs.getString("data_nascimento"));
                    usuario.setDelete(rs.getInt("deletado"));
                    usuario.setStatus(rs.getInt("status"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuarioList.add(usuario);
                }

            }
        }
        // trocar o if por um where ja no sql 
        return usuarioList;
    }

    public void createUsuarioPlano(Connection conn, Usuario entity) throws SQLException {
        Usuario usuario = readyUsuarioPlano(conn, entity);
        if (usuario == null) {
            String sql = "INSERT INTO usuario_plano(usuario_fk, plano_fk, data_inicio,ativo) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            int i = 0;
            ps.setLong(++i, entity.getId());
            ps.setLong(++i, entity.getPlano().getId());
            Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(++i, dataDeHoje);
            ps.setInt(++i, entity.getPlano().getAtivo());
            deleteUsuarioPlano(conn, entity.getId());
            ps.execute();
            ps.close();
        }else{
            updatePlanoUsuario(conn, entity);
        }
        

    }

    public void deleteUsuarioPlano(Connection conn, Long id) throws SQLException {
        String sql = "UPDATE usuario_plano SET data_termino=?, ativo=? WHERE usuario_fk =? and ativo =1;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        ps.setTimestamp(++i, dataDeHoje);
        ps.setInt(++i, 0);
        ps.setLong(++i, id);
        ps.execute();
        ps.close();
    }

    private Usuario readyUsuarioPlano(Connection conn, Usuario entity) throws SQLException {
        String sql = "SELECT usuario_fk, plano_fk, data_inicio, data_termino, ativo FROM usuario_plano  where usuario_fk = ? and plano_fk =?";
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setLong(1, entity.getId());
        ps.setLong(2, entity.getPlano().getId());
        ResultSet rs = ps.executeQuery();
        Usuario usuario = null;
        if (rs.next()) {
            usuario = new Usuario();
        }
        rs.close();
        ps.close();
        return usuario;
    }
    private void updatePlanoUsuario(Connection conn, Usuario entity) throws SQLException{
        String sql = "UPDATE usuario_plano SET data_inicio=?, ativo=? WHERE plano_fk =? and usuario_fk =?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        ps.setTimestamp(1, dataDeHoje);
        ps.setInt(2, 1);
        ps.setLong(3, entity.getPlano().getId());
        ps.setLong(4, entity.getId());
        deleteUsuarioPlano(conn, entity.getId());
        ps.execute();
        ps.close();
               
    }
}
