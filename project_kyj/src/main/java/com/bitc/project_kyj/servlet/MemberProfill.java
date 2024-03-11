package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBUserDAO;
import com.bitc.project_kyj.DB.TBUserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "profill", value = "/login/profill.do")
public class MemberProfill extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");

        TBUserDAO dao = new TBUserDAO();
        dao.dbOpen();

        TBUserDTO member = dao.selectMember(userId);
        if (dao.dbIsOpen()) {
            dao.dbClose();
        }

        req.setAttribute("member", member);
        req.getRequestDispatcher("/login/MemberProfill.jsp").forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String userId = req.getParameter("userId");
        String userEmail = req.getParameter("userEmail");
        String userPhone = req.getParameter("userPhone");
        String userAddr = req.getParameter("userAddr");

        TBUserDAO dao = new TBUserDAO();
        dao.dbOpen();

        TBUserDTO member = new TBUserDTO();
        member.setId(userId);
        member.setEmail(userEmail);
        member.setPhone(userPhone);
        member.setAddr(userAddr);

        dao.updateMember(member);

        if (dao.dbIsOpen()){
            dao.dbClose();
        }


        System.out.println(userId + ", " + userEmail + ", " + userPhone + ", " + userAddr);

        res.sendRedirect("/login/profill.do?id=" + userId);
    }

}
