package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class MirrorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        //Map<String, Object> pageVariables = new HashMap<>();
        //Map<String, String[]> params = request.getParameterMap();
        //pageVariables.put("key", !params.isEmpty() && params.get("key") != null? params.get("key")[0] : "");
        //response.getWriter().println(PageGenerator.instance().getPage("pageMirror.html", pageVariables));

        Map<String, String[]> params = request.getParameterMap();
        response.getWriter().println(!params.isEmpty() && params.get("key") != null? params.get("key")[0] : "");

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
    }
}
