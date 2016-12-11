/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.readers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;
import servlet.authors.AuthorsEdit;

/**
 *
 * @author User
 */
public class ReadersEdit extends HttpServlet {
    
    Facade facade = new Facade();

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
        try {
            request.setAttribute("reader", facade.getReaderById(new Integer(request.getParameter("id"))));
        } catch (SQLException ex) {
            Logger.getLogger(AuthorsEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("Edit.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            facade.editAccount(new Integer(request.getParameter("id")),
                    request.getParameter("lastName"),
                    request.getParameter("firstName"),
                    request.getParameter("surname"),
                    new Integer(request.getParameter("age")));
            
            
            response.setStatus(301);
            response.addHeader("Location", "/TEstLibaryProjectTomcatNew/Readers");
        } catch (SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            getServletConfig().getServletContext().getRequestDispatcher("/Error/SQLConnectionError.jsp").forward(request, response);    
        } catch (EntityNotFoundException ex) {
            System.out.println("Entity not found");
        }
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
