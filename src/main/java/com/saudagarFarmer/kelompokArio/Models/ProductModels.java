package com.saudagarFarmer.kelompokArio.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_products")
public class ProductModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ImageFileName;
    private String NamaHewan;
    private float berat;
    private int harga;

    private String kategori;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageFileName() {
        return ImageFileName;
    }

    public void setImageFileName(String imageFileName) {
        ImageFileName = imageFileName;
    }

    public String getNamaHewan() {
        return NamaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        NamaHewan = namaHewan;
    }

    public float getBerat() {
        return berat;
    }

    public void setBerat(float berat) {
        this.berat = berat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
