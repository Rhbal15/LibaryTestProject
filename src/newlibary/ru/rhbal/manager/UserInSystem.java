/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibary.ru.rhbal.manager;

import newlibary.ru.rhbal.entity.Account;

/**
 *
 * @author User
 */

//класс со статическими полями и методами, хранит пользователя авторизированного в программе
public class UserInSystem {
   private static Account account=null;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        UserInSystem.account = account;
    }
   
   
}
