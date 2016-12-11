/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.bookstatuses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import newlibary.ru.rhbal.facade.Facade;
import newlibary.ru.rhbal.manager.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.manager.exception.EntityNotFoundException;

/**
 *
 * @author User
 */
public class GetBook extends HttpServlet {

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
            request.setAttribute("Readers", facade.getAllReaders());
            request.setAttribute("Books", facade.getBookInStock());
            request.getRequestDispatcher("GetBook.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            getServletConfig().getServletContext().getRequestDispatcher("/Error/SQLConnectionError.jsp").forward(request, response);    

        }
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
        
        try {
            facade.getBook(new Integer(request.getParameter("readerId")),
                    new Integer(request.getParameter("bookId")),
                    new GregorianCalendar(new Integer(request.getParameter("year")),new Integer(request.getParameter("month")),new Integer(request.getParameter("day"))));
        } catch (SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            getServletConfig().getServletContext().getRequestDispatcher("/Error/SQLConnectionError.jsp").forward(request, response);    
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(GetBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BookInTakenNotFoundException ex) {
            Logger.getLogger(GetBook.class.getName()).log(Level.SEVERE, null, ex);
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
