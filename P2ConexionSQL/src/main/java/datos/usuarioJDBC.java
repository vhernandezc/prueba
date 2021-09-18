/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.clsusuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vh367
 */
public class usuarioJDBC {
     private  static final String SQL_SELECT="select*from tb_usuarios";
     
     public List<clsusuario> select(){
         Connection conn=null;
         PreparedStatement stmt=null;
         ResultSet rs=null;
         clsusuario usuari=null;
         List<clsusuario> usuarios=new ArrayList<clsusuario>();
         
         try {
             conn=ClsConexion.getConnection();
             stmt=conn.prepareStatement(SQL_SELECT);
             rs=stmt.executeQuery();
             
             while(rs.next()){
                 int id_usuario=rs.getInt("id_usuario");
                 String username=rs.getString("username");
                 String password=rs.getString("password");
                 
                 usuari=new clsusuario();
                  usuari.setId_usuario(id_usuario);
                 usuari.setUsername(username);
                 usuari.setPassword(password);
                
                 
                 usuarios.add(usuari);
             }
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }finally{
             ClsConexion.close(rs);
            ClsConexion.close(stmt);
         ClsConexion.close(conn);
         }
         return usuarios;
     }
      public boolean select_validacion(clsusuario datos){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsusuario usuario = new clsusuario();
        boolean tiene_permiso = false;
        
        try {
            conn = ClsConexion.getConnection();//hacemos la conexion
            String condicion = SQL_SELECT + " where username='"+datos.getUsername()+"'"+" and password='"+datos.getPassword()+"'";
                     
            stmt = conn.prepareStatement(condicion);//y mandamos a llamar a la instrucion select
            rs = stmt.executeQuery();//cuando ejecute el query devuelve un tipo de dato rs
            while(rs.next()){
                tiene_permiso = true;
                int id_usuario = rs.getInt("Id_usuario");//de
                String username = rs.getString("username");
                String password = rs.getString("password");
 
                usuario = new clsusuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
   
                                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexion.close(rs);//mandamos
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return tiene_permiso; 
    
}


}
