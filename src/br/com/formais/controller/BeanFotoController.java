
package br.com.formais.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.primefaces.model.UploadedFile;

@ManagedBean(name="beanFoto")
@ViewScoped
public class BeanFotoController implements Serializable{
   private static final long serialVersionUID=1696931312751655126L;

   private UploadedFile file;

   public UploadedFile getFile() {
	  return file;
   }

   public void setFile(UploadedFile file) {
	  this.file=file;
   }
   
   public String caminhoImagem (){

		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		 
		 String contexto = scontext.getRealPath("")+"\\resources\\images\\";
		 return contexto;
   }

   public void vpReducao() {
	  try {
		 
		 String contexto = caminhoImagem();
		 
		 BufferedImage imagemOriginal=ImageIO.read(new File(contexto + "animal.png"));
		 System.out.println(imagemOriginal);
		 int w=imagemOriginal.getWidth();
		 int h=imagemOriginal.getHeight();
		 
		 int wNew=w / 2;
		 int hNew=h / 2;
		 
		 BufferedImage newImage=new BufferedImage(wNew, hNew, BufferedImage.TYPE_INT_RGB);
		 
		 int contC=0;
		 int contL=0;
		 
		 for(int lin=0; lin < h; lin+=2) {
			for(int col=0; col < w; col+=2) {
			   try {
				  newImage.setRGB(col - contC , lin - contL , imagemOriginal.getRGB(col , lin));
				  contC++;
			   }catch (Exception e) {
				  System.out.println("Col: " + col + "Linha: " + lin);
			   }
			}
			contL++;
			contC = 0;
		 }
		 
		 ImageIO.write(newImage , "PNG" , new File(contexto + "vpReducao.png"));
	  
	  } catch (IOException e) {
		 e.printStackTrace();
	  }
   }
   
   public void vpAmpliacao() {
	  try {
		 String contexto = caminhoImagem();
		 BufferedImage imagemOriginal=ImageIO.read(new File(contexto + "cachorro2.png"));
		 
		 int w=imagemOriginal.getWidth();
		 int h=imagemOriginal.getHeight();
		 
		 int wNew= w*2;
		 int hNew= h*2;
		 
		 BufferedImage newImage=new BufferedImage(wNew, hNew, BufferedImage.TYPE_INT_RGB);
		 
		 int contC=0;
		 int contL=0;
		 
		 for(int lin=0; lin < hNew; lin+=2) {
			for(int col=0; col < wNew; col+=2) {
			   try {
				  newImage.setRGB(col, lin, imagemOriginal.getRGB(col - contC , lin - contL));
				  newImage.setRGB(col+1, lin, imagemOriginal.getRGB(col - contC , lin - contL));
				  newImage.setRGB(col, lin+1, imagemOriginal.getRGB(col - contC , lin - contL));
				  newImage.setRGB(col+1, lin+1, imagemOriginal.getRGB(col - contC , lin - contL));
				  contC++;
			   }catch (Exception e) {
				  System.out.println("Col: " + col + "Linha: " + lin);
			   }
			}
			contL++;
			contC = 0;
		 }
		 
		 ImageIO.write(newImage , "PNG" , new File(contexto + "vpAmpliacao.png"));
	  
	  } catch (IOException e) {
		 e.printStackTrace();
	  }
   }
   
   public void bilinearReducao() {
	  try {
		 String contexto = caminhoImagem();
		 BufferedImage imagemOriginal=ImageIO.read(new File(contexto + "cachorro1.png"));
		 
		 int w=imagemOriginal.getWidth();
		 int h=imagemOriginal.getHeight();
		 
		 int wNew= w/2;
		 int hNew= h/2;
		 
		 BufferedImage newImage=new BufferedImage(wNew, hNew, BufferedImage.TYPE_INT_RGB);
		 
		 int contC=0;
		 int contL=0;
		 
		 for(int lin=0; lin < h; lin+=2) {
			for(int col=0; col < w; col+=2) {
			   try {
				  
				  int rgb1 = imagemOriginal.getRGB(col, lin);      
				  int rgb2 = imagemOriginal.getRGB(col+1, lin);      
				  int rgb3 = imagemOriginal.getRGB(col, lin+1);      
				  int rgb4 = imagemOriginal.getRGB(col+1, lin+1);  

				  int pixel = mediaDoPixel(rgb1 , rgb2 , rgb3 , rgb4);
				  
				  newImage.setRGB(col - contC, lin - contL, pixel);
				  contC++;
			   }catch (Exception e) {
				  System.out.println("Col: " + col + "Linha: " + lin);
			   }
			}
			contL++;
			contC = 0;
		 }
		 
		 ImageIO.write(newImage , "PNG" , new File(contexto + "bilinearReducao.png"));
	  
	  } catch (IOException e) {
		 e.printStackTrace();
	  }
   }
   
