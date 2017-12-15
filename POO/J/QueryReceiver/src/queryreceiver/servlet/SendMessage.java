package queryreceiver.servlet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import queryreceiver.data.MessageDAO;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet(name = "send-message", description = "Used to send a query to the owner team", urlPatterns = { "/send-message" })
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDAO m = new MessageDAO();
		Timer t = new Timer();
		System.out.println("a");
		t.scheduleAtFixedRate(new TimerTask() {
			private int messageCount = -1;
			@Override
			public void run() {
				System.out.println("b");
				int tempMessageCount = m.getCount();
				if(tempMessageCount > messageCount) {
					messageCount = tempMessageCount;
					System.out.println(messageCount);
				}
			}
		}, 0, 1000);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
