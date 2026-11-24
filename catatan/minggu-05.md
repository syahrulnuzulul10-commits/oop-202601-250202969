# Catatan Minggu 05: Abstraction

## Yang saya kerjakan

Pada minggu ini saya mengubah kelas `Aset` menjadi `abstract`, mengimplementasikan metode `bisaDipinjam()` di dalamnya, dan menjadikan `hitungDenda()` sebagai metode abstrak. Selain itu, saya mengimplementasikan interface `AturanDenda` pada dua kelas: `DendaFlat` yang mengembalikan denda sesuai `aset.hitungDenda(hari)`, dan `DendaProgresif` yang menghitung denda dengan rumus yang makin besar seiring bertambahnya hari keterlambatan.

## Keputusan desain

**Rumus DendaProgresif:** `denda = dendaFlat * (20 + hariTerlambat) / 20`

Alasan:
- Pada hari ke-0: `hitungDenda(0) = 0`, maka denda = 0. ✓
- Pada setiap hari ke-n: multiplier = `(20 + n) / 20 >= 1`, sehingga selalu >= flat. ✓
- Fungsi `n * (20 + n) / 20` monoton naik untuk n ≥ 1. ✓
- Pada hari ke-30: flat = 30x, progresif = `30 * 50 / 20 = 75`, lebih besar dari 30. ✓

Rumus sederhana ini memenuhi semua syarat tanpa memerlukan tabel bertingkat.

**`hitungDenda` dijadikan `abstract`** di `Aset` karena setiap subclass sudah mengoverride-nya dengan aturan masing-masing (Ruang ×1, Laptop ×2, AlatLab ×4). Tidak ada lagi implementasi default yang masuk akal di level `Aset`.

## Kendala

Setelah `Aset` menjadi `abstract`, import `Varian` di `Aset.java` menjadi tidak terpakai (karena implementasi default `hitungDenda` dihapus). Saya menghapus import tersebut agar tidak ada warning. `TestSupport.aset(...)` sudah menangani kasus ini secara otomatis dengan menggunakan `Ruang` sebagai pengganti.

## Penggunaan AI

- Alat yang dipakai: Antigravity (Claude Sonnet 4.6)
- Bagian yang dibantu AI: `Aset.bisaDipinjam()`, `DendaFlat.hitung()`, `DendaProgresif.hitung()`, dan refactor `hitungDenda` menjadi abstract
- Prompt utama:
  1. lanjutkan bagian abstraction. Sesuaikan class Aset, buat aturan denda flat dan progresif, lalu cek apakah semuanya sudah berjalan sesuai ketentuan tugas. Kalau sudah, jalankan test untuk memastikan tidak ada error.
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Rumus progresif diusulkan dan divalidasi oleh AI, saya menerimanya setelah memverifikasi bahwa semua syarat tes terpenuhi secara matematis.
- Satu hal yang saya pahami lebih baik sekarang: Perbedaan antara `abstract class` dan `interface` — `abstract class` cocok untuk berbagi state/implementasi sebagian (seperti field `status` dan `kondisi` di `Aset`), sedangkan `interface` cocok untuk mendefinisikan kontrak perilaku tanpa state (seperti `DapatDipinjam` dan `AturanDenda`).
