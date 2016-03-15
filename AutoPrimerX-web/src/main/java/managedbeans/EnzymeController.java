package managedbeans;

import entities.Enzyme;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionBeans.EnzymeFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("enzymeController")
@SessionScoped
public class EnzymeController implements Serializable {

    @EJB
    private EnzymeFacadeLocal ejbFacade;
    private List<Enzyme> items = null;
    private Enzyme selected;

    public EnzymeController() {
    }

    public Enzyme getSelected() {
        return selected;
    }

    public void setSelected(Enzyme selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EnzymeFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Enzyme prepareCreate() {
        selected = new Enzyme();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EnzymeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EnzymeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EnzymeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Enzyme> getItems() {
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
    
    private void persist2(PersistAction persistAction, String successMessage, Enzyme sel) {
        if (sel != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(sel);
                } else {
                    getFacade().remove(sel);
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

    public Enzyme getEnzyme(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Enzyme> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Enzyme> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void combEnzyme(){
        String name = selected.getNameEnzyme();
        int base = selected.getNumBasePair();
        String entrada = selected.getDnaTarget();
        entrada = entrada.replace("/","");
        entrada = entrada.replace(" ","");
        entrada = entrada.toUpperCase();
        //System.out.println("Entrada limpia:"+entrada);
        int largo =  entrada.length();
        ArrayList<String> arreglo = new ArrayList<>();
        arreglo.add(entrada);
        ArrayList<String> arreglo2 = new ArrayList<>();
        for (int i=0; i < largo; i++){
            
            for (int j=0; j < arreglo.size();j++){
                String temporal = arreglo.get(j);
                if (temporal.charAt(i)=='R'){
                    //System.out.println(temporal.substring(0,i)+"(R)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='H'){
                    //System.out.println(temporal.substring(0,i)+"(H)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='M'){
                    //System.out.println(temporal.substring(0,i)+"(M)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='N'){
                    //System.out.println(temporal.substring(0,i)+"(N)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));                    
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='Y'){
                    //System.out.println(temporal.substring(0,i)+"(Y)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='K'){
                    //System.out.println(temporal.substring(0,i)+"(K)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='V'){
                    //System.out.println(temporal.substring(0,i)+"(V)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='S'){
                    //System.out.println(temporal.substring(0,i)+"(S)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='B'){
                    //System.out.println(temporal.substring(0,i)+"(B)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"C"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='W'){
                    //System.out.println(temporal.substring(0,i)+"(W)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else if(temporal.charAt(i)=='D'){
                    //System.out.println(temporal.substring(0,i)+"(D)"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"A"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"G"+temporal.substring(i+1,largo));
                    arreglo2.add(temporal.substring(0,i)+"T"+temporal.substring(i+1,largo));
                }
                else{
                    arreglo2.add(temporal);
                }
            }
            arreglo.clear();
            arreglo = new ArrayList<>(arreglo2);
            arreglo2.clear();
            
        }
        for (int i = 0; i < arreglo.size(); i++) {
            System.out.println("Creando enzima...");
            Enzyme temp = new Enzyme();
            initializeEmbeddableKey();
            temp.setNameEnzyme(name + " - ver " + i);
            temp.setDnaTarget(arreglo.get(i));
            temp.setNumBasePair(base);
            ejbFacade.create(temp);
            persist2(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EnzymeCreated"), temp);
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
            System.out.println("Enzima creada.");
        }
    }

    @FacesConverter(forClass = Enzyme.class)
    public static class EnzymeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EnzymeController controller = (EnzymeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "enzymeController");
            return controller.getEnzyme(getKey(value));
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
            if (object instanceof Enzyme) {
                Enzyme o = (Enzyme) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Enzyme.class.getName()});
                return null;
            }
        }

    }

}
