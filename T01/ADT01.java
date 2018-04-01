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
public class ADT01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Figura mifigura = new Figura();
        
        mifigura.pintarTrianguloR('*', 10);        
        
        mifigura.pintarTrianguloE('0', 10);
        
        mifigura.pintarCuadrado('*', 10);
        
        mifigura.pintarRectangulo('0', 15,10);
        
        mifigura.pintarRombo('R',7);
        
        mifigura.pintarTrapecio('A',7);
        
        mifigura.pintarHexagono('R', 5);
        
        mifigura.pintarOctagono('O',5);
        
    }
}
