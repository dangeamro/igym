package com.designer.common.framework;

import java.util.Hashtable;
import java.util.Map;


public class InvalidLoginTracker {

    private static Map<String,Integer> invalidAttemptsMap = null;
    
    static
    {
    	invalidAttemptsMap = new Hashtable<String,Integer>();
    }
	
	/**
     * Default Constructor
     */
    public InvalidLoginTracker() {
    }

    /**
     * Method to add invalid login attempts to HashMap maintained at
     * application context level of the application
     * @param userId String
     * @param attemptCount int
     */
    public static void putInvalidUserLogin(String userId, int attemptCount) {
       	invalidAttemptsMap.put(userId, new Integer(attemptCount));
    }

    /**
     * Method to remove invalid login attempts from HashMap maintained at
     * application context level of the application
     * @param userId String
     */
    public static void removeInvalidUserLogin(String userId) {
    	invalidAttemptsMap.remove(userId);
    }

    /**
     * Method to get invalid login attempts for the given user id which is
     * maintained at the application context level.
     * @param userId String
     * @return int
     */
    public static int getInvalidLoginAttempts(String userId) {
        Integer invalidAttempts = invalidAttemptsMap.get(userId);
        if (invalidAttempts != null) {
            return invalidAttempts.intValue();
        } else {
            return 0;
        }
    }
}