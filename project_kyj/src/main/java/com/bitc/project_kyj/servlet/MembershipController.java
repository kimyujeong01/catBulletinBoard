package com.bitc.project_kyj.servlet;

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

@WebServlet(name = "membership", value = "/login/membership.do")
public class MembershipController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String userName = req.getParameter("Name");
        String userId = req.getParameter("Id");
        String userPass = req.getParameter("Pass");
        String userEmail = req.getParameter("Email");
        String userPhone = req.getParameter("Phone");
        String userAddr = req.getParameter("Addr");


        if (userId == "" || userPass == "" || userName == ""){
            if (userId == ""){
                JSFunction.alertBack("아이디를 입력하세요",res);
                return;
            }else if (userPass == ""){
                JSFunction.alertBack("비밀번호를 입력하세요",res);
                return;
            }else if (userName == ""){
                JSFunction.alertBack("이름을 입력하세요",res);
                return;
            }
        }


        TBUserDAO dao = new TBUserDAO();
        dao.dbOpen();

        int cnt = dao.checkID(userId);

        if (cnt == 0){
            TBUserDTO member = new TBUserDTO();
            member.setId(userId);
            member.setPass(userPass);
            member.setName(userName);
            member.setEmail(userEmail);
            member.setPhone(userPhone);
            member.setAddr(userAddr);

            int result = dao.insertMember(member);

            if (dao.dbIsOpen()){
                dao.dbClose();
            }

            if (result > 0) {
                JSFunction.alertLocation("회원 등록이 완료 되었습니다.","/board/list.do",res);
            }
            else {
                JSFunction.alertBack("데이터 추가 중 오류가 발생했습니다.", res);
            }
        } else {

            if (dao.dbIsOpen()){
                dao.dbClose();
            }

            JSFunction.alertBack("이미 있는 ID입니다." ,res);
            return;
        }




    }

}
