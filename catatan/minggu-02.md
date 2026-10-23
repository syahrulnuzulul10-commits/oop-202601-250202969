# Catatan Minggu 02: Struktur OOP: Class, Object, Encapsulation

## Yang saya kerjakan
Pada minggu ini, saya mengimplementasikan konsep enkapsulasi pada class `Aset.java`. Seluruh atribut (`kode`, `nama`, `status`, dan `kondisi`) dijadikan `private`, nilai default diinisialisasi pada saat instansiasi objek (`TERSEDIA` dan `BAIK`), dan metode getter serta setter dibuat dengan mekanisme validasi yang ketat terhadap input `null` maupun string kosong/blank.

## Keputusan desain
- Menggunakan `private final String kode` tanpa menyediakan setter `setKode` untuk menjamin immutability pada identitas kode aset.
- Nilai awal status diatur ke `StatusAset.TERSEDIA` dan kondisi ke `Kondisi.BAIK`.
- Validasi dilakukan menggunakan `isBlank()` dan pengecekan `null` yang melempar `IllegalArgumentException`.
- Format kode dipertahankan sesuai input tanpa perubahan kapitalisasi otomatis agar konsisten dengan ekspektasi pengujian.

## Kendala
Memastikan semua atribut bersifat `private` dan memastikan method `setKode` ditiadakan sesuai spesifikasi refleksi pada unit test `Minggu02AsetTest`. Seluruh 6 unit test berhasil lolos.

## Penggunaan AI
- Alat yang dipakai: Antigravity AI
- Bagian yang dibantu AI: Implementasi method validasi dan setter/getter di `Aset.java`.
- Prompt utama: "Week 2 sesuai instruksi tugas dan struktur proyek yang sudah tersedia. Fokus pada implementasi konsep class, object, dan encapsulation pada file Aset.java.

Periksa terlebih dahulu struktur kode dan ketentuan yang ada. Pastikan seluruh atribut kode, nama, status, dan kondisi menggunakan akses private. Atribut kode harus bersifat final dan tidak memiliki setter agar nilainya tidak dapat diubah setelah objek dibuat.

Atur nilai awal status menjadi StatusAset.TERSEDIA dan kondisi menjadi Kondisi.BAIK. Buat getter dan setter untuk atribut yang memang boleh diubah, serta tambahkan validasi agar input null, string kosong, atau string yang hanya berisi spasi ditolak menggunakan IllegalArgumentException.

Pertahankan nilai kode sesuai input tanpa mengubah kapitalisasi hurufnya. Ikuti struktur dan aturan penamaan yang sudah digunakan dalam proyek. Jangan mengubah file unit test atau menambahkan fitur yang tidak diminta.

Setelah implementasi selesai, jalankan unit test Minggu02AsetTest. Jika terdapat pengujian yang gagal, periksa penyebabnya dan perbaiki kode sesuai spesifikasi tugas. Pastikan juga modifier atribut dan ketiadaan setter kode sesuai ketentuan pengujian.

Gunakan kode yang sederhana, rapi, dan mudah dipahami oleh mahasiswa yang sedang mempelajari OOP. Setelah selesai, jelaskan perubahan yang dilakukan, alasan penggunaan private dan final, serta hasil pengujian."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan field `kode` bersifat `final` tanpa setter demi menjaga integritas data identitas aset.
- Satu hal yang saya pahami lebih baik sekarang: Pentingnya prinsip enkapsulasi untuk melindungi state internal objek dari manipulasi langsung di luar class, serta peran validasi pada konstruktor dan mutator (setter).
