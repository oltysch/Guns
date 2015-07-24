package com.epam.ot.action;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionFactory {

    static Map<String, Action> actions;

    static {
        actions.put();
    }

    public Action getAction(HttpServletRequest req) {
        return actions.get(req);
    }
}
