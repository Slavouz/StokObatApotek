package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

public class create {
    model_create mc = new model_create();
    model_tabel mt = new model_tabel();
    public void createForm() {
        Stage stage = new Stage();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, 400, 500);

        // Barang
        Label namaBarang = new Label();
        namaBarang.setText("Masukan Nama Barang");
        grid.add(namaBarang, 0, 0);
        TextField namaBarangInput = new TextField();
        grid.add(namaBarangInput, 1, 0);

        // NoBatch
        Label noBatch = new Label();
        noBatch.setText("Masukan Nomor Batch");
        grid.add(noBatch, 0, 1);
        TextField noBatchInput = new TextField();
        grid.add(noBatchInput, 1, 1);

        // PBF
        Label PBF = new Label();
        PBF.setText("Masukan PBF");
        grid.add(PBF, 0, 2);
        TextField PBFInput = new TextField();
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
        grid.add(diskonInput, 1, 11);

        Button buat = new Button("Buat");
        buat.setOnAction((ActionEvent e) -> {
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
                    mc.tambah(nB, noB, pbf, jns, stn, jml, tglM, tglK, expd, h1, h2, dsc);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                stage.close();
            }
        });

        grid.add(buat, 0, 13);

        Button batal = new Button("Batal");
        batal.setOnAction((ActionEvent e) -> {
            stage.close();
        });
        grid.add(batal, 1, 13);

        stage.setTitle("Masukan Daftar Obat");
        stage.setScene(scene);
        stage.show();
    }
}
