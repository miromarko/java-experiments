import impl.mysql.dao.UsersDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;

import model.Users;
import dao.UsersDAO;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  Connection conn = null;

          try
          {
              String userName = "root";
              String password = "";
              String url = "jdbc:mysql://localhost/testdb";
              Class.forName ("com.mysql.jdbc.Driver").newInstance ();
              conn = DriverManager.getConnection (url, userName, password);
              System.out.println ("Database connection established");
              Users u1 = new Users();
              u1.setName("Miro1");
              Users u2 = new Users();
              u2.setName("Miro2");
              u2.setId(1);
              UsersDAO dao = new UsersDAOImpl();
              u1.setId(2);
              dao.create(u1, conn);
//              dao.create(u2, conn);
          }
          catch (Exception e)
          {
              System.err.println ("Cannot connect to database server");
          }
          finally
          {
              if (conn != null)
              {
                  try
                  {
                      conn.close ();
                      System.out.println ("Database connection terminated");
                  }
                  catch (Exception e) { /* ignore close errors */ }
              }
          }
	}

}
