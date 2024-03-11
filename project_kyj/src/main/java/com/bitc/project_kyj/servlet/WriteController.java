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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "write", value = "/board/write.do")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 1,
        maxRequestSize = 1024 * 1024 * 10
)
public class WriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.getRequestDispatcher("/board/Write.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");
        String userPass = (String) session.getAttribute("userPass");
        String title = req.getParameter("title");
        String content = req.getParameter("title");

        TBBoardDTO board = new TBBoardDTO();
        board.setId(userId);
        board.setPass(userPass);
        board.setTitle(title);
        board.setContent(content);

        // 업로드된 파일 처리

        String saveDir = "C:/fullstack501/jsp/project_kyj/src/main/webapp/resources/upload";
        String originalFileName = "";
        try{
            originalFileName = FileUtil.uploadFile(req,saveDir);
        }catch (Exception e){
            JSFunction.alertLocation("파일 업로드 오류가 발생했습니다.","/board/write.do",res);
            return;
        }

        // 서버에 저장된 파일의 이름 변경
        if (!originalFileName.equals("")){
            String saveFileName = FileUtil.renameFile(originalFileName,saveDir);

            //원본 파일명과 수정된 파일명을 DTO 객체에 저장
            board.setOfile(originalFileName);
            board.setSfile(saveFileName);
        }

        //DB연결
        TBBoardDAO dao = new TBBoardDAO();
        dao.dbOpen();
        //DB에 데이터 추가
        int result = dao.insertBoard(board);
//        int result = 0;

        if (dao.dbIsOpen()) {
            dao.dbClose();
        }
        //리다이렉트로 list.do로이동
        if (result == 1) {
            res.sendRedirect("/board/list.do");
        }else {
            JSFunction.alertBack("글쓰기에 실패하였습니다.",res);
        }
    }

}
