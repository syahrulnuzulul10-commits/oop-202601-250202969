# Penerapan Prinsip SOLID pada Sistem Aset-Pinjam

Dokumen ini menjelaskan implementasi prinsip-prinsip SOLID (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, dan Dependency Inversion) yang diterapkan pada kode sumber proyek `asetpinjam`.

---

## 1. Single Responsibility Principle (SRP)

> *Sebuah kelas seharusnya hanya memiliki satu alasan untuk berubah (satu tanggung jawab utama).*

### Kelas & Penerapan:
- **`id.ac.upb.asetpinjam.katalog.KatalogAset`**:
  Kelas ini memiliki satu tanggung jawab tunggal, yaitu mengelola katalog aset dalam memori dan menyediakan mekanisme pencarian (berdasarkan nama dan jenis aset). Kelas ini tidak mencampurkan logika pencatatan waktu peminjaman, aturan denda, maupun persistensi database.
- **`id.ac.upb.asetpinjam.jadwal.JadwalPeminjaman`**:
  Khusus bertanggung jawab mengatur jadwal reservasi, pengecekan bentrok waktu peminjaman antar-aset, serta kuota peminjaman.
- **`id.ac.upb.asetpinjam.denda.LayananDenda`**:
  Hanya berfokus pada eksekusi penghitungan biaya denda keterlambatan dengan mendelegasikannya ke strategi denda yang relevan.

### Alasan Desain:
Pemisahan tanggung jawab ini membuat pemeliharaan kode menjadi terisolasi. Jika algoritma pencarian aset diubah, hanya `KatalogAset` yang perlu dimodifikasi tanpa menyentuh modul jadwal atau denda.

---

## 2. Open/Closed Principle (OCP)

> *Entitas perangkat lunak harus terbuka untuk ekstensi (open for extension), tetapi tertutup untuk modifikasi (closed for modification).*

### Kelas & Penerapan:
- **Hirarki `Aset` (`id.ac.upb.asetpinjam.model.Aset`)**:
  Kelas `Aset` didefinisikan sebagai kelas abstrak dengan metode abstrak `hitungDenda(int hariTerlambat)`. Subclass konkret seperti `Ruang`, `Laptop`, dan `AlatLab` mengimplementasikan logika denda spesifik masing-masing sesuai jenis aset.
  Bila di masa depan sistem ingin menambahkan jenis aset baru (misalnya `Kendaraan` atau `Buku`), kita hanya perlu membuat subclass baru dari `Aset` tanpa perlu mengubah kode sumber kelas `Aset` yang sudah ada maupun memodifikasi kode kelas-kelas aset lainnya.
- **Interface `AturanDenda` (`id.ac.upb.asetpinjam.kontrak.AturanDenda`)**:
  Perhitungan denda diekstrak ke dalam interface `AturanDenda`. Kelas strategi baru seperti `DendaFlat`, `DendaProgresif`, atau aturan masa depan dapat ditambahkan kapan saja tanpa merombak kelas `LayananDenda`.

### Alasan Desain:
Prinsip OCP meminimalkan risiko regresi (bug baru pada kode lama yang sudah berjalan stabil) saat menambah fitur baru.

---

## 3. Liskov Substitution Principle (LSP)

> *Objek dari superclass harus dapat digantikan oleh objek dari subclass-nya tanpa mengubah kebenaran program.*

### Kelas & Penerapan:
- **`Ruang`, `Laptop`, dan `AlatLab` sebagai turunan dari `Aset`**:
  Ketiga subclass turunan mematuhi kontrak perilaku kelas induk `Aset`. Di mana pun tipe `Aset` dibutuhkan—misalnya pada parameter konstruktor `Peminjaman(id, peminjam, aset, mulai, selesai)`, penyimpanan di `KatalogAset.tambah(Aset)`, maupun parameter kalkulasi `AturanDenda.hitung(Aset, hariTerlambat)`—semua subclass dapat disubstitusikan secara transparan tanpa menyebabkan error tak terduga atau pelanggaran kondisi awal/akhir (*preconditions/postconditions*).

### Alasan Desain:
Memastikan polimorfisme berjalan aman dan konsisten di seluruh lapisan sistem.

---

## 4. Interface Segregation Principle (ISP)

> *Klien tidak boleh dipaksa bergantung pada interface yang tidak mereka gunakan.*

### Kelas & Penerapan:
- **`id.ac.upb.asetpinjam.kontrak.DapatDipinjam`**:
  Interface ini dibuat ramping dan berfokus (*fine-grained*), hanya mendefinisikan method `boolean bisaDipinjam()`. Kelas atau fungsi yang hanya bertugas memvalidasi kelayakan peminjaman tidak perlu dibebani method inventarisasi lain atau metode denda.
- **`id.ac.upb.asetpinjam.kontrak.AturanDenda`**:
  Terpisah penuh dari antarmuka peminjaman dan inventaris, hanya mendefinisikan `long hitung(Aset aset, int hariTerlambat)`.

### Alasan Desain:
Mencegah terjadinya *fat interface* yang memaksa kelas mengimplementasikan method kosong atau *unsupported operations*.

---

## 5. Dependency Inversion Principle (DIP)

> *Modul tingkat tinggi tidak boleh bergantung pada modul tingkat rendah; keduanya harus bergantung pada abstraksi. Abstraksi tidak boleh bergantung pada detail; detail harus bergantung pada abstraksi.*

### Kelas & Penerapan:
- **`id.ac.upb.asetpinjam.denda.LayananDenda`**:
  Kelas tingkat tinggi `LayananDenda` tidak bergantung langsung (*hardcoded dependency*) pada implementasi konkret seperti `DendaFlat` atau `DendaProgresif`. Sebaliknya, `LayananDenda` bergantung pada abstraksi interface `AturanDenda` yang diinjeksikan melalui konstruktor:
  ```java
  public LayananDenda(AturanDenda aturan) {
      this.aturan = aturan;
  }
  ```

### Alasan Desain:
Ketergantungan terhadap abstraksi mempermudah pengujian unit (*unit testing* menggunakan mock/stub) serta memungkinkan penggantian strategi algoritma secara dinamis pada saat runtime (*Dependency Injection*).
