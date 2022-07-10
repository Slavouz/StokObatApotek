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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
                String noBatch = rs.getString("no_batch");
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

        tambahBtn.setOnAction((ActionEvent e) -> {
            mt.tambah(this);
        });

        updateBtn.setOnAction((ActionEvent e) -> {
            if (table.getSelectionModel().isEmpty()) {
                Alert em = new Alert(Alert.AlertType.INFORMATION);
                em.setTitle("Informasi");
                em.setHeaderText("Mohon untuk memilih item yang ingin di ubah");
                em.show();
            } else {
                StokObat so = (StokObat) table.getSelectionModel().getSelectedItem();
                Stage stage2 = new Stage();
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setAlignment(Pos.CENTER);
                Scene scene2 = new Scene(grid, 400, 500);

                // Barang
                Label namaBarang = new Label();
                namaBarang.setText("Masukan Nama Barang");
                grid.add(namaBarang, 0, 0);
                TextField namaBarangInput = new TextField();
                namaBarangInput.setText(so.getNama());
                grid.add(namaBarangInput, 1, 0);

                // NoBatch
                Label noBatch = new Label();
                noBatch.setText("Masukan Nomor Batch");
                grid.add(noBatch, 0, 1);
                TextField noBatchInput = new TextField();
                noBatchInput.setText(so.getNoBatch());
                grid.add(noBatchInput, 1, 1);

                // PBF
                Label PBF = new Label();
                PBF.setText("Masukan PBF");
                grid.add(PBF, 0, 2);
                TextField PBFInput = new TextField();
                PBFInput.setText(so.getPbf());
                grid.add(PBFInput, 1, 2);

                // Jenis
                Label jenis = new Label();
                jenis.setText("Masukan Jenis Obat");
                grid.add(jenis, 0, 3);
                ComboBox<String> jenisInput = new ComboBox<>();
                jenisInput.getItems().addAll(
                        "Obat Bebas",
                        "Obat Bebas Terbatas",
                        "Obat Keras",
                        "Psikotropika",
                        "Narkotika",
                        "Prekusor",
                        "OOT"
                );
                jenisInput.getSelectionModel().select(0);
                grid.add(jenisInput, 1, 3);

                // Satuan
                Label satuan = new Label();
                satuan.setText("Masukan Satuan");
                grid.add(satuan, 0, 4);
                TextField satuanInput = new TextField();
                satuanInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        satuanInput.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
                satuanInput.setText(so.getSatuan());
                grid.add(satuanInput, 1, 4);

                // Jumlah
                Label jumlah = new Label();
                jumlah.setText("Masukan Jumlah Stok");
                grid.add(jumlah, 0, 5);
                TextField jumlahInput = new TextField();
                jumlahInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        jumlahInput.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
                jumlahInput.setText(so.getStok());
                grid.add(jumlahInput, 1, 5);

                // Tanggal Masuk
                Label tglMasuk = new Label();
                tglMasuk.setText("Masukan Tanggal Masuk");
                grid.add(tglMasuk, 0, 6);
                DatePicker tglMasukInput = new DatePicker();
                grid.add(tglMasukInput, 1, 6);

                // Tanggal Keluar
                Label tglKeluar = new Label();
                tglKeluar.setText("Masukan Tanggal Keluar");
                grid.add(tglKeluar, 0, 7);
                DatePicker tglKeluarInput = new DatePicker();
                grid.add(tglKeluarInput, 1, 7);

                // Tanggal Expired
                Label exp = new Label();
                exp.setText("Masukan Tanggal Expired");
                grid.add(exp, 0, 8);
                DatePicker expInput = new DatePicker();
                grid.add(expInput, 1, 8);

                // Tanggal Harga1
                Label harga1 = new Label();
                harga1.setText("Masukan Harga 1");
                grid.add(harga1, 0, 9);
                TextField harga1Input = new TextField();
                harga1Input.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        harga1Input.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
                harga1Input.setText(so.getHarga1());
                grid.add(harga1Input, 1, 9);

                // Tanggal Harga2
                Label harga2 = new Label();
                harga2.setText("Masukan Harga 2");
                grid.add(harga2, 0, 10);
                TextField harga2Input = new TextField();
                harga2Input.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        harga2Input.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
                harga2Input.setText(so.getHarga2());
                grid.add(harga2Input, 1, 10);

                // Tanggal Diskon
                Label diskon = new Label();
                diskon.setText("Masukan Diskon");
                grid.add(diskon, 0, 11);
                TextField diskonInput = new TextField();
                diskonInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        diskonInput.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
                diskonInput.setText(so.getDiskon());
                grid.add(diskonInput, 1, 11);

                Button buat = new Button("Ubah");
                buat.setOnAction((ActionEvent ex) -> {
                    if (namaBarangInput.getText().isEmpty() || noBatchInput.getText().isEmpty() || PBFInput.getText().isEmpty() || satuanInput.getText().isEmpty() || jumlahInput.getText().isEmpty() || tglMasukInput.getValue() == null || tglKeluarInput.getValue() == null || expInput.getValue() == null || harga1Input.getText().isEmpty() || harga2Input.getText().isEmpty() || diskonInput.getText().isEmpty()) {
                        Alert em = new Alert(Alert.AlertType.ERROR);
                        em.setTitle("ERROR");
                        em.setHeaderText("Inputan tidak boleh kosong");
                        em.show();
                    } else {
                        String nB = namaBarangInput.getText();
                        String noB = noBatchInput.getText();
                        String pbf = PBFInput.getText();
                        String jns = jenisInput.getValue();
                        int stn = Integer.parseInt(satuanInput.getText());
                        int jml = Integer.parseInt(jumlahInput.getText());
                        String tglM = tglMasukInput.getValue().toString();
                        String tglK = tglKeluarInput.getValue().toString();
                        String expd = expInput.getValue().toString();
                        int h1 = Integer.parseInt(harga1Input.getText());
                        int h2 = Integer.parseInt(harga2Input.getText());
                        int dsc = Integer.parseInt(diskonInput.getText());
                        try {
                            String sql = "UPDATE stok_obat SET nama_barang = '" + nB
                                    + "', no_batch = '" + noB
                                    + "', PBF = '" + pbf
                                    + "', jenis = '" + jns
                                    + "', satuan = '" + stn
                                    + "', jumlah_stok = '" + jml
                                    + "', tgl_msk = '" + tglM
                                    + "', tgl_klr = '" + tglK
                                    + "', exp = '" + expd
                                    + "', harga1 = '" + h1
                                    + "', harga2 = '" + h2
                                    + "', diskon = '" + dsc
                                    + "' WHERE no = " + so.getId() + "";
                            Connection conn = koneksi.koneksiDB();
                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();

                            Alert info = new Alert(Alert.AlertType.INFORMATION);
                            info.setTitle("Informasi");
                            info.setHeaderText("Data berhasil diubah");
                            info.show();
                            mt.refTb(this);
                        } catch (SQLException exc) {
                            System.out.println(exc);
                            Alert s = new Alert(Alert.AlertType.ERROR);
                            s.setTitle("ERROR");
                            s.setHeaderText("Error: " + ex);
                            s.show();
                        }
                        stage2.close();
                    }
                });

                grid.add(buat, 0, 13);

                Button batal = new Button("Batal");
                batal.setOnAction((ActionEvent ec) -> {
                    stage2.close();
                });
                grid.add(batal, 1, 13);

                stage2.setTitle("Edit Daftar Obat");
                stage2.setScene(scene2);
                stage2.show();
            }
        });

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

//    public void refreshTable() {
//        ObservableList<StokObat> dataa = FXCollections.observableArrayList();
//        try {
//            String sql = "SELECT * from stok_obat";
//            Connection conn = koneksi.koneksiDB();
//            PreparedStatement pst = conn.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            table.getItems().clear();
//            while (rs.next()) {
//                int id = rs.getInt("no");
//                String namaBarang = rs.getString("nama_barang");
//                String noBatch = rs.getString("no_batch");
//                String pbf = rs.getString("PBF");
//                String jenis = rs.getString("jenis");
//                int satuan = rs.getInt("satuan");
//                int stok = rs.getInt("jumlah_stok");
//                String tglMsk = rs.getString("tgl_msk");
//                String tglKlr = rs.getString("tgl_klr");
//                String exp = rs.getString("exp");
//                int harga1 = rs.getInt("harga1");
//                int harga2 = rs.getInt("harga2");
//                int diskon = rs.getInt("diskon");
//
//                StokObat so = new StokObat(String.valueOf(id), namaBarang, noBatch, pbf, jenis, satuan, stok, tglMsk, tglKlr, exp, harga1, harga2, diskon);
//                data.add(so);
//            }
//            table.setItems(dataa);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
}
