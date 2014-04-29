package com.bionic.iakovenko.department.manager;

import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import java.sql.Connection;
import javax.sql.DataSource;

/**
 * 
 * @autor Alex Iakovenko 
 * Date: 4/8/14 
 * Time: 10:58 PM
 */
public class ConnectionPool {
  
    private final Logger logger = SingleLogger.getInstance().getLog();
    
    public Connection getConnection() {
        ConfigurationManager config = ConfigurationManager.getInstance();
        Connection conn = null;
        
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup(
                    config.getProperty(ConfigurationManager.DB_RESOURCE_NAME));
            conn = ds.getConnection();
        }catch(Exception e){
            logger.error(e.toString(), e);
        }
        return conn;
    }
    
}
