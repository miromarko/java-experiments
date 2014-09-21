package ro.springsoft.dataservice;


import ro.springsoft.dao.IDAOFactory;

public interface IDataService<T extends IDAOFactory> {
    T getDAOFactory();

    void shutdown();
}