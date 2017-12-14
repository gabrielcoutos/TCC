
package controlando.model.entity;

import controlando.model.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;


public class Restricao extends BaseEntity{
    private Integer tempoAcesso;
    private String nome;
    private List<Site> siteList;
    private Plano plano;
    
    public Restricao(){
        this.siteList = new ArrayList<>();
    }

    public Integer getTempoAcesso() {
        return tempoAcesso;
    }

    public void setTempoAcesso(Integer tempoAcesso) {
        this.tempoAcesso = tempoAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

  
}
