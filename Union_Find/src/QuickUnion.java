public class QuickUnion {
    public int[] id;
    public int[] sz;

    public QuickUnion(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i=0;i<n;i++){
            id[i]=i;
            sz[i]=1;
        }
    }
    public int root(int i){
        while (i!=id[i]){
            id[i]=id[id[i]];
            i=id[i];
        }
        return i;
    }
    public boolean connected (int p, int q){
        return root(p)==root(q);
    }
    public void union(int p,int q){
        int i = root(p);
        int j = root(q);
        if (i == j){
            return;
        }
        if (sz[i] < sz[j]){
            id[i] = j; 
            sz[j] += sz[i];
        }
        else{
            id[j] = i; 
            sz[i] += sz[j];
        }
    }
    public static void main(String[] args){
        int n = 10;
        QuickUnion test = new QuickUnion(n);
        test.union(1,0);
        test.union(1,8);
        System.out.println(test.connected(1, 8));
    }
}
