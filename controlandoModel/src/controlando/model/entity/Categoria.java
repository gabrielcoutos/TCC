
package controlando.model.entity;

import controlando.model.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;


public class Categoria extends BaseEntity {
    private String nome;
    private String descricao;
    private List<Site> siteList;

    public Categoria(){
        this.siteList= new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }
    
}
