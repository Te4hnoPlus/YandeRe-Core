package plus.utl.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlSector {
    private final Statement statmt;
    private final String Table;
    private final List<String> Collums;
    private final String SaveFormat;

    private static boolean hasDriver = false;

    public SqlSector(String sqlName,String table,List<String> collums) {
        try {
            Collums = collums;
            Table = table==null?"YandeReDb":table;

            if(!hasDriver){
                Class.forName("org.sqlite.JDBC");
                hasDriver = true;
            }

            Connection conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s.s3db", sqlName));
            statmt = conn.createStatement();
            statmt.execute(String.format("CREATE TABLE if not exists '%s' ('user' text);", table));
            System.out.println("| YandeRe : SqLite initialized");
        } catch (Exception err) {
            err.printStackTrace();
            throw new NullPointerException();
        }

        for(String s:Collums){
            try {
                statmt.execute(String.format("SELECT %s FROM %s", s, table));
            } catch (SQLException e){
                try {
                    System.out.println(String.format("| YandeRe : add %s to table [%s]", s, table));
                    statmt.execute(String.format("ALTER TABLE %s ADD '%s' text", table, s));
                } catch (Exception er){
                    System.out.println("| YandeRe : SqLite critical error!");
                    er.printStackTrace();
                    throw new NullPointerException();
                }
            }
        }
        SaveFormat = String.format("INSERT INTO '%s' ('user', '%s') VALUES ('%%s')", Table, (String.join("', '", Collums)));
    }

    public void removeData(String user) {
        try {
            statmt.execute(String.format("DELETE FROM '%s' WHERE user = '%s' ", Table, user));
        } catch (SQLException err) {
            //err.printStackTrace();
        }
    }

    public List<String> getSavedData(String user){

        try {
            ResultSet rawResult = statmt.executeQuery(String.format("SELECT * FROM %s WHERE user = '%s' ", Table, user));
            if(rawResult.isClosed())return null;
            List<String> result = new ArrayList<>();
            for(String c:Collums){
                result.add(rawResult.getString(c));
            }
            return result;
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }

    public void saveData(String user, List<String> data){
        removeData(user);
        try {
            data.add(0, user);
            statmt.execute(String.format( SaveFormat, (String.join("', '", data)) ));
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }


}
