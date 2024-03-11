package com.bitc.project_kyj.servlet;

import com.bitc.project_kyj.DB.TBBoardDAO;
import com.bitc.project_kyj.DB.TBBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "list",value = "/board/list.do")
public class ListController extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException{

        TBBoardDAO dao = new TBBoardDAO();
        dao.dbOpen();

        //    게시물의 전체 수 가져오기
        int totalCount = dao.totalCount();
//    한 페이지당 출력할 게시물 수
        int pageSize = 10;
//    한 블럭 당 표시할 페이지 수
        int blockPage = 3;
//    Math.ceil() : 소수점 아래의 숫자가 1 이상의 숫자일 경우 올림 연산을 진행하는 메소드
//    전체 페이지 수, 올림 연산을 진행
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);

        //    페이지 번호 받아오기
        String pageNumTemp = req.getParameter("pageNum");
//    페이지 번호 기본 값
        int pageNum = 1;

//    파라미터로 전달받은 페이지 번호가 있을 경우 해당 페이지 번호 사용
        if (pageNumTemp != null && !pageNumTemp.equals("")) {
            pageNum = Integer.parseInt(pageNumTemp);

//      페이지 번호가 1보다 작을 경우 페이지 번호를 1로 변경
            if (pageNum < 1) {
                pageNum = 1;
            }
//      페이지 번호가 총 페이지 수 보다 클 경우 페이지 번호를 총 페이지 수로 변경
            else if (pageNum > totalPage) {
                pageNum = totalPage;
            }
        }

//    MySql의 LIMIT 연산 시 가져올 시작 번호, index는 0부터 가져옴
        int start = (pageNum - 1) * pageSize;
//    int end = pageNum * pageSize; // oracle 사용 시 가져올 끝 번호, MySql에서는 필요 없음
//    현재 블록의 시작 페이지 번호
        int startPageNum = (((pageNum - 1) / blockPage) * blockPage) + 1;
//    현재 블록의 끝 페이지 번호
        int endPageNum = startPageNum + blockPage - 1;

//    현재 블록의 끝 페이지가 총 페이지 수 보다 클 경우 총 페이지 수를 현재 블록의 끝 페이지로 지정
        if (endPageNum > totalPage) {
            endPageNum = totalPage;
        }


        List<TBBoardDTO> baordList = dao.selectMVCBoardListPaging(start, pageSize);

        if (dao.dbIsOpen()){
            dao.dbClose();
        }


        req.setAttribute("baordList",baordList);

        req.setAttribute("blockPage", blockPage);
//    현재 페이지 번호 저장
        req.setAttribute("pageNum", pageNum);
//    현재 블럭의 시작 페이지 번호 저장
        req.setAttribute("startPageNum", startPageNum);
//    현재 블럭의 끝 페이지 번호 저장
        req.setAttribute("endPageNum", endPageNum);
//    총 페이지 번호 저장
        req.setAttribute("totalPage", totalPage);

        req.getRequestDispatcher("/board/List.jsp").forward(req,res);
    }


}
