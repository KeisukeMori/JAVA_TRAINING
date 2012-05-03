package ch01.ex01_04;

public class Square {
    public static void main(String[] args) {
        int square;
        // 1から100までの平方表を作成します。
        System.out.println("平方表");
        for (int i = 1; i < 101; i++) {
            square = i * i;
            System.out.println(square);
        }
    }
}
