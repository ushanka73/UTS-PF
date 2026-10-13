========================================================================
MINI PROJECT UTS: PEMROGRAMAN FUNGSIONAL (SCALA 3)
JUDUL: Sistem Manajemen Gudang Tembakau (Bal Bako)
KELOMPOK 9
ANGGOTA: 
1. Dava Julian E. P. (NIM: 3074)
2. Hayden Putra P. M. (NIM: 3040)
========================================================================

1. DESKRIPSI SINGKAT
Aplikasi berbasis console ini dirancang untuk mengelola data penerimaan 
bal tembakau (Bal Bako). Program menerapkan prinsip Functional Programming 
(FP) untuk memproses grade tembakau, menentukan tindakan gudang berdasarkan 
kadar air, menghitung estimasi penyusutan berat akibat fermentasi, dan 
menampilkan laporan tanpa mengubah data asli (Immutability).

2. PRASYARAT SISTEM
- Java Development Kit (JDK) versi 11 atau lebih baru.
- Scala 3 Compiler (bisa melalui Scala CLI atau IntelliJ IDEA dengan plugin Scala).

3. CARA MENJALANKAN PROGRAM
Pilih salah satu dari dua metode di bawah ini sesuai dengan lingkungan pengembangan yang Anda gunakan:

[METODE A: Menggunakan Scala CLI (Paling Cepat & Direkomendasikan)]
1. Pastikan `scala-cli` sudah terinstal di komputer Anda.
2. Buka Terminal atau Command Prompt.
3. Arahkan direktori (cd) ke folder tempat file kode ini disimpan.
   Contoh: cd path/ke/folder/proyek
4. Jalankan perintah berikut (sesuaikan 'SistemBalBako.scala' dengan nama file asli Anda):
   scala-cli run SistemBalBako.scala
5. Program akan berjalan dan meminta input jumlah bal tembakau.

[METODE B: Menggunakan IntelliJ IDEA]
1. Buka IntelliJ IDEA, lalu pilih "Open" dan arahkan ke folder proyek ini.
2. Pastikan Project SDK sudah diatur ke JDK yang valid, dan Scala SDK sudah terkonfigurasi (Scala 3.x).
3. Buka file sumber kode utama (misalnya: `SistemBalBako.scala`).
4. Gulir ke bagian paling bawah hingga menemukan baris: `@main def main(): Unit =`
5. Klik ikon "Play" (Segitiga hijau) yang muncul di sebelah kiri baris `@main`, atau klik kanan di area kode tersebut lalu pilih "Run 'SistemBalBako'".
6. Program akan berjalan di panel "Run" di bagian bawah IntelliJ.

4. PANDUAN INPUT USER
Saat program berjalan, Anda akan diminta memasukkan data secara berurutan:
- Jumlah total bal yang akan diinput (angka bulat, misal: 2).
- Untuk setiap bal, masukkan:
  * ID Bal (String, misal: TB-01)
  * Berat awal (Desimal/Double, misal: 100.5)
  * Kadar Air % (Desimal/Double, misal: 15.0)
  * Posisi Daun (String: "bawah", "tengah", atau "atas")

5. PEMETAAN KONSEP FUNCTIONAL PROGRAMMING DALAM KODE
- Immutability: Penggunaan `case class` untuk `BalBako` dan `BalGiling` menjamin data tidak dimutasi (hanya `val`).
- Pure Function & Conditional: Fungsi `tentukanGrade` dan `lossRateFermentasi` menggunakan `match` tanpa side-effect.
- Function Composition: `val tindakanGudang = ambilKadarAir andThen tentukanTindakan` (Materi PDF 6).
- Function Chaining: Penggunaan `.filter(...).map(...)` pada method `ambilBalIdeal` untuk memproses List tanpa loop `for`/`while` (Materi PDF 1 & 6).
- Higher-Order Function: Fungsi `ambilInfoBal` yang menerima parameter berupa fungsi lambda (`formatter`).
- Tail Recursion: Fungsi `inputUser` (untuk input berulang) dan `hitungTotalEstimasiBerat` (untuk akumulasi total) menggunakan anotasi `@tailrec` dengan base case dan recursive case yang jelas, tanpa variabel `var` (Materi PDF 7).

6. CATATAN
Source code, diagram alur, dan materi presentasi (PPT) disertakan dalam paket pengumpulan yang sama.
========================================================================
