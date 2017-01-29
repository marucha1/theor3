/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enrique
 */
@WebServlet(name = "ProyectoTheor", urlPatterns = {"/ProyectoTheor"})
public class ProyectoTheor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String botonOrigen;
        
        try {
            
            //Instancio un objeto de tipo PrintWriter sobre el que escribir la respuesta
            PrintWriter out = response.getWriter();
            //Relaciono el atributo creado con los botones de la página de inicio
            botonOrigen = request.getParameter("nombreBoton");
            switch (botonOrigen) {
                case "Registrarse":
                    //Redirecciono al archivo .html FORMULARIO RESGISTRO
                    response.sendRedirect("FormularioRegistro.html");
                    break;
                    
                case "Iniciar Sesion":
                    //Redirecciono al archivo .html PÁGINA CONVERSIÓN 
                    response.sendRedirect("PaginaConversion.html");
                    break;
                    
                case "Registrado":
                    //Redirecciono al archivo .html PÁGINA LOGIN / REGISTRO
                    response.sendRedirect("index.html");
                    break;
                    
                case "Convertir":
                    //Redirecciono al archivo .html FORMULARIO DE CONVERSIÓN
                    response.sendRedirect("FormularioConversion.html");
                    break;
                    
                case "Volver":
                    //Redirecciono al archivo .html PÁGINA CONVERSIÓN
                    response.sendRedirect("PaginaConversion.html");
                    break;
                    
                case "Cerrar Sesion":
                    //Redirecciono al archivo .html INDEX
                    response.sendRedirect("index.html");
                    break;
                    
                
                    
                
                
                
            }
            
            
            
        } catch (IOException ioe){}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
