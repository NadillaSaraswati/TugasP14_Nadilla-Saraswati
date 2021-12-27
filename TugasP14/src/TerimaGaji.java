import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class TerimaGaji extends Gaji {
    static Connection con;
	String url = "jdbc:mysql://localhost:3306/gaji_karyawan";

    @Override
    public void delete() {
		String text4 = "\nHapus Data Pegawai";
		System.out.println(text4.toUpperCase());
		
		try{
	        show();
            System.out.print("\nInput No Pegawai : ");
            String keyword = text.nextLine();
	        
	        String sql = "DELETE FROM daftar_gaji WHERE no_pegawai LIKE '%"+keyword+"%'";
	        con = DriverManager.getConnection(url,"root","");
	        Statement statement = con.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Data pegawai berhasil dihapus (Nomor "+noPegawai+")");
	        }
	   }
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e){
            System.out.println("Input data yang benar!");
        }
	}

    @Override
    public void search() throws SQLException {
		String text5 = "\nCari Data Pegawai";
		System.out.println(text5.toUpperCase());
				
		System.out.print("Input nama pegawai : ");    
		String keyword = text.nextLine();
		
		String sql = "SELECT * FROM daftar_gaji WHERE nama_pegawai LIKE '%"+keyword+"%'";
		con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
            System.out.print("\nNomor Pegawai\t\t : ");
            System.out.print(result.getString("no_pegawai"));
            System.out.print("\nNama Pegawai\t\t : ");
            System.out.print(result.getString("nama_pegawai"));
            System.out.print("\nJabatan\t\t\t : ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nJumlah Kehadiran\t : ");
            System.out.print(result.getInt("kehadiran"));
            System.out.print("\nPotongan Gaji\t\t : ");
            System.out.print(result.getInt("potongan"));
            System.out.print("\nTotal gaji\t\t : ");
            System.out.print(result.getInt("total_gaji"));
            System.out.print("\n");
	    }   
    }
}
