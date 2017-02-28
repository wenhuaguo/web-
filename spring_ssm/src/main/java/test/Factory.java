package test;

/**
 * Created by Acer on 2017/1/30.
 */
public class Factory {
        //用两个递归
        public static Long getSun(int num){
            if(num == 1){
                return 1L;
            }else {
                return num * getSun(num - 1);
            }
        }
        public static Long getAllSon(int num){
            if(num == 1){
                return getSun(num);
            }else {
                return getSun(num) + getAllSon(num - 1);
            }

    }
    public static int count(int n){
        int result = 0;
        return 1;
    }
    public static void main(String[] args) {
        System.out.println(getAllSon(4));
        long a,u,b;
        u = 1;
        b = 0;
        String str = null;
        for (a = 1; a <= 20; a++){
            u = u * a;
            b = b + u;
            if (a == 1){
                str = a + "!";
            }else {
                str = str + "+" + a + "!";
            }

            System.out.println(str + "=" + b);
        }

    }



}