   int mediaDoPixel(int a, int b, int c, int d) {
	  
	  /* DESLOCAMENTO DE BITS PARA PEGAR EXTRAIR 
	   * O RED, GREEN E BLUE DO PIXEL
	   * (MÉDIA DE 4 PIXELS)
	   */
	  
	  int bluea = a & 0xFF;
	  int grena = (a >> 8) & 0xFF; 
	  int reda = (a >> 16) & 0xFF; 

	  int blueb = b & 0xFF;
	  int grenb = (b >> 8) & 0xFF; 
	  int redb = (b >> 16) & 0xFF; 
	  
	  int bluec = c & 0xFF;
	  int grenc = (c >> 8) & 0xFF; 
	  int redc = (c >> 16) & 0xFF; 
	  
	  int blued = d & 0xFF;
	  int grend = (d >> 8) & 0xFF; 
	  int redd = (d >> 16) & 0xFF; 
	  
	  int mediaRed = (reda + redb + redc + redd) / 4;
	  int mediaGreen = (grena + grenb + grenc + grend) /4;
	  int mediaBlue = (bluea + bluec + blueb + blued) / 4;
	  
	  Color media = new Color(mediaRed , mediaGreen , mediaBlue);
	  return media.getRGB();
   }
   
int mediaDoisPixel(int a, int b) {
   
   	/* DESLOCAMENTO DE BITS PARA PEGAR EXTRAIR 
   	 * O RED, GREEN E BLUE DO PIXEL
   	 * (MÉDIA DE DOIS PIXELS
   	 */
	  
	  int bluea = a & 0xFF;
	  int grena = (a >> 8) & 0xFF; 
	  int reda = (a >> 16) & 0xFF; 

	  int blueb = b & 0xFF;
	  int grenb = (b >> 8) & 0xFF; 
	  int redb = (b >> 16) & 0xFF; 
	  
	  int mediaRed = (reda + redb) / 2;
	  int mediaGreen = (grena + grenb) /2;
	  int mediaBlue = (bluea + blueb) / 2;
	  
	  Color media = new Color(mediaRed , mediaGreen , mediaBlue);
	  return media.getRGB();
   }
   
   public void bilinearAmpliacao() {
	  try {
		 String contexto = caminhoImagem();
		 BufferedImage imagemOriginal=ImageIO.read(new File(contexto + "leao.jpg"));
		 
		 int w=imagemOriginal.getWidth();
		 int h=imagemOriginal.getHeight();
		 
		 int wNew = w*2;
		 int hNew = h*2;
		 
		 BufferedImage newImage=new BufferedImage(wNew, hNew, BufferedImage.TYPE_INT_RGB);
		 
		 int contC=0;
		 int contL=0;
		 
		 for(int lin=0; lin < hNew; lin+=2) {
			for(int col=0; col < wNew; col+=2) {
			   try {
				  newImage.setRGB(col, lin, imagemOriginal.getRGB(col - contC , lin - contL));
				  contC++;
			   }catch (Exception e) {
				  System.out.println("Col: " + col + "Linha: " + lin);
			   }
			}
			contL++;
			contC = 0;
		 }
		 
		 contC = 0;
		 contL = 0;
		 
		 for(int lin=0; lin < hNew; lin+=2) {
			for(int col=0; col < wNew; col+=2) {
			   try {
				  
				  int cor1 = newImage.getRGB(col , lin);
				  int cor2;
				  int cor3;
				  int cor4;
				  
				  int medCor1Cor2 = 1, medCor1Cor3 = 1, med4cores = 1;

				  if (col+2 == wNew || lin+2 == hNew)
				  {
					 if (col+2 == wNew) {
						System.out.println("CHEGANDO NA BORDA (coluna)... : " + col);

						cor3 = newImage.getRGB(col , lin+2);
						medCor1Cor3 = mediaDoisPixel(cor1 , cor3);
						newImage.setRGB(col, lin+1 , medCor1Cor3);
						newImage.setRGB(col+1, lin , 1);	// ADICIONANDO BORDA BRANCA NA ULTIMA COLUNA
						newImage.setRGB(col+1, lin+1 , 0);	// ADICIONANDO BORDA BRANCA NA ULTIMA COLUNA
					
					 }else if (lin+2 == hNew) {
						System.out.println("CHEGANDO NA BORDA (linha)... : " + lin);
						
						cor2 = newImage.getRGB(col+2 , lin);
						medCor1Cor2 = mediaDoisPixel(cor1 , cor2);
						newImage.setRGB(col+1, lin , medCor1Cor2);
						newImage.setRGB(col, lin+1 , 0);	 // ADICIONANDO BORDA BRANCA NA ULTIMA LINHA	
						newImage.setRGB(col+1, lin+1 , 0);	 // ADICIONANDO BORDA BRANCA NA ULTIMA LINHA
					 }
					 else {
						System.out.println("FIM!!!");
					 }
				  }
				  else {
					 cor2 = newImage.getRGB(col+2 , lin);
					 cor3 = newImage.getRGB(col , lin+2);
					 cor4 = newImage.getRGB(col+2 , lin+2);
					 
					 medCor1Cor2 = mediaDoisPixel(cor1 , cor2);
					 medCor1Cor3 = mediaDoisPixel(cor1 , cor3);
					 med4cores = mediaDoPixel(cor1 , cor2, cor3, cor4);
					 
					 newImage.setRGB(col+1, lin , medCor1Cor2);
					 newImage.setRGB(col, lin+1 , medCor1Cor3);
					 newImage.setRGB(col+1, lin+1 , med4cores);
				  }
				  
				  contC++;
			   }catch (Exception e) {
				  System.out.println("Col: " + col + "Linha: " + lin);
			   }
			}
			contL++;
			contC = 0;
		 }
		 
		 ImageIO.write(newImage , "JPG" , new File(contexto + "bilinearReducao.jpg"));
	  
	  } catch (IOException e) {
		 e.printStackTrace();
	  }
   }
   
}
