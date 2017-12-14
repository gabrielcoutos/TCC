package controlando.model.dao;

import controlando.model.base.BaseDAO;
import controlando.model.base.BaseEntity;
import controlando.model.criteria.CategoriaCriteria;
import controlando.model.entity.Categoria;
import controlando.model.entity.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoriaDAO implements BaseDAO<Categoria> {

    @Override
    public void create(Connection conn, Categoria entity) throws Exception {
        String sql = "INSERT INTO categoria(nome, descricao) VALUES (?, ?) returning id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Categoria readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM categoria where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Categoria categoria = null;
        if (rs.next()) {
            categoria = new Categoria();
            categoria.setNome(rs.getString("nome"));
            categoria.setDescricao(rs.getString("descricao"));
            categoria.setId(id);       
        }
        rs.close();
        ps.close();

        return categoria;
    }

    @Override
    public List<Categoria> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from  categoria  where 1=1 ";
        sql += applyCriteria(criteria);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Categoria categoria;
        List<Categoria> categoriaList = new ArrayList<>();
        while (rs.next()) {
            categoria = new Categoria();
            categoria.setNome(rs.getString("nome"));
            categoria.setDescricao(rs.getString("descricao"));
            categoria.setId(rs.getLong("id"));
            categoriaList.add(categoria);
        }
        rs.close();
        ps.close();
        return categoriaList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "select count(*) count from  categoria  where 1=1;";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Long count = null;
        if (rs.next()) {
            count = rs.getLong("count");
        }
        rs.close();
        s.close();
        return count;
    }

    @Override
    public void update(Connection conn, Categoria entity) throws Exception {
        String sql = "UPDATE categoria SET  nome=?, descricao=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM categoria WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = "";
        String categoriaIlike = (String) criteria.get(CategoriaCriteria.CATEGORIA_ILIKE);
        if (categoriaIlike != null && !categoriaIlike.isEmpty()) {
            sql += " AND nome ILIKE '%" + categoriaIlike + "%'";
        }

        return sql;
    }

}
