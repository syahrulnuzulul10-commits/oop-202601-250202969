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
- Prompt utama: "Week 3 sesuai instruksi tugas dan struktur proyek yang sudah tersedia. Fokus pada penerapan inheritance atau pewarisan pada sistem Aset-Pinjam.

Buat tiga subclass dari class Aset, yaitu Ruang, Laptop, dan AlatLab. Gunakan extends Aset dan panggil konstruktor induk melalui super(kode, nama) agar validasi atribut dasar tetap ditangani oleh class Aset.

Implementasikan atribut khusus untuk masing-masing subclass:

Ruang: atribut kapasitas yang harus bernilai lebih dari 0.
Laptop: atribut spesifikasi dan nomorInventaris yang tidak boleh null, kosong, atau hanya berisi spasi.
AlatLab: atribut laboratorium yang tidak boleh null atau blank, serta tingkatRisiko yang tidak boleh null.

Gunakan private final untuk atribut tambahan yang nilainya tidak perlu diubah setelah objek dibuat. Tambahkan konstruktor, getter jika diperlukan oleh spesifikasi, dan override method getJenis() agar setiap subclass mengembalikan nilai JenisAset yang sesuai.

Periksa terlebih dahulu enum, class induk, pola penamaan, dan unit test yang sudah tersedia. Ikuti struktur proyek yang ada dan jangan mengubah unit test hanya agar pengujian lolos. Hindari menambahkan fitur yang tidak diminta.

Setelah implementasi selesai, jalankan unit test Minggu03HierarkiTest. Perbaiki kesalahan yang ditemukan berdasarkan spesifikasi tugas dan pastikan setiap subclass dapat digunakan melalui referensi bertipe Aset.

Terakhir, jelaskan secara singkat bagaimana inheritance digunakan pada ketiga subclass, manfaat super() dan overriding getJenis(), serta berikan contoh pada sistem Aset-Pinjam tentang situasi ketika komposisi lebih tepat digunakan daripada inheritance. Gunakan kode yang sederhana, rapi, dan mudah dipahami oleh mahasiswa yang sedang belajar OOP."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan pesan exception informatif dan atribut subclass diatur sebagai `final` agar konsisten dengan prinsip immutability.
- Satu hal yang saya pahami lebih baik sekarang: Pemahaman tentang bagaimana konstruktor subclass mendelegasikan inisialisasi state dasar ke superclass, serta kapan harus memilih komposisi alih-alih inheritance untuk menghindari hierarki pewarisan yang rapuh (*fragile base class problem*).
