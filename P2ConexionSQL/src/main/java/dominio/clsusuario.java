/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import interEmpleados.loginEmpleados;

/**
 *
 * @author vh367
 */
public class clsusuario {
    private int id_usuario;
    private String username;
    private String password;

    
    public clsusuario(){
        
    }
    public clsusuario(int id_usuario){
        this.id_usuario=id_usuario;
        
    }
    public clsusuario(int id_usuario,String username, String password){
        this.username=username;
        this.password=password;
        this.id_usuario=id_usuario;
         
    }

    @Override
    public String toString() {
        return "usuario{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + '}';
    }
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}