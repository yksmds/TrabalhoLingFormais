package br.com.trabalho1;

import java.util.Scanner;

public class Automato01{
   
   public String estendida(String estado, String palavra) {
	  
	  if(palavra.length() == 0) {
		 return estado;
	  }
	  
	  int p=palavra.length() - 1;
	  char a=palavra.charAt(p);
	  palavra=palavra.substring(0 , p);
	  
	  return transicao(estendida(estado , palavra) , a);
   }

   private String transicao(String estado, char a) {
	  if ("q0".equals(estado)){
		 if (a == 'a'){
			return "q1";
		 }
		 else if (a == 'b'){
			return "q2";
		 }
		 else if (a == 'c'){
			return "q3";
		 }			
	  }
	  
	  if ("q1".equals(estado)){
		 if (a == 'a'){
			return "q4";
		 }
		 else if (a == 'b'){
			return "q7";
		 }
		 else if (a == 'c'){
			return "q8";
		 }			
	  }
	  
	  if ("q2".equals(estado)){
		 if (a == 'a'){
			return "q7";
		 }
		 else if (a == 'b'){
			return "q5";
		 }
		 else if (a == 'c'){
			return "q9";
		 }			
	  }
	  
	  if ("q3".equals(estado)){
		 if (a == 'a'){
			return "q8";
		 }
		 else if (a == 'b'){
			return "q9";
		 }
		 else if (a == 'c'){
			return "q6";
		 }			
	  }
	  
	  if ("q4".equals(estado)){
		 if (a == 'a'){
			return "q4";
		 }
		 else if (a == 'b'){
			return "q7";
		 }
		 else if (a == 'c'){
			return "q8";
		 }			
	  }
	  
	  if ("q5".equals(estado)){
		 if (a == 'a'){
			return "q7";
		 }
		 else if (a == 'b'){
			return "q5";
		 }
		 else if (a == 'c'){
			return "q9";
		 }			
	  }
	  
	  if ("q6".equals(estado)){
		 if (a == 'a'){
			return "q8";
		 }
		 else if (a == 'b'){
			return "q9";
		 }
		 else if (a == 'c'){
			return "q6";
		 }			
	  }
	  
	  if ("q7".equals(estado)){
		 if (a == 'a'){
			return "q10";
		 }
		 else if (a == 'b'){
			return "q10";
		 }
		 else if (a == 'c'){
			return "q13";
		 }			
	  }
	  
	  if ("q8".equals(estado)){
		 if (a == 'a'){
			return "q11";
		 }
		 else if (a == 'b'){
			return "q13";
		 }
		 else if (a == 'c'){
			return "q11";
		 }			
	  }
	  
	  if ("q9".equals(estado)){
		 if (a == 'a'){
			return "q13";
		 }
		 else if (a == 'b'){
			return "q12";
		 }
		 else if (a == 'c'){
			return "q12";
		 }			
	  }
	  
	  if ("q10".equals(estado)){
		 if (a == 'a'){
			return "q10";
		 }
		 else if (a == 'b'){
			return "q10";
		 }
		 else if (a == 'c'){
			return "q13";
		 }			
	  }
	  
	  if ("q11".equals(estado)){
		 if (a == 'a'){
			return "q11";
		 }
		 else if (a == 'b'){
			return "q13";
		 }
		 else if (a == 'c'){
			return "q11";
		 }			
	  }
	  
	  if ("q12".equals(estado)){
		 if (a == 'a'){
			return "q13";
		 }
		 else if (a == 'b'){
			return "q12";
		 }
		 else if (a == 'c'){
			return "q12";
		 }			
	  }
	  
	  if ("q13".equals(estado)){
		 if (a == 'a'){
			return "q14";
		 }
		 else if (a == 'b'){
			return "q14";
		 }
		 else if (a == 'c'){
			return "q14";
		 }			
	  }
	  
	  if ("q14".equals(estado)){
		 if (a == 'a'){
			return "q14";
		 }
		 else if (a == 'b'){
			return "q14";
		 }
		 else if (a == 'c'){
			return "q14";
		 }			
	  }
	  return null;
   }
   
   
   public static void menu(){
      System.out.println("\t==================");
      System.out.println("1. Digitar Palavra [a,b,c] ");
      System.out.println("0. Fim");
      System.out.println("Opcao:");
   }

   @SuppressWarnings("resource")
   public static void main(String[] args) {
	  Automato01 aut = new Automato01();
	  String palavra = "";
	  Scanner entrada = new Scanner(System.in);
	  Scanner entrada2 = new Scanner(System.in);
	  int opcao;
	  
	   do{
	      menu();
	      opcao = entrada.nextInt();
	      
	      switch(opcao){
	      case 1:
	    	 System.out.println("Entre com a palavra desejada: ");
	    	 palavra = entrada2.next();
	    	 
	    	 if(!verificarValidacao(palavra)){	    		
	    		System.err.println("Símbolos Permitidos: a,b,c \n");
	    		//menu();
	    	 }
	    	 String estadoFinal = aut.estendida("q0" , palavra);

			 if ("q4".equals(estadoFinal) || "q5".equals(estadoFinal) || "q6".equals(estadoFinal) || "q10".equals(estadoFinal) || "q11".equals(estadoFinal)
				|| "q12".equals(estadoFinal) || "q14".equals(estadoFinal)){

				System.out.println("Palavra Reconhecida \n");
			 }else{
				System.err.println("Palavra Não Reconhecida");
			 }
	          break;
	      case 0:
	    	 System.exit(1);
	    	 break;
	      default:
	          System.out.println("Opção inválida.");
	      }
	  } while(opcao != 0);
	}

   private static Boolean verificarValidacao(String palavra) {
	  for (int i = 0; i < palavra.length(); i++) {
		 if (palavra.charAt(i) != 'a' && palavra.charAt(i) != 'b' && palavra.charAt(i) != 'c'){
			System.out.println(palavra.charAt(i));
			return false;
		 }
	  }
	  return true;
   }
}
