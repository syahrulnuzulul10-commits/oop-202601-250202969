# Catatan Minggu 04: Polymorphism

## Yang saya kerjakan
Pada minggu ini, saya mengimplementasikan konsep polimorfisme, baik dynamic polymorphism (method overriding) maupun static polymorphism (method overloading). Pada method overriding, saya mengimplementasikan dan meng-override method `hitungDenda(int hariTerlambat)` pada class `Aset` dan ketiga subclass-nya (`Ruang` dengan multiplier 1x, `Laptop` dengan multiplier 2x, dan `AlatLab` dengan multiplier 4x) berdasarkan tarif varian mahasiswa (`Varian.aktif().dendaPerHari`). Pada method overloading, saya mengimplementasikan dua variasi method `cari` pada `KatalogAset`: pencarian berdasarkan kata kunci nama aset (`cari(String kata)`) dan pencarian berdasarkan kata kunci sekaligus filter jenis aset (`cari(String kata, JenisAset jenis)`).

## Keputusan desain
- **Method Overriding & Dynamic Binding**:
  - `Aset.hitungDenda(int hariTerlambat)` menjadi method dasar dengan validasi bahwa `hariTerlambat` tidak boleh bernilai negatif (melempar `IllegalArgumentException`). Jika bernilai 0 hari, maka denda bernilai 0.
  - Setiap subclass meng-override method `hitungDenda`:
    - `Ruang`: `hariTerlambat * 1 * Varian.aktif().dendaPerHari`
    - `Laptop`: `hariTerlambat * 2 * Varian.aktif().dendaPerHari`
    - `AlatLab`: `hariTerlambat * 4 * Varian.aktif().dendaPerHari`
  - Perhitungan denda dipanggil secara polimorfik melalui referensi bertipe `Aset`, di mana Java runtime secara dinamis mengeksekusi implementasi milik class konkret objek tersebut (*dynamic method dispatch*).
- **Method Overloading**:
  - `KatalogAset.cari(String kata)` melakukan pencarian case-insensitive terhadap nama aset dengan mengecek apakah substring terdapat pada nama aset.
  - `KatalogAset.cari(String kata, JenisAset jenis)` memanfaatkan kembali hasil dari `cari(kata)` lalu memfilter aset yang memiliki `JenisAset` yang cocok.

## Kendala
Memastikan validasi hari negatif konsisten di seluruh class dan subclass, serta memastikan pencarian pada `KatalogAset` bersifat case-insensitive dan menangani input bernilai null dengan aman mengembalikan daftar kosong. Seluruh 8 pengujian di `Minggu04PolimorfismeTest` lolos dengan sempurna.

## Penggunaan AI
- Alat yang dipakai: Antigravity AI
- Bagian yang dibantu AI: Implementasi method overriding `hitungDenda` pada hierarki aset dan method overloading `cari` pada `KatalogAset`.
- Prompt utama: Bantu saya mengimplementasikan konsep polymorphism pada project Java OOP minggu ke4, meliputi method overriding untuk perhitungan denda pada class Aset dan subclass-nya, serta method overloading untuk fitur pencarian pada class KatalogAset. Pastikan kode sesuai dengan ketentuan tugas dan semua pengujian berhasil.
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan method overloading `cari(kata, jenis)` menggunakan kembali method `cari(kata)` agar tidak terjadi duplikasi logika pencarian string case-insensitive (*DRY principle*).
- Satu hal yang saya pahami lebih baik sekarang: Perbedaan nyata antara method overriding (polimorfisme dinamis saat runtime berdasarkan objek aktual) dan method overloading (polimorfisme statis saat compile time berdasarkan tanda tangan parameter method).
