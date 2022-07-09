package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateView {
    public void createForm() {
        Stage stage = new Stage();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, 600, 800);

        // Barang
        Label namaBarang = new Label();
        namaBarang.setText("Masukan Nama Barang");
        grid.add(namaBarang, 1, 0);
        TextField namaBarangInput = new TextField();
        grid.add(namaBarangInput, 1, 1);

        // NoBatch
        Label noBatch = new Label();
        noBatch.setText("Masukan Nomor Batch");
        grid.add(noBatch, 1, 4);
        TextField noBatchInput = new TextField();
        noBatchInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    noBatchInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(noBatchInput, 1, 5);

        // PBF
        Label PBF = new Label();
        PBF.setText("Masukan PBF");
        grid.add(PBF, 1, 8);
        TextField PBFInput = new TextField();
        grid.add(PBFInput, 1, 9);

        // Jenis
        Label jenis = new Label();
        jenis.setText("Masukan Jenis Obat");
        grid.add(jenis, 1, 11);
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
        grid.add(jenisInput, 1, 12);

        // Satuan
        Label satuan = new Label();
        satuan.setText("Masukan Satuan");
        grid.add(satuan, 1, 15);
        TextField satuanInput = new TextField();
        satuanInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    satuanInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        grid.add(satuanInput, 1, 16);

        // Jumlah
        Label jumlah = new Label();
        jumlah.setText("Masukan Jumlah Stok");
        grid.add(jumlah, 2, 0);
        TextField jumlahInput = new TextField();
        jumlahInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    jumlahInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(jumlahInput, 2, 1);

        // Tanggal Masuk
        Label tglMasuk = new Label();
        tglMasuk.setText("Masukan Tanggal Masuk");
        grid.add(tglMasuk, 2, 4);
        DatePicker tglMasukInput = new DatePicker();
        grid.add(tglMasukInput, 2, 5);

        // Tanggal Keluar
        Label tglKeluar = new Label();
        tglKeluar.setText("Masukan Tanggal Keluar");
        grid.add(tglKeluar, 2, 8);
        DatePicker tglKeluarInput = new DatePicker();
        grid.add(tglKeluarInput, 2, 9);

        // Tanggal Expired
        Label exp = new Label();
        exp.setText("Masukan Tanggal Expired");
        grid.add(exp, 2, 11);
        DatePicker expInput = new DatePicker();
        grid.add(expInput, 2, 12);

        // Tanggal Harga1
        Label harga1 = new Label();
        harga1.setText("Masukan Harga 1");
        grid.add(harga1, 2, 15);
        TextField harga1Input = new TextField();
        harga1Input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    harga1Input.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(harga1Input, 2, 16);

        // Tanggal Harga2
        Label harga2 = new Label();
        harga2.setText("Masukan Harga 2");
        grid.add(harga2, 3, 0);
        TextField harga2Input = new TextField();
        harga2Input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    harga2Input.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(harga2Input, 3, 1);

        // Tanggal Diskon
        Label diskon = new Label();
        diskon.setText("Masukan Diskon");
        grid.add(diskon, 3, 4);
        TextField diskonInput = new TextField();
        diskonInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    diskonInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(diskonInput, 3, 5);

        Button buat = new Button("Buat");
        buat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(jenisInput.getValue());
            }
        });

        grid.add(buat, 1, 25);

        Button clear = new Button("Kosongkan Form");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                namaBarangInput.clear();
                noBatchInput.clear();
                PBFInput.clear();
                jenisInput.setValue(null);
                satuanInput.clear();
                jumlahInput.clear();
                tglMasukInput.setValue(null);
                tglKeluarInput.setValue(null);
                expInput.setValue(null);
                harga1Input.clear();
                harga2Input.clear();
                diskonInput.clear();

            }
        });
        grid.add(clear, 2, 25);

        Button batal = new Button("Batal");
        batal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.close();
            }
        });
        grid.add(batal, 3, 25);

        stage.setTitle("Masukan Daftar Obat");
        stage.setScene(scene);
        stage.show();
    }
}
