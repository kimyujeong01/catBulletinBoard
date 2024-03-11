package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBBoardDAO;
import com.bitc.project_kyj.DB.TBBoardDTO;
import com.bitc.project_kyj.utils.FileUtil;
import com.bitc.project_kyj.utils.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "pass", value = "/board/pass.do")
public class PassController extends HttpServlet {

    //사용자 비밀번호 입력 form화면 출력
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String mode = req.getParameter("mode");
        int idx = Integer.parseInt(req.getParameter("idx"));
        String id = req.getParameter("id");

        if (session.getAttribute("userId") == null) {
            JSFunction.alertLocation("로그인 후 이용해 주세요", "/login/login.do", res);
            return;
        } else {
            if (id.equals(session.getAttribute("userId").toString()) == false) {
                JSFunction.alertLocation("작성자가 아닙니다.", "/board/list.do", res);
                return;
            }

            req.setAttribute("mode", mode);
            req.setAttribute("idx", idx);
        }

        req.getRequestDispatcher("/board/Pass.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        String pass = req.getParameter("pass");
        String mode = req.getParameter("mode");
        int idx = Integer.parseInt(req.getParameter("idx"));


        TBBoardDAO dao = new TBBoardDAO();
        dao.dbOpen();

        boolean confirmed = dao.conFirmPassword(idx, pass);
        int result = 0;

        if (confirmed) {

            if (mode.equals("update")) {
                if (dao.dbIsOpen()) {
                    dao.dbClose();
                }

                res.sendRedirect("/board/edit.do?idx=" + idx);

            } else if (mode.equals("delete")) {

                TBBoardDTO board = dao.selectMVCBoardDetail(idx);
                FileUtil.deleteFile("C:/fullstack501/jsp/project_kyj/src/main/webapp/resources/upload", board.getSfile());


                result = dao.deleteBoard(idx);

                if (dao.dbIsOpen()) {
                    dao.dbClose();
                }

                if (result == 1) {
                    JSFunction.alertLocation("삭제되었습니다.", "/board/list.do", res);
                } else {
                    JSFunction.alertLocation("삭제 중 오류가 발생했습니다.", "/board/list.do", res);
                }

            }

        } else {
            if (dao.dbIsOpen()) {
                dao.dbClose();
            }
            JSFunction.alertBack("비밀번호 검증이 실패했습니다..", res);

        }


    }
}
