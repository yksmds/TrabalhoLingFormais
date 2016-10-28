package br.com.formais.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.formais.model.Estado;


public class ValidarAutomato{
   List<Estado> estadosList;
      
   public List<Estado> getEstadosList() {
	  if(estadosList == null)
		 estadosList = new ArrayList<Estado>();
      return estadosList;
   }

   public void setEstadosList(List<Estado> estadosList) {
      this.estadosList=estadosList;
   }

   public String estendida(String estadoInicial, String palavra) {
	  
	  if(palavra.length() == 0) {
		 return estadoInicial;
	  }
	  
	  int p=palavra.length() - 1;
	  char a=palavra.charAt(p);
	  
	  String x = String.valueOf(a);
	  
	  palavra=palavra.substring(0 , p);
	  
	  return transicao(estendida(estadoInicial, palavra), x, estadosList);
   }

   private String transicao(String estado, String a, List<Estado> estados) {
	  String estadoTransicao=null;
	  boolean op = false;
	  for(Estado e : estados){
		 if(e.getEstado().equals(estado)){
			for (String aux : e.getEstadosTransicoes()){
			   
			    Set<String> keys = e.getTransicao().keySet();
			    String[] arreyKeys = keys.toArray(new String[keys.size()]);
			    
			    for (int i=0; i < arreyKeys.length; i++){
			       if (a.equals(arreyKeys[i])){
			    	  aux = e.getTransicao().get(arreyKeys[i]);
			    	  estadoTransicao = aux;
			    	  op = true;
			    	  break;
			       }
			    }
			    if(op)
			       break;
			}
		 }else if(op){
			break;
		 }
	  }
	  return estadoTransicao;
   }
     
}
