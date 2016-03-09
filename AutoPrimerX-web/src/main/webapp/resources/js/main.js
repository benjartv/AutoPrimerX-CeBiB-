
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

document.addEventListener("DOMContentLoaded", function(event) {
    var codons = document.getElementsByClassName("codons");
    if(codons !== null){
        var resultado = document.getElementById("result");
        for(i = 0; i < codons.length; i++){
            var node = document.createElement('span');
            var text = document.createTextNode(codons[i].innerHTML);
            node.appendChild(text);
            node.setAttribute('id', 'result'+i);
            node.setAttribute('class', 'hidden');
            resultado.appendChild(node);                      
        }
    }
});

function showSeq(obj){
    var parent = obj.parentNode.parentNode;
    var child = parent.childNodes;
    var spans = [];
    for(i=0; i<child.length; i++){ 
        if(child[i].nodeType !== 3 && child[i].getAttribute('id') !== null && child[i].getAttribute('id') !== obj.getAttribute('id') && child[i].getAttribute('id').substring(0,6).localeCompare('codon')){ 
            spans.push(child[i]);
        }
    }
    var id = obj.getAttribute('id');
    id = id.substring(6, id.length);
    var hiddenIds = [];
    for(i=0; i<spans.length; i++){
        if(spans[i].getAttribute('id').localeCompare("codon"+id)){
            var aux = spans[i].getAttribute('id');
            //aux = aux.substring(5, aux.length);
            hiddenIds.push(aux.substring(5, aux.length));
        }
    }
    
    document.getElementById('result'+id).className = "";
    
    for(i=0; i<hiddenIds.length; i++){
        document.getElementById('result'+hiddenIds[i]).className = "hidden";
    }
    console.log(hiddenIds);
    
}

function getNucleotideSeq(element){
    var aminoacidSeq = element.innerHTML.replace(/\s+/g, '');
    var secuencias = [];
    secuencias.push("");
    for(i = 0; i < aminoacidSeq.length; i++){
        switch(aminoacidSeq.charAt(i)){
            case "A":
                for(j = 0; j < secuencias.length; j++){
                    secuencias = secuencias.concat("A");
                }
                break;
            case "C":
                for(j = 0; j < secuencias.length; j++){
                    secuencias = secuencias.concat("C");
                }
                break;
            case "G":
                for(j = 0; j < secuencias.length; j++){
                    secuencias = secuencias.concat("G");
                }
                break;
            case "T":
                for(j = 0; j < secuencias.length; j++){
                    secuencias = secuencias.concat("T");
                }
                break;
            case "R":
                largo = secuencias.length;
                for(k = 0; k < largo; i++){
                    secuencias.push(secuencias[k]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < secuencias.length/2){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("G");
                    }
                }
                break;
            case "Y":
                largo = secuencias.length;
                for(k = 0; k < largo; i++){
                    secuencias.push(secuencias[k]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < secuencias.length/2){
                        secuencias[j] = secuencias[j].concat("C");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("T");
                    }
                }
                break;
            case "S":
                largo = secuencias.length;
                for(k = 0; k < largo; i++){
                    secuencias.push(secuencias[k]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < secuencias.length/2){
                        secuencias[j] = secuencias[j].concat("G");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("C");
                    }
                }
                break;
            case "W":
                largo = secuencias.length;
                for(k = 0; k < largo; i++){
                    secuencias.push(secuencias[k]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < secuencias.length/2){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("T");
                    }
                }
                break;
            case "K":
                largo = secuencias.length;
                for(k = 0; k < largo; i++){
                    secuencias.push(secuencias[k]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < secuencias.length/2){
                        secuencias[j] = secuencias[j].concat("G");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("T");
                    }
                }
                break;
            case "M":
                largo = secuencias.length;
                for(k = 0; k < largo; i++){
                    secuencias.push(secuencias[k]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < secuencias.length/2){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("C");
                    }
                }
                break;
            case "B":
                largo = secuencias.length;
                for(k = 0; k < largo*2; i++){
                    secuencias.push(secuencias[k%largo]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < largo){
                        secuencias[j] = secuencias[j].concat("C");
                    }
                    else if(j < largo * 2){
                        secuencias[j] = secuencias[j].concat("G");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("T");
                    }
                }
                break;
            case "D":
                largo = secuencias.length;
                for(k = 0; k < largo*2; i++){
                    secuencias.push(secuencias[k%largo]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < largo){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else if(j < largo * 2){
                        secuencias[j] = secuencias[j].concat("G");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("T");
                    }
                }
                break;
            case "H":
                largo = secuencias.length;
                for(k = 0; k < largo*2; i++){
                    secuencias.push(secuencias[k%largo]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < largo){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else if(j < largo * 2){
                        secuencias[j] = secuencias[j].concat("C");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("T");
                    }
                }
                break;
            case "V":
                largo = secuencias.length;
                for(k = 0; k < largo*2; i++){
                    secuencias.push(secuencias[k%largo]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < largo){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else if(j < largo * 2){
                        secuencias[j] = secuencias[j].concat("C");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("G");
                    }
                }
                break;
            case "N":
                largo = secuencias.length;
                for(k = 0; k < largo*3; i++){
                    secuencias.push(secuencias[k%largo]);
                }
                j = 0;
                for(j = 0; j < secuencias.length; j++){
                    if(j < largo){
                        secuencias[j] = secuencias[j].concat("A");
                    }
                    else if(j < largo * 2){
                        secuencias[j] = secuencias[j].concat("C");
                    }
                    else if(j < largo * 3){
                        secuencias[j] = secuencias[j].concat("T");
                    }
                    else{
                        secuencias[j] = secuencias[j].concat("G");
                    }
                }
                break;
            default:
                break;
        }
    }
    
    var list = document.createElement("ul");
    for (i = 0; i < secuencias.length; i++){
        var item = document.createElement("li");
        item.appendChild(document.createTextNode(secuencias[i]));
        list.appendChild(item);
    }
    
    var num = element.getAttribute('id').replace("link", "");
    var body = document.getElementById("body".concat(num));
    body.appendChild(list);
    this.removeAttribute('onclick');
}
/*
document.addEventListener("DOMContentLoaded", function() {    
    var observer = new MutationObserver(function(mutations){
        var links = document.getElementsByClassName("link-seq");
        for(i = 0; i < links.length; i++){
            links[i].onclick = function(){
                console.log(links[i]);
                getNucleotideSeq(links[i]);
            };
        }
        mutations.forEach(function(mutation) {
            console.log(mutation.type);
          }); 
    });
    console.log("Hola");
    var target = document.getElementById("accordion");
    var config = {childList: true, subtree: true};
    observer.observe(target, config);
});*/

