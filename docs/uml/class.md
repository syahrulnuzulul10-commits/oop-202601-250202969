# Class Diagram Sistem Aset-Pinjam

Berikut adalah diagram kelas UML sistem Aset-Pinjam yang mencakup seluruh paket model, kontrak, denda, jadwal, dan katalog.

```mermaid
classDiagram
    direction TB

    namespace id_ac_upb_asetpinjam_kontrak {
        class DapatDipinjam {
            <<interface>>
            +bisaDipinjam() bool
        }
        class AturanDenda {
            <<interface>>
            +hitung(Aset aset, int hariTerlambat) long
        }
    }

    namespace id_ac_upb_asetpinjam_model {
        class StatusAset {
            <<enumeration>>
            TERSEDIA
            DIPINJAM
            RUSAK
        }
        class Kondisi {
            <<enumeration>>
            BAIK
            RUSAK_RINGAN
            RUSAK_BERAT
        }
        class JenisAset {
            <<enumeration>>
            RUANG
            LAPTOP
            ALAT_LAB
        }
        class StatusPeminjaman {
            <<enumeration>>
            DIAJUKAN
            DISETUJUI
            DITOLAK
            DIPINJAM
            DIBATALKAN
            DIKEMBALIKAN
            TERLAMBAT
        }
        class TingkatRisiko {
            <<enumeration>>
            RENDAH
            SEDANG
            TINGGI
        }
        class Peminjam {
            -String id
            -String nama
            +id() String
            +nama() String
        }
        class Aset {
            <<abstract>>
            -String kode
            -String nama
            -StatusAset status
            -Kondisi kondisi
            +getKode() String
            +getNama() String
            +getStatus() StatusAset
            +getKondisi() Kondisi
            +setNama(String nama) void
            +setStatus(StatusAset status) void
            +setKondisi(Kondisi kondisi) void
            +bisaDipinjam() bool
            +getJenis() JenisAset
            +hitungDenda(int hariTerlambat)* long
        }
        class Ruang {
            -int kapasitas
            +getKapasitas() int
            +getJenis() JenisAset
            +hitungDenda(int hariTerlambat) long
        }
        class Laptop {
            -String spesifikasi
            -String nomorInventaris
            +getSpesifikasi() String
            +getNomorInventaris() String
            +getJenis() JenisAset
            +hitungDenda(int hariTerlambat) long
        }
        class AlatLab {
            -String laboratorium
            -TingkatRisiko tingkatRisiko
            +getLaboratorium() String
            +getTingkatRisiko() TingkatRisiko
            +getJenis() JenisAset
            +hitungDenda(int hariTerlambat) long
        }
        class Peminjaman {
            -String id
            -Peminjam peminjam
            -Aset aset
            -LocalDateTime mulai
            -LocalDateTime selesai
            -StatusPeminjaman status
            +getId() String
            +getPeminjam() Peminjam
            +getAset() Aset
            +getMulai() LocalDateTime
            +getSelesai() LocalDateTime
            +getStatus() StatusPeminjaman
            +setStatusDariPenyimpanan(StatusPeminjaman status) void
            +ubahStatus(StatusPeminjaman baru) void
        }
    }

    namespace id_ac_upb_asetpinjam_denda {
        class DendaFlat {
            +hitung(Aset aset, int hariTerlambat) long
        }
        class DendaProgresif {
            +hitung(Aset aset, int hariTerlambat) long
        }
        class LayananDenda {
            -AturanDenda aturan
            +hitung(Aset aset, int hariTerlambat) long
        }
    }

    namespace id_ac_upb_asetpinjam_katalog {
        class KatalogAset {
            -List~Aset~ daftar
            +tambah(Aset aset) void
            +semua() List~Aset~
            +cari(String kata) List~Aset~
            +cari(String kata, JenisAset jenis) List~Aset~
        }
    }

    namespace id_ac_upb_asetpinjam_jadwal {
        class JadwalPeminjaman {
            -List~Peminjaman~ daftar
            -int kuotaLaptop
            #kuotaLaptop() int
            #daftarMutable() List~Peminjaman~
            +tambah(Peminjaman p) void
            +bentrok(Peminjaman baru) bool
            +semuaUrutMulai() List~Peminjaman~
            +perAset() Map~String, List~Peminjaman~~
            +hapus(String id) bool
            +ajukan(Peminjaman baru) void
        }
    }

    DapatDipinjam <|.. Aset : implements
    Aset <|-- Ruang : extends
    Aset <|-- Laptop : extends
    Aset <|-- AlatLab : extends

    AturanDenda <|.. DendaFlat : implements
    AturanDenda <|.. DendaProgresif : implements

    Aset --> "1" StatusAset : status
    Aset --> "1" Kondisi : kondisi
    AlatLab --> "1" TingkatRisiko : tingkatRisiko

    Peminjaman --> "1" Peminjam : peminjam
    Peminjaman --> "1" Aset : aset
    Peminjaman --> "1" StatusPeminjaman : status

    KatalogAset o-- "0..*" Aset : daftar
    JadwalPeminjaman o-- "0..*" Peminjaman : daftar

    LayananDenda o-- "1" AturanDenda : aturan
    AturanDenda ..> Aset : parameter
```

## Deskripsi Elemen Diagram

1. **Paket `id.ac.upb.asetpinjam.model`**:
   - Berisi kelas inti domain entitas: `Aset` (kelas abstrak), `Ruang`, `Laptop`, `AlatLab` (turunan konkret dari `Aset`), `Peminjam` (record), dan `Peminjaman`.
   - Enumerasi untuk status dan atribut: `StatusAset`, `Kondisi`, `JenisAset`, `StatusPeminjaman`, `TingkatRisiko`.
2. **Paket `id.ac.upb.asetpinjam.kontrak`**:
   - Berisi interface kontrak pemodelan: `DapatDipinjam` (abstraksi aset yang dapat dipinjam) dan `AturanDenda` (abstraksi kalkulasi denda - Pola Strategy).
3. **Paket `id.ac.upb.asetpinjam.denda`**:
   - Implementasi strategi denda: `DendaFlat` dan `DendaProgresif`, serta kelas konteks `LayananDenda`.
4. **Paket `id.ac.upb.asetpinjam.katalog`**:
   - `KatalogAset`: Menyimpan koleksi dan menyediakan fungsi pencarian aset.
5. **Paket `id.ac.upb.asetpinjam.jadwal`**:
   - `JadwalPeminjaman`: Menyimpan koleksi peminjaman, validasi bentrok waktu, kuota, dan pengurutan jadwal.
