import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Gaji implements PTABC {
    static Connection con;
	String url = "jdbc:mysql://localhost:3306/gaji_karyawan";

    public String noPegawai;
    public Integer kategori;
    public Integer gajiPokok;
    public Integer libur;
    public Integer kehadiran;
    public Integer potongan;
    public Integer totalGaji;
    public String jabatan;
    public String namaPegawai;
    
    Scanner text = new Scanner(System.in);

    @Override
    public void show() throws SQLException {
        String judul = "Info Gaji";
        String judulDua = judul.replace("Info Gaji", "INFORMASI DAFTAR GAJI KARYAWAN"); 
        System.out.println(judulDua);
						
		String sql ="SELECT * FROM daftar_gaji";
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

    @Override
    public void insert() throws SQLException {
        String text2 = "\nInput Data Pegawai";
		System.out.println(text2.toUpperCase());
		
    	try {
        // No Pegawai
        System.out.print("No Pegawai \t\t : ");
        this.noPegawai = text.next();

        // Nama Pegawai
        System.out.print("Nama Pegawai \t\t : ");
        this.namaPegawai = text.next();

        // Pilih Jabatan
        System.out.println("\n-Kategori Jabatan-");
        System.out.println("1 = Komisaris \n2 = Direktur Utama \n3 = Sekretaris \n4 = Internal Audit \n5 = Manager\n");
        do{
            System.out.print("Pilihan Jabatan (no) \t : ");
            this.kategori = text.nextInt(); 
            if (kategori <1 || kategori >5){
                System.out.println("Input nomor kategori jabatan yang benar! ");
            }
        }
            while (kategori <1 || kategori >5);
                switch(kategori){
                case 1 :
                this.jabatan = "Komisaris";
                break;
                case 2 :
                this.jabatan = "Direktur Utama";
                break;
                case 3:
                this.jabatan = "Sekretaris";
                break;
                case 4 :
                this.jabatan = "Internal Audit";
                break;
                case 5 :
                this.jabatan = "Manager";
                break;
                default :
                System.out.println("Input nomor kategori jabatan yang benar!");
                }
        
        // Gaji Pokok
        switch(kategori){
            case 1 :
            gajiPokok = 30000000;
            break;
            case 2 :
            gajiPokok = 25000000;
            break;
            case 3:
            gajiPokok = 20000000;
            break;
            case 4 :
            gajiPokok = 15000000;
            break;
            case 5 :
            gajiPokok = 10000000;
            break;
            default :
            System.out.println();
        }

        // Jumlah kehadiran
        System.out.print("Jumlah Libur\t\t : ");
        this.libur = text.nextInt();
        if (libur >30 || libur < 1){
            throw new ArithmeticException("Inputkan data dengan benar! (hitungan dalam 30 hari)");
        }
        else{
            kehadiran = 30 - libur;
        }

        //Potongan
        potongan = libur * 50000;

        //Total gaji
        totalGaji = gajiPokok - potongan;

        String sql = "INSERT INTO daftar_gaji (no_pegawai, nama_pegawai, jabatan, kehadiran, potongan, total_gaji) VALUES ('"+noPegawai+"','"+namaPegawai+"','"+jabatan+"','"+kehadiran+"','"+potongan+"','"+totalGaji+"')";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        statement.execute(sql);
        System.out.println("Data berhasil di inputkan!");
    }
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan input data");
    } 
    catch (InputMismatchException e) {
        System.err.println("Inputan harus berupa angka");
       }
    }

    @Override
    public void edit() throws SQLException{
        String text3 = "\nEdit Data Pegawai";
		System.out.println(text3.toUpperCase());
        try {
            show();
            System.out.print("\nInput No Pegawai : ");
            String keyword = text.nextLine();
            
            String sql = "SELECT * FROM daftar_gaji WHERE no_pegawai LIKE '%"+keyword+"%'";
            con = DriverManager.getConnection(url,"root","");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){ 
                
                System.out.print("Nama baru ["+result.getString("nama_pegawai")+"]\t: ");
                String namaPegawai = text.nextLine();
                   
                sql = "UPDATE daftar_gaji SET nama_pegawai='"+namaPegawai+"' WHERE no_pegawai='"+noPegawai+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Data pegawai telah diperbarui (Nomor "+noPegawai+")");
                }
            }
            statement.close();        
        } 
		catch (SQLException e) {
        	System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
	}
    
    public void delete() {}

    public void search() throws SQLException {}
}
