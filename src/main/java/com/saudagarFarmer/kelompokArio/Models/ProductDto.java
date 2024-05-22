package com.saudagarFarmer.kelompokArio.Models;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

    private MultipartFile ImageFile;
    private String NamaHewan;
    private float berat;
    private int harga;

    private String kategori;

    public MultipartFile getImageFile() {
        return ImageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        ImageFile = imageFile;
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
