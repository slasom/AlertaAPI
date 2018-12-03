package io.swagger.api.basededatos;

import java.sql.*;

public class ConexionBD {

    // Ruta de nuestra base de datos
    private String servidor = "jdbc:mysql://sql7.freemysqlhosting.net/sql7267476";
    //private String servidor = System.getenv("JDBC_DATABASE_URL");

    // Nombre de usuario de mysql
    private String username = "sql7267476";
    //private String username = System.getenv("JDBC_DATABASE_USERNAME");

    // Clave de usuario de mysql
    private String password = "9EqPaz4ZjV";
    //private String password = System.getenv("JDBC_DATABASE_PASSWORD");

    // Nuestra librería mysql
    private String driver = "com.mysql.jdbc.Driver";

    // Objeto del tipo Connection para crear la conexión
    private Connection con;

    public void Conexion()
    
    {
        try {
            // Cargar drivers de MySQL
            Class.forName(driver);

            // Establecer la conexion con la base de datos
            //String urlTotal="jdbc:mysql://sql7.freemysqlhosting.net/sql7267476?user=sql7267476&password=9EqPaz4ZjV";
            //con = DriverManager.getConnection(servidor, username, password);
            con=DriverManager.getConnection(System.getenv("mysql://b162b90eac8d6b:b07df736@eu-cdbr-west-02.cleardb.net/heroku_2775fdda06665ac?reconnect=true"));
            //con = DriverManager.getConnection(urlTotal);


            System.out.println("Conexión realizada a la base de datos con éxito.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error!, conexión fallida a la base de datos.");
        }
        
    }

    public Connection getConnection() {
        return con; // Retorno el objeto Connection
    }
    
    public void insertarCaida(float t_respuesta, String fecha_hora, boolean esCaida) {


        // Pasamos el objeto Connection de nuestra clase "ConexionBD" a esta instancia por medio del método getConnection()
        //Connection con = getConnection();
        
        PreparedStatement preparedStatement;

        // Crear sentencia SQL para insertar en la base de datos
       String insertTableSQL = "INSERT INTO alerta_caidas"
                + "(t_respuesta, fecha_hora, es_caida) VALUES"
                + "(?,?,?)";

        try {
            Connection con = this.con;
            preparedStatement = con.prepareStatement(insertTableSQL);

            preparedStatement.setFloat(1, t_respuesta);
            preparedStatement.setString(2, fecha_hora); //Formato: "Y-m-d H:i:s"
            preparedStatement.setBoolean(3, esCaida);
          
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Alerta insertada en la BD!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
    }
}
