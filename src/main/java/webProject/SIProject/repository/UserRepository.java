package webProject.SIProject.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import webProject.SIProject.DBConnectoinUtil;
import webProject.SIProject.domain.User;

import java.sql.*;

@Slf4j
@Repository
public class UserRepository {
    public User findByUsername(String username) throws SQLException {
        String sql = "select username, password, role from USER where username="+username;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;
        User user = null;

        try{
            con = getConnection();
            pstm=con.prepareStatement(sql);
            rs =pstm.executeQuery();


//            while (rs.next()){
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                if(rs.getString("role") == Role.USER.toString()){
//                    user.setRole(Role.USER);
//                }else if(rs.getString("role") == Role.MANAGER.toString()){
//                    user.setRole(Role.MANAGER);
//                }else{
//                    user.setRole(Role.ADMIN);
//                }
//            }

            return user;
        }catch(SQLException e) {
            log.error("db error",e);
            throw e;
        } finally {
            close(con,pstm,null);
        }
    }

    public String save(User user) throws SQLException {
        log.info("insert save");
        String sql = "insert into USER(id, username, password, role, createTime) values(?,?,?,?,?)";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = null;

        try{
            con = getConnection();
            pstm=con.prepareStatement(sql);

            pstm.setString(1,user.getId().toString());
            pstm.setString(2,user.getUsername());
            pstm.setString(3,user.getPassword());
            pstm.setInt(4, user.getRole().ordinal());
            pstm.setString(5,user.getCreateTime().toString());

            pstm.executeUpdate();
            return "ok";
        }catch(SQLException e) {
            log.error("db error",e);
            throw e;
        } finally {
            close(con,pstm,null);
        }
    }

    private static Connection getConnection() {
        return DBConnectoinUtil.getConnection();
    }
    private void close(Connection con, Statement stmt, ResultSet rs){

        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                log.info("error",e);
            }
        }

        if(stmt != null){
            try{
                stmt.close();
            }catch (SQLException e){
                log.info("error",e);
            }
        }
        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                log.info("error",e);
            }
        }
    }
}
