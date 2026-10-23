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
- Prompt utama: "kerjakan week 2 dan week 3"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan field `kode` bersifat `final` tanpa setter demi menjaga integritas data identitas aset.
- Satu hal yang saya pahami lebih baik sekarang: Pentingnya prinsip enkapsulasi untuk melindungi state internal objek dari manipulasi langsung di luar class, serta peran validasi pada konstruktor dan mutator (setter).
