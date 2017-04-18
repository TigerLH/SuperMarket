package com.remark;

/**
 * @author hong.lin
 * @description
 * @date 2017/4/17.
 */
public class AlgorithmTest {
    public static void main(String ...args){
        // System.out.println(fact(12));
        //count();
        //System.out.println(sum(7));
        //sushu();
        //printNum();
        //fenjie(12);
        //System.out.println(59>90?"A":(59<60?"C":"B"));
//        getCount(2,2);
       // sort(60,8,10);
        //t();
       // h("12314666665512323232323661","66");
//        s();
        a();
    }

    /**
     * 阶乘
     * @param n
     * @return
     */
    public static long fact(int n){
        if (n==0) return 0;
        else if (n==1) return 1;
        else return n*fact(n-1);
    }

    /**
     *1,2,3,4统计组成不重复且不相同的三位数
     */
    public static void count(){
        int i;
        int j;
        int k;
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                for(k=0;k<4;k++){
                    if(i!=j&&i!=k&&j!=k){
                        System.out.println(""+i+j+k);
                    }
                }
            }
        }
    }

    /**
     * 如果一对两个月大的兔子以后每个月可以生一对兔子，而一对新生的兔子出生两个月后才可以生兔子
     * 斐波那契数列1,1,2,3,5,8,13,21
     * **/
    public static int sum(int n){
        if (n==1||n==2) return 1;
        else return sum(n-1)+sum(n-2);
    }

    /**
     * 判断101-200之间有多少个素数，并输出所有素数。
     * 除了1和本身以外不能被整除的数为素数
     */
    public static void sushu(){
        for(int j=101;j<200;j++){
            boolean isSuShu = true;
            for(int i=2;i<j;i++){
                if(j%i==0){   //能被整除就不是素数
                    isSuShu = false;
                    break;
                }
            }
            if(isSuShu == true){
                System.out.println(""+j);
            }
        }
    }

    /**
     * 打印出所有的 水仙花数 ，所谓 水仙花数 是指一个三位数，其各位数字立方和等于该数本身
     */
    public static void printNum(){
        for(int i=100;i<=999;i++){
            int l = i/100;  //百位
            int m = i%100/10; //十位
            int n = i%10; //个位
            if(i==l*l*l+m*m*m+n*n*n){
                System.out.println(i);
            }
        }
    }

    /**
     * 分解因数
     * @param num
     */
    public static void fenjie(int num){
        for(int i=2;i<=num;i++){
            if(num%i==0){
                System.out.print(i);
                if(num!=i){
                    System.out.print("*");
                }
                fenjie(num/i);  //整除后的值再递归
            }
        }
        System.exit(0);
    }

    /**
     * 题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
     * 字符串转换为字符数组,然后遍历通过ASC码判断类型
     * @param str
     */
    public static void count(String str){
        int digital = 0;
        int character = 0;
        int blank = 0;
        int other = 0;
        char[]  arr = str.toCharArray();
            for(int i=0;i<arr.length;i++){
                if(arr[i]>='0'&&arr[i]<='9'){
                    digital++;
                }else if((arr[i]>='a'&&arr[i]<='z')||(arr[i]>='A'&&arr[i]<='Z')){
                    character++;
                }else if(arr[i]==' '){
                    blank++;
                }else{
                    other++;
                }
            }
            System.out.println("数字个数："+digital);
            System.out.println("英文字母个数："+character);
            System.out.println("空格个数："+blank);
            System.out.println("其他字符个数："+other);
        }

    /**
     *  题目：求s = a + aa + aaa + aaaa + aa...a的值，其中a是一个数字。例如2 + 22 + 222 + 2222 + 22222(此时共有5个数相加)
     * @param n
     */
    public static void getCount(int n,int w){
            int sum = 0;
            int num = 0;
            for (int i=0;i<=w;i++){
                num = num+(int)Math.pow(10,i)*n;
                sum = sum+num;
            }
            System.out.println(sum);
        }


    /**
     * 程序10】 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在第10次落地时，共经过多少米？第10次反弹多高？
     */
    public static void totalhigh(){
        int sum = 0;
        int high = 100;
        for(int i=1;i<=10;i++){
            sum = sum + high; //落下高度
            high = high/2;
            sum = sum + high; //反弹高度
        }
    }

    /**
     *  题目：输入三个整数x,y,z，请把这三个数由小到大输出。
     */

    public static void sort(int x,int y,int z){
        if(x>y){
            int t = x;
            x = y;
            y = t;
        }
        if(x>z){
            int t = x;
            x = z;
            z = t;
        }
        if(y>z){
            int t = y;
            y = z;
            z = t;
        }
        System.out.println(""+x);
        System.out.println(""+y);
        System.out.println(""+z);
    }


    /**
     *  题目：编写一个函数，输入n为偶数时，调用函数求1/2+1/4+...+1/n,当输入n为奇数时，调用函数
     */
//

    public static void t(){
        int sum = 0;
        for (int i = 6;; i++) {// 最少6个分最后一次
            sum = i;// 桃子数
            for (int j = 0; j < 5; j++) {// 分的次数循环
                if ((sum - 1) % 5 == 0 && j < 5) {// 如果扔一个后能均分5份，继续分
                    sum = (sum - 1) / 5 * 4;// 每分一次剩余桃子数
                    if (j == 4) {// 如果已分5次，且仍能除尽，输出，退出程序
                        System.out.println(sum);
                        System.exit(0);
                    }
                }
            }

        }
    }


    public static void h(String m,String s){
        String[] arrays = m.toLowerCase().split(s);
        System.out.println(arrays.length-1);
    }


    /**
     * 排列组合不同的值
     */
    public static void s(){
        String[] arrays = new String[]{"a","b","c"};
        for(int i=0;i<arrays.length;i++){
            for(int j=0;j<arrays.length;j++){
                for(int k=0;k<arrays.length;k++){
                    if(i!=j&&i!=k&&j!=k){    //去除重复数字组合情况（保证下标不一致）
                        System.out.println(""+arrays[i]+arrays[j]+arrays[k]);
                    }
                }
            }
        }
    }

    /**
     * 组合最小数字
     */
    public static void a(){
        int min = 8142801;
        int[] arrays = new int[]{81,42,801};
        for(int i=0;i<arrays.length;i++){
            for(int j=0;j<arrays.length;j++){
                for(int k=0;k<arrays.length;k++){
                    if(i!=j&&i!=k&&j!=k){   //去除重复数字组合情况
                        int temp = Integer.parseInt(""+arrays[i]+arrays[j]+arrays[k]);
                        if(temp<min){
                            min = temp;
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }
}
