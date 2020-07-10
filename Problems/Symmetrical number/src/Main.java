import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int arr[] = new int[4];
        for(int i =4;i>=0;i--){
            arr[i-1] = number%10;
            number/=10;
        }
        if(arr[0] == arr[3] && arr[1]==arr[2]){
            System.out.println(1);
        } else {
            System.out.println(37);
        }
    }
}