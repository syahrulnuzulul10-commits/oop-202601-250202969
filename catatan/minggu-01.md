# Catatan Minggu 01: Paradigma Pemrograman dan Setup

## Yang saya kerjakan
Pada minggu pertama ini, saya menyiapkan repositori lokal dan mengisi konfigurasi identitas mahasiswa (NIM dan nama) pada `mahasiswa.properties`. Selanjutnya, saya mengimplementasikan fungsi sapaan sistem Aset-Pinjam menggunakan tiga paradigma pemrograman yang berbeda: prosedural (`HelloProsedural.java`), berorientasi objek / OOP (`HelloOOP.java`), dan fungsional (`HelloFungsional.java`). Terakhir, saya memverifikasi kebenaran implementasi melalui rangkaian unit test `Minggu01Test`.

## Keputusan desain
- Menggunakan pola format string yang seragam pada ketiga gaya: `"Hello Aset-Pinjam, saya " + nama + " (" + nim + ")"` agar konsisten dan memenuhi kriteria unit test.
- Pada gaya OOP, state berupa `nama` dan `nim` dienkapsulasi sebagai field `private final` dalam class `HelloOOP` yang diinisialisasi melalui konstruktor.
- Pada gaya fungsional, memanfaatkan `Function<String, String>` dengan ekspresi lambda yang menerima parameter `nama` dan menangkap closure `nim`.

### Perbandingan Tiga Gaya untuk Sistem Peminjaman Aset:
1. **Gaya Prosedural**:
   - *Kelebihan*: Sederhana, mudah dipahami untuk alur sekuensial sederhana tanpa overhead pembuatan objek.
   - *Keterbatasan*: Sulit dimaintain dan dikembangkan ketika entitas data aset dan peminjaman semakin kompleks karena data dan operasi terpisah tanpa enkapsulasi.
2. **Gaya OOP (Object-Oriented Programming)**:
   - *Kelebihan*: Sangat ideal memodelkan domain nyata sistem peminjaman (seperti Ruang, Laptop, Mahasiswa, TransaksiPeminjaman) dengan menjaga integritas data melalui enkapsulasi, inheritance, dan polimorfisme.
   - *Keterbatasan*: Membutuhkan perancangan struktur kelas dan relasi yang lebih teliti di awal serta potensi overhead memori/instansiasi objek.
3. **Gaya Fungsional**:
   - *Kelebihan*: Immutability dan pure functions meminimalkan efek samping (side effects), sangat baik untuk kalkulasi murni seperti perhitungan denda, diskon, atau filter daftar aset.
   - *Keterbatasan*: Kurang intuitif untuk memodelkan sistem dengan perubahan status yang kompleks (stateful lifecycle peminjaman: diajukan -> disetujui -> dipinjam -> dikembalikan) jika dibandingkan dengan state machine pada OOP.

## Kendala
Kendala awal adalah memastikan lingkungan build dan Maven terkonfigurasi dengan benar di mesin lokal. Setelah konfigurasi environment siap, implementasi ketiga paradigma dapat diselesaikan dan seluruh pengujian lolos.

## Penggunaan AI
- Alat yang dipakai: Antigravity AI
- Bagian yang dibantu AI: Penyusunan implementasi `HelloProsedural`, `HelloOOP`, dan `HelloFungsional` serta sinkronisasi format sapaan.
- Prompt utama: "kerjakan week 1"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan template format string sapaan tepat dan konsisten antara ketiga kelas agar assertion `isEqualTo` pada unit test terpenuhi sempurna.
- Satu hal yang saya pahami lebih baik sekarang: Bagaimana perbedaan mendasar penanganan state dan perilaku pada paradigma prosedural (fungsi statis tanpa state objek), OOP (state terenkapsulasi dalam objek), dan fungsional (fungsi higher-order/lambda tanpa modifikasi state langsung).
