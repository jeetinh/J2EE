import java.util.Scanner;

public class Book {
    // 1. Khai bao thuoc tinh
    private int id;
    private String title;
    private String author;
    private double price;

    // 2. Constructors
    public Book() {}

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // 3. Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // 4. Ham nhap thong tin (Phien ban "bat tu" voi try-catch)
    public void input() {
        Scanner x = new Scanner(System.in);

        // Nhap ID - Kieu so nguyen (int)
        while (true) {
            try {
                System.out.print("Nhap ma sach (so nguyen): ");
                this.id = Integer.parseInt(x.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Loi! Ma sach phai la so. Vi du: 1, 2, 3...");
            }
        }

        System.out.print("Nhap ten sach: ");
        this.title = x.nextLine();

        System.out.print("Nhap tac gia: ");
        this.author = x.nextLine();

        // Nhap Don gia - Kieu so thuc (double)
        while (true) {
            try {
                System.out.print("Nhap don gia (so thap phan): ");
                this.price = Double.parseDouble(x.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Loi! Don gia phai la so. Vi du: 150000.5");
            }
        }
    }

    // 5. Ham xuat thong tin (Dung Text Block va formatted)
    public void output() {
        String msg = """
            BOOK [ID: %d | Ten: %s | Tac gia: %s | Gia: %.2f]
            """.formatted(id, title, author, price);
        System.out.print(msg);
    }
}