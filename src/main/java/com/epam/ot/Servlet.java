package com.epam.ot;

import com.epam.ot.action.Action;
import com.epam.ot.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = "/new")
public class Servlet extends HttpServlet {
    //    TODO - make working servlet.
    private ActionFactory factory;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = factory.getAction(req);
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String view = action.execute(req, resp);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() throws ServletException {
        factory = new ActionFactory();
    }
}
