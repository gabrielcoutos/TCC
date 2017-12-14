package controlando.model.dao;

import controlando.model.base.BaseDAO;
import controlando.model.criteria.SiteCriteria;
import controlando.model.entity.Categoria;
import controlando.model.entity.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SiteDAO implements BaseDAO<Site> {

    @Override
    public void create(Connection conn, Site entity) throws Exception {
        String sql = "INSERT INTO site(nome, descricao, url, categoria_fk)  VALUES ( ?, ?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ps.setString(++i, entity.getUrl());
        ps.setLong(++i, entity.getCategoria().getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }       
        rs.close();
        ps.close();
    }

    @Override
    public Site readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT site.*,categoria.nome nome_categoria FROM site inner join categoria on categoria.id = site.categoria_fk where site.id =?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Site site = new Site();
        if(rs.next()) {           
            site.setId(id);
            site.setNome(rs.getString("nome"));
            site.setDescricao(rs.getString("descricao"));
            site.setUrl(rs.getString("url"));
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("categoria_fk"));
            categoria.setNome(rs.getString("nome_categoria"));                       
            site.setCategoria(categoria);
        }
        return site;
    }

    @Override
    public List<Site> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select site.id id_site , site.nome nome_site,site.descricao descricao_site,site.url url,categoria.nome nome_cat from  site  left join categoria on categoria.id = site.categoria_fk where 1=1  ";
        sql += applyCriteria(criteria);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Site site = null;
        Categoria cat = null;
        List<Site> siteList = new ArrayList<>();
        while (rs.next()) {
            site = new Site();
            site.setNome(rs.getString("nome_site"));
            site.setUrl(rs.getString("url"));
            site.setDescricao(rs.getString("descricao_site"));
            site.setId(rs.getLong("id_site"));
            cat = new Categoria();
            cat.setNome(rs.getString("nome_cat"));
            site.setCategoria(cat);
            siteList.add(site);
        }
        rs.close();
        ps.close();
        return siteList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Site entity) throws Exception {
        String sql = "UPDATE site SET  nome=?, descricao=?, url=?, categoria_fk =? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getDescricao());
        ps.setString(++i, entity.getUrl());
        ps.setLong(++i, entity.getCategoria().getId());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();

    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM site WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql ="";
        String site = (String) criteria.get(SiteCriteria.SITE_ILIKE);
        if(site !=null && !site.isEmpty()){
            sql+="AND site.nome ILIKE '%"+ site+"%' or categoria.nome ILIKE '%"+site+"%'";
        }
        sql+= " order by nome_cat , nome_site";
        return sql;
    }
    

}
