/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import Interfaces.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author angel
 */
public class Prestamos_admin implements metodos_prestamo{
    Libro libro_p;
    static int prestamos_d=999999999;
    ArrayList<Libro> libro = new ArrayList();
    static ArrayList<Libro> libros_prestadin= new ArrayList();
    Frontend_Admin admin= new Frontend_Admin();
    int indice;
    @Override
    public boolean pedir(ArrayList<Libro> libro,Libro libro_p,int indice){
        boolean encontrado=false;
        if(admin.encontrado==true){
            if(prestamos_d>0){
                if(libro_p.cantidad!=0){
                    libro_p.cantidad=libro_p.cantidad-1;
                    libro.set(indice,libro_p);
                    libros_prestadin.add(libro_p);
                    try {
                        
                        File archivin = new File("src\\main\\java\\Libros\\Libros_prestados.obj");
                        archivin.delete();
                        FileOutputStream file= new FileOutputStream("src\\main\\java\\Libros\\Libros_prestados.obj",true);
                        ObjectOutputStream escribir=new ObjectOutputStream(file);

                        for(Libro lib : libros_prestadin) // For each.
                        {
                            escribir.writeObject(lib);
                            escribir.reset();
                        }

                        encontrado=true;
                        escribir.close();
                        File archivinn = new File("src\\main\\java\\Libros\\Libros.obj");
                        archivinn.delete();
                        FileOutputStream filee= new FileOutputStream("src\\main\\java\\Libros\\Libros.obj");
                        ObjectOutputStream escribirr=new ObjectOutputStream(filee);
                        for(Libro lib : libro) // For each.
                        {
                            escribirr.writeObject(lib);
                            escribirr.reset();
                        }
                        String mostrar="";
                        for(int i=0;i<libros_prestadin.size();i++){
                            mostrar+=libros_prestadin.get(i).toString()+"\n\n";
                        }
                        prestamos_d--;
                        JOptionPane.showMessageDialog(null,"libros que te llevas a casa:\n\n"+mostrar);
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,"Error\nNo se ha encontrado el archivo papuu");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Error\nNo se ha encontrado el archivo papu");
                    }
                    
                }else{
                JOptionPane.showMessageDialog(null,"Libro no disponible");
                }
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Si llegaste aqui wow, en tu casa debes tener pocas cosas que hacer");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No puedes pedir un libro que no existe bobis");
        }
        return encontrado;
    }
}
