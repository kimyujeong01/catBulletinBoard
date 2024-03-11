package com.bitc.project_kyj.DB;

import jakarta.servlet.ServletContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TBBoardDAO extends JDBConnect {
    public TBBoardDAO() {
        super();
    }

    public TBBoardDAO(ServletContext app) {
        super(app);
    }

    public TBBoardDAO(String dbDriver, String dbUrl, String dbUserId, String dbUserPw) {
        super(dbDriver, dbUrl, dbUserId, dbUserPw);
    }


    //상세 글 보기
    public TBBoardDTO selectMVCBoardDetail(int idx) {
        TBBoardDTO board = new TBBoardDTO();

        try {
            String sql = "select idx,title,content,ofile,sfile,id,pass from tb_board where idx = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                board.setIdx(rs.getInt("idx"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setOfile(rs.getString("ofile"));
                board.setSfile(rs.getString("sfile"));
                board.setId(rs.getString("id"));
                board.setPass(rs.getString("pass"));
            }

        } catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return board;
    }

    public List<TBBoardDTO> selectMVCBoardListPaging(int start, int pageSize) {
        List<TBBoardDTO> boardList = new ArrayList<>();

        try {
            String sql = "select idx,title,date_format(postdate,'%Y%m%d') postdate,visitcount,";
            sql += "(select user_name from tb_user where user_id = a.id) name, ";
            sql += "(select count(*) from tb_comment where board_idx = a.idx) as commcnt ";
            sql += "from tb_board a order by idx desc ";
            sql += "LIMIT ?, ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start); // 데이터를 가져오기 시작할 index, index는 0부터 시작
            pstmt.setInt(2, pageSize); // 가져올 데이터 수 지정

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TBBoardDTO board = new TBBoardDTO();

                board.setIdx(rs.getInt("idx"));
                board.setTitle(rs.getString("title"));
                board.setName(rs.getString("name"));
                board.setPostdate(rs.getString("postdate"));
                board.setVisitcount(rs.getInt("visitcount"));
                board.setCommcnt(rs.getInt("commcnt"));

                boardList.add(board);
            }
        } catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return boardList;
    }

    //글 조회수 증가
    public void updateVisitCount(int idx) {
        try {
            String sql = "update tb_board ";
            sql += "set visitcount = visitcount + 1 ";
            sql += "where idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("수정", e.getMessage());
        }

    }

    public int insertBoard(TBBoardDTO board) {
        int result = 0;

        try {
            String sql = "insert into tb_board(id,title,content,postdate,ofile,sfile,pass) ";
            sql += "values(?,?,?,now(),?,?,?) ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getId());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            pstmt.setString(4, board.getOfile());
            pstmt.setString(5, board.getSfile());
            pstmt.setString(6, board.getPass());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("추가", e.getMessage());
        }
        return result;
    }

    public boolean conFirmPassword(int idx, String pass) {
        boolean result = false;

        try {
            String sql = "select count(*) as cnt from tb_board where idx = ? and pass = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            pstmt.setString(2, pass);
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

    public int deleteBoard(int idx) {
        int result = 0;

        try {
            String sql = "delete from tb_board where idx = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("삭제", e.getMessage());
        }

        return result;
    }

    public int updateBoard(TBBoardDTO board) {
        int result = 0;

        try {
            String sql = "update tb_board ";
            sql += "set title = ?,content = ?,ofile = ?,sfile = ? ";
            sql += "where idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getOfile());
            pstmt.setString(4, board.getSfile());
            pstmt.setInt(5, board.getIdx());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("수정", e.getMessage());
        }

        return result;
    }


    public int updateBoardNotfile(TBBoardDTO board) {
        int result = 0;

        try {
            String sql = "update tb_board ";
            sql += "set title = ?,content = ? ";
            sql += "where idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getIdx());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            printErrorMessage("수정", e.getMessage());
        }

        return result;
    }

    public int totalCount() {
        int result = 0;

        try {
            String sql = "SELECT COUNT(*) AS cnt FROM tb_board ";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
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
