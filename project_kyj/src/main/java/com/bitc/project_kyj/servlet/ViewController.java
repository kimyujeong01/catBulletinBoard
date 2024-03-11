package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBBoardDAO;
import com.bitc.project_kyj.DB.TBBoardDTO;
import com.bitc.project_kyj.DB.TBCommentDAO;
import com.bitc.project_kyj.DB.TBCommentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "view",value = "/board/view.do")
public class ViewController extends HttpServlet {

    //지정한 글번호에 대한 상세 정보를 출력
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        int idx = Integer.parseInt(req.getParameter("idx"));
        TBBoardDAO dao = new TBBoardDAO();
        dao.dbOpen();

        dao.updateVisitCount(idx);
        TBBoardDTO board = dao.selectMVCBoardDetail(idx);



        TBCommentDAO dao1 = new TBCommentDAO();
        dao1.dbOpen();

        List<TBCommentDTO> commentList = dao1.selectComment(idx);



        if (dao.dbIsOpen()){
            dao.dbClose();
        }

        if (dao1.dbIsOpen()){
            dao1.dbClose();
        }



        req.setAttribute("board",board);
        req.setAttribute("commentList",commentList);
        req.getRequestDispatcher("/board/View.jsp").forward(req,res);
    }
}
