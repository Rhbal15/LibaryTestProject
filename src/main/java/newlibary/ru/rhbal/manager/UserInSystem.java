/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import newlibary.ru.rhbal.entity.Reader;
/**
 *
 * @author User
 */

//класс со статическими полями и методами, хранит пользователя авторизированного в программе
public class UserInSystem {
    private static Reader reader = null;

    public static Reader getReader() {
        return reader;
    }

    public static void setReader(Reader reader) {
        UserInSystem.reader = reader;
    }
    
}
