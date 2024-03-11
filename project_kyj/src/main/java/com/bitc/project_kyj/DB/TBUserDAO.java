package com.bitc.project_kyj.DB;

import jakarta.servlet.ServletContext;

import java.sql.SQLException;

public class TBUserDAO extends JDBConnect {

    public TBUserDAO() {
        super();
    }

    public TBUserDAO(ServletContext app) {
        super(app);
    }

    public TBUserDAO(String dbDriver, String dbUrl, String dbUserId, String dbUserPw) {
        super(dbDriver, dbUrl, dbUserId, dbUserPw);
    }

    //로그인
    public boolean isMemeber(String userId, String userPwd) {
        boolean result = false;

        try {
            String sql = "select count(*) as cnt from tb_user where user_id = ? and user_pass = ?  ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPwd);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("cnt") == 1) {
                    result = true;
                }
            }

        } catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return result;
    }

    // 사용자 정보 가져오기
    public TBUserDTO selectMember(String userId) {
        TBUserDTO member = new TBUserDTO();

        try {
            String sql = "select user_id,user_pass,user_name,user_email,user_phone,user_addr,user_level from tb_user where user_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                member.setId(rs.getString("user_id"));
                member.setPass(rs.getString("user_pass"));
                member.setName(rs.getString("user_name"));
                member.setEmail(rs.getString("user_email"));
                member.setPhone(rs.getString("user_phone"));
                member.setAddr(rs.getString("user_addr"));
                member.setLevel(rs.getInt("user_level"));
            }

        } catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return member;
    }

    public int insertMember(TBUserDTO member) {
        int result = 0;

        try {
            String sql = "insert into tb_user(user_id,user_pass,user_name,user_email,user_phone,user_addr)";
            sql += "values(?,?,?,?,?,?) ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPass());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPass());
            pstmt.setString(6, member.getAddr());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("추가", e.getMessage());
        }


        return result;
    }


    public int updateMember(TBUserDTO member) {
        int result = 0;

        try {
            String sql = "update tb_user ";
            sql += "set user_email = ?,user_phone = ?,user_addr = ? ";
            sql += "where user_id = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getAddr());
            pstmt.setString(4, member.getId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("수정", e.getMessage());
        }


        return result;
    }

    public void delectMember(String userId){
        try {
            String sql = "delete from tb_user where user_id = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);

            pstmt.executeUpdate();

        }catch (SQLException e){
            printErrorMessage("삭제",e.getMessage());
        }
    }

    public int checkID(String id){
        int result = 0;
        try{
            String sql = "select count(*) as cnt from tb_user where user_id = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("cnt") == 1) {
                    result = 1;
                }
            }

        }catch (SQLException e){
            printErrorMessage("조회",e.getMessage());
        }

        return result;
    }



    private void printErrorMessage(String userMsg, String errMsg) {
        System.out.println("\n**********************************************************\n");
        System.out.println("데이터 베이스 " + userMsg + " 중 오류가 발생했습니다.");
        System.out.println("오류 내용 : " + errMsg);
        System.out.println("\n**********************************************************\n");
    }


}
