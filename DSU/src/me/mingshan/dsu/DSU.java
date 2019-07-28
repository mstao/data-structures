package me.mingshan.dsu;

public class DSU {
    private int[] parents_;
    private int[] ranks_;

    public DSU(int n)
    {
        parents_ = new int[n+1];
        ranks_ = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            parents_[i]=i;
            ranks_[i]=i;
        }
    }

    public boolean Union(int u,int v)
    {
        int pu = Find(u);
        int pv = Find(v);
        if(pu==pv)
            return false;
        if (ranks_[pv] > ranks_[pu])
            parents_[pu] = pv;
        else if (ranks_[pu] > ranks_[pv])
            parents_[pv] = pu;
        else {
            parents_[pv] = pu;
            ranks_[pu] += 1;
        }
        return true;
    }

    public int Find(int u)
    {
        while (parents_[u]!=u)
        {
            parents_[u]=parents_[parents_[u]];
            u=parents_[u];
        }
        return u;
    }
}
