package com.kin.web.myphoto.global;


import com.kin.web.myphoto.pc.accountManager.entity.User;

public class ThreadLocalHelper {
    public static final ThreadLocal<String> requestIdStore = new ThreadLocal<>();
    public static final ThreadLocal<User>sessionUserStroe= new ThreadLocal<>();
    public static String getRequestId(){
        return requestIdStore.get();
    }
    public static void setRequestId(String requestId){
        requestIdStore.set(requestId);
    }
    public static void removeRequestId(){
        requestIdStore.remove();
    }

    public static void setSessionUser(User user){
        sessionUserStroe.set(user);
    }
    public static User getSessionUser(){
        return sessionUserStroe.get();
    }
    public static void removeSessionUser(){
        sessionUserStroe.remove();
    }
}
