

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> matrixIn = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> matrixOut = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> matrixInc = new ArrayList<>();

    static ArrayList<ArrayList<Integer>> m0 = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> m1 = new ArrayList<>();

    public static void main(String[] args) {
        int n_vert = 0, n_trans = 0;
        int new_n_vert, new_n_trans;
        int option;
        int status = 0;

        Scanner scanner = new Scanner(System.in);
        ColoredPetri petri_net = new ColoredPetri();

        while(true){
            System.out.println("""
                    Menu:
                    1) Add matrix
                    2) Add tokens
                    3) Add place\040
                    4) Delete transition
                    5) Check ifTrue
                    6) Check pure
                    7) Output vector
                    8) Output  matrix
                    9) Output postcondition matrix
                    10)Solve  matrix
                    11)Solve  vector
                    0) End
                    """);

            option = scanner.nextInt();
            if(option == 0){
                System.out.println("Going out...\n\n");
                break;
            }

            switch(option){
                case 1:
                    System.out.println("Number of seats:\n\t");
                    n_vert = scanner.nextInt();
                    System.out.println("Number of transitions:\t");
                    n_trans = scanner.nextInt();
                    System.out.println("Matrix:\n");
                    matrixIn = petri_net.matrix(n_vert, n_trans);
                    System.out.println("\nMatrix out:\n");
                    matrixOut = petri_net.matrix(n_vert, n_trans);
                    status = 1;
                    break;
                case 2:
                    if(status==1)
                        m0=petri_net.addTokens(n_vert);
                    status = 2;
                    break;
                case 3:
                    if(status==1||status==2||status==3){
                        System.out.println("Enter how many more places:\t");
                        new_n_vert = scanner.nextInt();
                        n_vert = n_vert + new_n_vert;
                        System.out.println("Enter how many more amounts of transitions:\t");
                        new_n_trans = scanner.nextInt();
                        n_trans = n_trans + new_n_trans;

                        matrixIn.clear();
                        matrixOut.clear();
                        matrixInc.clear();

                        m0.clear();
                        m1.clear();

                        System.out.println("Enter the input matrix:\n");
                        matrixIn = petri_net.matrix(n_vert, n_trans);
                        System.out.println("Enter the output matrix:\n");
                        matrixOut = petri_net.matrix(n_vert, n_trans);
                    }
                    status = 1;

                    break;
                case 4:
                    if(status==1||status==2||status==3){
                        System.out.println("Enter the number of seats:\t");
                        n_vert = scanner.nextInt();
                        System.out.println("Enter the number of transitions:\t");
                        n_trans = scanner.nextInt();

                        matrixIn.clear();
                        matrixOut.clear();
                        matrixInc.clear();

                        m0.clear();
                        m1.clear();

                        System.out.println("Enter the input matrix:\n");
                        matrixIn = petri_net.matrix(n_vert, n_trans);
                        System.out.println("\nEnter the output matrix:\n");
                        matrixOut = petri_net.matrix(n_vert, n_trans);
                    }
                    status = 1;
                    break;
                case 5:
                    if(status==3)
                        petri_net.outputConection(matrixInc, n_vert, n_trans);
                    break;
                case 6:
                    if(status==1||status==2||status==3)
                        petri_net.outputIfTrue(matrixIn, matrixOut, n_vert, n_trans);
                    break;
                case 7:
                    if(status==2||status==3)
                        petri_net.outputVec(m0);
                    break;
                case 8:
                    if(status==1||status==2||status==3)
                        petri_net.outputResult(matrixIn, n_vert, n_trans);
                    break;
                case 9:
                    if(status==1||status==2||status==3)
                        petri_net.outputResult(matrixOut, n_vert, n_trans);
                    break;
                case 10:
                    if(status==1||status==2||status==3)
                        matrixInc = petri_net.resMatrix(matrixIn, matrixOut, n_vert, n_trans);
                    petri_net.outputResult(matrixInc, n_vert, n_trans);
                    status=3;
                    break;
                case 11:
                    if(status==2||status==3)
                        m1 = petri_net.solveMatrix(matrixIn, matrixOut, m0, n_vert, n_trans);
                    petri_net.outputVec(m1);
                    break;
                default:
                    System.out.println("Error!\n");
                    break;
            }

        }

    }

}

