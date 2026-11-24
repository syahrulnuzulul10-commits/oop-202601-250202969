# Catatan Minggu 06: UML dan SOLID

## Yang saya kerjakan

Pada minggu ini saya memodelkan arsitektur sistem Aset-Pinjam menggunakan UML Class Diagram dan Sequence Diagram yang konsisten dengan basis kode yang telah dikembangkan hingga minggu 5. Class Diagram memuat seluruh struktur paket, atribut, method, tipe data, visibility, relasi, dan multiplicity. Sequence Diagram memodelkan alur interaksi pengajuan peminjaman aset dengan fragmen `alt` untuk skenario sukses dan skenario bentrok waktu. Selain itu, saya menyusun dokumen analisis prinsip-prinsip SOLID (`docs/uml/solid.md`) dengan menunjuk kelas-kelas nyata dalam kode beserta alasan desainnya.

## Keputusan desain

- **Format Diagram UML**: Diagram disediakan dalam format PlantUML (`.puml`), Mermaid (`.mmd`), dan Markdown (`.md`) yang me-render Mermaid secara langsung. Hal ini memudahkan pembacaan langsung di GitHub maupun integrasi dengan tooling diagram seperti VS Code dan IntelliJ.
- **Pemodelan Sequence Diagram**: Menggunakan objek `JadwalPeminjaman` sebagai koordinator validasi yang memeriksa status aset (`bisaDipinjam()`) dan potensi bentrok jadwal (`bentrok(peminjaman)`). Percabangan kondisi ditunjukkan menggunakan fragmen `alt` (skenario sukses memasukkan peminjaman ke daftar dan menyetujui, skenario gagal melempar `PeminjamanBentrokException`).
- **Prinsip SOLID**: Dijelaskan kelima prinsipnya (SRP, OCP, LSP, ISP, dan DIP) dengan menunjuk langsung kelas-kelas yang relevan seperti `KatalogAset`, hierarki turunan `Aset`, interface `DapatDipinjam`, strategi `AturanDenda`, dan dependency injection pada `LayananDenda`.

## Kendala

Menjaga konsistensi antara diagram dan kode aktual memerlukan ketelitian pada detail method, tipe kembalian, dan visibility (seperti method protected di `JadwalPeminjaman` dan record `Peminjam`). Hal ini diatasi dengan memeriksa langsung setiap kelas di folder `core/src/main/java/id/ac/upb/asetpinjam/`.

## Penggunaan AI

- Alat yang dipakai: Antigravity
- Bagian yang dibantu AI: Penyusunan sintaks diagram PlantUML dan Mermaid (`class.puml`, `class.mmd`, `sequence.puml`, `sequence.mmd`), serta perumusan dokumen analisis `solid.md`.
- Prompt utama:
  1. "Tolong kerjakan tugas minggu 6 — buat Class Diagram dan Sequence Diagram UML yang konsisten dengan kode, serta analisis prinsip SOLID di docs/uml/solid.md."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Memastikan nama method dan visibilitas pada diagram sepenuhnya selaras dengan kode nyata dan bukan sekadar sketsa konseptual.
- Satu hal yang saya pahami lebih baik sekarang: Bagaimana prinsip Open/Closed (OCP) dan Dependency Inversion (DIP) bekerja bersama-sama dengan pola Strategy pada `AturanDenda` dan `LayananDenda` untuk menghasilkan kode yang mudah diekstensi dan mudah diuji.
