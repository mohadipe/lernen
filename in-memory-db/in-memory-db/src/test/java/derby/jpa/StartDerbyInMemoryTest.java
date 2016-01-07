package derby.jpa;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mohadipe.entities.DetailEntity;
import de.mohadipe.entities.MasterEntity;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StartDerbyInMemoryTest {
	
	private static Logger logger = LoggerFactory.getLogger(StartDerbyInMemoryTest.class);
	
	private static final String DB_CONNECTION = "jdbc:derby:memory:myDB";
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	
	@BeforeClass
	public static void setUp() {
		try {
			logger.info("Startingemory database for unit tests");
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			DriverManager.getConnection(DB_CONNECTION + ";create=true").close();
		} catch (ClassNotFoundException | SQLException e) {
			fail(e.getMessage());
		}
		
        try {
            logger.info("BuildingEntityManager for unit tests");
            factory = Persistence.createEntityManagerFactory("derbyTestUnit");
            manager = factory.createEntityManager();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
	}

	@AfterClass
    public static void tearDown() throws Exception {
        logger.info("Shuting Hibernate JPA layer.");
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
        logger.info("Stoppingemory database.");
        try {
            DriverManager.getConnection(DB_CONNECTION + ";shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
            // Shutdown success
        }
    }
	
	@Test
    public void testPersistence() {
        try {
            manager.getTransaction().begin();

            DetailEntity u = new DetailEntity();
            u.setEmail("eskatos@yopmail.com");
            u.setFirstName("eskatos");
            u.setLastName("YOP");
            u.setOrganisation("Tagada");
            logger.debug(u.toString());
            manager.persist(u);
            assertTrue(manager.contains(u));

            MasterEntity g = new MasterEntity();
            g.addUser(u);

            manager.persist(g);
            assertTrue(manager.contains(g));

            g.removeUser(u);
            manager.remove(u);
            manager.merge(g);
            assertFalse(manager.contains(u));

            manager.getTransaction().commit();

        } catch (Exception ex) {
        	manager.getTransaction().rollback();
            fail(ex.getMessage());
        }
    }
}
