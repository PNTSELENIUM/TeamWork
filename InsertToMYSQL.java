package ConnectToMySQL;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

    public class InsertToMYSQL {


        public static Connection connect = null;
        public static Statement statement = null;
        public static PreparedStatement ps = null;
        public static ResultSet rs = null;


        public static Properties loadProperties() throws IOException {
            Properties prop = new Properties();
            InputStream ism = new FileInputStream("src/secret.properties");
            prop.load(ism);
            ism.close();
            return prop;
        }
        public static Connection connectToMySQL() throws Exception {
            Properties prop = loadProperties();
            String driverClass = prop.getProperty("MYSQLJDBC.driver");
            String url = prop.getProperty("MYSQLJDBC.url");
            String userName = prop.getProperty("MYSQLJDBC.user");
            String password = prop.getProperty("MYSQLJDBC.password");
            Class.forName(driverClass);
            connect = DriverManager.getConnection(url,userName,password);
            System.out.println("Database is connected");
            return connect;

        }

        public static void main(String[] args) {
            try {
                // get the connection to the database
                Connection mycon = connectToMySQL();
                // Create a statement
                Statement statement = mycon.createStatement();

                // Write the sql query and execute it
                String sql = "Insert into Students(studentID, studentName,studentsDOB)"+
                        "values (206,'Masum Rahman','01-09-1989')";
              statement.executeUpdate(sql);

                // Process the result set

                    System.out.println("Insertion Complete");


            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }


