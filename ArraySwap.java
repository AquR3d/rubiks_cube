public abstract class ArraySwap{
    // swaps these elements in the array
    public static void Swap(Object[] array, int idx1, int idx2){
        Swap(array, idx1, array, idx2);
    }

    // this overloaded method swaps across arrays
    public static void Swap(Object[] array1, int idx1, Object[] array2, int idx2){
        Object buffer = array1[idx1]; // buffer still needed bc/ it could be the same array
        array1[idx1] = array2[idx2];
        array2[idx2] = buffer;
    }

    // overload with same array of swap four
    public static void SwapFour(Object[] array, int idx1, int idx2, int idx3, int idx4, boolean clockwise){
        SwapFour(array, idx1, array, idx2, array, idx3, array, idx4, clockwise);
    }

    // swaps across these 4 arrays in order
    public static void SwapFour(Object[] arr1, int idx1, Object[] arr2, int idx2, Object[] arr3, int idx3, Object[] arr4, int idx4, boolean clockwise){
        /*
        clock wise:
            arr1 -> arr2
            
             ^        v
             
            arr4 <- arr3
        */
        
        // swapping clockwise in this function is defined as moving arr1 to arr2, arr2 to arr3... arr4 to arr1.
        if (clockwise){
            Swap(arr1, idx1, arr2, idx2);
            Swap(arr4, idx4, arr1, idx1);
            Swap(arr3, idx3, arr4, idx4); 
        } else { 
            Swap(arr4, idx4, arr1, idx1);
            Swap(arr1, idx1, arr2, idx2);
            Swap(arr2, idx2, arr3, idx3);
        }
    }
}