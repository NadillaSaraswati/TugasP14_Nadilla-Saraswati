import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {
    static Connection con;
    public static void main(String[] args) throws Exception {
    
        Scanner text = new Scanner (System.in);
    	String pilihanUser;
    	boolean isLanjutkan = true;

        String url = "jdbc:mysql://localhost:3306/gaji_karyawan";

    try {
        TerimaGaji karyawan =new TerimaGaji();

        Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,"root","");
		System.out.println("Class Driver ditemukan!");
        
        while (isLanjutkan) {
            System.out.println("*GAJI KARYAWAN PT ABC* \n");
            System.out.println("1. Lihat Data Pegawai");
            System.out.println("2. Tambah Data Pegawai");
            System.out.println("3. Ubah Data Pegawai");
            System.out.println("4. Hapus Data Pegawai");
            System.out.println("5. Cari Data Pegawai");
            
            System.out.print("\nInput Pilihan anda (1/2/3/4/5): ");
            pilihanUser = text.next();
            
            switch (pilihanUser) {
            case "1":
                karyawan.show();
                break;
            case "2":
                karyawan.insert();
                break;
            case "3":
                karyawan.edit();
                break;
            case "4":
                karyawan.delete();
                break;
            case "5":
                karyawan.search();
                break;
            default:
                System.err.println("\nInput dengan benar!\nSilahkan input angka 1/2/3/4/5");
            }
            
            System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
            pilihanUser = text.next();
            isLanjutkan = pilihanUser.equalsIgnoreCase("y");

        }
        System.out.println("Program selesai!");

    }

    catch(ClassNotFoundException ex) {
        System.err.println("Driver Error");
        System.exit(0);
    }
    
    catch(SQLException e){
        System.err.println("Tidak berhasil koneksi");
    }
    
    finally{
        System.out.println("\nProgram Selesai\n");
        LocalDate tanggalSekarang = LocalDate.now();
        LocalTime waktuSekarang = LocalTime.now();
        System.out.println("Diakses pada :");
        System.out.println("Tanggal\t= "+tanggalSekarang.toString());
        System.out.println("Waktu\t= "+waktuSekarang.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println();
    }
    }
}
