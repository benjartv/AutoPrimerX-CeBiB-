package managedbeans;

import entities.CodonUsage;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionBeans.CodonUsageFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("codonUsageController")
@SessionScoped
public class CodonUsageController implements Serializable {

    @EJB
    private CodonUsageFacadeLocal ejbFacade;
    private List<CodonUsage> items = null;
    private CodonUsage selected;

    public CodonUsageController() {
        
    }
    
    public CodonUsage getSelected() {
        return selected;
    }

    public void setSelected(CodonUsage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CodonUsageFacadeLocal getFacade() {
        return ejbFacade;
    }

    public CodonUsage prepareCreate() {
        selected = new CodonUsage();
        initializeEmbeddableKey();
                
       selected.setUUU_letraAminoacido("F");
       selected.setUCU_letraAminoacido("S");
       selected.setUAU_letraAminoacido("Y");
       selected.setUGU_letraAminoacido("C");
       
       selected.setUUC_letraAminoacido("F");
       selected.setUCC_letraAminoacido("S");
       selected.setUAC_letraAminoacido("Y");
       selected.setUGC_letraAminoacido("C");
       
       selected.setUUA_letraAminoacido("L");
       selected.setUCA_letraAminoacido("S");
       selected.setUAA_letraAminoacido("*");
       selected.setUGA_letraAminoacido("*");
       
       selected.setUUG_letraAminoacido("L");
       selected.setUCG_letraAminoacido("S");
       selected.setUAG_letraAminoacido("*");
       selected.setUGG_letraAminoacido("W");
       
       selected.setCUU_letraAminoacido("L");
       selected.setCCU_letraAminoacido("P");
       selected.setCAU_letraAminoacido("H");
       selected.setCGU_letraAminoacido("R");
       
       selected.setCUC_letraAminoacido("L");
       selected.setCCC_letraAminoacido("P");
       selected.setCAC_letraAminoacido("H");
       selected.setCGC_letraAminoacido("R");
       
       selected.setCUA_letraAminoacido("L");
       selected.setCCA_letraAminoacido("P");
       selected.setCAA_letraAminoacido("Q");
       selected.setCGA_letraAminoacido("R");
       
       selected.setCUG_letraAminoacido("L");
       selected.setCCG_letraAminoacido("P");
       selected.setCAG_letraAminoacido("Q");
       selected.setCGG_letraAminoacido("R");
       
       selected.setAUU_letraAminoacido("I");
       selected.setACU_letraAminoacido("T");
       selected.setAAU_letraAminoacido("N");
       selected.setAGU_letraAminoacido("S");
       
       selected.setAUC_letraAminoacido("I");
       selected.setACC_letraAminoacido("T");
       selected.setAAC_letraAminoacido("N");
       selected.setAGC_letraAminoacido("S");
       
       selected.setAUA_letraAminoacido("I");
       selected.setACA_letraAminoacido("T");
       selected.setAAA_letraAminoacido("K");
       selected.setAGA_letraAminoacido("R");
       
       selected.setAUG_letraAminoacido("M");
       selected.setACG_letraAminoacido("T");
       selected.setAAG_letraAminoacido("K");
       selected.setAGG_letraAminoacido("R");
       
       selected.setGUU_letraAminoacido("V");
       selected.setGCU_letraAminoacido("A");
       selected.setGAU_letraAminoacido("D");
       selected.setGGU_letraAminoacido("G");
       
       selected.setGUC_letraAminoacido("V");
       selected.setGCC_letraAminoacido("A");
       selected.setGAC_letraAminoacido("D");
       selected.setGGC_letraAminoacido("G");
       
       selected.setGUA_letraAminoacido("V");
       selected.setGCA_letraAminoacido("A");
       selected.setGAA_letraAminoacido("E");
       selected.setGGA_letraAminoacido("G");
       
       selected.setGUG_letraAminoacido("V");
       selected.setGCG_letraAminoacido("A");
       selected.setGAG_letraAminoacido("E");
       selected.setGGG_letraAminoacido("G");
        return selected;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CodonUsageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CodonUsageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CodonUsageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CodonUsage> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CodonUsage getCodonUsage(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CodonUsage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CodonUsage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CodonUsage.class)
    public static class CodonUsageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CodonUsageController controller = (CodonUsageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "codonUsageController");
            return controller.getCodonUsage(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CodonUsage) {
                CodonUsage o = (CodonUsage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CodonUsage.class.getName()});
                return null;
            }
        }

    }

}
