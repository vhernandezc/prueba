/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.clsEmpleado;
import dominio.clsusuario;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vh367
 */
public class clsEmpleadoJDBC {
     
    Scanner t = new Scanner(System.in);//lee los datos que ingresamos
    Scanner j = new Scanner(System.in);
    Scanner j2 = new Scanner(System.in);
    String r;
    private static final String SQL_SELECT="select*from tb_alumnos";
    private static final String SQL_INSERT="insert into tb_alumnos(empleado, Enero,Febrero,Marzo) values(?,?,?,?)";
    private static final String SQL_UPDATE="update tb_alumnos set empleado=?,Enero=?,Febrero=?,Marzo=? where codigo=?";
    private static final String SQL_DELETE="DELETE FROM tb_alumnos WHERE codigo =?";


    public DefaultTableModel muestrDatosTabla()
    {
        String []  nombresColumnas = {"codigo","empleado","Enero","Febrero","Marzo"};
        String [] datosFilas = new String[5];
        
        DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);// es null porque los datos se ingresan de la tabla en mysql
        
        String sql = "SELECT * FROM tb_alumnos";//indicamos la tabla de Mysql
        
        Connection cn = null;
        
        PreparedStatement pst = null;
        
        ResultSet rs = null;
        
        try
        {
            cn = ClsConexion.getConnection();
            
            pst = cn.prepareStatement(sql);                        
            
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                datosFilas[0] = rs.getString("codigo");//asignamos columna a cada dato
                
               datosFilas[1] = rs.getString("empleado");
                
               datosFilas [2] = rs.getString("Enero");
                 datosFilas[3] = rs.getString("Febrero");
                  datosFilas[4] = rs.getString("Marzo");
                
                modelo.addRow(datosFilas);//enviamos el formato a la tabla
                
            }
            
           
        }
        catch(SQLException e)
        {
            
            JOptionPane.showMessageDialog(null,"Error al conectar");
            
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();//cerramos conexiones
                
                if (pst != null) pst.close();
                
                if (cn != null) cn.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
         return modelo;
    }
    
    
    //METODO PARA INSERTAR DATOS A LA TABLA
  public int insert(clsEmpleado alumno){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, alumno.getEmpleado());
            stmt.setInt(2, alumno.getEnero());
            stmt.setInt(3, alumno.getFebrero());
            stmt.setInt(4, alumno.getMarzo());
         
            
            System.out.println("empleado agregado...");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
      
  
        
        return rows;}
  
  
 
 
  
  public int Update(clsEmpleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_UPDATE);//y mandamos a llamar a la instrucion
            stmt.setDouble(5, empleado.getCodigo());//le mandamos los parametros en el orden
            stmt.setString(1, empleado.getEmpleado());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
       
            
            System.out.println("ejecutando query:" + SQL_UPDATE);//ejecutamos el query con insert
            rows = stmt.executeUpdate();//eje el udate
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);//nos devuelve cuantos re in.
            ClsConexion.close(conn);
        }
        
        return rows;
    }
  
  
  //metodo para eliminar datos
  public int eliminar(clsEmpleado empleado){
      Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empleado.getCodigo());//le decimos que va a recibir un parametro
            
            
            System.out.println("se eliminó el dato:" );
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);// cerramos conexiones
            ClsConexion.close(conn);
        }
        return rows;
  }
  
  //menu principal
 public void menu(){
          boolean salir = false;
        String opc = "";
        do{
            clsEmpleadoJDBC alJDBC=new clsEmpleadoJDBC();
            System.out.println("1- Insertar Informacion");
           
            System.out.println("2- Ver Informacion");
            System.out.println("3- eliminar información");
            System.out.println("4- usuario");
            System.out.println("5- salir");
            opc = t.nextLine();
            switch(opc){
               
                case "1":
               clsEmpleado agregar=new clsEmpleado();
               
                    System.out.println("ingrese empleado:");
                   r= j.nextLine();
                    agregar.setEmpleado(r);//agregamos el nombre en la columna empleado
                   
                    System.out.println("ingrese Enero");
                    r=j.nextLine();
                    agregar.setEnero(Integer.parseInt(r));
                   
                    System.out.println("ingrese Febrero");
                    r=j.nextLine();
                    agregar.setFebrero(Integer.parseInt(r));
                    
                    System.out.println("ingrese Marzo");
                    r=j.nextLine();
                    agregar.setMarzo(Integer.parseInt(r));
                    alJDBC.insert(agregar);
                   
             
                 
                break;
                case "2":  /*List<clsEmpleado>todos=alJDBC.seleccion();//declaramos una lista para mostrar datos
     
     for (clsEmpleado alumno:todos){
         System.out.println("alumnos"+alumno);//alumno va a llevar todos los datos de la tabla
     }*/
                break;    
                
                
                case "3":   clsEmpleado quitar=new clsEmpleado();
                             
                    System.out.println("ingrese codigo a eliminar:");
                   r= j2.nextLine();
                  
                    quitar.setCodigo(Integer.parseInt(r));//mandamos comomparametro el codigo ingresado
                    alJDBC.eliminar(quitar);
                           break;
               
               
                case "6":clsusuario usu=new clsusuario();
                    usuarioJDBC udb= new usuarioJDBC();
                    
                   
                    String usr ="mabelyn";
                    String pwd="444";
                    
                    usu.setUsername(usr);
                    usu.setPassword(pwd);
                    
                    if(udb.select_validacion(usu)){
                        System.out.println("simon, si trabaja aquí vena");
                    }else{
                        System.out.println("nese no trabaja aqui");
                    }
                
                
                
            case "5": salir=true;//terminamos la ejecución
                      
                
            break;}
        }while(!salir);
    }
}