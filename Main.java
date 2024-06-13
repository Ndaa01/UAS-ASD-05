import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BarangRentalNode headBarang = null;
        TransaksiRentalNode headTransaksi = null;

        headBarang = tambahBarangRental(headBarang, "S 4567 YV", "Honda Beat", "Motor", 2017, 10000);
        headBarang = tambahBarangRental(headBarang, "N 4511 VS", "Honda Vario", "Motor", 2018, 10000);
        headBarang = tambahBarangRental(headBarang, "N 1453 AA", "Toyota Yaris", "Mobil", 2022, 30000);
        headBarang = tambahBarangRental(headBarang, "AB 4321 A", "Toyota Innova", "Mobil", 2019, 60000);
        headBarang = tambahBarangRental(headBarang, "B 1234 AG", "Toyota Avanza", "Mobil", 2021, 25000);


        int pilihan;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Peminjaman");
            System.out.println("3. Tampilkan seluruh transaksi");
            System.out.println("4. Urutkan Transaksi urut no TNKB");
            System.out.println("5. Keluar");
            System.out.print("Pilih (1-5): ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                BarangRental.tampilkanDaftarKendaraan(headBarang);
                    break;
                case 2:
                headTransaksi = prosesPeminjaman(headBarang, headTransaksi, input);
                    break;
                case 3:tampilkanSemuaTransaksi(headTransaksi); // Memanggil fungsi dengan benar
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    public static BarangRentalNode tambahBarangRental(BarangRentalNode head, String noTNKB, String nama, String jenis, int tahun, int biaya) {
        BarangRental br = new BarangRental(noTNKB, nama, jenis, tahun, biaya);
        BarangRentalNode newNode = new BarangRentalNode(br);

        if (head == null) {
            return newNode; 
        }

        BarangRentalNode last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode; 
        return head; 
    }

    public static TransaksiRentalNode prosesPeminjaman(BarangRentalNode headBarang, TransaksiRentalNode headTransaksi, Scanner input) {
        BarangRental.tampilkanDaftarKendaraan(headBarang); // Tampilkan daftar kendaraan

        System.out.print("Masukkan No TNKB kendaraan yang ingin dipinjam: ");
        input.nextLine(); 
        String noTNKB = input.nextLine();

        BarangRental br = cariBarangRental(headBarang, noTNKB);
        if (br == null) {
            System.out.println("Kendaraan dengan No TNKB tersebut tidak ditemukan.");
            return headTransaksi;
        }

        System.out.print("Masukkan lama pinjam (dalam jam): ");
        int lamaPinjamJam = input.nextInt();
        if (lamaPinjamJam <= 0) {
            System.out.println("Lama pinjam harus lebih dari 0 jam.");
            return headTransaksi;
        }

        double totalBiaya = hitungTotalBiaya(br, lamaPinjamJam);
        System.out.println("Total biaya sewa: Rp " + totalBiaya);

        System.out.print("Masukkan nama peminjam: ");
        input.nextLine(); // Membersihkan buffer input
        String namaPeminjam = input.nextLine();

        int kodeTransaksi = (headTransaksi == null) ? 1 : headTransaksi.data.getKodeTransaksi() + 1;
        TransaksiRental transaksi = new TransaksiRental(kodeTransaksi, namaPeminjam, lamaPinjamJam, totalBiaya, br);

        TransaksiRentalNode newNode = new TransaksiRentalNode(transaksi);
        newNode.next = headTransaksi; // Node baru menunjuk ke headTransaksi yang lama
        headTransaksi = newNode; // Update headTransaksi dengan node baru
        return headTransaksi; // Kembalikan headTransaksi yang sudah diperbarui
    }

    public static BarangRental cariBarangRental(BarangRentalNode head, String noTNKB) {
        BarangRentalNode current = head;
        while (current != null) {
            if (current.data.getNoTNKB().equalsIgnoreCase(noTNKB)) {
                return current.data;
            }
            current = current.next;
        }
        return null; // Jika tidak ditemukan
    }

    public static double hitungTotalBiaya(BarangRental br, int lamaPinjamJam) {
        int biayaPerJam = br.getBiayaSewa(); // Asumsi biaya sewa per hari dibagi rata per jam
        return biayaPerJam * lamaPinjamJam;
    }

    public static void tampilkanSemuaTransaksi(TransaksiRentalNode head) {
        if (head == null) {
            System.out.println("Belum ada transaksi peminjaman.");
            return;
        }

        System.out.println("\nDaftar Transaksi Peminjaman:");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("No | Kode Transaksi | Nama Peminjam     | No TNKB     | Lama Pinjam (jam) | Total Biaya");
        System.out.println("---------------------------------------------------------------------------------------");

        TransaksiRentalNode current = head;
        int nomorUrut = 1;
        while (current != null) {
            TransaksiRental tr = current.data;
            System.out.printf("%-3d | %-15d | %-18s | %-11s | %-18d | Rp%,.2f\n",
                    nomorUrut, tr.getKodeTransaksi(), tr.getNamaPeminjam(),
                    tr.getBr().getNoTNKB(), tr.getLamaPinjam(), tr.getTotalBiaya());
            current = current.next;
            nomorUrut++;
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

}

