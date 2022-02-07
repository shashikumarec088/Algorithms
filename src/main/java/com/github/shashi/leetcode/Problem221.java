package com.github.shashi.leetcode;

public class Problem221 {
    int ans = 0;
    char[][] mat;
    Integer[][] cache;
    int nr, nc;
    public int maximalSquare(char[][] matrix) {
        return matrixSqA4(matrix);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
    }

    public int maxDp(char[][] mat){
        this.mat = mat;
        nr = mat.length;
        nc = mat[0].length;
        cache = new Integer[nr][nc];
        dp(0,0);
        return ans*ans;
    }

    public int dp(int r, int c){
        if(r>= nr || c >= nc)
            return 0;
        if(cache[r][c] != null) return cache[r][c];
        int down = dp(r+1,c);
        int right = dp(r,c+1);
        int diag = dp(r+1,c+1);
        int min = Math.min(down,
                Math.min(right,diag));
        int cur = min;
        if(mat[r][c] == '1')
        {
            cur = 1+min;
            if(ans<cur)
                ans = cur;
        }else cur = 0;
        cache[r][c] = cur;
        return cur;
    }

    public int matrixSqA4(char[][] mat){
        int rn = mat.length, cn = mat[0].length,max=0, prev =0;
        int[] dp = new int[cn];
        for(int i=0; i<rn; i++){
            prev = 0;
            for(int j=0; j<cn; j++){
                int temp = dp[j];
                if(mat[i][j]=='1'){
                    if(i==0 || j==0)dp[j] = 1;
                    else dp[j] = 1 + Math.min(Math.min(dp[j-1],dp[j]),
                            prev);
                    max = Math.max(max,dp[j]);
                }else dp[j]=0;
                prev = temp;
            }
        }
        return max*max;
    }

    public int matrixSqA3(char[][] mat){
        int rn = mat.length, cn = mat[0].length,max=0;
        int[][] dp = new int[rn][cn];
        for(int i=0; i<rn; i++){
            for(int j=0; j<cn; j++){
                if(mat[i][j]=='1'){
                    if(i==0 || j==0)dp[i][j] = 1;
                    else dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),
                            dp[i][j-1]);
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }



    public int matrixSqA2(char[][] mat){
        int nr = mat.length,nc = mat[0].length,ans=0;
        int[][] cache = new int[nr][nc];
        for(int i=0; i<nc; i++){
            cache[nr-1][i] = mat[nr-1][i]=='1'?1:0;
            if(cache[nr-1][i]==1) ans=1;
        }

        for(int i=0; i<nr; i++){
            cache[i][nc-1] = mat[i][nc-1]=='1'?1:0;
            if(cache[i][nc-1]==1)ans=1;
        }

        int r = nr-2, c = nc-2;
        while(r>=0 && c>=0){
            for(int i=c; i>=0; i--)
                if(mat[r][i]=='1'){
                    cache[r][i]= 1+ Math.min(cache[r][i+1],Math.min(cache[r+1][i],
                            cache[r+1][i+1]));
                    if(ans<cache[r][i])
                        ans = cache[r][i];
                }

            for(int i =r; i>=0;i--)
                if(mat[i][c]=='1'){
                    cache[i][c] = 1+Math.min(cache[i][c+1],
                            Math.min(cache[i+1][c],cache[i+1][c+1]));
                    if(ans<cache[i][c])
                        ans = cache[i][c];
                }
            r--;
            c--;
        }
        return ans*ans;
    }

    public int maxArA1(char[][] mat){
        int nr = mat.length, nc = mat[0].length, area=0;
        for(int r=0; r<nr; r++){
            for(int c=0; c<nc; c++){
                if(mat[r][c]=='1'){
                    int m=1;
                    boolean flag =true;
                    while(m+r <nr && m+c < nc && flag){
                        for(int k=r; k<=r+m;k++){
                            if(mat[k][m+c]=='0'){
                                flag=false;
                                break;
                            }
                        }
                        for(int k=c; k<=c+m;k++){
                            if(mat[m+r][k]=='0'){
                                flag=false;
                                break;
                            }
                        }
                        if(flag)m++;
                    }
                    area = Math.max(area,m);
                }
            }
        }
        return area * area;
    }
}
