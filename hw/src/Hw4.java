
class MyArraySizeException extends Exception{
    public MyArraySizeException(){
        super("Array is not 4x4");
    }
}

class MyArrayDataException extends Exception{
    public MyArrayDataException (String mes){
        super(mes+" is not a number");
    }
}



public class Hw4 {

    public static int arraySum(String[][] arr) throws MyArraySizeException,MyArrayDataException{

        if(arr.length!=4){
            throw new MyArraySizeException();
        } else {
            for(String[] x:arr){
                if(x.length!=4) throw  new MyArraySizeException();
            }
        }
        int sum =0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(!arr[i][j].matches("[-+]?\\d*")){
                    throw new MyArrayDataException(arr[i][j]+" in "+i+":"+j);
                }
                sum +=Integer.parseInt(arr[i][j]);
            }
        }

        return sum;
    }


    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        String[][] arr1 = {
            {"1","2","3","34"},{"5","6","7","33"},{"3","5","23"},{"1","55","23"}
        };

        try {
            int arrSum = arraySum(arr1);
        } catch (MyArraySizeException e){
            System.out.println(e.getMessage());
        }
        String[][] arr2 = {
                {"1","2","3","43"},{"5","6","7","32"},{"3","5","13x","23"},{"1","55","23","28"}
        };

        try {
            int arrSum = arraySum(arr2);
        } catch (MyArrayDataException e){
            System.out.println(e.getMessage());

        }

        String[][] arr3 = {
                {"1","2","3","43"},{"5","6","7","32"},{"3","5","13","23"},{"1","55","23","28"}
        };

        try {
            int arrSum = arraySum(arr3);
            System.out.println(arrSum);
        } catch (MyArrayDataException | MyArraySizeException e){
            System.out.println(e.getMessage());

        }
    }
}
