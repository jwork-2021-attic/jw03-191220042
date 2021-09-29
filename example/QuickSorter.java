package example;

public class QuickSorter implements Sorter {
    private int[] a;

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        quickSort(0,a.length-1);
    }

    private void quickSort(int left, int right) {
        if(left>=right)
            return;
        int pivot=a[left];
        int i=left;
        int j=right;

        while(i<j)
        {
            while(pivot<=a[j]&&i<j)
                j--;
            while(pivot>=a[i]&&i<j)
                i++;
            if(i<j)
            {
               swap(i,j);
            }
        }
//        a[left]=a[i];
//        a[i]=pivot;
        swap(left,i);
        quickSort(left,i-1);
        quickSort(i+1,right);
    }


    @Override
    public String getPlan() {
        return this.plan;
    }
}
