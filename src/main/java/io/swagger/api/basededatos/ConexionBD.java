package io.swagger.api.basededatos;

import java.sql.*;

public class ConexionBD {

    // Ruta de nuestra base de datos
    //private String servidor = "jdbc:mysql://masterjss.000webhostapp.com/id8177062_masterjss";
    private String servidor = "jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_2775fdda06665ac";

    // Nombre de usuario de mysql
    //private String username = "id8177062_root";
    private String username = "b162b90eac8d6b";

    // Clave de usuario de mysql
    //private String password = "masterJSS";
    private String password = "e393c0d385012b0";

    // Nuestra librería mysql
   // private String driver = "com.mysql.jdbc.Driver";

    // Objeto del tipo Connection para crear la conexión
    private Connection con;
    
    

    public void Conexion()
    
    {
        
        
        try {
            // Cargar drivers de MySQL
            //Class.forName(driver);
            
            // Establecer la conexion con la base de datos
            //jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
            
            con = DriverManager.getConnection(servidor, username, password);
            //con=DriverManager.getConnection(System.getenv("CLEARDB_DATABASE_URL"));
            //con = DriverManager.getConnection(urlTotal);


            System.out.println("Conexión realizada a la base de datos con éxito.");
        } catch (/*ClassNotFoundException |*/ SQLException e) {
            e.printStackTrace();
            System.out.println("Error!, conexión fallida a la base de datos.");
        }
        
    }

    public Connection getConnection() {
        return con; // Retorno el objeto Connection
    }
    
    public void insertarCaida(float t_respuesta, Timestamp fecha_hora, boolean esCaida) {


        // Pasamos el objeto Connection de nuestra clase "ConexionBD" a esta instancia por medio del método getConnection()
        //Connection con = getConnection();
        
        PreparedStatement preparedStatement;

        // Crear sentencia SQL para insertar en la base de datos
       String insertTableSQL = "INSERT INTO alerta_caidas"
                + "(t_respuesta, time, es_caida) VALUES"
                + "(?,?,?)";

        try {
            Connection con = this.con;
            preparedStatement = con.prepareStatement(insertTableSQL);

            preparedStatement.setFloat(1, t_respuesta);
            preparedStatement.setTimestamp(2, fecha_hora); //Formato: "Y-m-d H:i:s"
            preparedStatement.setBoolean(3, esCaida);
          
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Alerta insertada en la BD!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
    }
    
    public void insertarBateria(int id, float t_respuesta, Timestamp fecha_hora) {


        // Pasamos el objeto Connection de nuestra clase "ConexionBD" a esta instancia por medio del método getConnection()
        //Connection con = getConnection();
        
        PreparedStatement preparedStatement;

        // Crear sentencia SQL para insertar en la base de datos
       String insertTableSQL = "INSERT INTO alerta_bateria"
                + "(idDispositivo, t_respuesta, time) VALUES"
                + "(?,?,?)";

        try {
            Connection con = this.con;
            preparedStatement = con.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, id);
            preparedStatement.setFloat(2, t_respuesta);
            preparedStatement.setTimestamp(3, fecha_hora); //Formato: "Y-m-d H:i:s"
           
          
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Alerta insertada en la BD!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } 
    }
}
