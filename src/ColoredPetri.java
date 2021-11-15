
import java.util.ArrayList;
import java.util.Scanner;


public class ColoredPetri {

    private Scanner scanner;

    public ArrayList<ArrayList<Integer>> matrix(int n_vert, int n_trans){
        scanner = new Scanner(System.in);
        int  weight;
        Trans dice = new Trans();

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for(int i=0;i<n_vert;i++){
            ArrayList<Integer> list = new ArrayList<>();
            //index = 'A';
            System.out.println("Enter the weight "+(i+1)+"\t");
            for(int j=0;j<n_trans;j++){
                weight = scanner.nextInt();
                dice.setWeight(weight);
                list.add(dice.getWeight());
            }
            matrix.add(list);
        }
        return matrix;
    } public void outputResult(ArrayList<ArrayList<Integer>> matrix, int n_vert, int n_trans){
        int i;
        for(i=0;i<n_trans;i++){
            System.out.print("\tT"+i+"\t");
        }
        System.out.println();
        for(i = 0;i<n_vert;i++){
            System.out.print("P"+i+"[ ");
            for(int j = 0;j<n_trans;j++){
                System.out.print("\t"+matrix.get(i).get(j)+"\t");
            }
            System.out.print("]\n");
        }
    }

    public void outputVec(ArrayList<ArrayList<Integer>> matrix){
        for(int i = 0;i<matrix.size();i++){
            System.out.println("P"+i+matrix.get(i));

        }
    }
    public ArrayList<ArrayList<Integer>> resMatrix(ArrayList<ArrayList<Integer>> matrixIn, ArrayList<ArrayList<Integer>> matrixOut, int n_vert, int n_trans){
        ArrayList<ArrayList<Integer>> matrixInc = new ArrayList<>();
        int result;

        for(int i=0;i<n_vert;i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=0;j<n_trans;j++){
                result=(matrixOut.get(i).get(j)) - (matrixIn.get(i).get(j));
                list.add(result);
            }
            matrixInc.add(list);
        }
        return matrixInc;
    }


    public ArrayList<ArrayList<Integer>> addTokens(int n_vert){
        scanner = new Scanner(System.in);
        int  weight;
        Trans dice = new Trans();

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for(int i=0;i<n_vert;i++){
            ArrayList<Integer> list = new ArrayList<>();
            System.out.println("Enter the number"+(i+1)+ "\t");
            for(int j=0;j<1;j++){
                weight = scanner.nextInt();
                dice.setWeight(weight);
                //index = index + 1;
                list.add(dice.getWeight());
            }
            matrix.add(list);
        }
        return matrix;
    }
    public boolean[] checkIfTrue(ArrayList<ArrayList<Integer>> matrixIn, ArrayList<ArrayList<Integer>> m0, int n_vert, int n_trans){
        boolean[] vetHabs = new boolean[n_trans];
        int i, j, k;

        k=0;
        for(i=0;i<n_trans;i++){
            vetHabs[k] = true;
            for(j=0;j<n_vert;j++){
                if (m0.get(j).get(0) >= matrixIn.get(j).get(i)){
                    vetHabs[k] = vetHabs[k];
                }else{
                    vetHabs[k] = false;
                }
            }
            k=k+1;
        }
        return vetHabs;
    }
    public ArrayList<ArrayList<Integer>> solveMatrix(ArrayList<ArrayList<Integer>> matrixIn, ArrayList<ArrayList<Integer>> matrixOut, ArrayList<ArrayList<Integer>> m0, int n_vert, int n_trans){
        ArrayList<ArrayList<Integer>> m1 = new ArrayList<>();
        int result;
        boolean[] vetHabs;

        Scanner scan = new Scanner(System.in);
        int trans_trigger;

        vetHabs= checkIfTrue(matrixIn, m0, n_vert, n_trans);
        output(vetHabs, n_trans);

        System.out.println("\nWhich transition\n");
        trans_trigger = scan.nextInt();

        if(vetHabs[trans_trigger]){
            for(int j=0;j<n_vert;j++){
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=0;i<1;i++){
                    result=(m0.get(j).get(0) - matrixIn.get(j).get(trans_trigger)) + matrixOut.get(j).get(trans_trigger);
                    list.add(result);
                }
                m1.add(list);
            }
        }else{
            System.out.println("Error!");
        }
        return m1;
    }

    public boolean ifConnected(ArrayList<ArrayList<Integer>> matrixInc, int n_vert, int n_trans){
        int count=0;
        for(int i = 0;i<n_trans;i++){
            for(int j = 0;j<n_vert;j++){
                if(matrixInc.get(j).get(i)==0)
                    count++;
            }
        }
        return n_vert - count >= 2;
    }

    public void outputConection(ArrayList<ArrayList<Integer>> matrixInc, int n_vert, int n_trans){
        if(ifConnected(matrixInc, n_vert, n_trans))
            System.out.println("Connected");
        else
            System.out.println("Disconnected");
    }

    public boolean ifTrue(ArrayList<ArrayList<Integer>> matrixIn, ArrayList<ArrayList<Integer>> matrixOut, int n_vert, int n_trans){
        int result;

        for(int i=0;i<n_vert;i++){
            for(int j=0;j<n_trans;j++){
                result=(matrixOut.get(i).get(j)) * (matrixIn.get(i).get(j));
                if(result!=0)
                    return false;
            }
        }
        return true;
    }
    public void output(boolean[] vetHabs, int n_trans){
        System.out.println("Transitions enabled:\n");
        for(int i=0; i<n_trans; i++){
            if(vetHabs[i])
                System.out.println("Transition "+i+" enabled.");
            else
                System.out.println("Transition "+i+" disabled.");
        }
    }
    public void outputIfTrue(ArrayList<ArrayList<Integer>> matrixIn, ArrayList<ArrayList<Integer>> matrixOut, int n_vert, int n_trans){
        if(ifTrue(matrixIn, matrixOut, n_vert, n_trans))
            System.out.println("\nTrue.\n");
        else
            System.out.println("\nFalse.\n");
    }


}
