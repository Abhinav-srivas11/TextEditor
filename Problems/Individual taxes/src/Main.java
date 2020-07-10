import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arrayCapacity = sc.nextInt();
        double[] arrIncome = new double[arrayCapacity];
        double[] arrTax = new double[arrayCapacity];
        double max =0; int maxIndex =0;
        sc.nextLine();
        for (int i = 0; i < arrayCapacity; i++) {
            arrIncome[i] = sc.nextInt();
        }
        sc.nextLine();
        for (int i = 0; i < arrayCapacity; i++) {
            arrTax[i] = sc.nextInt();
        }
        double[] tax = new double[arrayCapacity];
        for(int i= 0; i<arrTax.length ; i++){
//                System.out.println("income is : "+arrIncome[i]+ "tax is : "+arrTax[i]);
            tax[i] = ((arrTax[i]/100) * arrIncome[i]);
//                System.out.println("tex is : "+ tax[i]);

        }
        max = tax[0];
        maxIndex = 1;
        for(int i= 0; i<tax.length-1 ; i++){
//            System.out.println("tex is : "+ tax[i]);
            if(max < tax[i+1]){
                if(max == tax[i+1]){break;}
                max = tax[i+1];
                maxIndex = i+2;
            }
        }

        System.out.println(maxIndex);
    }
}