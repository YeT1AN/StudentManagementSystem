//import java.util.ArrayList;
//import java.util.Random;
//
//public class Test {
//    public static void main(String[] args) {
//        System.out.println(getCode());
//    }
//
//
//    private static String getCode(){
//        // create a collection and add all lowercase and uppercase chars
//        ArrayList<Character> list = new ArrayList<>();
//        for (int i = 0; i < 26; i++) {
//            list.add((char)('a' + i));
//            list.add((char)('A' + i));
//        }
//
//        StringBuilder sb = new StringBuilder();
//        // get 4 random chars
//        Random r = new Random();
//        for (int i = 0; i < 4; i++) {
//            // get random index
//            int index = r.nextInt(list.size());
//            // use the random index to get char
//            char c = list.get(index);
//            // add the random char to the sb
//            sb.append(c);
//        }
//
//        // append a random number
//        int number = r.nextInt(10);
//        sb.append(number);
//
//        // to modify the content of a string need to convert the string to a character array, make modifications in the array, then create a new string again
//        char[] arr = sb.toString().toCharArray();
//        // take the last index and swap it with a random index
//        int randomIndex = r.nextInt(arr.length);
//        // swap the element at the maximum index with the element at the random index
//        char temp = arr[randomIndex];
//        arr[randomIndex] = arr[arr.length - 1];
//        arr[arr.length - 1] = temp;
//
//        // create a new string
//        return new String(arr);
//    }
//}