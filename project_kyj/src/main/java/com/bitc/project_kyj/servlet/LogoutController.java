package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.utils.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "logout",value = "/login/logout.do")
public class LogoutController extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("uesrName");

        session.invalidate();

        req.getRequestDispatcher("/board/list.do").forward(req,res);
    }
}
