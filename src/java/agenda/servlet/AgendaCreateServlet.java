/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.servlet;

import agenda.model.AgendaFactory;
import agenda.model.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumno
 */
@WebServlet(name = "AgendaCreate", urlPatterns = {"/AgendaCreate"})
public class AgendaCreateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        AgendaFactory agendaDB = (AgendaFactory)session.getAttribute("agendaDB");
        
        int id=0;
        
        try{
            String str = request.getParameter("id");
            if(str != null){
                id = Integer.parseInt(request.getParameter("id"));
            }
        }catch(Exception e){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        
        
        try{
           //int id = agendaDB.getAgenda().size();

           String tagNombre = request.getParameter("nombre");           
           String tagApellidos = request.getParameter("apellidos");
           String tagCorreo = request.getParameter("correo");
           String tagTelefono = request.getParameter("telefono");
           
           Persona personaNueva = new Persona(id, tagNombre, tagApellidos, tagCorreo, tagTelefono);       
           
           if(id == 0){
                agendaDB.createPersona(personaNueva);
           }else{
               agendaDB.updatePersona(personaNueva);
           }
           
           response.sendRedirect(request.getContextPath() + "/AgendaList");
           
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
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
