// Nguyen Dinh Bao - 2280600205
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);

        // Menu khong dau de tranh loi font
        String msg = """
                \n========== QUAN LY SACH ==========
                1. Them 1 cuon sach
                2. Xoa 1 cuon sach
                3. Thay doi cuon sach
                4. Xuat thong tin tat ca cac cuon sach
                5. Tim sach co tua de chua chu "Lap trinh"
                6. Lay toi da K cuon sach co gia <= P
                7. Tim sach theo danh sach tac gia
                0. Thoat
                Chon chuc nang:\s""";

        int chon = 0;
        do {
            System.out.print(msg);
            try {
                chon = Integer.parseInt(x.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            // Su dung Switch expression
            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2 -> {
                    System.out.print("Nhap ma sach can xoa: ");
                    int bookid = Integer.parseInt(x.nextLine());
                    listBook.removeIf(b -> b.getId() == bookid);
                    System.out.println("Da xoa sach thanh cong");
                }
                case 3 -> {
                    System.out.print("Nhap ma sach can dieu chinh: ");
                    int bookid = Integer.parseInt(x.nextLine());
                    listBook.stream()
                            .filter(p -> p.getId() == bookid)
                            .findFirst()
                            .ifPresentOrElse(Book::input,
                                    () -> System.out.println("Khong tim thay ID"));
                }
                case 4 -> {
                    System.out.println("\n Xuat thong tin danh sach ");
                    listBook.forEach(Book::output); // Method reference
                }
                case 5 -> {
                    // Tim chu "lap trinh" khong phan biet hoa thuong
                    listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lap trinh"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhap so luong K: ");
                    int k = Integer.parseInt(x.nextLine());
                    System.out.print("Nhap gia P: ");
                    double p = Double.parseDouble(x.nextLine());
                    // Ket hop filter va limit
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.print("Nhap danh sach tac gia (cach nhau bang dau phay): ");
                    String input = x.nextLine();
                    // Chuyen danh sach nhap vao thanh tap Set
                    Set<String> authorSet = Arrays.stream(input.split(","))
                            .map(String::trim)
                            .collect(Collectors.toSet());
                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Chon sai, vui long nhap lai!");
            }
        } while (chon != 0);
    }
}