#include <iostream>

using namespace std;

int main(){
    
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int dp[10001]; 
    int n;
    cin >> n;
    dp[0]=0;
    dp[1]=1;
    dp[2]=3;

    for(int i=3; i<n+1; i++){
        dp[i] = (2*dp[i-2] + dp[i-1]) % 10007;
    }
    
    cout << dp[n] << '\n';

    return 0;
}