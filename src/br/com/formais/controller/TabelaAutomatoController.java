
package br.com.formais.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import br.com.formais.model.Estado;

@ManagedBean(name="tabelaAutomato")
@ViewScoped
public class TabelaAutomatoController implements Serializable{
   private static final long serialVersionUID=1L;

   private String alfabeto;
   private String estado;
   private String palavra;

   private List<String> alfabetos;
   private List<String> estados;
   private List<String> estadosSelecionados;
   private List<Estado> estadosDoAutomato;

   private Estado estadoAutomato;
   

   public Estado getEstadoAutomato() {
	  if (estadoAutomato == null)
		 estadoAutomato = new Estado();
      return estadoAutomato;
   }

   public void setEstadoAutomato(Estado estadoAutomato) {
      this.estadoAutomato=estadoAutomato;
   }

   public List<Estado> getEstadosDoAutomato() {
	  if(estadosDoAutomato == null)
		 estadosDoAutomato=new ArrayList<Estado>();
	  return estadosDoAutomato;
   }

   public void setEstadosDoAutomato(List<Estado> estadosDoAutomato) {
	  this.estadosDoAutomato=estadosDoAutomato;
   }

   public String getAlfabeto() {
	  return alfabeto;
   }

   public void setAlfabeto(String alfabeto) {
	  this.alfabeto=alfabeto;
   }

   public String getEstado() {
	  return estado;
   }

   public void setEstado(String estado) {
	  this.estado=estado;
   }

   public List<String> getAlfabetos() {
	  if(alfabetos == null)
		 alfabetos=new ArrayList<String>();
	  return alfabetos;
   }

   public void setAlfabetos(List<String> alfabetos) {
	  this.alfabetos=alfabetos;
   }

   public List<String> getEstados() {
	  if(estados == null)
		 estados=new ArrayList<String>();
	  return estados;
   }

   public void setEstados(List<String> estados) {
	  this.estados=estados;
   }
   
   public List<String> getEstadosSelecionados() {
	  if(estadosSelecionados == null)
		 estadosSelecionados = new ArrayList<String>();
      return estadosSelecionados;
   }

   public void setEstadosSelecionados(List<String> estadosSelecionados) {
      this.estadosSelecionados=estadosSelecionados;
   }

   public String getPalavra() {
      return palavra;
   }

   public void setPalavra(String palavra) {
      this.palavra=palavra;
   }
   
   public void criarTabela() {
	  String[] alfabetoDividido=getAlfabeto().split(";");
	  for(String s : alfabetoDividido) {
		 getAlfabetos().add(s);
	  }
	  montarTabela();
   }
   
   public void quebraEstado(){
	  String[] estadosDividido=getEstado().split(";");
	  for(String s : estadosDividido) {
		 getEstados().add(s);
	  }
   }
   
   public void limpar(){
	  setEstado("");
	  setEstados(null);
	  setEstadoAutomato(null);
	  setColunaDaImagem(null);
	  setEstadosSelecionados(null);
	  setPalavra("");
	  setAlfabetos(null);
	  setAlfabeto("");
	  setEstadosDoAutomato(null);
   }
   
   /**
    * Este método monta a tabela de forma que cada estado corresponde a uma linha,
    * ou seja, para cada linha é instaciado um Objeto(Estado).
    */
   public void montarTabela() {
	  for(int i=0; i < getEstados().size(); i++) {
		 Estado e=new Estado(getEstados().get(i), addTransicao());
		 for (int j=0; j < alfabetos.size(); j++){
			String q = "q";
			String v = j+"";
			e.getEstadosTransicoes().add(q+v);
		 }
		 getEstadosDoAutomato().add(e);
	  }
   }

   private Map<String, String> addTransicao() {
	  Map<String, String> stats=new LinkedHashMap<String, String>();
	  for(int i=0; i < alfabetos.size(); i++) {
		 String q = "q";
		 String v = i+"";
		 stats.put(alfabetos.get(i) , q+v);
	  }
	  return stats;
   }
   
   /**
    * Retorna número de variáveis que contém o alfabeto.
    * @return
    */
   public int getAlfabetoCount() {
	  return getAlfabetos().size();
   }
   
   /**
    * Método que altera o valor de cada célula.
    * Muda o valor antigo para o valor novo.
    * 
    * @param event
    */
   @SuppressWarnings("unused")
   public void onCellEdit(CellEditEvent event) {
	  Object oldValue=event.getOldValue();
	  Object newValue=event.getNewValue();
	  int linha=event.getRowIndex();
	  
	  
	  if(newValue != null && !newValue.equals(oldValue)) {
		 FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
		 FacesContext.getCurrentInstance().addMessage(null , msg);
	  }
	  int cont = 0;
	  boolean alterou = false;
	  for (Estado aux_estados : getEstadosDoAutomato()){
		 if(linha == cont){ //verifico se a linha (estado) que irei alterar a transicao é o mesmo que o usuário escolheu.
			for(String aux_estadostrans : aux_estados.getEstadosTransicoes()){
			   	
			   Set<String> keys = aux_estados.getTransicao().keySet();
			   String[] arreyKeys = keys.toArray(new String[keys.size()]);
			   
			   for (int i=0; i < arreyKeys.length; i++){
			       if (colunaDaImagem.equals(arreyKeys[i])){
			    	  oldValue = aux_estados.getTransicao().get(arreyKeys[i]);
			    	  aux_estados.getTransicao().put(arreyKeys[i] , (String) newValue);
			    	  alterou = true;
			    	  break;
			       }
			    }
			   getEstadosDoAutomato().get(linha).setTransicao(aux_estados.getTransicao());
			  
			   if(alterou)
				  break;
			 }
		 }
		 else if(alterou){
			break;
		 }cont++;
	  }
   }
      
   public void validarAutomato(){
	  if (palavra == null || "".equals(palavra)){
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
			FacesMessage.SEVERITY_WARN, "Atenção!", "Digite a palavra a ser verificada."));
		 return;
	  }
	  String[] p = palavra.split("");
	  for(int i=0; i < p.length;i++){
		 if(!alfabetos.contains(p[i])){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
			   FacesMessage.SEVERITY_WARN, "Atenção!", "Digite uma palavra válida."));
			return;
		 }
	  }
	  ValidarAutomato automatoValida = new ValidarAutomato();
	  automatoValida.setEstadosList(getEstadosDoAutomato());
	  String resultado = automatoValida.estendida(estados.get(0) , getPalavra());	
	  if(estadosSelecionados.contains(resultado)){
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
			FacesMessage.SEVERITY_INFO, "Info", "PALAVRA ACEITA PELO AUTÔMATO!!!!"));
		 System.out.println("PALAVRA ACEITA PELO AUTÔMATO!!!!");
	  }else{
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
			FacesMessage.SEVERITY_WARN, "ERRO!", "PALAVRA NÃO ACEITA PELO AUTÔMATO."));
		 System.out.println("PALAVRA NÃO ACEITA PELO AUTÔMATO.");
	  }
   }
   
   
   /************************/
   private String colunaDaImagem;

   public void pegaColuna(String d){
	  setColunaDaImagem(d);
   }

   public String getColunaDaImagem() {
	  return colunaDaImagem;
   }

   public void setColunaDaImagem(String colunaDaImagem) {
	  this.colunaDaImagem=colunaDaImagem;
   }
   /******************************************************/

}
