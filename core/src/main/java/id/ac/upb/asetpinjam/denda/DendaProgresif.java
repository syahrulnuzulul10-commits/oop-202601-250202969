package id.ac.upb.asetpinjam.denda;

import id.ac.upb.asetpinjam.kontrak.AturanDenda;
import id.ac.upb.asetpinjam.model.Aset;

/**
 * Minggu 5: denda yang makin berat bila makin lama terlambat.
 *
 * <p>Rumus progresifnya KEPUTUSAN ANDA (catat di docs/asumsi.md). Syarat minimal yang diuji:
 * 0 hari = 0; tidak pernah lebih murah dari DendaFlat; tidak pernah turun saat hari bertambah.
 */
public class DendaProgresif implements AturanDenda {

    @Override
    public long hitung(Aset aset, int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0;
        }
        // Rumus progresif: denda flat * hari * (1 + hari/20)
        // Pada hari=1: 1 * (1 + 0.05) = 1.05x flat => >= flat
        // Pada hari=30: 30 * (1 + 1.5) = 30 * 2.5 = 75x flat-per-hari > 30x flat-per-hari => > flat
        // Tidak pernah turun (fungsi hari * (1 + hari/20) selalu naik untuk hari >= 1)
        long dendaFlat = aset.hitungDenda(hariTerlambat);
        // multiplier = 1 + hariTerlambat / 20.0, dikalikan ke denda flat
        // Untuk memastikan integer: gunakan perhitungan langsung
        // denda = dendaFlat * (20 + hariTerlambat) / 20, dibulatkan ke bawah
        return dendaFlat * (20 + hariTerlambat) / 20;
    }
}
