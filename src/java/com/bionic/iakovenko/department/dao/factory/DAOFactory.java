package com.bionic.iakovenko.department.dao.factory;

/**
 * @autor Alex Iakovenko
 * Date: 3/30/14
 * Time: 10:30 AM
 */
public class DAOFactory {

    public static DBDAOFactory getFactory(DbType type){
        switch (type){
            case MY_SQL:
                return new MySQLDAOFactory();
            default:
                throw new UnsupportedOperationException("Data Base has not been supported yet!");
        }
    }

}
