/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.entity.BookStatus;
import servlet.bookstatuses.BookStatusViewModel;

/**
 *
 * @author User
 */
public class BookStatusViewModelCreater {
    public ArrayList<BookStatusViewModel> createList(ArrayList<BookStatus> list){
        ArrayList<BookStatusViewModel> viewModels = new ArrayList<>();
        
        for(BookStatus bookStatus : list){
            BookStatusViewModel viewModel=new BookStatusViewModel(bookStatus.getId(),
                    bookStatus.getReader(),bookStatus.getBook(),
                    dateToString(bookStatus.getTimeReceipt()),
                    dateToString(bookStatus.getTimeReturn()),
                    dateToString(bookStatus.getMustBeReturned()));
            viewModels.add(viewModel);
        }
        return viewModels;
    }
    
    public String dateToString(GregorianCalendar date){
        if(date!=null){
            String value=new String(String.valueOf(date.get(Calendar.DAY_OF_MONTH))+"."+
                    String.valueOf(date.get(Calendar.MONTH))+"."+
                    String.valueOf(date.get(Calendar.YEAR)));
            return value;
        }
        return  null;
    }
}
