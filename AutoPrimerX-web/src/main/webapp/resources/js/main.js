
document.addEventListener("DOMContentLoaded", function(event) { 
    var conservado = document.getElementById("conservado");
    var identico = document.getElementById("identico");
    var button = document.getElementById("submit");
    var error = document.getElementById("errorMsge");

    conservado.onchange = function(){
        if(parseInt(conservado.value) <= parseInt(identico.value)){
            error.className = "col-md-12 alert alert-danger";
            button.disabled = true;
            button.className = "ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-disabled";
        }
        else{
            error.className = "col-md-12 alert alert-danger hidden";
            button.disabled = false;
            button.className = "ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only";
        }
    };
    
    identico.onchange = function() {
        if(parseInt(conservado.value) <= parseInt(identico.value)){
            error.className = "col-md-12 alert alert-danger";
            button.disabled = true;
            button.className = "ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-disabled";
        }
        else{
            error.className = "col-md-12 alert alert-danger hidden";
            button.disabled = false;
            button.className = "ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only";
        }
    };
});