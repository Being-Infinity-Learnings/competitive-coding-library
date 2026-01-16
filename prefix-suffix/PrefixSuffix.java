class PrefixSuffix{
    public static int[] prefixSumArray(int arr[]){
        int prefixSum[] = new int[arr.length];
        prefixSum[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }
        return prefixSum;
    }
    public static int[] suffixSumArray(int arr[]){
        int suffixSum[] = new int[arr.length];
        suffixSum[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){
            suffixSum[i] = suffixSum[i+1] + arr[i];
        }
        return suffixSum;
    }
    public static int[] prefixMinArray(int arr[]){
        int prefixMin[] = new int[arr.length];
        prefixMin[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            prefixMin[i] = Math.min(prefixMin[i-1], arr[i]);
        }
        return prefixMin;
    }
    public static int[] suffixMinArray(int arr[]){
        int suffixMin[] = new int[arr.length];
        suffixMin[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){   
            suffixMin[i] = Math.min(suffixMin[i+1], arr[i]);
        }
        return suffixMin;
    }
    public static int[] prefixMaxArray(int arr[]){
        int prefixMax[] = new int[arr.length];
        prefixMax[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            prefixMax[i] = Math.max(prefixMax[i-1], arr[i]);
        }
        return prefixMax;
    }
    public static int[] suffixMaxArray(int arr[]){
        int suffixMax[] = new int[arr.length];
        suffixMax[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){   
            suffixMax[i] = Math.max(suffixMax[i+1], arr[i]);
        }
        return suffixMax;
    }
    public static int[] prefixGCDArray(int arr[]){
        int prefixGCD[] = new int[arr.length];
        prefixGCD[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            prefixGCD[i] = gcd(prefixGCD[i-1], arr[i]);
        }
        return prefixGCD;   
    }
    public static int[] suffixGCDArray(int arr[]){
        int suffixGCD[] = new int[arr.length];
        suffixGCD[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){   
            suffixGCD[i] = gcd(suffixGCD[i+1], arr[i]);
        }
        return suffixGCD;   
    }
    private static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
}
