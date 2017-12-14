
package controlando.model.entity;

import controlando.model.base.BaseEntity;


public class Site extends BaseEntity implements Comparable<Site>{
    private String nome;
    private String descricao;
    private String url;
    private Categoria categoria;
    private Restricao restricao;
    private String qtd;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Restricao getRestricao() {
        return restricao;
    }

    public void setRestricao(Restricao restricao) {
        this.restricao = restricao;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    @Override
    public int compareTo(Site o) {
        if(Integer.parseInt(this.qtd) > Integer.parseInt(o.getQtd())){
            return -1;
        }
         if(Integer.parseInt(this.qtd) < Integer.parseInt(o.getQtd())){
            return 1;
        }
         return 0;
    }

    
}
