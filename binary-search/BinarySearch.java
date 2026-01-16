class BinarySearch {
    public static int exactBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; 
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        return -1; 
    }
    public static int floor(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                ans = mid; 
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        return ans; 
    }
    public static int ceil(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int ans = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] >= target) {
                ans = mid;
                right = mid - 1; 
            } else {
                left = mid + 1; 
            }
        }
        return ans; 
    }

    public static int firstOccurence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                ans = mid; 
                right = mid - 1; 
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        return ans; 
    } 
    public static int lastOccurence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                ans = mid; 
                left = mid + 1; 
            } else if (arr[mid] < target) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
        return ans; 
    }
}
