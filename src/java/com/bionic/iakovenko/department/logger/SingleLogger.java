package com.bionic.iakovenko.department.logger;

import org.apache.log4j.Logger;

/**
 * Class provides a single logger for logging events to external file.
 * The setting of logger options can be done changing <i>log4j.properties</i> file.
 *
 * @autor Alex Iakovenko
 * Date: 4/7/14
 * Time: 4:34 PM
 */
public class SingleLogger {
    private static SingleLogger singleLogger;
    private static Logger log = Logger.getLogger(SingleLogger.class.getName());


    private SingleLogger(){
    }

    public synchronized static SingleLogger getInstance(){
        if (singleLogger != null){
            return singleLogger;
        } else {
            return new SingleLogger();
        }
    }
    public synchronized Logger getLog(){

        return this.log;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException();
    }
}
