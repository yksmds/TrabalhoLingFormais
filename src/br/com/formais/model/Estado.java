package br.com.formais.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Estado implements Serializable{

   private static final long serialVersionUID=1L;
   
   private Integer id;
   private String estado;
   private String estadoTransicao;
   private List<String> estadosTransicoes;
   private Map<String,String> transicao;
   
   public Estado(){
   }
   
   public Estado(String estado, Map<String, String> transicao) {
	  this.estado = estado;
	  this.transicao = transicao;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado=estado;
   }
     
   public Map<String, String> getTransicao() {
      return transicao;
   }
   
   public void setTransicao(Map<String, String> transicao) {
      this.transicao=transicao;
   }

   public String transicaoForLetra(String caracter) {
	  return transicao.get(caracter);
   }
   
   public String getEstadoTransicao() {
      return estadoTransicao;
   }

   public void setEstadoTransicao(String estadoTransicao) {
      this.estadoTransicao=estadoTransicao;
   }
   
   public List<String> getEstadosTransicoes() {
	  if (estadosTransicoes == null){
		 estadosTransicoes = new ArrayList<String>();
	  }
      return estadosTransicoes;
   }

   public void setEstadosTransicoes(List<String> estadosTransicoes) {
      this.estadosTransicoes=estadosTransicoes;
   }

   public void setId(Integer id) {
      this.id=id;
   }

   public Integer getId() {
	  return id;
   }
}
