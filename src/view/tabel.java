/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import koneksi.koneksi;
import model.model_tabel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.model_tabel.StokObat;

/**
 *
 * @author davinky
 */
public class tabel extends Application {

    model_tabel mt = new model_tabel();
    public ObservableList<StokObat> data = FXCollections.observableArrayList();
    public TableView table = new TableView();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        ObservableList<StokObat> data = FXCollections.observableArrayList();
        try {
            koneksi.koneksiDB();
            System.out.println("Berhasil terkoneksi");
        } catch (SQLException e) {
            System.out.println(e);
        }

        stage.setTitle("Data Stok Obat");
        stage.setWidth(1080);
        stage.setHeight(600);
        table.setEditable(true);

        Button refBtn = new Button("Refresh");
        Button tambahBtn = new Button("Tambah");
        Button updateBtn = new Button("Ubah");
        Button hapusBtn = new Button("Hapus");

        TableColumn idC = new TableColumn("No.");
        TableColumn namaBarangC = new TableColumn("Nama Barang");
        TableColumn noBatchC = new TableColumn("No. Batch");
        TableColumn pbfC = new TableColumn("PBF");
        TableColumn jenisC = new TableColumn("Jenis");
        TableColumn satuanC = new TableColumn("Satuan");
        TableColumn stokC = new TableColumn("Jumlah Stok");
        TableColumn tglMskC = new TableColumn("Tanggal Masuk");
        TableColumn tglKlrC = new TableColumn("Tanggal Keluar");
        TableColumn expC = new TableColumn("Exp. Date");
        TableColumn harga1C = new TableColumn("Harga 1");
        TableColumn harga2C = new TableColumn("Harga 2");
        TableColumn diskonC = new TableColumn("Diskon");

        table.getColumns().addAll(idC, namaBarangC, noBatchC, pbfC, jenisC, satuanC, stokC, tglMskC, tglKlrC, expC, harga1C, harga2C, diskonC);
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
        final VBox tbox = new VBox();
        vbox.setSpacing(8);
        hbox.setSpacing(8);
        tbox.setSpacing(8);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setPadding(new Insets(5, 10, 10, 10));
        tbox.setPadding(new Insets(5, 0, 10, 10));
        hbox.setAlignment(Pos.CENTER);
        tbox.setAlignment(Pos.CENTER_RIGHT);
        tbox.getChildren().add(refBtn);
        hbox.getChildren().addAll(tambahBtn, updateBtn, hapusBtn);
        vbox.getChildren().addAll(tbox, table, hbox);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        try {
            String sql = "SELECT * from stok_obat";
            Connection conn = koneksi.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("no");
                String namaBarang = rs.getString("nama_barang");
                int noBatch = rs.getInt("no_batch");
                String pbf = rs.getString("PBF");
                String jenis = rs.getString("jenis");
                int satuan = rs.getInt("satuan");
                int stok = rs.getInt("jumlah_stok");
                String tglMsk = rs.getString("tgl_msk");
                String tglKlr = rs.getString("tgl_klr");
                String exp = rs.getString("exp");
                int harga1 = rs.getInt("harga1");
                int harga2 = rs.getInt("harga2");
                int diskon = rs.getInt("diskon");

                StokObat so = new StokObat(String.valueOf(id), namaBarang, noBatch, pbf, jenis, satuan, stok, tglMsk, tglKlr, exp, harga1, harga2, diskon);
                data.add(so);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        idC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("id")
        );
        namaBarangC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("nama")
        );
        noBatchC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("noBatch")
        );
        pbfC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("Pbf")
        );
        jenisC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("jenis")
        );
        satuanC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("satuan")
        );
        stokC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("Stok")
        );
        tglMskC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("tglMsk")
        );
        tglKlrC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("tglKlr")
        );
        expC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("exp")
        );
        harga1C.setCellValueFactory(new PropertyValueFactory<StokObat, String>("harga1")
        );
        harga2C.setCellValueFactory(new PropertyValueFactory<StokObat, String>("harga2")
        );
        diskonC.setCellValueFactory(new PropertyValueFactory<StokObat, String>("diskon")
        );
        table.setItems(data);

        refBtn.setOnAction((ActionEvent e) -> {
            try {
                mt.refTb(this);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        
        hapusBtn.setOnAction((ActionEvent e) -> {
            try {
                mt.hapus(this);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
