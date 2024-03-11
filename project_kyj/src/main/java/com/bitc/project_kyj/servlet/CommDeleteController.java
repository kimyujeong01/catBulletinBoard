package com.bitc.project_kyj.servlet;


import com.bitc.project_kyj.DB.TBBoardDTO;
import com.bitc.project_kyj.DB.TBCommentDAO;
import com.bitc.project_kyj.utils.FileUtil;
import com.bitc.project_kyj.utils.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "commdelete", value = "/comm/commdelete.do")
public class CommDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        HttpSession session = req.getSession();

        int boardIdx = Integer.parseInt(req.getParameter("boardIdx"));
        int idx = Integer.parseInt(req.getParameter("idx"));
        String id = req.getParameter("id");

        if (session.getAttribute("userId") == null) {
            JSFunction.alertLocation("로그인 후 이용해 주세요", "/login/login.do", res);
            return;
        } else {
            if (id.equals(session.getAttribute("userId").toString()) == false) {
                JSFunction.alertLocation("작성자가 아닙니다.", "/board/view.do?idx="+boardIdx, res);
                return;
            }
        }

        TBCommentDAO dao = new TBCommentDAO();
        dao.dbOpen();


        int result = dao.deleteComm(idx);

        if (dao.dbIsOpen()) {
            dao.dbClose();
        }

        if (result == 1) {
            JSFunction.alertLocation("삭제되었습니다.", "/board/view.do?idx="+boardIdx, res);
        } else {
            JSFunction.alertLocation("삭제 중 오류가 발생했습니다.", "/board/list.do", res);
        }

    }
}
