/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt01;

/**
 *
 * @author alejandro
 */
public class Figura {
    
    public void pintarTrianguloR(char caracter, int altura) {
        for (int i=0; i<altura; i++) {            
            for (int j=0; j<=i; j++)
                System.out.print(caracter);
            
            System.out.print("\n");
        }
    }
    
    public void pintarTrianguloE(char caracter, int altura) {
        
        for(int i=0; i<altura; i++) {           
            for(int j=0; j<altura-i; j++)
                System.out.print(" ");
            for(int j=0; j<=2*i; j++)
                System.out.print(caracter);
            for(int j=0; j<altura-i; j++)
                System.out.print(" ");
            
            System.out.print("\n");
        }
    }
    
    public void pintarCuadrado(char caracter, int lado) {
        for(int i=0; i<lado; i++) {
            for(int j=0; j<lado; j++)
                System.out.print(" "+caracter);
            
            System.out.print("\n");
        }
    }
    
    public void pintarRectangulo(char caracter, int base, int altura) {
        for(int i=0; i<altura; i++) {
            for(int j=0; j<base; j++)
                System.out.print(" "+caracter);
            
            System.out.print("\n");
        }
    }
    
    public void pintarRombo(char caracter, int altura) {
        int ajuste = (altura*2)-1;
        int buffer = -1;
        
        for(int i=0; i<ajuste; i++) {
            
            buffer = (i<altura)?++buffer:--buffer;
            
            for(int j=0; j<altura-buffer; j++)
                System.out.print(" ");
            for(int j=0; j<=2*buffer; j++)
                System.out.print(caracter);
            for(int j=0; j<altura-buffer; j++)
                System.out.print(" ");
            
            System.out.print("\n");
        }
    }
    
    public void pintarHexagono(char caracter, int altura) {
        int ajuste = (altura*2)-1;
        
        int buffer = -1;
        
        for (int i=0; i<ajuste; i++) {
            
            buffer = (i<altura)?++buffer:--buffer;
            
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                for(int j=0; j<=ajuste + 2*buffer; j++)
                    System.out.print(caracter);
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                
                System.out.print("\n");
        }
    }
    
    public void pintarTrapecio(char caracter, int altura) {
        int ajuste = (altura*2)-1;
        
        for (int i=0; i<altura; i++) {
            
                for(int j=0; j<altura-i; j++)
                    System.out.print(" ");
                for(int j=0; j<=ajuste + 2*i; j++)
                    System.out.print(caracter);
                for(int j=0; j<altura-i; j++)
                    System.out.print(" ");
                
                System.out.print("\n");
        }
    }
    
    public void pintarOctagono(char caracter, int altura) {
        //int mitad = altura
        int ajuste = (altura*2)-1;
        
        int buffer = -1;
        
        for (int i=0; i<altura; i++) {
            buffer++;
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                for(int j=0; j<=ajuste + 2*buffer; j++)
                    System.out.print(caracter);
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                
                System.out.print("\n");
        }
        
        for (int i=0; i<altura-1; i++) {
            //buffer++;
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                for(int j=0; j<=ajuste + 2*buffer; j++)
                    System.out.print(caracter);
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                
                System.out.print("\n");
        }
        
        for (int i=0; i<altura; i++) {
            buffer--;
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                for(int j=0; j<=ajuste + 2*buffer; j++)
                    System.out.print(caracter);
                for(int j=0; j<altura-buffer; j++)
                    System.out.print(" ");
                
                System.out.print("\n");
        }
        
    }
    
}
