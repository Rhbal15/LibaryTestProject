/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.bookstatuses;

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
import servlet.authors.Authors;

/**
 *
 * @author User
 */
public class BookStatusDelete extends HttpServlet {

    Facade facade = new Facade();
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
        int id = new Integer(request.getParameter("id"));
                
        try {
            facade.deleteBookStatus(id);
        } catch (SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            getServletConfig().getServletContext().getRequestDispatcher("/Error/SQLConnectionError.jsp").forward(request, response);    
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(Authors.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setStatus(301);
        response.addHeader("Location", "/TEstLibaryProjectTomcatNew/BookStatuses");
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
