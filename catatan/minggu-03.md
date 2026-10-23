# Catatan Minggu 03: Inheritance

## Yang saya kerjakan
Pada minggu ini, saya mengimplementasikan konsep pewarisan (inheritance) dengan membuat tiga subclass turunan dari class `Aset`: `Ruang`, `Laptop`, dan `AlatLab`. Masing-masing subclass menggunakan kembali logika konstruktor induk melalui pemanggilan `super(kode, nama)` serta mendefinisikan atribut spesifik masing-masing (`kapasitas` pada Ruang, `spesifikasi` dan `nomorInventaris` pada Laptop, serta `laboratorium` dan `tingkatRisiko` pada AlatLab) lengkap dengan validasi nilainya. Saya juga meng-override method `getJenis()` pada setiap subclass agar mengembalikan nilai enum `JenisAset` yang sesuai.

## Keputusan desain
- Validasi data dasar (`kode` dan `nama`) tetap dipercayakan sepenuhnya kepada class induk `Aset` melalui pemanggilan `super(kode, nama)`.
- Atribut tambahan pada subclass dienkapsulasi dengan `private final` dan diverifikasi dalam konstruktor subclass:
  - `kapasitas` pada `Ruang` harus bilangan positif (> 0).
  - `spesifikasi` dan `nomorInventaris` pada `Laptop` tidak boleh `null` atau blank/whitespace.
  - `laboratorium` tidak boleh kosong dan `tingkatRisiko` tidak boleh `null` pada `AlatLab`.
- Polimorfisme jenis aset didukung melalui override method `getJenis()`.

### Contoh di Mana Inheritance Kurang Tepat Dibanding Komposisi:
Contoh nyata pada sistem ini adalah pemodelan **perangkat pendukung ruang** (misalnya proyektor, papan tulis interaktif, AC, atau PC Lab yang ada di dalam ruang).
Jika kita memaksakan pemodelan dengan inheritance (misal membuat `RuangDenganProyektor` atau `RuangLabKomputer` yang mewarisi `Ruang`), struktur class akan menjadi sangat kaku (*class explosion*) dan sulit diubah ketika fasilitas ruang ditambah atau dipindahkan.
Pendekatan yang jauh lebih fleksibel adalah **komposisi** (*composition over inheritance*), di mana class `Ruang` memiliki koleksi/daftar aset fasilitas (`List<Aset> fasilitas`), atau sebuah aset memiliki relasi kepemilikan/lokasi ke ruang tertentu (`Lokasi`). Dengan komposisi, fasilitas ruang dapat ditambahkan, dilepas, atau diganti secara dinamis saat runtime tanpa merombak hierarki class.

## Kendala
Memastikan validasi pada konstruktor induk (`super`) terpanggil sebelum validasi tambahan pada subclass, serta memastikan semua subclass memenuhi ekspektasi pengujian hierarki polimorfik. Seluruh 8 pengujian `Minggu03HierarkiTest` berhasil lolos.

## Penggunaan AI
- Alat yang dipakai: Antigravity AI
- Bagian yang dibantu AI: Implementasi subclass `Ruang`, `Laptop`, `AlatLab`, dan perancangan analisis keterbatasan pewarisan vs komposisi.
- Prompt utama: "kerjakan week 2 dan week 3"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan pesan exception informatif dan atribut subclass diatur sebagai `final` agar konsisten dengan prinsip immutability.
- Satu hal yang saya pahami lebih baik sekarang: Pemahaman tentang bagaimana konstruktor subclass mendelegasikan inisialisasi state dasar ke superclass, serta kapan harus memilih komposisi alih-alih inheritance untuk menghindari hierarki pewarisan yang rapuh (*fragile base class problem*).
