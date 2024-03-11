package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBUserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "memberdelete",value = "/login/memberdelete.do")
public class Memberdelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");

        TBUserDAO dao = new TBUserDAO();
        dao.dbOpen();

        dao.delectMember(userId);

        if (dao.dbIsOpen()){
            dao.dbClose();
        }

        res.sendRedirect("/login/logout.do");
    }


}
