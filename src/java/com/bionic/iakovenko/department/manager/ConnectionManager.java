
package com.bionic.iakovenko.department.manager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author oper4
 */
public class ConnectionManager {

    private static final ConnectionManager instance = new ConnectionManager();
    private ConnectionPool pool;

    private ConnectionManager(){
        pool = new ConnectionPool();

    }
    
    public static ConnectionManager getInstance(){

        return instance;
    }
    
    public Connection getConnection(){

        return pool.getConnection();
    }
    
    public void freeConnection(Connection conn){
        try{
        conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public Object clone(){

        throw new UnsupportedOperationException();
    }
}
