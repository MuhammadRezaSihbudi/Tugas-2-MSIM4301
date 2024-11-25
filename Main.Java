package Tugas2;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Array untuk menyimpan daftar menu
        Menu[] menu = {
            new Menu("Nasi Goreng", 20000, "Makanan"),
            new Menu("Ayam Bakar", 25000, "Makanan"),
            new Menu("Soto Ayam", 15000, "Makanan"),
            new Menu("Bakso", 18000, "Makanan"),
            new Menu("Teh Manis", 5000, "Minuman"),
            new Menu("Es Jeruk", 7000, "Minuman"),
            new Menu("Kopi", 10000, "Minuman"),
            new Menu("Susu Cokelat", 8000, "Minuman")
        };

        List<Menu> pesanan = new ArrayList<>();
        List<Integer> jumlahPesanan = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Restoran Sederhana ===");
            System.out.println("1. Tampilkan Menu");
            System.out.println("2. Pesan Menu");
            System.out.println("3. Cetak Struk");
            System.out.println("4. Kelola Menu");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = sc.nextInt();
            sc.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1:
                    tampilkanMenu(menu);
                    break;
                case 2:
                    pesanMenu(menu, pesanan, jumlahPesanan);
                    break;
                case 3:
                    double totalBiaya = hitungTotalBiaya(pesanan, jumlahPesanan);
                    cetakStruk(pesanan, jumlahPesanan, totalBiaya);
                    break;
                case 4:
                    menu = kelolaMenu(menu);
                    break;
                case 5:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        sc.close();
    }

    public static void tampilkanMenu(Menu[] menu) {
        System.out.println("\n=== Menu Makanan ===");
        for (Menu item : menu) {
            if (item.getKategori().equalsIgnoreCase("Makanan")) {
                System.out.println(item);
            }
        }
        System.out.println("\n=== Menu Minuman ===");
        for (Menu item : menu) {
            if (item.getKategori().equalsIgnoreCase("Minuman")) {
                System.out.println(item);
            }
        }
    }

    public static void pesanMenu(Menu[] menu, List<Menu> pesanan, List<Integer> jumlahPesanan) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Masukkan nama menu yang ingin dipesan (atau ketik 'selesai'): ");
            String pilihan = sc.nextLine();
            if (pilihan.equalsIgnoreCase("selesai")) break;

            boolean ditemukan = false;
            for (Menu item : menu) {
                if (item.getNama().equalsIgnoreCase(pilihan)) {
                    System.out.print("Masukkan jumlah: ");
                    int jumlah = sc.nextInt();
                    sc.nextLine(); // Konsumsi newline
                    pesanan.add(item);
                    jumlahPesanan.add(jumlah);
                    ditemukan = true;
                    break;
                }
            }
            if (!ditemukan) {
                System.out.println("Menu tidak ditemukan. Silakan coba lagi.");
            }
        }
    }

    public static double hitungTotalBiaya(List<Menu> pesanan, List<Integer> jumlahPesanan) {
        double total = 0;
        for (int i = 0; i < pesanan.size(); i++) {
            total += pesanan.get(i).getHarga() * jumlahPesanan.get(i);
        }

        double pajak = total * 0.1;
        double biayaPelayanan = 20000;

        if (total > 100000) {
            total *= 0.9; // Diskon 10%
        }

        if (total > 50000) {
            for (Menu item : pesanan) {
                if (item.getKategori().equalsIgnoreCase("Minuman")) {
                    total -= item.getHarga(); // Gratis satu
                    break;
                }
            }
        }

        return total + pajak + biayaPelayanan;
    }

    public static void cetakStruk(List<Menu> pesanan, List<Integer> jumlahPesanan, double totalBiaya) {
        System.out.println("\n=== Struk Pesanan ===");
        for (int i = 0; i < pesanan.size(); i++) {
            Menu item = pesanan.get(i);
            int jumlah = jumlahPesanan.get(i);
            System.out.println(item.getNama() + " x" + jumlah + " - Rp " + (item.getHarga() * jumlah));
        }
        System.out.println("Total Biaya: Rp " + totalBiaya);
    }

    public static Menu[] kelolaMenu(Menu[] menu) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Kelola Menu ===");
        System.out.println("1. Tambah Menu");
        System.out.println("2. Ubah Harga Menu");
        System.out.println("3. Hapus Menu");
        System.out.print("Pilih menu: ");
        int pilihan = sc.nextInt();
        sc.nextLine(); // Konsumsi newline

        switch (pilihan) {
            case 1:
                System.out.print("Masukkan nama menu baru: ");
                String nama = sc.nextLine();
                System.out.print("Masukkan harga menu baru: ");
                double harga = sc.nextDouble();
                sc.nextLine(); // Konsumsi newline
                System.out.print("Masukkan kategori menu baru (Makanan/Minuman): ");
                String kategori = sc.nextLine();
                Menu[] menuBaru = Arrays.copyOf(menu, menu.length + 1);
                menuBaru[menu.length] = new Menu(nama, harga, kategori);
                return menuBaru;
            case 2:
                System.out.println("Pilih menu yang ingin diubah harga:");
                for (int i = 0; i < menu.length; i++) {
                    System.out.println((i + 1) + ". " + menu[i]);
                }
                System.out.print("Masukkan nomor menu: ");
                int index = sc.nextInt() - 1;
                System.out.print("Masukkan harga baru: ");
                double hargaBaru = sc.nextDouble();
                sc.nextLine(); // Konsumsi newline
                menu[index].setHarga(hargaBaru);
                System.out.println("Harga berhasil diubah!");
                return menu;
            case 3:
                System.out.println("Pilih menu yang ingin dihapus:");
                for (int i = 0; i < menu.length; i++) {
                    System.out.println((i + 1) + ". " + menu[i]);
                }
                System.out.print("Masukkan nomor menu: ");
                int nomor = sc.nextInt() - 1;
                Menu[] menuHapus = new Menu[menu.length - 1];
                int idx = 0;
                for (int i = 0; i < menu.length; i++) {
                    if (i != nomor) {
                        menuHapus[idx++] = menu[i];
                    }
                }
                System.out.println("Menu berhasil dihapus!");
                return menuHapus;
            default:
                System.out.println("Pilihan tidak valid.");
                return menu;
        }
    }
}
