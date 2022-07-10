/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author davinky
 */
import view.create;
import java.sql.SQLException;

public interface controller_create {
    public void tambah (String namaBarang, String no_batch, String pbf, String jenis, int satuan, int jumlahStok, String tglMsk, String tglKlr, String exp, int harga1, int harga2, int diskon) throws SQLException;
}
