package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBBoardDAO;
import com.bitc.project_kyj.DB.TBBoardDTO;
import com.bitc.project_kyj.DB.TBUserDAO;
import com.bitc.project_kyj.DB.TBUserDTO;
import com.bitc.project_kyj.utils.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "login",value = "/login/login.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {

        req.getRequestDispatcher("/login/Login.jsp").forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req ,HttpServletResponse res) throws ServletException,IOException {

        HttpSession session = req.getSession();

        String userId = req.getParameter("userId");
        String userPass = req.getParameter("userPass");

        TBUserDAO dao = new TBUserDAO();
        dao.dbOpen();
        boolean result = dao.isMemeber(userId,userPass);


        if (result == true){
            TBUserDTO member = dao.selectMember(userId);
            dao.dbClose();

            session.setAttribute("userId",member.getId());
            session.setAttribute("userName",member.getName());
            session.setAttribute("userPass",member.getPass());

            res.sendRedirect("/board/list.do");

        }else {
            dao.dbClose();
            JSFunction.alertBack("회원이 아니거나 ID나 비밀번호가 틀렸습니다.",res);
        }


    }
}
