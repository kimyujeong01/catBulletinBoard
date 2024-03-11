package com.bitc.project_kyj.DB;

import jakarta.servlet.ServletContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TBCommentDAO extends JDBConnect {
    public TBCommentDAO() {
        super();
    }

    public TBCommentDAO(ServletContext app) {
        super(app);
    }

    public TBCommentDAO(String dbDriver, String dbUrl, String dbUserId, String dbUserPw) {
        super(dbDriver, dbUrl, dbUserId, dbUserPw);
    }


    public List<TBCommentDTO> selectComment(int idx) {
        List<TBCommentDTO> commList = new ArrayList<>();

        try {
            String sql = "select (select user_name from tb_user where user_id = a.id) as name,content,date_format(postdate,'%Y%m%d') postdate,idx,id from tb_comment a where board_idx = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TBCommentDTO comment = new TBCommentDTO();

                comment.setName(rs.getString("name"));
                comment.setContent(rs.getString("content"));
                comment.setPostdate(rs.getString("postdate"));
                comment.setIdx(rs.getInt("idx"));
                comment.setId(rs.getString("id"));

                commList.add(comment);
            }
        }
        catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return commList;
    }

    public int insertComm(TBCommentDTO comment) {
        int result = 0;

        try {
            String sql = "insert into tb_comment(id,content,postdate,board_idx) ";
            sql += "values(?,?,now(),?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,comment.getId());
            pstmt.setString(2, comment.getContent());
            pstmt.setInt(3,comment.getBoard_idx());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("추가", e.getMessage());
        }
        return result;
    }

    public int deleteComm(int idx) {
        int result = 0;

        try {
            String sql = "delete from tb_comment where idx=?; ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("삭제", e.getMessage());
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
