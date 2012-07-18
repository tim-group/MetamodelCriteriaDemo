package com.timgroup.jpa.query;

public class Run {
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String queriesName = args[0];
        Class<? extends Runnable> queriesClass = Class.forName(Run.class.getPackage().getName() + "." + queriesName).asSubclass(Runnable.class);
        Runnable runnable = queriesClass.newInstance();
        runnable.run();
    }
    
}
