package model;

/**
 *
 * @author davinky
 */
import controller.controller_create;
import java.sql.*;
import javafx.scene.control.Alert;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
import view.*;

public class model_create implements controller_create {

    model_tabel mt = new model_tabel();
    tabel tb = new tabel();

    @Override
    public void tambah(String namaBarang, String no_batch, String pbf, String jenis, int satuan, int jumlahStok, String tglMsk, String tglKlr, String exp, int harga1, int harga2, int diskon) throws SQLException {
        try {
            String sqlT = "INSERT INTO stok_obat (nama_barang, no_batch, PBF, jenis, satuan, jumlah_stok, tgl_msk, tgl_klr, exp, harga1, harga2, diskon) VALUES ('" + namaBarang + "',  '" + no_batch + "', '" + pbf + "', '" + jenis + "', '" + satuan + "', '" + jumlahStok + "', '" + tglMsk + "', '" + tglKlr + "', '" + exp + "', '" + harga1 + "', '" + harga2 + "', '" + diskon + "')";
            Connection conn = koneksi.koneksi.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sqlT);
            pst.executeUpdate();
            Alert s = new Alert(Alert.AlertType.INFORMATION);
            s.setTitle("Berhasil");
            s.setHeaderText("Data berhasil ditambah");
            s.show();
            mt.refTb(tb);
//            tb.refreshTable();
        } catch (SQLException ex) {
            System.out.println(ex);
            Alert s = new Alert(Alert.AlertType.ERROR);
            s.setTitle("ERROR");
            s.setHeaderText("Error: " + ex);
            s.show();
        }
    }

}
