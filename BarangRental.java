import java.util.Scanner;

public class BarangRental {

    String noTNKB;
    String namaKendaraan;
    String jenisKendaraan;
    int tahun;
    int biayaSewa;

    BarangRental(String noTKNB, String namaKendaraan, String jenisKendaraan, int tahun, int biayaSewa) {
        this.noTNKB = noTKNB;
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        this.tahun = tahun;
        this.biayaSewa = biayaSewa;
    }

    public String getNoTNKB() {
        return noTNKB;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public int getTahun() {
        return tahun;
    }

    public int getBiayaSewa() {
        return biayaSewa;
    }

    public static void tampilkanDaftarKendaraan(BarangRentalNode head) {
        if (head == null) {
            System.out.println("Tidak ada kendaraan yang tersedia.");
            return;
        }

        System.out.println("\nDaftar Kendaraan:");
        System.out.println("--------------------------------------------------");
        System.out.println("No. | No TNKB     | Nama Kendaraan | Jenis | Tahun | Biaya Sewa Perjam");
        System.out.println("--------------------------------------------------");

        BarangRentalNode current = head;
        int nomorUrut = 1;
        while (current != null) {
            BarangRental br = current.data;
            System.out.printf("%-3d | %-11s | %-14s | %-5s | %-4d | Rp%,d\n",
                    nomorUrut, br.getNoTNKB(), br.getNamaKendaraan(),
                    br.getJenisKendaraan(), br.getTahun(), br.getBiayaSewa());
            current = current.next;
            nomorUrut++;
        }
        System.out.println("--------------------------------------------------");
    }

    public static BarangRental cariBarangRental(BarangRentalNode head, String noTNKB) {
        BarangRentalNode current = head;
        while (current != null) {
            if (current.data.getNoTNKB().equalsIgnoreCase(noTNKB.trim())) { // Trim dan bandingkan tanpa case-sensitive
                return current.data;
            }
            current = current.next;
        }
        return null; // Jika tidak ditemukan
    }
    
}