package DatabaseConnectivity;

import java.sql.*;

public class JavaPreparedStatements {
    public void JavaPreparedStatements(){

    }

    String username = "postgres";
    String password = "postgres";
    String url  ="jdbc:postgresql://localhost:5432/platform_data_local";


    public String getUserByUserID(Integer id ) throws SQLException {
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name =null;

        try{
            con = DriverManager.getConnection(url,username,password);
            String query = "select * from users where id =?";
            ps= con.prepareStatement(query);
            ps.setInt(1,id);

            rs= ps.executeQuery();
            while (rs.next()){
                name = rs.getString("name");
            }


        }catch (Exception e){
             e.printStackTrace();
        }finally {
            if (ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
            if(con!=null){
                con.close();
            }
        }

        return name;
    }


    public void  insertUsersTable(String name, int age,int salary) throws SQLException {
        Connection con =null;
        PreparedStatement ps =null;
        int rows;

        try{
            con= DriverManager.getConnection(url,username,password);
            String sql ="insert into users (name,age,salary) values(?,?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setInt(3,salary);
           rows= ps.executeUpdate();
            System.out.println("inserted rows "+rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null){
                ps.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }


    public String  getName() throws SQLException {
        Connection con =null;
        Statement st = null;
        ResultSet rs =null;
        String name =null;

        try{
            con= DriverManager.getConnection(url,username,password);

            st= con.createStatement();

            rs= st.executeQuery("select * from users where id =3");
            while (rs.next()){
                name = rs.getString("name");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(rs!=null){
                rs.close();
            }
            if(st!=null){
                st.close();
            }
            if(con!=null){
                con.close();
            }
        }


        return name;
    }

}
