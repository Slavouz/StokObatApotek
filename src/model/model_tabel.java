package model;

/**
 *
 * @author davinky
 */
import controller.controller_tabel;
import java.sql.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
import view.*;

public class model_tabel implements controller_tabel {

    public static class StokObat {

        private final SimpleStringProperty id;
        private final SimpleStringProperty nama_barang;
        private final SimpleStringProperty no_batch;
        private final SimpleStringProperty pbf;
        private final SimpleStringProperty jenis;
        private final SimpleStringProperty satuan;
        private final SimpleStringProperty stok;
        private final SimpleStringProperty tgl_msk;
        private final SimpleStringProperty tgl_klr;
        private final SimpleStringProperty exp;
        private final SimpleStringProperty harga1;
        private final SimpleStringProperty harga2;
        private final SimpleStringProperty diskon;

        public StokObat(String id, String nama_barang, String no_batch, String pbf, String jenis, int satuan, int stok, String tgl_msk, String tgl_klr, String exp, int harga1, int harga2, int diskon) {
            this.id = new SimpleStringProperty(id);
            this.nama_barang = new SimpleStringProperty(nama_barang);
            this.no_batch = new SimpleStringProperty(no_batch);
            this.pbf = new SimpleStringProperty(pbf);
            this.jenis = new SimpleStringProperty(jenis);
            this.satuan = new SimpleStringProperty(String.valueOf(satuan));
            this.stok = new SimpleStringProperty(String.valueOf(stok));
            this.tgl_msk = new SimpleStringProperty(tgl_msk);
            this.tgl_klr = new SimpleStringProperty(tgl_klr);
            this.exp = new SimpleStringProperty(exp);
            this.harga1 = new SimpleStringProperty(String.valueOf(harga1));
            this.harga2 = new SimpleStringProperty(String.valueOf(harga2));
            this.diskon = new SimpleStringProperty(String.valueOf(diskon));
        }

        public String getId() {
            return id.get();
        }

        public void setId(String fId) {
            id.set(fId);
        }

        public String getNama() {
            return nama_barang.get();
        }

        public void setNama(String fNama) {
            nama_barang.set(fNama);
        }

        public String getNoBatch() {
            return no_batch.get();
        }

        public void setNoBatch(int fBatch) {
            no_batch.set(String.valueOf(no_batch));
        }

        public String getPbf() {
            return pbf.get();
        }

        public void setPbf(String fPbf) {
            pbf.set(fPbf);
        }

        public String getJenis() {
            return jenis.get();
        }

        public void setJenis(String fJenis) {
            jenis.set(fJenis);
        }

        public String getSatuan() {
            return satuan.get();
        }

        public void setSatuan(String fSatuan) {
            satuan.set(fSatuan);
        }

        public String getStok() {
            return stok.get();
        }

        public void setStok(String fStok) {
            stok.set(fStok);
        }

        public String getTglMsk() {
            return tgl_msk.get();
        }

        public void setTglMsk(String fTglMsk) {
            tgl_msk.set(fTglMsk);
        }

        public String getTglKlr() {
            return tgl_klr.get();
        }

        public void setTglKlr(String fTglKlr) {
            tgl_klr.set(fTglKlr);
        }

        public String getExp() {
            return exp.get();
        }

        public void setExp(String fExp) {
            exp.set(fExp);
        }

        public String getHarga1() {
            return harga1.get();
        }

        public void setHarga1(String fHarga1) {
            harga1.set(fHarga1);
        }

        public String getHarga2() {
            return harga2.get();
        }

        public void setHarga2(String fHarga2) {
            harga2.set(fHarga2);
        }

        public String getDiskon() {
            return diskon.get();
        }

        public void setDiskon(String fDiskon) {
            harga2.set(fDiskon);
        }
    }

    @Override
    public void hapus(tabel tb) throws SQLException {
        if (tb.table.getSelectionModel().isEmpty()) {
            Alert em = new Alert(Alert.AlertType.INFORMATION);
            em.setTitle("Informasi");
            em.setHeaderText("Mohon untuk memilih item yang ingin di hapus");
            em.show();
        } else {
            StokObat so = (StokObat) tb.table.getSelectionModel().getSelectedItem();
            String index = so.getId();
            Alert konfirm = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
            konfirm.setTitle("Konfirmasi");
            konfirm.setHeaderText("Anda yakin ingin menghapus data no. " + index + " ?");
            konfirm.showAndWait();
            if (konfirm.getResult() == ButtonType.YES) {
                try {
                    String sql = "DELETE FROM stok_obat WHERE no = '" + index + "'";
                    Connection conn = koneksi.koneksi.koneksiDB();
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    Alert info = new Alert(AlertType.INFORMATION);
                    info.setTitle("Informasi");
                    info.setHeaderText("Data berhasil dihapus");
                    info.show();
                    refTb(tb);
                } catch (SQLException e) {
                    System.out.println(e);
                    Alert s = new Alert(AlertType.ERROR);
                    s.setTitle("ERROR");
                    s.setHeaderText("Error: " + e);
                    s.show();
                }
            }
        }
    }

    @Override
    public void refTb(tabel tb) throws SQLException {
        ObservableList<StokObat> data = tb.data;
        String sql = "SELECT * FROM stok_obat";
        Connection conn = koneksi.koneksi.koneksiDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        tb.table.getItems().clear();
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
        tb.table.setItems(data);
    }
}
