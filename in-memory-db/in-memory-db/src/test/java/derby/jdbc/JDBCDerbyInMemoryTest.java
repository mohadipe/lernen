package derby.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JDBCDerbyInMemoryTest {

	private static Logger logger = LoggerFactory
			.getLogger(JDBCDerbyInMemoryTest.class);

	private static final String DB_CONNECTION = "jdbc:derby:memory:myDB";

	private static Connection connection;

	@BeforeClass
	public static void setUp() {
		try {
			logger.info("Startingemory database for unit tests");
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager.getConnection(DB_CONNECTION
					+ ";create=true");

		} catch (ClassNotFoundException | SQLException e) {
			fail(e.getMessage());
		}
	}

	@Before
	public void initDB() throws SQLException {
		String DDL = "CREATE TABLE test_table (" + "id INT NOT NULL, "
				+ "name VARCHAR(20) NOT NULL, " + "PRIMARY KEY (id))";
		Statement stmnt = connection.createStatement();
		stmnt.executeUpdate(DDL);
		String DML = "INSERT INTO test_table VALUES (1, 'test data')";
		stmnt = connection.createStatement();
		stmnt.executeUpdate(DML);
	}

	@Test
	public void leseTestDaten() throws SQLException {
		String select = "SELECT * FROM test_table";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(select);
		if (resultSet == null) {
			fail("Es sollte etwas selektiert werden.");
		}
		do {
			resultSet.next();
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			logger.debug("ID: " + id + " Name: " + name);
			assertEquals(1, id);
			assertEquals("test data", name);
		} while (resultSet.next());
		resultSet.close();
		statement.close();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		try {
			connection.close();
			DriverManager.getConnection(DB_CONNECTION + ";drop=true");
		} catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
            // Shutdown success
		}
	}
}
