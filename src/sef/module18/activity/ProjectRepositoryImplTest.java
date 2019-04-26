package sef.module18.activity;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ProjectRepositoryImplTest extends TestCase {
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
        super.tearDown();
    }

    public void testFindProjectByID() {
        ProjectRepositoryImpl projects = new ProjectRepositoryImpl(conn);
        try {
            Project result = projects.findProjectByID(1);
            assertEquals(result.getProjectName(), "Online Insurance System");
            assertEquals(result.getProjectDescription(), "A web application that automates insurance transactions.");

            result = projects.findProjectByID(-1);
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

    public void testFindProjectByName() {
        ProjectRepositoryImpl projects = new ProjectRepositoryImpl(conn);
        try {
            List<Project> results = projects.findProjectByName(	"Online");
            assertEquals(2, results.size());

            assertEquals(results.get(0).getProjectDescription(), "A web application that automates insurance transactions.");
            assertEquals(results.get(0).getProjectName(), "Online Insurance System");
            assertEquals(results.get(0).getProjectID(), 1);


            assertEquals(results.get(1).getProjectDescription(), "A web application that handles shopping transactions online.");
            assertEquals(results.get(1).getProjectName(), "Online Shopping System");
            assertEquals(results.get(1).getProjectID(), 4);

            results = projects.findProjectByName(	"asfasasd");
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

    public void testFindProjectByEmployee() {
        ProjectRepositoryImpl projects = new ProjectRepositoryImpl(conn);
        try {
            List<Project> results = projects.findProjectByEmployee(	3);
            assertEquals(1, results.size());

            assertEquals(results.get(0).getProjectDescription(), "A stand-alone application that records and generates time reports.");
            assertEquals(results.get(0).getProjectName(), "Time Report System");
            assertEquals(results.get(0).getProjectID(), 2);

            results = projects.findProjectByEmployee(	-1);
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

    public void testInsertProject() {
        ProjectRepositoryImpl projects = new ProjectRepositoryImpl(conn);
        try {
            int projectID = projects.insertProject(new ProjectImpl(0,"Test System", "Test Test Test..."));
            Project result = projects.findProjectByID(projectID);
            assertEquals(result.getProjectName(), "Test System");
            assertEquals(result.getProjectDescription(), "Test Test Test...");

        }catch(HRSSystemException e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            fail();
        }
    }

    public void testUpdateProject() {
        ProjectRepositoryImpl projects = new ProjectRepositoryImpl(conn);
        try {
            int projectID = projects.insertProject(new ProjectImpl(0,"Test System", "Test Test Test..."));
            boolean updateStatus = projects.updateProject(new ProjectImpl(projectID, "Testing update system", "Update Update ..."));
            assertTrue(updateStatus);

            updateStatus = projects.updateProject(new ProjectImpl(-1, "Testing update system", "Update Update ..."));
            assertFalse(updateStatus);

            Project project = projects.findProjectByID(projectID);

            assertEquals(projectID, project.getProjectID());
            assertEquals(project.getProjectName(), "Testing update system");
            assertEquals(project.getProjectDescription(), "Update Update ...");
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