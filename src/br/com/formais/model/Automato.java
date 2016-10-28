package br.com.formais.model;

import java.util.ArrayList;
import java.util.List;

public class Automato{
   
   Integer id;
   
   
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id=id;
   }
   List<String> estados;
   List<String> variaveis;
   List<String> transicao;
   List<Transicao> transicoes;
   
   private String estado;
   private String letra;
   
   public String getEstado() {
      return estado;
   }
   public void setEstado(String estado) {
      this.estado=estado;
   }
   public String getLetra() {
      return letra;
   }
   public void setLetra(String letra) {
      this.letra=letra;
   }
   public List<String> getEstados() {
      return estados;
   }
   public void setEstados(List<String> estados) {
      this.estados=estados;
   }
   public List<String> getVariaveis() {
	  if(variaveis == null){
		 variaveis = new ArrayList<String>();
	  }
      return variaveis;
   }
   public void setVariaveis(List<String> variaveis) {
      this.variaveis=variaveis;
   }
   public List<Transicao> getTransicoes() {
	  if(transicoes == null)
		 transicoes = new ArrayList<Transicao>();
      return transicoes;
   }
   public void setTransicoes(List<Transicao> transicoes) {
      this.transicoes=transicoes;
   }
   
   
}
