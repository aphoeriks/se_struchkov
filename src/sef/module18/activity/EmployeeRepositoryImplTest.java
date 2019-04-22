package sef.module18.activity;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sef.module13.activity.Account;
import sef.module13.activity.AccountDAO;
import sef.module13.activity.AccountDAOException;
import sef.module13.activity.AccountDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EmployeeRepositoryImplTest extends TestCase {
    private Connection conn;
    private String url ;
    private String username;
    private String password;
    Log logger = LogFactory.getLog(this.getClass());

    public void setUp() throws Exception {
        super.setUp();
        username = "sa";
        password = "";
        Class.forName("org.h2.Driver");
        url = "jdbc:h2:~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");
    }

    public void tearDown() throws Exception {
    }

    public void testFindEmployeeByID() {
        EmployeeRepositoryImpl employee = new EmployeeRepositoryImpl(conn);
        try {
            Employee result = employee.findEmployeeByID(1);

            assertEquals(result.getFirstName().toUpperCase(), "JOHN");
            assertEquals(result.getLastName().toUpperCase(), "DOE");
            assertEquals(result.getProfLevel(), 1);

            result = employee.findEmployeeByID(-1);
            assertEquals(result, null);
        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }

    }

    public void testFindEmployeeByName() {
        EmployeeRepositoryImpl employee = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = employee.findEmployeeByName("J", "DO");
            assertEquals(3, results.size());

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);


            assertEquals(results.get(1).getFirstName().toUpperCase(), "JANE");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(1).getProfLevel(),2);

            assertEquals(results.get(2).getFirstName().toUpperCase(), "JAMES");
            assertEquals(results.get(2).getLastName().toUpperCase(), "DONNELL");
            assertEquals(results.get(2).getProfLevel(), 3);

            results = employee.findEmployeeByName("k", "kitten");
            assertEquals(0, results.size());

        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }
    }

    public void testFindEmployeeByProfLevel() {
        EmployeeRepositoryImpl employee = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = employee.findEmployeeByProfLevel(1);
            assertEquals(2, results.size());

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);

            assertEquals(results.get(1).getFirstName().toUpperCase(), "SCOTT");
            assertEquals(results.get(1).getLastName().toUpperCase(), "FEIST");
            assertEquals(results.get(1).getProfLevel(), 1);

            results = employee.findEmployeeByProfLevel(-1);
            assertEquals(results.size(), 0);
        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }
    }

    public void testFindEmployeeByProject() {
        EmployeeRepositoryImpl employee = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = employee.findEmployeeByProject(3);
            assertEquals(results.size(),2);

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JANE");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 2);

            assertEquals(results.get(1).getFirstName().toUpperCase(), "JAMES");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DONNELL");
            assertEquals(results.get(1).getProfLevel(), 3);

            results = employee.findEmployeeByProject(99);
            assertEquals(results.size(),0);
        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }
    }

    public void testInsertEmployee() {
        EmployeeRepositoryImpl employee = new EmployeeRepositoryImpl(conn);
        try {

            int employeeID =  employee.insertEmployee(new EmployeeImpl(99, "Jackie", "Chan", 99));
            Employee result = employee.findEmployeeByID(employeeID);
            assertEquals(result.getFirstName().toUpperCase(), "JACKIE");
            assertEquals(result.getLastName().toUpperCase(), "CHAN");
            assertEquals(result.getProfLevel(), 99);
        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }
    }

    public void testUpdateEmployee() {
        EmployeeRepositoryImpl employees = new EmployeeRepositoryImpl(conn);
        try {

            boolean result = employees.updateEmployee(new EmployeeImpl(181, "Jackie", "Chan", 99));
            assertFalse(result);

            result = employees.updateEmployee(new EmployeeImpl(2, "Michael", "Dorn", 6));
            assertTrue(result);
            if(result){
                Employee employee = employees.findEmployeeByID(2);
                assertEquals(6,employee.getProfLevel());
            }
        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }
    }
}