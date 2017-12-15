package queryreceiver.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;

public class MessageDAO {
	private String getMessageCount = "SELECT COUNT(*) FROM `message`";
	private String getMessages = "SELECT * FROM `message`";
	private String getLastMessage = "SELECT * FROM `message` ORDER BY `datetime` DESC LIMIT 1";
	private Connection connection;
	public int getCount()  {
		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement p = c.prepareStatement(getMessageCount);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				return rs.getInt(0);
			}
			return 0;
		}
		catch(Exception exc) {
			throw new RuntimeException(exc);
		}
	}
}
