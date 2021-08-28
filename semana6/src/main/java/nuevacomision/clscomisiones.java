/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevacomision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import modelo.clsmodelovendedor;

/**
 *
 * @author vh367
 */
public class clscomisiones {
       
             public void menu(){
                 String opc="";
                 do{
                    
                     System.out.println("1-ingrese informacion");
                     System.out.println("2-ver informacion");
                     System.out.println("3-sumar enero");
                     System.out.println("|me quiero salir");
                     opc=t.nextLine();
                     
                     switch(opc){
                         case "1": ingreso_informacion();
          
                         break;
                         case "2":
                         break;
                         case "3":
                         break;
                     }
                 }while(!opc.equals("4"));
             }
    }       
