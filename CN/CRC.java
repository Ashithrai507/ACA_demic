import java.util.Scanner;

public class CRC{
    static int[] input = new int[50];
    static int[] crc = new int[20];
    static int[] poly = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};
    
    static int n,r = 16;
    public static void main(String[] args){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the bit:");
        n = sc.nextInt();
        System.out.print("Enter the number of bits:");
        for(int i=0;i<n;i++)
        {
            input[i] = sc.nextInt();
        }
        for(int i=n;i<n+r;i++)
        {
            input[i] = 0;
        }
        
        genCRC();
        
        System.out.print("Gen CRC:");
        for(int i=0;i<r;i++)
        {
            System.out.print(crc[i]+" ");
            input[n+i] = crc[i];
        }
        System.out.print("transmitted code:");
        for(int i=0 ; i<n+r;i++)
        {
            System.out.print(input[i]+" ");
        }
        System.out.print("do you want an error(1/0):");
        int cho = sc.nextInt();
        if(cho==1)
        {
            System.out.print("Enter the position");
            int pos = sc.nextInt();
            input[pos] ^= 1;
        }
        
        System.out.print("Received code is:");
        for(int i=0;i<r;i++)
        {
            System.out.print(input[i]+" ");
        }
        genCRC();
        
        System.out.print("Gen CRC:");
        for(int i=0;i<n+r;i++)
        {
            System.out.print(crc[i]+" ");
        }
        
        boolean error = false;
        for(int i=0;i<r;i++)
        {
            if(crc[i] != 0)
            {
                error = true;
                break;
            }
        }
        if(! error)
        {
            System.out.print("NO error");
        }
        else
        {
            System.out.print("There is an error");
        }
    }
    static void genCRC()
    {
        int[] temp = new int[50];
        System.arraycopy(input,0,temp,0,n+r);
        for(int i=0;i<n;i++)
        {
            if(temp[i] == 1)
            {
                for(int j=0;j<=r;j++)
                {
                    temp[i+j] ^= poly[j];
                }
            }
        }
        System.arraycopy(temp,n,crc,0,r);
    }
    
}