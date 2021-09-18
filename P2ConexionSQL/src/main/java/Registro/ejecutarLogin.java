/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import dominio.clsusuario;
import interEmpleados.loginEmpleados;

/**
 *
 * @author vh367
 */
public class ejecutarLogin {
    public static void main (String[]args){//metodo para poder ejecutar nuestro login
       loginEmpleados registrar=new loginEmpleados();
       registrar.setVisible(true);
    }
}
