package SecondTimePackage;

public class Moderate {

    public void numSwapper(int[] arr, int i, int j) {
        arr[i] += arr[j];
        arr[j] = -(arr[j] - arr[i]);
        arr[i] -= arr[j];
    }

}