package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBBoardDAO;
import com.bitc.project_kyj.DB.TBBoardDTO;
import com.bitc.project_kyj.DB.TBCommentDAO;
import com.bitc.project_kyj.DB.TBCommentDTO;
import com.bitc.project_kyj.utils.FileUtil;
import com.bitc.project_kyj.utils.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "commwrite", value = "/comm/commwrite.do")
public class CommWriteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{

        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");
        String commContent = req.getParameter("commcontent");
        int boardIdx = Integer.parseInt(req.getParameter("idx"));

        if (session.getAttribute("userId") == null) {
            JSFunction.alertLocation("로그인 후 이용해 주세요", "/login/login.do", res);
            return;
        }


        TBCommentDTO comment = new TBCommentDTO();
        comment.setId(userId);
        comment.setContent(commContent);
        comment.setBoard_idx(boardIdx);



        TBCommentDAO dao = new TBCommentDAO();
        dao.dbOpen();

        int result = dao.insertComm(comment);

        if (dao.dbIsOpen()) {
            dao.dbClose();
        }

        if (result == 1) {
            res.sendRedirect("/board/view.do?idx="+boardIdx);
        }else {
            JSFunction.alertBack("글쓰기에 실패하였습니다.",res);
        }
    }
}
