package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBBoardDAO;
import com.bitc.project_kyj.DB.TBBoardDTO;
import com.bitc.project_kyj.utils.FileUtil;
import com.bitc.project_kyj.utils.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "edit", value = "/board/edit.do")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 1,
        maxRequestSize = 1024 * 1024 * 10
)
public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int idx = Integer.parseInt(req.getParameter("idx"));
        TBBoardDAO dao = new TBBoardDAO();
        dao.dbOpen();
        req.setAttribute("board", dao.selectMVCBoardDetail(idx));
        if (dao.dbIsOpen()) {
            dao.dbClose();
        }

        req.getRequestDispatcher("/board/Edit.jsp").forward(req, res);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int idx = Integer.parseInt(req.getParameter("idx"));
        String oldSaveFile = req.getParameter("oldSaveFile");
        int result = 0;

        TBBoardDTO board = new TBBoardDTO();
        board.setTitle(title);
        board.setContent(content);
        board.setIdx(idx);

        String saveDir = "C:/fullstack501/jsp/project_kyj/src/main/webapp/resources/upload";
        String oriFileName = "";
        try {
            oriFileName = FileUtil.uploadFile(req, saveDir);
        } catch (Exception e) {
            JSFunction.alertBack("파일 업로드 오류 입니다.", res);
            return;
        }

        if (oriFileName.equals("")){

            TBBoardDAO dao = new TBBoardDAO();
            dao.dbOpen();
            result = dao.updateBoardNotfile(board);

            if (dao.dbIsOpen()) {
                dao.dbClose();
            }

        }else {
            //업로드 된 파일명 수정
            if (!oriFileName.equals("")) {
                String saveFileName = FileUtil.renameFile(oriFileName, saveDir);

                //새로 업로드 된 파일 저장
                board.setOfile(oriFileName);
                board.setSfile(saveFileName);

                // 이전 파일 삭제 부분
                FileUtil.deleteFile(saveDir, oldSaveFile);
            }

            TBBoardDAO dao = new TBBoardDAO();
            dao.dbOpen();
            result = dao.updateBoard(board);

            if (dao.dbIsOpen()) {
                dao.dbClose();
            }
        }



        if (result == 1) {
            res.sendRedirect("/board/view.do?idx=" + idx);
        } else {
            JSFunction.alertLocation("데이터 수정에 실패하였습니다. \n다시 입력해주세요", "/board/view.do?idx=" + idx, res);
        }

    }

}
