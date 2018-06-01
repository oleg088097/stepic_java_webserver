package main;


import accounts.AccountService;
import accounts.dbService.DBException;
import accounts.dbService.dataSets.UsersDataSet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        // Setup default users
        long userId;
        try {
            userId = accountService.addNewUser("admin", "admin", "admin");
            UsersDataSet dataSet;
            if (userId != -1) {
                dataSet = accountService.getUserByUserId(userId);
                System.out.println("User data set: " + dataSet);
            }

            userId = accountService.addNewUser("test", "test", "test");
            if (userId != -1) {
                dataSet = accountService.getUserByUserId(userId);
                System.out.println("User data set: " + dataSet);
            }
        } catch (DBException e) {
            e.printStackTrace();
            System.exit(1);

        }

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        Log.getLog().info("Server started");

        server.join();
    }
}
