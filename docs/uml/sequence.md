# Sequence Diagram: Pengajuan Peminjaman

Diagram interaksi ini menunjukkan skenario pengajuan peminjaman aset (`ajukan(peminjaman)`), memodelkan alur sukses serta alur bentrok menggunakan fragmen `alt`.

```mermaid
sequenceDiagram
    autonumber
    actor Peminjam
    participant Jadwal as jadwal:JadwalPeminjaman
    participant Peminjaman as peminjaman:Peminjaman
    participant Aset as aset:Aset

    Peminjam->>Jadwal: ajukan(peminjaman)
    activate Jadwal

    Jadwal->>Peminjaman: getAset()
    activate Peminjaman
    Peminjaman-->>Jadwal: aset
    deactivate Peminjaman

    Jadwal->>Aset: bisaDipinjam()
    activate Aset
    Aset-->>Jadwal: true
    deactivate Aset

    Jadwal->>Jadwal: bentrok(peminjaman)

    alt Skenario Sukses (Tidak Bentrok)
        Note over Jadwal: Tidak ada peminjaman bertabrakan waktu pada aset yang sama
        Jadwal->>Jadwal: daftar.add(peminjaman)
        Jadwal->>Peminjaman: ubahStatus(StatusPeminjaman.DISETUJUI)
        activate Peminjaman
        Peminjaman-->>Jadwal: void
        deactivate Peminjaman
        Jadwal-->>Peminjam: Berhasil diajukan
    else Skenario Gagal (Bentrok)
        Note over Jadwal: Ditemukan peminjaman aktif yang tumpang tindih waktu
        Jadwal-->>Peminjam: throw PeminjamanBentrokException("Jadwal bentrok")
    end

    deactivate Jadwal
```

## Penjelasan Skenario

1. **Aktor `Peminjam`** memanggil method `ajukan(peminjaman)` pada objek `JadwalPeminjaman`.
2. `JadwalPeminjaman` mengambil referensi aset yang bersangkutan melalui `peminjaman.getAset()`.
3. `JadwalPeminjaman` memeriksa kelayakan status dan kondisi aset via `aset.bisaDipinjam()`.
4. `JadwalPeminjaman` memanggil method internal `bentrok(peminjaman)` untuk mengecek tumpang tindih waktu terhadap jadwal peminjaman yang sudah ada pada aset yang sama.
5. **Fragmen `alt`**:
   - **Skenario Sukses**: Bila tidak terjadi bentrok (`false`), objek `peminjaman` ditambahkan ke dalam daftar jadwal (`daftar.add(peminjaman)`), status peminjaman diubah menjadi `DISETUJUI` melalui `ubahStatus()`, dan konfirmasi berhasil dikembalikan ke peminjam.
   - **Skenario Gagal (Bentrok)**: Bila terjadi bentrok waktu (`true`), eksekusi dialihkan ke cabang `else` dan sistem melempar exception `PeminjamanBentrokException` sehingga pengajuan ditolak.
