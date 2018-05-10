package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FabricanteDAO;
import br.edu.ifsul.modelo.Fabricante;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleFabricante")
@SessionScoped
public class ControleFabricante implements Serializable {
    private FabricanteDAO dao;
    private Fabricante objeto;

    public ControleFabricante() {
        dao = new FabricanteDAO();
    }
    
    public String listar() {
        return "/privado/fabricante/listar?faces-redirect=true";
    }
    
    public String novo() {
        objeto = new Fabricante();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar() {
        if(dao.salvar(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public String editar(Integer id) {
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public FabricanteDAO getDao() {
        return dao;
    }

    public void setDao(FabricanteDAO dao) {
        this.dao = dao;
    }

    public Fabricante getObjeto() {
        return objeto;
    }

    public void setObjeto(Fabricante objeto) {
        this.objeto = objeto;
    }
}