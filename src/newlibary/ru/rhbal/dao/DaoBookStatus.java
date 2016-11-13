/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.dao;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import newlibary.ru.rhbal.dao.exception.BookInTakenNotFoundException;
import newlibary.ru.rhbal.dao.exception.NoBookInLibaryException;
import newlibary.ru.rhbal.entity.Account;
import newlibary.ru.rhbal.entity.Book;
import newlibary.ru.rhbal.entity.BookStatus;
import newlibary.ru.rhbal.entity.Model;
import newlibary.ru.rhbal.entity.Status;
import newlibary.ru.rhbal.manager.UserInSystem;


//DAO ��� ������ � �������� �������� ����
public class DaoBookStatus extends AbstractDao<BookStatus>{
    
    
    //-------------------------------------------------------------------------------------------------
    //������ �������� ������� ������� (������ ���� �� ����������)
    //-------------------------------------------------------------------------------------------------
    
    //�������� ������� ������ ����� �� � ��������, ������� ������ ������������� � ������������
    //����������������� � �������
    public BookStatus create(String name, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        
        //������� ����� �� �����, ���� � ��� �� ����������� ����������
        DaoBook daoBook = new DaoBook();
        Book book=daoBook.getBook(name); 
        if(book==null)
            throw new NoBookInLibaryException();
        
        //������� ��� ����� ������� ������, ������� � � ������
        BookStatus bookStatus = new BookStatus(book, mustBeReturned);
        getModel().add(bookStatus);
        return bookStatus;
    }
    
    //����� ��� �������� ������� ������ �� ����� ����� � ������ ��������
    public BookStatus create(int numberReader, int numberBook, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        
        //������� ����� �� ������, ���� � ���, �� ����������� ����������
        DaoBook daoBook = new DaoBook();
        Book book=daoBook.getAll().get(numberBook);
        if(book==null)
            throw new NoBookInLibaryException();
        
        //������� ��� ����� ������� ������, ������� � � ������
        BookStatus bookStatus=new BookStatus(book,mustBeReturned);
        Model.getINSTANCE().getAccounts().get(numberReader).getBookStatuses().add(bookStatus);
        return bookStatus;
    }
    
    //�������� ������� ������ �� �������� � �������� �����
    public BookStatus create(String name,Account account, GregorianCalendar mustBeReturned) throws NoBookInLibaryException{
        
        //������� ����� �� � ��������
        DaoBook daoBook = new DaoBook();
        Book book=daoBook.getBook(name); 
        if(book==null)
            throw new NoBookInLibaryException();
        
        //������� ��� ����� ������� ������, ������� � � ������
        //���������� ���������
        BookStatus bookStatus = new BookStatus(book, mustBeReturned);
        account.getBookStatuses().add(bookStatus);
        return bookStatus;
    }
    
    //-------------------------------------------------------------------------------------------------
    
    
    
    //-------------------------------------------------------------------------------------------------
    //������ ����������� ���� � ����������
    //-------------------------------------------------------------------------------------------------
    
    //����������� ����� �� � �����, ����� ������� ������� �������������� � ����������������� ������������
    public void putBook(String name) throws BookInTakenNotFoundException{
        
        //���� ������� ������, ���� �����, �� ������ � ������ � ����� ��������, ���� ��� 
        //����������� ����������
        for(BookStatus value: getModel()){
            if(value.getBook().getName().equals(name)){
                closeStatus(value);
                return;
            }
            throw new BookInTakenNotFoundException();
        }
    }
    
    //����������� ����� �� � ����� � ��������
    public void putBook(String name, Account account) throws BookInTakenNotFoundException{
        //���� ������� ������ � ���������� ��������, ���� �����, �� ������ � ������ � ����� ��������, ���� ��� 
        //����������� ����������
        for(BookStatus value: account.getBookStatuses()){
            if(value.getBook().getName().equals(name)){
                closeStatus(value);
                return;
            }
            throw new BookInTakenNotFoundException();
        }
    }
    
    //����������� ����� �� � ������ � ������ ��������
    public void putBook(int numberReader, int numberBook) throws BookInTakenNotFoundException{
        //��������� �� ������� ������
        BookStatus bookStatus = Model.getINSTANCE().getAccounts().get(numberReader).getBookStatuses().get(numberBook);
        
        //������ � ������
        closeStatus(bookStatus);
    }
    //-------------------------------------------------------------------------------------------------
    
    @Override
    protected ArrayList<BookStatus> getModel(){
        return UserInSystem.getAccount().getBookStatuses();
    }
    
    //�����, ���������� �������� ������� ������, ��� �������� ����, ��� ����� ������� � ����������
    private void closeStatus(BookStatus bookStatus){
        bookStatus.setTimeReturn(new GregorianCalendar());
        bookStatus.getBook().setStatus(Status.INSTOCK);
    }
}
